var routerApp = angular.module('routerApp', ['ui.router']);

routerApp.config(function($stateProvider, $urlRouterProvider) {
    
    $urlRouterProvider.otherwise('/mapas');
    
    $stateProvider
        
        // HOME STATES AND NESTED VIEWS ========================================
        .state('mapas', {
            url: '/mapas',
            templateUrl: 'vistas/mapas.html'
        })
        
        // nested list with custom controller
        .state('datasets', {
            url: '/datasets',
            templateUrl: 'vistas/datasets.html',
            controller: function($scope) {
                $scope.dogs = ['Bernese', 'Husky', 'Goldendoodle'];
            }
        })
        
        // nested list with just some random string data
        .state('verperfil', {
        	url: "/verperfil",
            templateUrl: "vistas/verperfil.html",
            
        })
        
          .state('administrar', {
        	url: "/administrar",
            templateUrl: "vistas/administrar.html",
        })
        
        .state('administrar.verEliminarUsuarios', {
        	url: "/verEliminarUsuarios",
            templateUrl: "vistas/verEliminarUsuarios.html",
          
        })
        
          .state('administrar.anadirUsuarios', {
        	url: "/anadirUsuarios",
            templateUrl: "vistas/anadirUsuarios.html",
           
        })
        
           .state('administrar.configuracionOrganizacion', {
        	url: "/configuracionOrganizacion",
            templateUrl: "vistas/configuracionOrganizacion.html",
            controller: function($scope) {
                $scope.titulo = 'Administracion general de la organizacion';
            }
            
        })
        
        // ABOUT PAGE AND MULTIPLE NAMED VIEWS =================================
        .state('about', {
            url: '/about',
            views: {
                '': { templateUrl: 'partial-about.html' },
                'columnOne@about': { template: 'Look I am a column!' },
                'columnTwo@about': { 
                    templateUrl: 'table-data.html',
                    controller: 'scotchController'
                }
            }
            
        });
        
});

routerApp.controller('scotchController', function($scope) {
    
    $scope.message = 'test';
   
    $scope.scotches = [
        {
            name: 'Macallan 12',
            price: 50
        },
        {
            name: 'Chivas Regal Royal Salute',
            price: 10000
        },
        {
            name: 'Glenfiddich 1937',
            price: 20000
        }
    ];
    
});