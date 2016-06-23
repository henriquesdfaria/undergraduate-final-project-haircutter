'use strict';

/* App Module */

var clientApp = angular.module('clientApp', [
    'ngRoute',
    'clientControllers',
    'ui.utils.masks',
    'ngCpfCnpj'
  ]
);

clientApp.config(['$routeProvider',
    function ($routeProvider) {
      $routeProvider
        .when('/profile', {
            templateUrl: 'client/partials/profile-content.html',
            controller: 'ProfileController'
          }
        )
        .when('/schedules', {
          templateUrl: 'client/partials/schedules-content.html',
          controller: 'SchedulesController'
        })
        .when('/establishment-evaluation/:cnpj', {
          templateUrl: 'client/partials/evaluation-content.html',
          controller: 'EstablishmentEvaluationController'
        })
        .otherwise(
          {redirectTo: '/profile'}
        );
    }
  ]
);
