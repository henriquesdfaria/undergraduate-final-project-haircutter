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
      otherwise({
        redirectTo: '/establishment-creation-requests'
      });
  }]);
