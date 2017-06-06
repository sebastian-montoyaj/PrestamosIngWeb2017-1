/**
 * 
 */

var appUsuario = angular.module('usuario', ['ngRoute', 'ngCookies']);

appUsuario.service('usuario', function($http, $cookies, $location){
	
});

appUsuario.controller('PaginaPrincipal', function($location, usuario) {
	$location.url('/index.html');
});

appUsuario.config(['$routeProvider', function($routeProvider) {
	
	$routeProvider.when('/', {
        templateUrl: 'index.html',
        controller: 'PaginaPrincipal'
	});
	
}]);