'use strict';

/* App Module */

var publicApp = angular.module('publicApp', [
  'ngRoute',
  'publicControllers',
  'ui.utils.masks',
  'ngCpfCnpj'
]);

publicApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/new-establishment-request', {
        templateUrl: 'public/partials/content.html',
        controller: 'Controller'
      }).
      otherwise({
        redirectTo: '/new-establishment-request'
      });
  }]);
