/**
 *
 */
/*global angular */

angular
	.module('AngularApp', [
		'ui.router',
		'datatables',
		'ui.bootstrap',
		'angular-growl',
		'angular.filter',
		'blockUI',
		'ngIdle',
		'ui.select',
		'ngSanitize'
	])
	.config(function($stateProvider, $urlRouterProvider, $locationProvider) {
		$stateProvider
			.state('login',{
				url : '/login',
				controller : 'LoginController',
				templateUrl : applicationContext + '/admin/login'
			})
			.state('logout',{
				url : '/logout',
				controller : 'LogoutController'
			})
			.state('index',{
				url : '/index',
				controller : 'IndexController',
				templateUrl : applicationContext + '/admin/index'
			})
			.state('secured_index',{
				url : '/secured/index',
				controller : 'SecuredIndexController',
				templateUrl : applicationContext + '/admin/secured/index'
			})
			.state('secured_course',{
				url : '/secured/course',
				controller : 'CourseController',
				templateUrl : applicationContext + '/admin/secured/course'
			})
			.state('secured_student',{
				url : '/secured/student',
				controller : 'StudentController',
				templateUrl : applicationContext + '/admin/secured/student'
			})
			.state('secured_score',{
				url : '/secured/score',
				controller : 'ScoreController',
				templateUrl : applicationContext + '/admin/secured/score'
			})
			.state('secured_extra',{
				url : '/secured/extra',
				controller : 'ExtraController',
				templateUrl : applicationContext + '/admin/secured/extra'
			});
	})
	.factory('tokenInterceptor', function (TokenService) {
		var callStack = [];
		
		return {
			request: function (config) {
				if (TokenService.getBearer() != null && (config.url.indexOf('/rest/') > -1 || config.url.indexOf('/admin/secured/') > -1)) {
					config.headers.Authorization = "Bearer " + TokenService.getBearer();
					callStack.push({ callPath: config.url });
				}
				
				return config;
			}
		};
	})
	.factory('forbiddenViewInterceptor', function ($q, $location, $timeout) {
		return {
			responseError: function (response) {
				if ((response.status == 403 || response.status == 401) && (response.config.url.indexOf('/admin/rest/') > -1 || response.config.url.indexOf('/admin/secured/') > -1)){
					if ($('.modal.in').length) {
						$('.modal.in')
							.on('hidden.bs.modal', function(){
								$(this).off('hidden.bs.modal');
								$location.path('/logout');
							})
							.modal('hide');
					} else {
						$location.path('/logout');
					}
				}
				return $q.reject(response);
			}
		};
	})
	.config(function ($httpProvider) {
		$httpProvider.interceptors.push('tokenInterceptor');
		$httpProvider.interceptors.push('forbiddenViewInterceptor');
	})
	.config(function (IdleProvider) {
		// configure Idle settings
		IdleProvider.idle(900);
		IdleProvider.timeout(60);
	})
	.config(function (growlProvider) {
		growlProvider.globalTimeToLive(10000);
		growlProvider.onlyUniqueMessages(true);
	})
	.config(['growlProvider', function (growlProvider) {
		growlProvider.globalTimeToLive(10000);
		growlProvider.onlyUniqueMessages(true);
	}])
	.config(['blockUIConfig', function (blockUIConfig) {
		blockUIConfig.requestFilter = function (config) {
			if (config.url.match(/[&\?]noblockui=/gi)) {
				return false;
			}
		};
	}])
	.directive('historyValidation', function() {
		return {
			restrict: 'A',
			scope: {fn: "&historyValidation"},
			link: function(scope, element, attrs) {
				element.bind("blur change input", function () {
					scope.$apply(scope.fn({model : attrs.ngModel, value : element.val()}));
				});
			}
		};
	})
	.directive('globalEvents', function($rootScope) {
		return function(scope, element, attrs) {
			element.bind('click', function(e){
				if (e && e.target && (e.target.id.match(/btn-menu/) || $(e.target).parents("#btn-menu").length 
						|| e.target.id.match(/cbp-spmenu-s1/i) || $(e.target).parents("#cbp-spmenu-s1").length)) {
					return true;
				}
				
				$rootScope.$broadcast('closeMenu');
			});
		};
	})
	.directive('decimalPlaces',function(){
		return {
			link:function(scope,ele,attrs){
				ele.bind('keypress',function(e){
					var newVal=$(this).val()+(e.charCode!==0?String.fromCharCode(e.charCode):'');
					if($(this).val().search(/(.*)\.[0-9][0-9]/)===0 && newVal.length>$(this).val().length){
						e.preventDefault();
					}
				});
			}
		};
	})
	.run(['$state', function($state){
		$state.go("login");
	}]);
