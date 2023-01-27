angular.module('AngularApp').controller('IndexController',
	[ '$scope', '$http', '$state', '$stateParams', '$rootScope', '$window', '$location', 'growl', 'blockUI', 'TokenService',
		function($scope, $http, $state, $stateParams, $rootScope, $window,  $location, growl, blockUI, TokenService) {
		
			$scope.checkState = () => {
				let sessionData = TokenService.getSessionData();
				if (!sessionData || !sessionData.authorities) {
					$state.go('logout');
				} else if (sessionData.authorities.includes('ROLE_ADMIN') || sessionData.authorities.includes('ROLE_CONFIG')) {
					$state.go('secured_index');
				}
			};
			
	}
]);
