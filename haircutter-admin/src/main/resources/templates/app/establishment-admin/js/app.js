'use strict';

/* App Module */

var establishmentAdminApp = angular.module('establishmentAdminApp', [
  'ngRoute',
  'establishmentAdminControllers'
]);

establishmentAdminApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/audit-logs', {
        templateUrl: 'establishment-admin/partials/content.html',
        controller: 'Controller'
      }).
      otherwise({
        redirectTo: '/audit-logs'
      });
  }]);
