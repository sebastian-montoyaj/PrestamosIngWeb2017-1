/**
 * @author Sebastian Montoya Jimenez
 */

// En primer lugar, creamos el modulo angular que controlara el comportamiento de los usuarios y le inyectamos los modulos: ngCookies y ngRoute
var appUsuario = angular.module('usuario', ['ngRoute', 'ngCookies']);

// Inmediatamente, especificamos la configuración de ruteo de nuestro sistema, por lo tanto:
appUsuario.config(['$routeProvider', function($routeProvider){
	
	// La ruta '/' estara asociada a la pagina: index.html y cuyo controlador sera: Login
	$routeProvider.when('/', {
        templateUrl: 'login.html',
        controller: 'Login'
    });
	// Ejemplo ruta: http://localhost:8080/Prestamos/
	
	// La ruta '/home' estara asociada a la pagina: main.html y cuyo controlador sera: homeController
	$routeProvider.when('/home', {
        templateUrl: 'main.html',
        controller: 'HomeController'
    });
   
}]);

// Despues, registramos un servicio llamado serviciosUsuario el cual tiene la mision de invocar los servicios web que se tienen creados y obtener su resultado
appUsuario.service('serviciosUsuario', function($http, $cookies, $cookieStore, $location){
	
	// Este servicio tiene la tarea de tomar un id y una contraseña para llamar el servicio de validacion de usuarios
    this.autenticar = function(id, passwd){
        return $http({
            url: 'http://localhost:8080/Prestamos/ingweb/Usuario/validarUsuario',
            method: 'POST',
            params: {identificador: id, password: passwd}
        })
    };
    
    // Este servicio tiene la tarea de invocar el servicio de consulta de rol de un usuario
    this.obtenerRolUsuario = function(id){
    	return $http({
            url: 'http://localhost:8080/Prestamos/ingweb/Usuario/obtenerRolUsuario',
            method: 'GET',
            params: {identificador: id}
        })
    };
    
    // Este servicio tiene la mision de remover la informacion de sesion guardada en la cookie y volver a la pantalla de inicio del sistema
    this.cerrarSesion = function(){
        $cookieStore.remove("identificador"),
        $cookieStore.remove("rol");
        $location.path("/");
    };
    
    // Este servicio revisa que el usuario si este conectado y en caso que no entonces redirige el usuario a la pantalla de inicio del sistema
    this.validarEstado = function(){
		if(typeof($cookies.get('identificador')) == 'undefined' || $cookies.get('identificador') == ""){
			$location.url("/");
			return false;
		}
	};
    
});

// Ahora, se crea el controlador Login cuya funcion es proveer el metodo de autenticacion del usuario y guardar la informacion de session en una cookie
appUsuario.controller('Login', function($scope, $location, $cookies, $window, serviciosUsuario){
	// Entonces, se crean unas variables que se asocian por medio de ng-model a campos de un formulario de inicio de sesion
	$scope.identificador = '';
    $scope.password = '';
    
    // Tambien se crean dos variable para ponerles tiempo de caducidad a las cookies (Si no se hace esto enotnces las cookies no duran nada - se borran ahi mismo)
    var now = new $window.Date();
    exp = new $window.Date(now.getFullYear(), now.getMonth()+6, now.getDate());
	
    // Luego se crea un metodo login el cual hace:
    $scope.login = function()
    {
    	// Llamar el servicio de autenticacion, al cual se le pasa el contenido de los campos del formulario
    	serviciosUsuario.autenticar($scope.identificador, $scope.password).then(
    		// Si el servicio es exitoso entonces
    		function success(data)
    		{
    			// Se revisa que: Si hay un resultado por parte del servicio y el resultado de la autenticacion es correcto entonces
    			if(data.data != "" && data.data == "true"){
    				
    				// Se guarda en la cookie el id del usuario
    				$cookies.identificador = $scope.identificador;
    				
    				$cookies.put('identificador', $scope.identificador, {expires: exp});
    				
    				// Se consulta el rol del usuario y se guarda tambien en la cookie
    				serviciosUsuario.obtenerRolUsuario($scope.identificador).then(
    					function success(data){
    						
    						$cookies.put('rol', data.data, {expires: exp});
    					}
    				)
    				
    				// Y se redirige el usuario a la pagina principal del sistema
    				$location.url('/home');
    				return;
    			}
    			
    			// En caso que, la validacion del usuario sea incorrecta entonces se muestra un mensaje, se limpian los campos y se retorna
    			alert("Usuario o contrasena incorrectos!")
    			$scope.identificador = '';
    			$scope.password = '';
    			return;
    		},
    		
    		// En caso de tener problemas con el servicio, se le anuncia al usuario
    		function failure(data){
				alert('Error inesperado, vuelva a intentarlo!');
			}
    	)
    }
    
});

// 
appUsuario.controller('HomeController', function($scope, $location, $cookies, serviciosUsuario){
	//devolvemos a la vista el id de usuario y el rol
    $scope.identificador = $cookies.get('identificador');
    $scope.rol = $cookies.get('rol');
	
    
    
    
    
    
    
    
    
    
    
    
	// Por ultimo, se crea un metodo logout el cual llama el servicio que limpia la cookie
    $scope.logout = function(){
    	serviciosUsuario.cerrarSesion();
    }
});










// Por ultimo, cada vez que se cambie de pagina se verifica que el usuario si este registrado
// NOTA: El proposito de esto es para evitar que los usuarios no puedan escribir rutas y pasar a secciones no autorizadas
appUsuario.run(['$rootScope', 'serviciosUsuario', function ($rootScope, serviciosUsuario) {
	$rootScope.$on('$routeChangeStart', function(event){
    	serviciosUsuario.validarEstado();
    })
}]);