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

      $scope.getEsblishmentServices = function () {
        $http({
            method: 'GET',
            url: '/api/professional/establishment/services',
          }
        ).success(function (data) {
            $scope.establishmentServices = data;

            _.forEach($scope.establishmentServices, function (service) {
                var time = moment({hour: 0, minute: 0});
                time.add(service.duration * 30, "minutes");
                service.durationTitle = (time.hours() !== 0 ? time.hours() + 'H' : '') +
                  (time.minutes() !== 0 ? time.minutes() + 'M' : '');
              }
            );
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
      $scope.getEsblishmentServices();
    }
  ]
);
