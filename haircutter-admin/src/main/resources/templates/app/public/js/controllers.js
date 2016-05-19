'use strict';

var publicControllers = angular.module('publicControllers', []);

// MENU

var client_menu = [{
  name: 'Perfil',
  link: '/client#/profile'
}, {
  name: 'Agendamentos',
  link: '/client#/schedules'
}
];

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
}, {
  name: 'Serviços',
  link: '/professional#/services'
}, {
  name: 'Calendário',
  link: '/professional#/calendars'
}, {
  name: 'Agendamentos',
  link: '/professional#/schedules'
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

/* Home Controller */
publicControllers.controller('HomeController', ['$scope', '$http', '$location',
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

            if (data && data.role === 'ROLE_CLIENT') {
              $scope.menu = client_menu;
            }

            $scope.loggedUser = data

          }
        );
      };

      $scope.search = function (city, searchValue) {
        if (searchValue == undefined && city != undefined) {
          $location.path('/search/' + city);
        } else if (city == undefined) {
          $location.path('/');
        } else {
          $location.path('/search/' + city + '/' + searchValue);
        }
      };


      $scope.getLoggedUser();
    }
  ]
);

/* Search Controller */
publicControllers.controller('SearchController', ['$scope', '$http', '$location', '$routeParams',
    function ($scope, $http, $location, $routeParams) {

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

            if (data && data.role === 'ROLE_CLIENT') {
              $scope.menu = client_menu;
            }

            $scope.loggedUser = data

          }
        );
      };

      $scope.search = function () {
        $http({
            method: 'GET',
            url: '/api/public/establishments-query/search?city=' + $routeParams.city,
          }
        ).success(function (data) {
            $scope.establishments = data;
            $scope.city = $routeParams.city;
            $scope.filterSearchValue = $scope.searchValue = $routeParams.searchValue;
          }
        );
      };

      $scope.searchButton = function (city, searchValue) {
        if (searchValue == undefined && city != undefined) {
          $location.path('/search/' + city);
        } else if (city == undefined) {
          $location.path('/');
        } else {
          $location.path('/search/' + city + '/' + searchValue);
        }
      };


      $scope.getLoggedUser();
      $scope.search();
    }
  ]
);

/* Register Controller */
publicControllers.controller('RegisterController', ['$scope', '$http', '$location',
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

            if (data && data.role === 'ROLE_CLIENT') {
              $scope.menu = client_menu;
            }

            $scope.loggedUser = data

          }
        );
      }


      $scope.register = function (user) {

        $http({
            method: 'POST',
            url: '/api/public/register',
            data: user,
            headers: {
              'Content-Type': 'application/json; charset=utf-8'
            }
          }
        ).then(function () {
            $location.path('/');
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

/* Establishment Controller */
publicControllers.controller('EstablishmentController', ['$scope', '$http', '$location', '$routeParams',
    function ($scope, $http, $location, $routeParams) {

      $scope.getLoggedUser = function () {
        $http({
            method: 'GET',
            url: '/api/public/get-logged-user',
          }
        ).success(function (data) {
            $scope.menu = [];

            if (data && data.role === 'ROLE_MANAGER') {
              $scope.menu = manager_menu;
              $scope.roleUrlPath = 'manager';
            }

            if (data && data.role === 'ROLE_MODERATOR') {
              $scope.menu = moderator_menu;
              $scope.roleUrlPath = 'moderator';
            }

            if (data && data.role === 'ROLE_ESTABLISHMENT_ADMIN') {
              $scope.menu = establishment_admin_menu;
              $scope.roleUrlPath = 'establishment-admin';
            }

            if (data && data.role === 'ROLE_PROFESSIONAL') {
              $scope.menu = professional_menu;
              $scope.roleUrlPath = 'professional';
            }

            if (data && data.role === 'ROLE_CLIENT') {
              $scope.menu = client_menu;
              $scope.roleUrlPath = 'client';
            }

            $scope.loggedUser = data

          }
        );
      };

      $scope.getEstablishment = function () {
        $http({
            method: 'GET',
            url: '/api/public/establishments-query/establishment/' + $routeParams.cnpj,
          }
        ).success(function (data) {
            $scope.establishment = data;
          }
        );
      };

      $scope.saveSchedule = function (schedule) {
        
        schedule.scheduleDate = moment(schedule.date, 'DD/MM/YYYY').toDate();
        
        $http({
            method: 'POST',
            url: '/api/' + $scope.roleUrlPath + '/schedule',
            data: schedule
          }
        ).success(function (data) {
            $location.path('/');
          }
        );
      };


      $scope.getLoggedUser();
      $scope.getEstablishment();
    }
  ]
);
