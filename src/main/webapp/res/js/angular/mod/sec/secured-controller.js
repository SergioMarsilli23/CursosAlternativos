angular.module('AngularApp').controller('SecuredController',
	[ '$scope', '$state', '$stateParams', '$rootScope', 'TokenService',
		function($scope, $state, $stateParams, $rootScope, TokenService) {
		
		let sessionData = TokenService.getSessionData();
		if (!sessionData || !sessionData.authorities) {
			growl.error("Sesión no válida. ", {ttl:5000});
			$state.go('logout');
		}
		
		$("*[required]").each(function(i, item) {
			$("label[for='" + $(item).attr("id") + "']").append("&nbsp;<span class='required'>*<span>")
		});
	}
]);
