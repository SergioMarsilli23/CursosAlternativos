angular
	.module('AngularApp')
	.service('TokenService',
		function ($log, $window, $injector, $q, $rootScope) {
			
			var cachedToken = null;
			
			if (!cachedToken && $window.sessionStorage.bearer && $window.sessionStorage.sessionData) {
				cachedToken = {
						sessionData: JSON.parse($window.sessionStorage.sessionData),
						bearer: $window.sessionStorage.bearer
				};
			}
		
			this.getToken = () => {
				if (cachedToken && cachedToken.bearer && cachedToken.sessionData) {
					return cachedToken;
				} else {
					return null;
				}
			};
			
			this.getBearer = () => {
				if (cachedToken && cachedToken.bearer && cachedToken.sessionData) {
					return cachedToken.bearer;
				} else {
					return null;
				}
			};
			
			this.getSessionData = () => {
				if (cachedToken && cachedToken.bearer && cachedToken.sessionData) {
					return cachedToken.sessionData;
				} else {
					return null;
				}
			};
			
			this.setToken = (objectOrString) => {
				let tokenString = null;
				try {
					if (!objectOrString) {
						throw $log.error("Se omitió el token o no es válido");
					} else if (typeof objectOrString == typeof String()) {
						tokenString = objectOrString;
					} else if (typeof objectOrString == typeof Object() && typeof objectOrString.token == typeof String()) {
						tokenString = objectOrString.token;
					} else {
						throw $log.error("Se omitió el token o no es válido");
					}
					
					tokenString = tokenString.replace(/(^['"]|['"]$)/g,"").replace(/^BEARER\s+/i,"");
					let part1 = tokenString.split('.')[1];
					let base64 = part1.replaceAll('-', '+').replaceAll('_', '/');
					let tokenData = $window.atob(base64);
					console.log(tokenData);
					
					$window.sessionStorage.bearer = tokenString;
					$window.sessionStorage.sessionData = tokenData;
					
					cachedToken = {
						bearer: tokenString,
						sessionData: JSON.parse(tokenData)
					};
				} catch (error) {
					$log.error("Se produjo un error al guardar el token de sesión");
				}
			};
			
			this.removeToken = () => {
				if ($window.sessionStorage.bearer) {
					delete $window.sessionStorage.bearer;
				}
				if ($window.sessionStorage.sessionData) {
					delete $window.sessionStorage.sessionData;
				}
				cachedToken = null;
			};

			/*

			this.setToken = function(tokenValue) {
				if (tokenValue && tokenValue.token) {
					let token = tokenValue.token.replace(/^BEARER\s+/i,"");
					//console.log("El token es: " + token);
					sessionStorage.token = token;
					
					var base64Url = token.split('.')[1];
					var base64 = base64Url.replace('-', '+').replace('_', '/');
					var claims = JSON.parse($window.atob(base64));
					//sessionData = JSON.parse(claims.sessionData);
					//console.log("this.setToken " + sessionData);
				}
				
				//refreshDomainData();
			};

			this.getSessionData = function() {
				if (angular.isDefined(token))
					return sessionData;
			};

			this.getToken = function() {
				if (this.checkIfTokenExist()) {
					return token;
				} else {
					$log.warn('No se localizó token de sesión.')
				}
			};

			this.checkIfTokenExist = function() {
				if (sessionStorage.token) {
					token = sessionStorage.token
					var base64Url = token.split('.')[1];
					var base64 = base64Url.replace('-', '+').replace('_', '/');
					var claims = JSON.parse($window.atob(base64));
					//sessionData = JSON.parse(claims.sessionData);
					//console.log("checkIfTokenExist " + sessionData);
					//refreshDomainData();
					return true;
				} else
					return false;
			};

			function setTokenAtStartup() {
				if (sessionStorage.token) {
					token = sessionStorage.token
					var base64Url = token.split('.')[1];
					var base64 = base64Url.replace('-', '+').replace('_', '/');
					var claims = JSON.parse($window.atob(base64));
					//sessionData = JSON.parse(claims.sessionData);
					//console.log("setTokenAtStartup: " + JSON.stringify(claims));
					//refreshDomainData();
					return true;
				} else
					return false;
			}

			function isStillValid() {
				if (sessionStorage.token) {
					token = sessionStorage.token
					var base64Url = token.split('.')[1];
					var base64 = base64Url.replace('-', '+').replace('_', '/');
					var claims = JSON.parse($window.atob(base64));
					var secondFromEpoc = new Date() / 1000;
					if (claims.exp - secondFromEpoc > 0)
						return true;
					else
						return false;
				} else {
					return false;
				}
			}*/
		});
