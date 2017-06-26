'use strict';

routerApp.factory('HomeFactory', function($http){

	var url = 'http://jsonplaceholder.typicode.com/users';
	var path = 'https://forshare-api.herokuapp.com/api/signup';

	var HomeFactory = {

		getUsers: function(){
			return $http.get(url).success(function(data){
				return data;
			})
			.error(function(err){
				console.log(err);
			})
		},

		createUser: function(user){

			return $http.post(path, user).success(function(data){
				return data;
			})
			.error(function(err){
				console.log(err)
			})


		}

	};

	return HomeFactory

});