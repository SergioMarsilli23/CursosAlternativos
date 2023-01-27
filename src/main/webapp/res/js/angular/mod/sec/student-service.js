
angular
	.module('AngularApp')
	.constant("StudentConstants", {
		rest: {
			getAll: applicationContext + '/rest/student',
			getById: applicationContext + '/rest/student/{ID}',
			save: applicationContext + '/rest/student',
			update: applicationContext + '/rest/student',
			delete: applicationContext + '/rest/student/{ID}'
		}
	})
	.service('StudentService',
		function ($log, $window, $http, $q, StudentConstants) {
			
			this.getAll = () => {
				return $http.get(StudentConstants.rest.getAll);
			};
			
			this.getById = (id) => {
				return $http.get(StudentConstants.rest.getById.replace('{ID}', id));
			};
			
			this.save = (studentToEdit) => {
				let student = angular.copy(studentToEdit.student);
				
				return $http.post(StudentConstants.rest.save, student);
			};
			
			this.update = (studentToEdit) => {
				let student = angular.copy(studentToEdit.student);
				
				return $http.put(StudentConstants.rest.update, student);
			};
			
			this.delete = (id) => {
				return $http.delete(StudentConstants.rest.delete.replace('{ID}', id));
			};
			
		});
