angular
	.module('AngularApp')
	.constant('LoginConstants', {
		endpoint: {
			login: applicationContext + "/auth"
		}
	})
	.service('LoginService',
		function($log, $http, LoginConstants) {
			return {
				iniciarSesion : function (credenciales) {
					//console.log(credenciales);
					let contrasena_cifrada = CryptoJS.SHA256(""+credenciales.password);
					let contrasena_b64 = contrasena_cifrada.toString(CryptoJS.enc.Base64);
					let credencial_cifrada = {
						user: credenciales.username,
						//password: contrasena_b64
						password: credenciales.password
					};
					
					//console.log(credencial_cifrada);
					
					return $http.post(LoginConstants.endpoint.login, credencial_cifrada);
				}
			};
		});
