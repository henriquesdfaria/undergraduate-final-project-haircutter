'use strict';

/* Controllers */

var professionalControllers = angular.module('professionalControllers', []);

/* ESTABLISHMENT PROFESSIONAL PROFILE CONTROLLER*/
professionalControllers.controller('ProfessionalProfileController', ['$scope', '$http', '$location',
    function ($scope, $http, $location) {
      $scope.profileActiveMenu = 'active';

      $scope.getLoggedUser = function () {
        $http({
            method: 'GET',
            url: '/api/public/get-logged-user'
          }
        ).success(function (data) {
            $scope.loggedUser = data;
          }
        );
      }

      $scope.getProfileSettings = function () {
        $http({
            method: 'GET',
            url: '/api/professional/profile',
          }
        ).success(function (data) {
            $scope.professional = data;
          }
        );
      }

      $scope.save = function (professional) {
        $http({
            method: 'PUT',
            url: '/api/professional/profile',
            data: professional
          }
        ).success(function () {
            $scope.getLoggedUser();
            $location.path('/profile');
          }
        );
      }

      $scope.getLoggedUser();
      $scope.getProfileSettings();
    }
  ]
);

/* PROFESSIONAL SERVICES CONTROLLER*/
professionalControllers.controller('ProfessionalServicesController', ['$scope', '$http', '$location',
    function ($scope, $http, $location) {
      $scope.servicesActiveMenu = 'active';

      $scope.getLoggedUser = function () {
        $http({
            method: 'GET',
            url: '/api/public/get-logged-user'
          }
        ).success(function (data) {
            $scope.loggedUser = data;
          }
        );
      }

      $scope.getServices = function () {
        $http({
            method: 'GET',
            url: '/api/professional/services',
          }
        ).success(function (data) {
            $scope.professionalServices = data;
          }
        );
      }

      $scope.removeService = function (service) {
        $http({
            method: 'DELETE',
            url: '/api/professional/service/' + service.id
          }
        ).success(function () {
            $scope.getServices();
            $location.path('/services');
          }
        );
      }

      $scope.getLoggedUser();
      $scope.getServices();
    }
  ]
);

/* CREATE PROFESSIONAL SERVICE CONTROLLER*/
professionalControllers.controller('CreateProfessionalServiceController', ['$scope', '$http', '$location',
    function ($scope, $http, $location) {

      $scope.servicesActiveMenu = 'active';

      $scope.getLoggedUser = function () {
        $http({
            method: 'GET',
            url: '/api/public/get-logged-user'
          }
        ).success(function (data) {
            $scope.loggedUser = data;
          }
        );
      }

      $scope.getEstablishmentServices = function () {
        $http({
            method: 'GET',
            url: '/api/professional/establishment/services',
          }
        ).success(function (data) {
            $scope.establishmentServices = data;
          }
        );
      }

      $scope.create = function (service) {

        $http({
            method: 'POST',
            url: '/api/professional/service',
            data: service
          }
        ).success(function () {
            $location.path('/services');
          }
        ).error(function () {
            $scope.internalError = true;
          }
        );
      }

      $scope.getLoggedUser();
      $scope.getEstablishmentServices();
    }
  ]
);

/* CALENDARS CONTROLLER*/
professionalControllers.controller('CalendarsController', ['$scope', '$http', '$location',
    function ($scope, $http, $location) {
      $scope.calendarsActiveMenu = 'active';

      $scope.getLoggedUser = function () {
        $http({
            method: 'GET',
            url: '/api/public/get-logged-user'
          }
        ).success(function (data) {
            $scope.loggedUser = data;
          }
        );
      }

      $scope.getCalendars = function () {
        $http({
            method: 'GET',
            url: '/api/professional/calendars',
          }
        ).success(function (data) {
            $scope.calendars = data;

            _.forEach($scope.calendars, function (calendar) {
                var time = moment({hour: 0, minute: 0});
                time.add(calendar.scheduleInMinutes, "minutes");
                calendar.schedule = time.format('HH:mm');
              }
            );
          }
        );
      }

      $scope.removeCalendar = function (calendar) {
        $http({
            method: 'DELETE',
            url: '/api/professional/calendar/' + calendar.id
          }
        ).success(function () {
            $scope.getCalendars();
            $location.path('/calendars');
          }
        );
      }

      $scope.getLoggedUser();
      $scope.getCalendars();
    }
  ]
);

/* CREATE CALENDAR CONTROLLER*/
professionalControllers.controller('CreateCalendarController', ['$scope', '$http', '$location',
    function ($scope, $http, $location) {
      
      $scope.calendarsActiveMenu = 'active';

      $scope.getLoggedUser = function () {
        $http({
            method: 'GET',
            url: '/api/public/get-logged-user'
          }
        ).success(function (data) {
            $scope.loggedUser = data;
          }
        );
      }

      $scope.createRange = function (calendars) {

        $http({
            method: 'POST',
            url: '/api/professional/calendars',
            data: calendars
          }
        ).success(function () {
            $location.path('/calendars');
          }
        ).error(function () {
            $scope.internalError = true;
          }
        );
      }

      $scope.getLoggedUser();
    }
  ]
);