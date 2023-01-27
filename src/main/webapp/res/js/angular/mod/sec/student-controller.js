angular.module('AngularApp').controller('StudentController',
	[ '$controller', '$scope', '$http', '$state', '$stateParams', '$rootScope', '$location', '$q', 'growl', 'blockUI', 'TokenService', 'DTOptionsBuilder', 'DTColumnBuilder', 'DTColumnDefBuilder', 'StudentService', 
		function($controller, $scope, $http, $state, $stateParams, $rootScope,  $location, $q, growl, blockUI, TokenService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder, StudentService) {
			angular.extend(this, $controller("SecuredController", {$scope, $state, $stateParams, $rootScope, TokenService}));
			
			$scope.dtInstance = {};
			$scope.studentList = [];
			
			var langES = {
					"sEmptyTable":     "No hay datos en la tabla ",
					"sInfo":           "Mostrando _START_ a _END_ de _TOTAL_ registros",
					"sInfoEmpty":      "Mostrando 0 a 0 de 0 registros",
					"sInfoFiltered":   "(filtrados de  un total de _MAX_  registros)",
					"sInfoPostFix":    "",
					"sInfoThousands":  ",",
					"sLengthMenu":     "Mostrar _MENU_ registros",
					"sLoadingRecords": "Cargando...",
					"sProcessing":     "Procesando...",
					"sSearch":         "Buscar:",
					"sZeroRecords":    "No se encuentran registros",
					"oPaginate": {
						"sFirst":    "Primero",
						"sLast":     "Ultimo",
						"sNext":     "Siguiente",
						"sPrevious": "Anterior"
					},
					"oAria": {
						"sSortAscending":  ": activate para ordenar por columna ascendente ",
						"sSortDescending": ": activate para ordenar por columna descendente"
					}
				};
			
			$scope.dtOptions = DTOptionsBuilder.newOptions()
				.withOption('bFilter', true)
				.withOption('searchable', true)
				.withOption('lengthChange', false)
				.withOption('paging', false)
				.withOption('info', true)
				.withOption('order', [[1, "desc"]])
				.withOption('stateSave', true)
				.withOption('stateDuration', -1)
				.withLanguage(langES)
				.withOption('stateSaveCallback', function (settings, data) {
					sessionStorage.setItem('studentList', JSON.stringify(data))
				})
				.withOption('stateLoadCallback', function (settings, data) {
					return JSON.parse(sessionStorage.getItem('DataTables_StudentList'))
				});
			
			$scope.dtColumnDefs = [
				DTColumnDefBuilder.newColumnDef(0).withClass('col-sm-1 center-text'),
				DTColumnDefBuilder.newColumnDef(1).withClass('center-text'),
				DTColumnDefBuilder.newColumnDef(2).withClass('center-text'),
				DTColumnDefBuilder.newColumnDef(3).withClass('center-text'),
				DTColumnDefBuilder.newColumnDef(4).withClass('center-text'),
				DTColumnDefBuilder.newColumnDef(5).withClass('col-sm-1 center-text').notSortable()
			];
			
			$scope.loadStudentList = () => {
					let deferred = $q.defer();
					
					StudentService.getAll().then((responseSuccess) => {
						$scope.studentList = responseSuccess.data;
						deferred.resolve();
					}, (responseError) => {
						deferred.reject();
						growl.error("Error al obtener la lista de alumnos. ", {ttl:5000});
					});
				};
			
			$scope.addStudent = () => {
				$scope.studentToEdit = {
						index: null,
						student: {}
				};
				$("#studentForm").valid();
				$("#studentEditDialog").modal("show");
			};
			
			$scope.removeStudent = (student, index) => {
				$scope.studentToDelete = {
						index: index,
						student: angular.copy(student)
				};
				$("#studentDeleteDialog").modal("show");
			};
			
			$scope.editStudent = (student, index) => {
				$scope.studentToEdit = {
						index: index,
						student: angular.copy(student)
				};
				$("#studentForm").valid();
				$("#studentEditDialog").modal("show");
			};
			
			$scope.deleteStudent = () => {
				if ($scope.studentToDelete.student.id) {
					StudentService.delete($scope.studentToDelete.student.id).then(function successCallback(response) {
						$scope.studentList.splice($scope.studentToDelete.index,1);
						$("#studentDeleteDialog").modal("hide");
						//$scope.dtInstance.DataTable.draw("page");
						growl.success("El registro de la alumno ha sido eliminado exitosamente.", {ttl:5000});
					}, function errorCallback(responseError) {
						growl.error("Se ha producido un error al actualizar el registro de la alumno.", {ttl:5000});
					});
				} else {
					growl.warning("Seleccione un registro válido.", {ttl:5000});
				}
			};
			
			$scope.saveStudent = () => {
				if ($("#studentForm").valid()) {
					if ($scope.studentToEdit.student.id) {
						StudentService.update($scope.studentToEdit).then(function successCallback(response) {
							let student = angular.copy(response.data);
							Object.assign($scope.studentList.find(student => student.id == $scope.studentToEdit.student.id), student);
							$("#studentEditDialog").modal("hide");
							growl.success("El registro de la alumno ha sido actualizado exitosamente.", {ttl:5000});
						}, function errorCallback(responseError) {
							growl.error("Se ha producido un error al actualizar el registro de la alumno.", {ttl:5000});
						});
					} else {
						StudentService.save($scope.studentToEdit).then(function successCallback(response) {
							let student = angular.copy(response.data);
							$scope.studentList.push(student);
							$("#studentEditDialog").modal("hide");
							growl.success("El registro de la alumno ha sido guardado exitosamente.", {ttl:5000});
						}, function errorCallback(responseError) {
							growl.error("Se ha producido un error al guardar el registro de la alumno.", {ttl:5000});
						});
					}
				} else {
					growl.warning("Capture la información requerida para continuar.", {ttl:5000});
				}
			};
	}
]);
