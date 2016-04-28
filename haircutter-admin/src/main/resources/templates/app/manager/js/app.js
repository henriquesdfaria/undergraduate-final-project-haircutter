'use strict';

/* App Module */

var managerApp = angular.module('managerApp', [
    'ngRoute',
    'managerControllers',
    'ui.utils.masks'
  ]
);

managerApp.config(['$routeProvider',
    function ($routeProvider) {
      $routeProvider
        .when('/establishment/services', {
            templateUrl: 'manager/partials/establishment-services-content.html',
            controller: 'EstablishmentServicesController'
          }
        )
        .when('/establishment/services/service/:establishmentServiceId', {
            templateUrl: 'manager/partials/establishment-service-content.html',
            controller: 'EstablishmentServiceController'
          }
        )
        .when('/establishment/services/create', {
            templateUrl: 'manager/partials/create-establishment-service-content.html',
            controller: 'CreateEstablishmentServiceController'
          }
        )
        .otherwise(
          {redirectTo: '/establishment/services'}
        );
    }
  ]
);
