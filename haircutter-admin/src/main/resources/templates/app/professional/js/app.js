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
        .when(
          '/services', {
            templateUrl: 'professional/partials/professional-services-content.html',
            controller: 'ProfessionalServicesController'
          }
        )
        .when(
          '/services/create', {
            templateUrl: 'professional/partials/create-professional-service-content.html',
            controller: 'CreateProfessionalServiceController'
          }
        )
        .when(
          '/calendars', {
            templateUrl: 'professional/partials/calendars-content.html',
            controller: 'CalendarsController'
          }
        )
        .when(
          '/calendars/create', {
            templateUrl: 'professional/partials/create-calendar-content.html',
            controller: 'CreateCalendarController'
          }
        )
        .when(
          '/schedules', {
            templateUrl: 'professional/partials/schedules-content.html',
            controller: 'SchedulesController'
          }
        )
        .otherwise(
          {redirectTo: '/profile'}
        );
    }
  ]
);
