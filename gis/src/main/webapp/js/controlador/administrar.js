'use strict';

routerApp.controller('administrarController', function(HomeFactory){

	var vm = this;

	vm.users = [];

	HomeFactory.getUsers().then(function(data){
		console.log(data.data);
		vm.users = data.data;
	})

});