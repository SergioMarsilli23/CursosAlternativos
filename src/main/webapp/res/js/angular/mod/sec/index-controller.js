angular.module('AngularApp').controller('SecuredIndexController',
	[ '$controller', '$scope', '$http', '$state', '$stateParams', '$rootScope', '$window', '$location', 'growl', 'blockUI', 'TokenService',
		function($controller, $scope, $http, $state, $stateParams, $rootScope, $window,  $location, growl, blockUI, TokenService) {
			angular.extend(this, $controller("SecuredController", {$scope, $state, $stateParams, $rootScope, TokenService}));
		
			$scope.checkState = () => {
				/*let sessionData = TokenService.getSessionData();
				if (!sessionData || !sessionData.authorities) {
					$state.go('login');
				}*/
			};
			
	}
]);
