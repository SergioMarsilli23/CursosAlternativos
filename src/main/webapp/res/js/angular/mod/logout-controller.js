angular.module('AngularApp').controller('LogoutController',
	['$state', '$location', 'growl', 'blockUI', 'Idle', 'TokenService',
		function($state, $location, growl, blockUI, Idle, TokenService) {
		
			TokenService.removeToken();
			$state.go('login');
		
		}
	]);
