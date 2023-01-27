
angular
	.module('AngularApp')
	.constant("ScoreConstants", {
		rest: {
			getAll: applicationContext + '/rest/score',
			getById: applicationContext + '/rest/score/{ID}',
			save: applicationContext + '/rest/score',
			update: applicationContext + '/rest/score',
			delete: applicationContext + '/rest/score/{ID}'
		}
	})
	.service('ScoreService',
		function ($log, $window, $http, $q, ScoreConstants) {
			
			this.getAll = () => {
				return $http.get(ScoreConstants.rest.getAll);
			};
			
			this.getById = (id) => {
				return $http.get(ScoreConstants.rest.getById.replace('{ID}', id));
			};
			
			this.save = (scoreToEdit) => {
				let score = angular.copy(scoreToEdit.score);
				
				return $http.post(ScoreConstants.rest.save, score);
			};
			
			this.update = (scoreToEdit) => {
				let score = angular.copy(scoreToEdit.score);
				
				return $http.put(ScoreConstants.rest.update, score);
			};
			
			this.delete = (id) => {
				return $http.delete(ScoreConstants.rest.delete.replace('{ID}', id));
			};
			
		});
