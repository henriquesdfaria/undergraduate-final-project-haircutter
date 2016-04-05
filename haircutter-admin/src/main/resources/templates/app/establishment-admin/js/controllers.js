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

    $scope.getAuditLogs = function () {
      $http({
        method: 'GET',
        url: '/api/establishment-admin/establishment-audit-log/audit-logs',
      }).success(function(data) {
        $scope.auditLogs = data;
        _.forEach($scope.auditLogs,function(auditLog) {
            auditLog.date = new moment(auditLog.date).format('DD/MM/YYYY hh:mm');
        });
      });
    }

    $scope.getLoggedUser();
    $scope.getAuditLogs();
  }
]);
