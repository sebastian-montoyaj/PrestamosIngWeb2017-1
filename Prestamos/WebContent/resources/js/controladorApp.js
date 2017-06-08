/**
 * @author Sebastian Montoya Jimenez
 */

// En primer lugar, creamos dos modulos para controlar el comportamiento del login y de las opciones de los usuarios
var appLogin = angular.module('login', ['ngRoute', 'ngCookies']);
var appMain = angular.module('main', ['ngRoute', 'ngCookies']);

// Inmediatamente, especificamos la configuración de ruteo de nuestro sistema para ambos modulos, por lo tanto:
appLogin.config(['$routeProvider', function($routeProvider){
	
	// La ruta '/' estara asociada a la pagina: index.html y cuyo controlador sera: Login
	$routeProvider.when('/', {
        templateUrl: 'login.html',
        controller: 'Login'
    });
	// Ejemplo ruta: http://localhost:8080/Prestamos/
}]);

appMain.config(['$routeProvider', function($routeProvider){
	// La ruta '/home' estara asociada a la pagina: main.html y cuyo controlador sera: homeController
	$routeProvider.when('/', {
        templateUrl: 'opciones.html',
        controller: 'HomeController'
    });
	// Ejemplo ruta: http://localhost:8081/Prestamos/main.html#!/
	
	$routeProvider.when('/estadoUsuario', {
        templateUrl: 'estadoUsuario.html',
        controller: 'HomeController'
    });
	// Ejemplo ruta: http://localhost:8081/Prestamos/main.html#!/estadoUsuario
	
	$routeProvider.when('/registrarDispositivo', {
        templateUrl: 'registrarDispositivo.html',
        controller: 'HomeController'
    });
	// Ejemplo ruta: http://localhost:8081/Prestamos/main.html#!/registrarDispositivo
	
	$routeProvider.when('/registrarEjemplar', {
        templateUrl: 'registrarEjemplar.html',
        controller: 'HomeController'
    });
	// Ejemplo ruta: http://localhost:8081/Prestamos/main.html#!/registrarEjemplar
	
	$routeProvider.when('/removerEjemplar', {
        templateUrl: 'removerEjemplar.html',
        controller: 'HomeController'
    });
	// Ejemplo ruta: http://localhost:8081/Prestamos/main.html#!/removerEjemplar

}]);


// Despues, registramos los servicios que utilizaran los dos modulos de angular que se han creado
appLogin.service('serviciosUsuario', function($http, $cookies, $cookieStore, $location){
	// Este servicio tiene la tarea de tomar un id y una contraseña para llamar el servicio de validacion de usuarios
    this.autenticar = function(id, passwd){
        return $http({
            url: 'ingweb/Usuario/validarUsuario',
            method: 'POST',
            params: {identificador: id, password: passwd}
        })
    };
    
    // Este servicio tiene la tarea de invocar el servicio de consulta de rol de un usuario
    this.obtenerRolUsuario = function(id){
    	return $http({
            url: 'ingweb/Usuario/obtenerRolUsuario',
            method: 'GET',
            params: {identificador: id}
        })
    };
    
});

appMain.service('serviciosMain', function($http, $cookies, $cookieStore, $location){    
    // Este servicio tiene la mision de remover la informacion de sesion guardada en la cookie y volver a la pantalla de inicio del sistema
    this.cerrarSesion = function(){
        $cookieStore.remove("identificador"),
        $cookieStore.remove("rol");
        location.href = 'index.html'
    };
    
    // Este servicio revisa que el usuario si este conectado y en caso que no entonces redirige el usuario a la pantalla de inicio del sistema
    this.validarEstado = function(){
		if(typeof($cookies.get('identificador')) == 'undefined' || $cookies.get('identificador') == ""){
			location.href = 'index.html'
			return false;
		}
	};
	
	// Este servicio se encarga de modificar actualizar el estado de un usuario
	this.modificarEstadoUsuario = function(id, estado){
        return $http({
            url: 'ingweb/Usuario/cambiarEstadoUsuario',
            method: 'POST',
            params: {identificador: id, nuevoEstado: estado}
        })
    };
    
    // Este servicio tiene la tarea de registrar un nuevo dispositivo en la base de datos
    this.registrarDispositivo = function(nom, desc, idTipo){
        return $http({
            url: 'ingweb/Administrador/registrarNuevoDispositivo',
            method: 'POST',
            params: {nombre: nom, descripcion: desc, idTipo: idTipo}
        })
    };
    
    // Este servicio por su parte es quien registra un nuevo ejemplar
    this.registrarEjemplar = function(idEjem, codEstado){
        return $http({
            url: 'ingweb/Administrador/registrarNuevoEjemplar',
            method: 'POST',
            params: {idDispositivo: idEjem, codigoEstadoDispositivo: codEstado}
        })
    };
    
    // Y este servicio es el encargado de "eliminar" un elemento de prestamo de la base de datos
    this.darDeBajaEjemplar = function(idEjem){
        return $http({
            url: 'ingweb/Administrador/removerEjemplar',
            method: 'POST',
            params: {idEjemplarDispositivo: idEjem}
        })
    };
    
});

