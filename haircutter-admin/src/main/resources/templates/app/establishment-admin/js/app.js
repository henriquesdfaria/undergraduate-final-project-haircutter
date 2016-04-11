'use strict';

/* App Module */

var establishmentAdminApp = angular.module('establishmentAdminApp', [
        'ngRoute',
        'establishmentAdminControllers'
    ]
);

establishmentAdminApp.config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                .when(
                    '/audit-logs', {
                        templateUrl: 'establishment-admin/partials/audit-logs-content.html',
                        controller: 'AuditLogsController'
                    }
                )
                .when('/establishment/profile', {
                        templateUrl: 'establishment-admin/partials/establishment-profile-content.html',
                        controller: 'EstablishmentProfileController'
                    }
                )
                .when('/establishment/employees', {
                        templateUrl: 'establishment-admin/partials/establishment-employees-content.html',
                        controller: 'EstablishmentEmployeesController'
                    }
                )
                .when('/establishment/employees/employee', {
                        templateUrl: 'establishment-admin/partials/establishment-employee-content.html',
                        controller: 'EstablishmentEmployeeController'
                    }
                )
                .otherwise(
                    {redirectTo: '/audit-logs'}
                );
        }
    ]
);
