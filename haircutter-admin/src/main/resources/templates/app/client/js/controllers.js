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
        ).success(function () {
            window.location.href = 'logout';
          }
        );
      }

      $scope.getLoggedUser();
      $scope.getProfileSettings();
    }
  ]
);

/*  SCHEDULES CONTROLLER*/
clientControllers.controller('SchedulesController', ['$scope', '$http',
    function ($scope, $http) {
      $scope.schedulesActiveMenu = 'active';

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

      $scope.getSchedules = function () {
        $http({
            method: 'GET',
            url: '/api/client/schedules',
          }
        ).success(function (data) {
            $scope.schedules = data;
            _.forEach($scope.schedules, function (schedule) {
                var time = moment({hour: 0, minute: 0});
                time.add(schedule.scheduleInMinutes, "minutes");
                schedule.durationTitle = (time.hours() !== 0 ? time.hours() + 'H' : '') +
                  (time.minutes() !== 0 ? time.minutes() + 'M' : '');

                schedule.scheduleDateString = new moment(schedule.scheduleDate).format('DD/MM/YYYY') + ' '
                  + schedule.durationTitle;
              }
            );
          }
        );
      }

      $scope.cancel = function (schedule) {
        $http({
            method: 'DELETE',
            url: '/api/client/schedule/' + schedule.id
          }
        ).success(function () {
            $scope.getSchedules();
            window.location.reload();
          }
        );
      }

      $scope.getLoggedUser();
      $scope.getSchedules();
    }
  ]
);

clientControllers.controller('EstablishmentEvaluationController', ['$scope', '$http', '$routeParams',
    function ($scope, $http, $routeParams) {
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
      };


      $scope.evaluate = function (establishmentEvaluation) {

        establishmentEvaluation.establishmentCnpj = $routeParams.cnpj;

        $http({
            method: 'POST',
            url: '/api/client/establishment/evaluate',
            data: establishmentEvaluation
          }
        ).then(function () {
          window.location = "/#/establishment/" + $routeParams.cnpj;
        }, function () {
          window.location = "/#/establishment/" + $routeParams.cnpj;
        });
      };


      $scope.getLoggedUser();
    }
  ]
);


clientControllers.controller('ComplaintController', ['$scope', '$http', '$routeParams',
    function ($scope, $http, $routeParams) {
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
      };


      $scope.complain = function (complaint) {

        complaint.establishmentCnpj = $routeParams.cnpj;

        $http({
            method: 'POST',
            url: '/api/client/complaint',
            data: complaint
          }
        ).then(function () {
          window.location = "/#/establishment/" + $routeParams.cnpj;
        }, function () {
          window.location = "/#/establishment/" + $routeParams.cnpj;
        });
      };


      $scope.getLoggedUser();
    }
  ]
);