// Ahora, se crea el controlador Login cuya funcion es proveer el metodo de autenticacion del usuario y guardar la informacion de session en una cookie
appLogin.controller('Login', function($scope, $location, $cookies, $window, serviciosUsuario){
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
    						location.href = 'main.html'; // Y se redirige el usuario a la pagina principal del sistema
    					}
    				)
    				
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

// Tambien se crea un controlador para la ventana principal y las ventanas que ya realizan alguna funcionalidad
appMain.controller('HomeController', function($scope, $location, $cookies, serviciosMain){
	$scope.idUsuario = '';
    $scope.estadoU = '';
    $scope.nomDisp = '';
    $scope.descDisp = '';
    $scope.tipoDisp = '';
    $scope.idDisp = '';
    $scope.estadoEjem = '';
    $scope.idEjemplar = '';
	
	$scope.esAdmin = function(){
    	return $scope.rol == "Administrador"
    }
    
    $scope.guardarEstadoUsuario = function(){   	
        serviciosMain.modificarEstadoUsuario($scope.idUsuario, $scope.estadoU).then(
    		function success(data){
    			if(data.data != "" && data.data == "Se realizo el cambio de estado exitosamente!"){
    				alert("Se realizo el cambio exitosamente!")
        			location.href = 'main.html';
    				return;
    			}
    			else{
    				alert('Error inesperado, vuelva a intentarlo!');
    			}
    		},
    		
    		function failure(data){
    			alert('Error al llamar el servicio.');
    		}
        )
    }
    
    $scope.guardarDispositivo = function(){
        serviciosMain.registrarDispositivo($scope.nomDisp, $scope.descDisp, $scope.tipoDisp).then(
    		function success(data){
    			if(data.data != "" && data.data == "Se realizo la inserccion del nuevo dispositivo exitosamente!"){
    				alert("Se realizo el registro exitosamente!")
        			location.href = 'main.html';
    				return;
    			}
    			else{
    				alert('Error inesperado, vuelva a intentarlo!');
    			}
    		},
    		
    		function failure(data){
    			alert('Error al llamar el servicio.');
    		}
        )
    }
    
    $scope.guardarEjemplar = function(){
        serviciosMain.registrarEjemplar($scope.idDisp, $scope.estadoEjem).then(
    		function success(data){
    			if(data.data != "" && data.data == "Se realizo la inserccion del nuevo ejemplar exitosamente!"){
    				alert("Se realizo el registro exitosamente!")
        			location.href = 'main.html';
    				return;
    			}
    			else{
    				alert('Error inesperado, vuelva a intentarlo!');
    			}
    		},
    		
    		function failure(data){
    			alert('Error al llamar el servicio.');
    		}
        )
    }
    
    $scope.retirarEjemplar = function(){
        serviciosMain.darDeBajaEjemplar($scope.idEjemplar).then(
    		function success(data){
    			if(data.data != "" && data.data == "Se realizo la eliminacion del ejemplar exitosamente!"){
    				alert("Se realizo la eliminacion exitosamente!")
        			location.href = 'main.html';
    				return;
    			}
    			else{
    				alert('Error inesperado, vuelva a intentarlo!');
    			}
    		},
    		
    		function failure(data){
    			alert('Error al llamar el servicio.');
    		}
        )
    }
    
});

// Por ultimo, cada vez que se cambie de pagina se verifica que el usuario si este registrado
// NOTA: El proposito de esto es para evitar que los usuarios no puedan escribir rutas y pasar a secciones no autorizadas
appMain.run(['$rootScope', '$cookies', 'serviciosMain', function ($rootScope, $cookies, serviciosMain) {
	//devolvemos a la vista el id de usuario y el rol
	$rootScope.identificador = $cookies.get('identificador');
	$rootScope.rol = $cookies.get('rol');
	
	$rootScope.$on('$routeChangeStart', function(event){
		serviciosMain.validarEstado();
    })
    
    // Por ultimo, se crea un metodo logout el cual llama el servicio que limpia la cookie
    $rootScope.logout = function(){
    	serviciosMain.cerrarSesion();
    }
}]);

appLogin.run(['$rootScope', '$cookies', function ($rootScope, $cookies) {
	if(typeof($cookies.get('identificador')) != 'undefined' && $cookies.get('identificador') != ""){
		location.href = 'main.html'
		return false;
	}
}]);