'use strict';

/* App Module */

var clientApp = angular.module('clientApp', [
        'ngRoute',
        'clientControllers'
    ]
);

clientApp.config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                .when('/profile', {
                        templateUrl: 'client/partials/profile-content.html',
                        controller: 'ProfileController'
                    }
                ).otherwise(
                    {redirectTo: '/profile'}
                );
        }
    ]
);
