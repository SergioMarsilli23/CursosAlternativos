angular.module('AngularApp').controller('ExtraController',
	[ '$controller', '$scope', '$http', '$state', '$stateParams', '$rootScope', '$location', '$q', 'growl', 'blockUI', 'TokenService', 'DTOptionsBuilder', 'DTColumnBuilder', 'DTColumnDefBuilder', 'ExtraService', 
		function($controller, $scope, $http, $state, $stateParams, $rootScope,  $location, $q, growl, blockUI, TokenService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder, ExtraService) {
			angular.extend(this, $controller("SecuredController", {$scope, $state, $stateParams, $rootScope, TokenService}));
			
			$scope.actionPostRequest = '{\n\t"calificacion": 0,\n\t"idAlumno": 0,\n\t"idCurso": 0\n}';
			$scope.actionPostResponse = '{\n\t"msg": "",\n\t"success": ""\n}';
			$scope.actionGetRequest = '';
			$scope.actionGetResponse = '';
			$scope.actionPutRequest = '{\n\t"calificacion": 0,\n\t"idCalificacion": 0\n}';
			$scope.actionPutResponse = '{\n\t"msg": "",\n\t"success": ""\n}';
			$scope.actionDeleteRequest = '';
			$scope.actionDeleteResponse = '';
			
			
			$scope.initExtra = () => {
				//Nothing to do
			};
			
			$scope.runPost = () => {
				try {
					let json = JSON.parse($scope.actionPostRequest);
					ExtraService.save(json).then(function successCallback(responseSuccess) {
						let extra = angular.copy(responseSuccess.data);
						$scope.actionPostResponse = JSON.stringify(extra);
						growl.success("El registro ha sido guardado exitosamente.", {ttl:5000});
					}, function errorCallback(responseError) {
						$scope.actionPostResponse = "ERROR HTTP " + responseError.status + " " + (responseError.statusText ? responseError.statusText : responseError.data.error);
						growl.error("Se ha producido un error al guardar el registro.", {ttl:5000});
					});
				} catch (err) {
					growl.error("El cuerpo especificado no se encuentra en formato JSON.", {ttl:5000});
				}
				
			};
			
			$scope.runGet = () => {
				if ($scope.actionGetRequest.match(/^[1-9]\d*$/)) {
					ExtraService.getById($scope.actionGetRequest).then((responseSuccess) => {
						let extra = angular.copy(responseSuccess.data);
						$scope.actionGetResponse = JSON.stringify(extra);
						growl.success("El registro ha sido consultado exitosamente.", {ttl:5000});
					}, (responseError) => {
						$scope.actionGetResponse = "ERROR HTTP " + responseError.status + " " + (responseError.statusText ? responseError.statusText : responseError.data.error);
						growl.error("Error al obtener el registro. ", {ttl:5000});
					});
				} else {
					growl.error("El ID debe de ser un entero positivo.", {ttl:5000});
				}
			};
			
			$scope.runPut = () => {
				try {
					let json = JSON.parse($scope.actionPutRequest);
					ExtraService.update(json).then(function successCallback(responseSuccess) {
						let extra = angular.copy(responseSuccess.data);
						$scope.actionPutResponse = JSON.stringify(extra);
						growl.success("El registro ha sido actualizado exitosamente.", {ttl:5000});
					}, function errorCallback(responseError) {
						$scope.actionPutResponse = "ERROR HTTP " + responseError.status + " " + (responseError.statusText ? responseError.statusText : responseError.data.error);
						growl.error("Se ha producido un error al actualizar el registro.", {ttl:5000});
					});
				} catch (err) {
					growl.error("El cuerpo especificado no se encuentra en formato JSON.", {ttl:5000});
				}
				
			};
			
			$scope.runDelete = () => {
				if ($scope.actionDeleteRequest.match(/^[1-9]\d*$/)) {
					ExtraService.delete($scope.actionDeleteRequest).then((responseSuccess) => {
						let extra = angular.copy(responseSuccess.data);
						$scope.actionDeleteResponse = JSON.stringify(extra);
						growl.success("El registro ha sido eliminado exitosamente.", {ttl:5000});
					}, (responseError) => {
						$scope.actionDeleteResponse = "ERROR HTTP " + responseError.status + " " + (responseError.statusText ? responseError.statusText : responseError.data.error);
						growl.error("Error al eliminar el registro. ", {ttl:5000});
					});
				} else {
					growl.error("El ID debe de ser un entero positivo.", {ttl:5000});
				}
			};			
			
	}
]);
