'use strict';

/* App Module */

var moderatorApp = angular.module('moderatorApp', [
  'ngRoute',
  'moderatorControllers'
]);

moderatorApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/establishment-creation-requests', {
        templateUrl: 'moderator/partials/content.html',
        controller: 'Controller'
      }).
    when('/complaints', {
      templateUrl: 'moderator/partials/complaints-content.html',
      controller: 'ComplaintsController'
    }).
      otherwise({
        redirectTo: '/establishment-creation-requests'
      });
  }]);
