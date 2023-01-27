angular.module('AngularApp')
	.controller('ScoreController',
		[ '$controller', '$scope', '$http', '$state', '$stateParams', '$rootScope', '$location', '$q', '$timeout', 'growl', 'blockUI', 'TokenService', 'DTOptionsBuilder', 'DTColumnBuilder', 'DTColumnDefBuilder', 'ScoreService', 'CourseService', 'StudentService', 
			function($controller, $scope, $http, $state, $stateParams, $rootScope,  $location, $q, $timeout, growl, blockUI, TokenService, DTOptionsBuilder, DTColumnBuilder, DTColumnDefBuilder, ScoreService, CourseService, StudentService) {
				angular.extend(this, $controller("SecuredController", {$scope, $state, $stateParams, $rootScope, TokenService}));
				
				$scope.dtInstance = {};
				$scope.scoreList = [];
				$scope.courseSelectorList = [];
				$scope.courseTopSelectorList = [];
				$scope.studentSelectorList = [];
				$scope.studentTopSelectorList = [];
				$scope.selectedCourse = null;
				$scope.selectedStudent = null;
				$scope.scoreList = [];
				$scope.responseViewerHeight = "height:" + (screen.height*0.55) + "px; max-height:" + (screen.height*0.55) + "px; ";
				$scope.responseBody = "";
				$scope.selectedScoreDetail = null;
				$scope.selectedScoreIdentifier = null;
				
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
					.withOption('order', [[4, "asc"]])
					.withOption('stateSave', true)
					.withOption('stateDuration', -1)
					.withLanguage(langES)
					.withOption('stateSaveCallback', function (settings, data) {
						sessionStorage.setItem('scoreList', JSON.stringify(data))
					})
					.withOption('stateLoadCallback', function (settings, data) {
						return JSON.parse(sessionStorage.getItem('DataTables_ScoreList'))
					});
				
				$scope.dtColumnDefs = [
					DTColumnDefBuilder.newColumnDef(0).withClass('center-text col-sm-1'),
					DTColumnDefBuilder.newColumnDef(1).withClass('center-text'),
					DTColumnDefBuilder.newColumnDef(2).withClass('center-text'),
					DTColumnDefBuilder.newColumnDef(3).withClass('center-text'),
					DTColumnDefBuilder.newColumnDef(4).withClass('center-text'),
					DTColumnDefBuilder.newColumnDef(5).withClass('center-text col-sm-1').notSortable()
				];
				
				$scope.loadScoreList = () => {
						let deferred = $q.defer();
						
						ScoreService.getAll().then((responseSuccess) => {
							$scope.scoreList = angular.copy(responseSuccess.data);
							$scope.loadCourseList();
							$scope.loadStudentList();
							deferred.resolve();
						}, (responseError) => {
							deferred.reject();
							growl.error("Error al obtener la lista de calificaciones. ", {ttl:5000});
						});
					};
					
				$scope.reloadScoreList = () => {
						let deferred = $q.defer();
						
						ScoreService.getAll().then((responseSuccess) => {
							let scoreList = angular.copy(responseSuccess.data);
							if ($scope.selectedCourse && $scope.selectedCourse.id) {
								scoreList = scoreList.filter(score => score.materia.id == $scope.selectedCourse.id);
							}
							if ($scope.selectedStudent && $scope.selectedStudent.id) {
								scoreList = scoreList.filter(score => score.alumno.id == $scope.selectedStudent.id);
							}
							$scope.scoreList = scoreList;
							deferred.resolve();
						}, (responseError) => {
							deferred.reject();
							growl.error("Error al obtener la lista de calificaciones. ", {ttl:5000});
						});
					};
				
				$scope.loadCourseList = () => {
						let deferred = $q.defer();
						
						CourseService.getAll().then((responseSuccess) => {
							let courseSelectorList = angular.copy(responseSuccess.data).sort((a,b) => {return a.nombre > b.nombre;});
							$scope.courseSelectorList = angular.copy(courseSelectorList);
							courseSelectorList.unshift({"id":0,"nombre":"[TODAS]"});
							$scope.courseTopSelectorList = courseSelectorList;
							$scope.selectedCourse = courseSelectorList[0];
							deferred.resolve();
						}, (responseError) => {
							deferred.reject();
							growl.error("Error al obtener la lista de materias. ", {ttl:5000});
						});
					};
					
				$scope.loadStudentList = () => {
						let deferred = $q.defer();
						
						StudentService.getAll().then((responseSuccess) => {
							let studentSelectorList = angular.copy(responseSuccess.data);
							studentSelectorList.forEach(student => student.nombreCompleto = student.nombre + " " + student.apellidoPaterno + " " + student.apellidoMaterno);
							studentSelectorList.sort((a,b) => {return a.nombreCompleto > b.nombreCompleto;});
							$scope.studentSelectorList = angular.copy(studentSelectorList);
							studentSelectorList.unshift({"id":0,"nombreCompleto":"[TODOS]"});
							$scope.studentTopSelectorList = studentSelectorList;
							$scope.selectedStudent = studentSelectorList[0];
							deferred.resolve();
						}, (responseError) => {
							deferred.reject();
							growl.error("Error al obtener la lista de alumnos. ", {ttl:5000});
						});
					};
					
				$scope.changeCourse = (item) => {
						$scope.selectedCourse = item;
						$scope.reloadScoreList();
					};
					
				$scope.changeStudent = (item) => {
						$scope.selectedStudent = item;
						$scope.reloadScoreList();
					};
					
				$scope.addScore = () => {
					$scope.scoreToEdit = {
						index: null,
						score: {}
					};
					$("#scoreForm").valid();
					$("#scoreEditDialog").modal("show");
				};
				
				$scope.removeScore = (score, index) => {
					$scope.scoreToDelete = {
							index: index,
							score: angular.copy(score)
					};
					$("#scoreDeleteDialog").modal("show");
				};
				
				$scope.editScore = (score, index) => {
					$scope.scoreToEdit = {
							index: index,
							score: Object.assign(angular.copy(score), {
								newNodeNull: !angular.isDefined(score.newNode)||score.newNode==null,
								newPathNull: !angular.isDefined(score.newPath)||score.newPath==null,
								currentNodeNull: !angular.isDefined(score.currentNode)||score.currentNode==null
								})
					};
					$("#scoreForm").valid();
					$("#scoreEditDialog").modal("show");
				};
				
				$scope.deleteScore = () => {
					if ($scope.scoreToDelete.score.id) {
						ScoreService.delete($scope.scoreToDelete.score.id).then(function successCallback(response) {
							$scope.scoreList.splice($scope.scoreToDelete.index,1);
							$("#scoreDeleteDialog").modal("hide");
							//$scope.dtInstance.DataTable.draw("page");
							growl.success("El registro de la calificacióm ha sido eliminado exitosamente.", {ttl:5000});
						}, function errorCallback(responseError) {
							growl.error("Se ha producido un error al actualizar el registro de la calificación.", {ttl:5000});
						});
					} else {
						growl.warning("Seleccione un registro válido.", {ttl:5000});
					}
				};
				
				$scope.saveScore = () => {
					if ($("#scoreForm").valid()) {
						if ($scope.scoreToEdit.score.id) {
							ScoreService.update($scope.scoreToEdit).then(function successCallback(response) {
								let score = angular.copy(response.data);
								Object.assign($scope.scoreList.find(score => score.id == $scope.scoreToEdit.score.id), score);
								$("#scoreEditDialog").modal("hide");
								growl.success("El registro de la calificación ha sido actualizado exitosamente.", {ttl:5000});
							}, function errorCallback(responseError) {
								growl.error("Se ha producido un error al actualizar el registro de la calificación.", {ttl:5000});
							});
						} else {
							ScoreService.save($scope.scoreToEdit).then(function successCallback(response) {
								let score = angular.copy(response.data);
								$scope.scoreList.push(score);
								$("#scoreEditDialog").modal("hide");
								growl.success("El registro de la calificación ha sido guardado exitosamente.", {ttl:5000});
							}, function errorCallback(responseError) {
								growl.error("Se ha producido un error al guardar el registro de la calificación.", {ttl:5000});
							});
						}
					} else {
						growl.warning("Capture la información requerida para continuar.", {ttl:5000});
					}
				};
			
			}
		]);
