var app=angular.module('Prestamos',['ngRoute']);

app.config(function($routeProvider){
	$routeProvider
	.when("/",{
		templateUrl:"view/login.html",
		controller:"prueba"
	})
	.when("/prestamo",{
		templateUrl:"view/prestamo.html",
		controller:"ctrPrestamo"
	})
	.when("/prestamo",{
		templateUrl:"view/prestamo.html",
		controller:"ctrPrestamo"
	})
});

app.controller('prueba',["$scope",function($scope){
	$scope.prueba="aaa";
}])

app.controller('ctrPrestamo',["$scope","$http",function($scope,$http){
		
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
				
		console.log($scope.fechaPrestamo)
		
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
		console.log(parametros);
		
		$http({
			method:"POST",
			url:"ingweb/Prestamo/solicitud",
			params:parametros,
			header:{"Content-type":"application/json"}
		}).then(
			function success(res){
				console.log(res)
				$scope.response=res.data
			},function err(err){
				$scope.response={estado:true,msj:'Solicitud realizada correctamente'}
			})
	}
	
	$http({
		method:"GET",
		url:"ingweb/Prestamo/ejemplares"
	}).then(
		function success(res){
			$scope.ejemplares=res.data.jsonEjemplares;
		},function err(err){
			console.log(err);
		})
}])