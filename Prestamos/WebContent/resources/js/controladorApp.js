/**
 * @author Sebastian Montoya Jimenez
 */

// En primer lugar, creamos el modulo angular que controlara el comportamiento de los usuarios y le inyectamos los modulos: ngCookies y ngRoute
var appLogin = angular.module('login', ['ngRoute', 'ngCookies']);
var appMain = angular.module('main', ['ngRoute', 'ngCookies']);

// Inmediatamente, especificamos la configuración de ruteo de nuestro sistema, por lo tanto:
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
	// Ejemplo ruta: http://localhost:8080/Prestamos/#!/home
	
	$routeProvider.when('/estadoUsuario', {
        templateUrl: 'estadoUsuario.html',
        controller: 'HomeController'
    });
	
	$routeProvider.when('/prestamo', {
        templateUrl: 'view/prestamo.html',
        controller: 'ctrPrestamo'
    });
	
	$routeProvider.when('/ejemplares', {
        templateUrl: 'view/ejemplares.html',
        controller: 'ctrEjemplares'
    });
	
	$routeProvider.when('/comprobar-entrega', {
        templateUrl: 'view/comprobarEntrega.html',
        controller: 'ctrComprobarEntrega'
    });
	
	$routeProvider.when('/comprobar-devolucion', {
        templateUrl: 'view/comprobarDevolucion.html',
        controller: 'ctrComprobarDevolucion'
    });

}]);


// Despues, registramos un servicio llamado serviciosUsuario el cual tiene la mision de invocar los servicios web que se tienen creados y obtener su resultado
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
	
	this.prestamos=function(){
		return $http({
			method:"GET",
			url:"ingweb/Prestamo/prestamosSolicitud"
		}).then(
			function success(res){				
				return res;
			},function err(err){
				return err;
			})
	}
	
	this.ejemplares=function(){
		return $http({
			method:"GET",
			url:"ingweb/Prestamo/ejemplares"
		}).then(
			function success(res){
				//$scope.ejemplares=res.data.jsonEjemplares;
				return res;
			},function err(err){
				return err;
			})
	}
    
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
    						location.href = 'main.html';
    					}
    				)
    				
    				// Y se redirige el usuario a la pagina principal del sistema
//    				$location.url('/home');
    				
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
appMain.controller('HomeController', function($scope, $location, $cookies, serviciosMain){
	
    $scope.esAdmin = function(){
    	return $scope.rol == "Administrador"
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


appMain.controller('ctrPrestamo',["$scope","$http","serviciosMain",function($scope,$http,serviciosMain){
		
	$scope.ejemplares=[];
	
	$scope.response={estado:false,msj:""};
	
	$scope.solicitar=function(){
		$scope.response={estado:false,msj:""};
		
		var listaEjemplares="";
		for(var item in $scope.ejemplares){			
			if($scope.ejemplares[item].select!=undefined)
				if($scope.ejemplares[item].select){
					listaEjemplares+=$scope.ejemplares[item].id+"-";
			}
		}
				
		if(listaEjemplares.length<=0){
			$scope.response={estado:false,msj:"Seleccione dispositivos a prestar"};
			return;
		}
		
		if($scope.fechaPrestamo==undefined){
			$scope.response={estado:false,msj:"Fecha de prestamo no valida"};
			document.getElementById('fechaPrestamo').focus();
			return;
		}
		
		var fechaPrestamo=$scope.fechaPrestamo.getFullYear();
		
		if(($scope.fechaPrestamo.getMonth()+1)<=9){
			fechaPrestamo+="-0"+($scope.fechaPrestamo.getMonth()+1);
		}
		else{
			fechaPrestamo+="-"+$scope.fechaPrestamo.getMonth();
		}
		
		if(($scope.fechaPrestamo.getDate()+1)<=9){
			fechaPrestamo+="-0"+($scope.fechaPrestamo.getDate()+1);
		}
		else{
			fechaPrestamo+="-"+$scope.fechaPrestamo.getDate();
		}
		
		var parametros={
			idUser:2,
			listaEjemplares:listaEjemplares.substring(0,listaEjemplares.length-1),
			fechaPrestamo:fechaPrestamo
		}
				
		$http({
			method:"POST",
			url:"ingweb/Prestamo/solicitud",
			params:parametros,
			header:{"Content-type":"application/json"}
		}).then(
			function success(res){				
				$scope.response=res.data
			},function err(err){
				$scope.response={estado:false,msj:'Error en los datos'}
			})
	}
	
	serviciosMain.ejemplares().then(
		function success(res){
			$scope.ejemplares=res.data.jsonEjemplares;
		},function err(err){
			console.log(err);
		}
	)

}])

appMain.controller('ctrEjemplares',["$scope","$http","serviciosMain",function($scope,$http,serviciosMain){
		
	$scope.ejemplares=[];
	
	serviciosMain.ejemplares().then(
			function success(res){
				$scope.ejemplares=res.data.jsonEjemplares;
			},function err(err){
				console.log(err);
			}
		)
	
}])

appMain.controller('ctrComprobarEntrega',["$scope","$http",'serviciosMain',function($scope,$http,serviciosMain){
		
	$scope.solicitudes=[];
	
	$scope.response={estado:false,msj:""};
	
	$scope.comprobarEntrega=function(){
		$scope.response={estado:false,msj:""};
		
		var param;
		for(var item in $scope.solicitudes){
			
			if($scope.solicitudes[item].select!=undefined){				
				if($scope.solicitudes[item].select){
					param={idPrestamo:$scope.solicitudes[item].id}
					console.log(param);
					$http({
						method:"POST",
						url:"ingweb/Prestamo/comprobarEntrega",
						params:param,
						header:{"Content-type":"application/json"}
					}).then(
						function success(res){
							console.log(res)
							$scope.response=res.data
							$scope.serPrestamos();
						},function err(err){
							$scope.response={estado:false,msj:'Error en los datos'}
							$scope.serPrestamos();
						})
				}
			}
		}
	}
	
	$scope.serPrestamos=function(){
		serviciosMain.prestamos().then(
				function success(res){
					$scope.solicitudes=res.data.jsonPrestamoSolicitudes;
				},function err(err){
					console.log(err);
				})
	}
	$scope.serPrestamos();

}])

appMain.controller('ctrComprobarDevolucion',["$scope","$http",'serviciosMain',function($scope,$http,serviciosMain){
		
	$scope.solicitudes=[];
	
	$scope.response={estado:false,msj:""};
	
	$scope.comprobarDevolucion=function(){
		$scope.response={estado:false,msj:""};
		
		var param;
		for(var item in $scope.solicitudes){
			if($scope.solicitudes[item].select!=undefined){				
				if($scope.solicitudes[item].select){
					param={idPrestamo:$scope.solicitudes[item].id}
					$http({
						method:"POST",
						url:"ingweb/Prestamo/comprobarDevolucion",
						params:param,
						header:{"Content-type":undefined}
					}).then(
						function success(res){
							console.log(res)
							$scope.response=res.data
							$scope.serPrestamos();
						},function err(err){
							$scope.response={estado:false,msj:'Error en los datos'}
							$scope.serPrestamos();
						})
				}
			}
		}
	}
	
	$scope.serPrestamos=function(){
		serviciosMain.prestamos().then(
				function success(res){
					$scope.solicitudes=res.data.jsonPrestamoSolicitudes;
				},function err(err){
					console.log(err);
				})
	}
	$scope.serPrestamos();

}])
