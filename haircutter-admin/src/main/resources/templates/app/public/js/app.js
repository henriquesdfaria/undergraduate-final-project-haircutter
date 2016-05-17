'use strict';

/* App Module */

var publicApp = angular.module('publicApp', [
    'ngRoute',
    'publicControllers',
    'ui.utils.masks',
    'ngCpfCnpj'
  ]
);

publicApp.config(['$routeProvider',
    function ($routeProvider) {
      $routeProvider.when('/home', {
          templateUrl: 'public/partials/home-content.html',
          controller: 'HomeController'
        }
      ).when('/new-establishment-request', {
          templateUrl: 'public/partials/content.html',
          controller: 'Controller'
        }
      ).when('/register', {
          templateUrl: 'public/partials/register-content.html',
          controller: 'RegisterController'
        }
      ).when('/search/:city/:searchValue', {
          templateUrl: 'public/partials/search-content.html',
          controller: 'SearchController'
        }
      ).when('/search/:city', {
          templateUrl: 'public/partials/search-content.html',
          controller: 'SearchController'
        }
      ).otherwise({
          redirectTo: '/home'
        }
      );
    }
  ]
);
