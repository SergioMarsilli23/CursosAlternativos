angular.module('AngularApp').controller('LoginController',
	[ '$scope', '$http', '$state', '$stateParams', '$rootScope', '$location', '$timeout', 'growl', 'blockUI', 'Idle', 'TokenService', 'LoginService',
		function($scope, $http, $state, $stateParams, $rootScope,  $location, $timeout, growl, blockUI, Idle, TokenService, LoginService) {
		
			$scope.credential = {};
			
			$scope.checkState = () => {
				let sessionData = TokenService.getSessionData();
				if (sessionData) {
					$state.go('secured_index');
				}
			};
			
			$scope.login = function () {
				LoginService.iniciarSesion(angular.copy($scope.credential))
					.then(function successCallback(response) {
						//console.log(response.data);
						growl.success("Éxito al iniciar sesión. ", {ttl:5000});
						TokenService.setToken(angular.copy(response.data));
						let sessionData = TokenService.getSessionData();
						if (sessionData) {
							$state.go('secured_index');
							/*if (sessionData.authorities && (sessionData.authorities.includes('ROLE_ADMIN') || sessionData.authorities.includes('ROLE_CONFIG'))) {
								$state.go('secured_index');
							} else {
								$state.go('index');
							}*/
						} else {
							growl.error("Error al iniciar sesión 2. ", {ttl:5000});
						}
					}, function errorCallback(responseError) {
						growl.error("Error al iniciar sesión. ", {ttl:5000});
					});
				
				return false;
			};
		
	}
]);
