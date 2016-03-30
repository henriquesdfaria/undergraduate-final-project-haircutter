'use strict';

/* Controllers */

var establishmentAdminControllers = angular.module('establishmentAdminControllers', []);

establishmentAdminControllers.controller('Controller', ['$scope', '$http', '$location',
  function($scope, $http, $location) {

    $scope.getLoggedUser = function () {
      $http({
        method: 'GET',
        url: '/api/public/get-logged-user',
      }).success(function(data) {
        $scope.loggedUser = data;
      });
    }

    $scope.getAuditLogs = function (cnpj) {
      $http({
        method: 'GET',
        url: '/api/establishment-admin/establishment-audit-logs',
      }).success(function(data) {
        $scope.auditLogs = data;
      });
    }

    $scope.getLoggedUser();
    $scope.getAuditLogs();
  }
]);
