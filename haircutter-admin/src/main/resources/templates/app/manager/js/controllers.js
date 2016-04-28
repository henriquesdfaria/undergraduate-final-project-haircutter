'use strict';

/* Controllers */

var managerControllers = angular.module('managerControllers', []);


/* ESTABLISHMENT SERVICES CONTROLLER*/
managerControllers.controller('EstablishmentServicesController', ['$scope', '$http', '$location',
    function ($scope, $http, $location) {
      $scope.establishmentServicesActiveMenu = 'active';

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
            url: '/api/manager/establishment/services',
          }
        ).success(function (data) {
            $scope.establishmentServices = data;
          }
        );
      }

      $scope.removeService = function (service) {
        $http({
            method: 'DELETE',
            url: '/api/manager/establishment/service/' + service.id
          }
        ).success(function () {
            $scope.getServices();
            $location.path('/establishment/services');
          }
        );
      }

      $scope.getLoggedUser();
      $scope.getServices();
    }
  ]
);

/* CREATE ESTABLISHMENT SERVICE CONTROLLER*/
managerControllers.controller('CreateEstablishmentServiceController', ['$scope', '$http', '$location',
    function ($scope, $http, $location) {

      $scope.establishmentServicesActiveMenu = 'active';

      $scope.maxlengthDescription = 200;

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

      $scope.create = function (service) {

        service.price = parseFloat(service.price);
        $http({
            method: 'POST',
            url: '/api/manager/establishment/service',
            data: service
          }
        ).success(function () {
            $location.path('/establishment/services');
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

/* ESTABLISHMENT EMPLOYEE CONTROLLER*/
managerControllers.controller('EstablishmentServiceController',
  ['$scope', '$routeParams', '$http', '$location',
    function ($scope, $routeParams, $http, $location) {
      $scope.establishmentServicesActiveMenu = 'active';

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


      $scope.getService = function () {
        $http({
            method: 'GET',
            url: '/api/manager/establishment/service/' + $routeParams.establishmentServiceId
          }
        ).success(function (data) {
            $scope.establishmentService = data;
          }
        );
      }

      $scope.save = function (service) {
        $http({
            method: 'PUT',
            url: '/api/manager/establishment/service',
            data: service
          }
        ).success(function () {
            $location.path('/establishment/service');
          }
        ).error(function () {
            $scope.internalError = true;
          }
        );
      }

      $scope.getLoggedUser();
      $scope.getService();
    }
  ]
);