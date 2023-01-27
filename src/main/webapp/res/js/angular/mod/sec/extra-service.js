
angular
	.module('AngularApp')
	.constant("ExtraConstants", {
		rest: {
			getAll: applicationContext + '/rest/extra',
			getById: applicationContext + '/rest/extra/{ID}',
			save: applicationContext + '/rest/extra',
			update: applicationContext + '/rest/extra',
			delete: applicationContext + '/rest/extra/{ID}'
		}
	})
	.service('ExtraService',
		function ($log, $window, $http, $q, ExtraConstants) {
			
			this.getAll = () => {
				return $http.get(ExtraConstants.rest.getAll);
			};
			
			this.getById = (id) => {
				return $http.get(ExtraConstants.rest.getById.replace('{ID}', id));
			};
			
			this.save = (extraToSave) => {
				let extra = angular.copy(extraToSave);
				
				return $http.post(ExtraConstants.rest.save, extra);
			};
			
			this.update = (extraToUpdate) => {
				let extra = angular.copy(extraToUpdate);
				
				return $http.put(ExtraConstants.rest.update, extra);
			};
			
			this.delete = (id) => {
				return $http.delete(ExtraConstants.rest.delete.replace('{ID}', id));
			};
			
		});
