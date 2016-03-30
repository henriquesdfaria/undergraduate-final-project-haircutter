'use strict';

var publicControllers = angular.module('publicControllers', []);

// MENU
var moderator_menu = [{
  name : 'Estabelecimentos',
  link : '/moderator#/establishment-creation-requests'
}];

var establishment_menu = [{
  name : 'Logs Auditoria',
  link : '/establishment-admin#/establishment-audit-logs'
}];

/* Controllers */
publicControllers.controller('Controller', ['$scope', '$http', '$location',
  function($scope, $http, $location) {

    $scope.getLoggedUser = function() {
      $http({
        method: 'GET',
        url: '/api/public/get-logged-user',
      }).success(function(data) {
        $scope.menu = [];

        if (data.userRoles && data.userRoles[0].role === 'ROLE_MODERATOR') {
          $scope.menu = moderator_menu;
        }

        if (data.userRoles && data.userRoles[0].role === 'ROLE_ESTABLISHMENT_ADMIN') {
          $scope.menu = establishment_menu;
        }

        $scope.loggedUser = data

      });
    }

    $scope.maxlengthDescription = 200;
    $scope.regexZipCode = '\\d{5}-\\d{3}';

    $scope.createNewRequest = function(establishment) {
      $scope.establishment = establishment;
      $scope.establishment.address.country = 'Brasil';

      $http({
        method: 'POST',
        url: '/api/public/new-establishment-creation-request',
        data: $scope.establishment,
        headers: {
          'Content-Type': 'application/json; charset=utf-8'
        }
      }).then(function(data) {
        $scope.showSuccess = true;
      }, function() {
        $scope.internalError = true;
      });

    };

    $scope.cancel = function() {
      $location.path('/');
    };

    $scope.getLoggedUser();
  }
]);
