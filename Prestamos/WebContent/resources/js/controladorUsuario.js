/**
 * @author Sebastian Montoya Jimenez
 */

// https://www.uno-de-piera.com/login-con-angularjs-utilizando-cookies-con-el-modulo-ngcookies/
// https://www.youtube.com/watch?v=9zij2kat4o8

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
appUsuario.service('serviciosUsuario', function($http, $cookies, $location){
	
    this.autenticar = function(id, passwd){
        return $http({
            url: 'http://localhost:8080/Prestamos/ingweb/Usuario/validarUsuario',
            method: 'POST',
            params: {identificador: id, password: passwd}
        })
    };
    
    this.validarEstado = function(){
		if(typeof($cookies.identificador) == 'undefined' || $cookies.identificador == ""){
			$location.url("/");
			return false;
		}
		
		if($location.url() == '/'){
			
		}
	};
    
});

// Ahora, se crea el controlador Login cuya funcion es proveer el metodo de autenticacion del usuario
appUsuario.controller('Login', function($scope, $location, $cookies, serviciosUsuario){
	$scope.identificador = '';
    $scope.password = '';
	
    $scope.login = function()
    {
    	serviciosUsuario.autenticar($scope.identificador, $scope.password).then(
    		function success(data)
    		{
    			if(data.data != "" && data.data == "true"){
    				
    				$cookies.identificador = $scope.identificador;
    				$location.url('/home');
    				return;
    			}
    			
    			alert("Usuario o contrasena incorrectos!")
    			$scope.identificador = '';
    			$scope.password = '';
    			return;
    		},
    		
    		function failure(data){
				alert('Error inesperado, vuelva a intentarlo!');
			}
    	)
    }
    
});

appUsuario.controller('HomeController', function($scope){
	
	$scope.validar = function() {
		alert('Mensaje')
	};
});










// Por ultimo, cada vez que se cambie de pagina se verifica que el usuario si este registrado
// NOTA: El proposito de esto es para evitar que los usuarios no puedan escribir rutas y pasar a secciones no autorizadas
appUsuario.run(['$rootScope', 'serviciosUsuario', function ($rootScope, serviciosUsuario) {
	$rootScope.$on('$routeChangeStart', function(event){
    	serviciosUsuario.validarEstado();
    })
}]);