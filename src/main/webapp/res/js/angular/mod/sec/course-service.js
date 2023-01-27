
angular
	.module('AngularApp')
	.constant("CourseConstants", {
		rest: {
			getAll: applicationContext + '/rest/course',
			getById: applicationContext + '/rest/course/{ID}',
			save: applicationContext + '/rest/course',
			update: applicationContext + '/rest/course',
			delete: applicationContext + '/rest/course/{ID}'
		}
	})
	.service('CourseService',
		function ($log, $window, $http, $q, CourseConstants) {
			
			this.getAll = () => {
				return $http.get(CourseConstants.rest.getAll);
			};
			
			this.getById = (id) => {
				return $http.get(CourseConstants.rest.getById.replace('{ID}', id));
			};
			
			this.save = (courseToEdit) => {
				let course = angular.copy(courseToEdit.course);
				
				return $http.post(CourseConstants.rest.save, course);
			};
			
			this.update = (courseToEdit) => {
				let course = angular.copy(courseToEdit.course);
				
				return $http.put(CourseConstants.rest.update, course);
			};
			
			this.delete = (id) => {
				return $http.delete(CourseConstants.rest.delete.replace('{ID}', id));
			};
			
		});
