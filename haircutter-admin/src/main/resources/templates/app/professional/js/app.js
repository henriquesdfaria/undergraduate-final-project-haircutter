'use strict';

/* App Module */

var professionalApp = angular.module('professionalApp', [
    'ngRoute',
    'professionalControllers'
  ]
);

professionalApp.config(['$routeProvider',
    function ($routeProvider) {
      $routeProvider
        .when(
          '/profile', {
            templateUrl: 'professional/partials/profile-content.html',
            controller: 'ProfessionalProfileController'
          }
        )
        .otherwise(
          {redirectTo: '/profile'}
        );
    }
  ]
);
