'use strict';

/* Controllers */

var moderatorControllers = angular.module('moderatorControllers', []);

moderatorControllers.controller('Controller', ['$scope', '$http', '$location',
  function($scope, $http, $location) {

    // GET LOGGED USER
    $scope.getLoggedUser = function () {
      $http({
        method: 'GET',
        url: '/api/public/get-logged-user',
      }).success(function(data) {
        $scope.loggedUser = data;
      });
    }

    // GET THE ESTABLISHMENT CREATION REQUESTS
    $scope.getCreationRequests = function () {
      $http({
        method: 'GET',
        url: '/api/moderator/establishment/creation-requests',
        data: $scope.establishmentCreationRequests,
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        }
      }).success(function(data) {
        $scope.establishmentCreationRequests = data;
      });
    }

    // APPROVE CREATION REQUESTS
    $scope.approveCreation = function (cnpj) {

      $http({
        method: 'PUT',
        url: '/api/moderator/establishment/creation-request/approve/' + cnpj,
      }).success(function() {
        $scope.getCreationRequests();
      });

    }

    // DENY CREATION REQUEST
    $scope.denyECR = function (ecr) {
      $http({
        method: 'PUT',
        url: 'http://localhost:8050/api/moderator/establishment/creation-request/deny',
        data: ecr
      }).success(function() {
        $scope.getCreationRequests();
        window.location.reload();
      });
    }

    // GET THE SELECTED ESTABLISHMENT CREATION REQUEST
    $scope.getSelectedECR = function(ecr) {
      $scope.selectedECR = ecr;
    }

    $scope.getLoggedUser();
    $scope.getCreationRequests();
  }
]);
