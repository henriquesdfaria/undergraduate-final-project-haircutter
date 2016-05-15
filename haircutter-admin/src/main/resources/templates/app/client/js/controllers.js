'use strict';

/* Controllers */

var clientControllers = angular.module('clientControllers', []);


/*  PROFILE CONTROLLER*/
clientControllers.controller('ProfileController', ['$scope', '$http',
    function ($scope, $http) {
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
            url: '/api/client/profile',
          }
        ).success(function (data) {
            $scope.user = data;
          }
        );
      }

      $scope.editProfileSettings = function (user) {
        $http({
            method: 'PUT',
            url: '/api/client/profile',
            data: user
          }
        );
      }

      $scope.deactivateUser = function () {
        $http({
            method: 'DELETE',
            url: '/api/client/deactivate'
          }
        ).success(function() {
          window.location.href='logout';
        });
      }

      $scope.getLoggedUser();
      $scope.getProfileSettings();
    }
  ]
);
