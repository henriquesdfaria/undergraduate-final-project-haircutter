'use strict';

var publicControllers = angular.module('publicControllers', []);

// MENU

var manager_menu = [{
  name: 'Serviços',
  link: '/manager#/establishment/services'
}
];

var moderator_menu = [{
  name: 'Estabelecimentos',
  link: '/moderator#/establishment-creation-requests'
}
];


var professional_menu = [{
  name: 'Perfil',
  link: '/professional#/profile'
}
];

var establishment_admin_menu = [{
  name: 'Logs Auditoria',
  link: '/establishment-admin#/establishment-audit-logs'
}, {
  name: 'Perfil',
  link: '/establishment-admin#/establishment/profile'
}, {
  name: 'Funcionários',
  link: '/establishment-admin#/establishment/employees'
}
];

/* Controllers */
publicControllers.controller('Controller', ['$scope', '$http', '$location',
    function ($scope, $http, $location) {

      $scope.getLoggedUser = function () {
        $http({
            method: 'GET',
            url: '/api/public/get-logged-user',
          }
        ).success(function (data) {
            $scope.menu = [];

            if (data && data.role === 'ROLE_MANAGER') {
              $scope.menu = manager_menu;
            }

            if (data && data.role === 'ROLE_MODERATOR') {
              $scope.menu = moderator_menu;
            }

            if (data && data.role === 'ROLE_ESTABLISHMENT_ADMIN') {
              $scope.menu = establishment_admin_menu;
            }

            if (data && data.role === 'ROLE_PROFESSIONAL') {
              $scope.menu = professional_menu;
            }

            $scope.loggedUser = data

          }
        );
      }

      $scope.maxlengthDescription = 200;
      $scope.regexZipCode = '\\d{5}-\\d{3}';

      $scope.createNewRequest = function (establishment) {
        $scope.establishment = establishment;
        $scope.establishment.address.country = 'Brasil';

        $http({
            method: 'POST',
            url: '/api/public/establishment/creation-request',
            data: $scope.establishment,
            headers: {
              'Content-Type': 'application/json; charset=utf-8'
            }
          }
        ).then(function () {
            $scope.showSuccess = true;
          }, function () {
            $scope.internalError = true;
          }
        );

      };

      $scope.cancel = function () {
        $location.path('/');
      };

      $scope.getLoggedUser();
    }
  ]
);
