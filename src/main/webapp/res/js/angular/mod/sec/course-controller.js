angular.module('AngularApp').controller('CourseController',
	[ '$controller', '$scope', '$http', '$state', '$stateParams', '$rootScope', '$location', '$q', 'growl', 'blockUI', 'TokenService', 'DTOptionsBuilder', 'DTColumnBuilder', 'DTColumnDefBuilder', 'CourseService', 
		function($controller, $scope, $http, $state, $stateParams, $rootScope,  $location, $q, growl, blockUI, TokenService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder, CourseService) {
			angular.extend(this, $controller("SecuredController", {$scope, $state, $stateParams, $rootScope, TokenService}));
			
			$scope.dtInstance = {};
			$scope.courseList = [];
			
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
					sessionStorage.setItem('courseList', JSON.stringify(data))
				})
				.withOption('stateLoadCallback', function (settings, data) {
					return JSON.parse(sessionStorage.getItem('DataTables_CourseList'))
				});
			
			$scope.dtColumnDefs = [
				DTColumnDefBuilder.newColumnDef(0).withClass('col-sm-1 center-text'),
				DTColumnDefBuilder.newColumnDef(1).withClass('center-text'),
				DTColumnDefBuilder.newColumnDef(2).withClass('center-text'),
				DTColumnDefBuilder.newColumnDef(3).withClass('col-sm-1 center-text').notSortable()
			];
			
			$scope.loadCourseList = () => {
					let deferred = $q.defer();
					
					CourseService.getAll().then((responseSuccess) => {
						$scope.courseList = responseSuccess.data;
						deferred.resolve();
					}, (responseError) => {
						deferred.reject();
						growl.error("Error al obtener la lista de materias. ", {ttl:5000});
					});
				};
			
			$scope.addCourse = () => {
				$scope.courseToEdit = {
						index: null,
						course: {}
				};
				$("#courseForm").valid();
				$("#courseEditDialog").modal("show");
			};
			
			$scope.removeCourse = (course, index) => {
				$scope.courseToDelete = {
						index: index,
						course: angular.copy(course)
				};
				$("#courseDeleteDialog").modal("show");
			};
			
			$scope.editCourse = (course, index) => {
				$scope.courseToEdit = {
						index: index,
						course: angular.copy(course)
				};
				$("#courseForm").valid();
				$("#courseEditDialog").modal("show");
			};
			
			$scope.deleteCourse = () => {
				if ($scope.courseToDelete.course.id) {
					CourseService.delete($scope.courseToDelete.course.id).then(function successCallback(response) {
						$scope.courseList.splice($scope.courseToDelete.index,1);
						$("#courseDeleteDialog").modal("hide");
						//$scope.dtInstance.DataTable.draw("page");
						growl.success("El registro de la materia ha sido eliminado exitosamente.", {ttl:5000});
					}, function errorCallback(responseError) {
						growl.error("Se ha producido un error al actualizar el registro de la materia.", {ttl:5000});
					});
				} else {
					growl.warning("Seleccione un registro válido.", {ttl:5000});
				}
			};
			
			$scope.saveCourse = () => {
				if ($("#courseForm").valid()) {
					if ($scope.courseToEdit.course.id) {
						CourseService.update($scope.courseToEdit).then(function successCallback(response) {
							let course = angular.copy(response.data);
							Object.assign($scope.courseList.find(course => course.id == $scope.courseToEdit.course.id), course);
							$("#courseEditDialog").modal("hide");
							growl.success("El registro de la materia ha sido actualizado exitosamente.", {ttl:5000});
						}, function errorCallback(responseError) {
							growl.error("Se ha producido un error al actualizar el registro de la materia.", {ttl:5000});
						});
					} else {
						CourseService.save($scope.courseToEdit).then(function successCallback(response) {
							let course = angular.copy(response.data);
							$scope.courseList.push(course);
							$("#courseEditDialog").modal("hide");
							growl.success("El registro de la materia ha sido guardado exitosamente.", {ttl:5000});
						}, function errorCallback(responseError) {
							growl.error("Se ha producido un error al guardar el registro de la materia.", {ttl:5000});
						});
					}
				} else {
					growl.warning("Capture la información requerida para continuar.", {ttl:5000});
				}
			};
	}
]);
