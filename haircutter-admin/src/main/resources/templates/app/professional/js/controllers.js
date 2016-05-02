'use strict';

/* Controllers */

var professionalControllers = angular.module('professionalControllers', []);

/* ESTABLISHMENT PROFESSIONAL PROFILE CONTROLLER*/
professionalControllers.controller('ProfessionalProfileController', ['$scope', '$http',
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
        );
      }

      $scope.getLoggedUser();
      $scope.getProfileSettings();
    }
  ]
);
