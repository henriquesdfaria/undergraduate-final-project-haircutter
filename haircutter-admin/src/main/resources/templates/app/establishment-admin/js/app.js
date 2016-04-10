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
                .otherwise(
                    {redirectTo: '/audit-logs'}
                );
        }
    ]
);
