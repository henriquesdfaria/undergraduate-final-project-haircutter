'use strict';

/* Controllers */

var establishmentAdminControllers = angular.module('establishmentAdminControllers', []);

/* AUDIT LOGS CONTROLLER*/
establishmentAdminControllers.controller('AuditLogsController', ['$scope', '$http',
        function ($scope, $http) {
            $scope.auditLogsActiveMenu = 'active';

            $scope.getLoggedUser = function () {
                $http({
                        method: 'GET',
                        url: '/api/public/get-logged-user',
                    }
                ).success(function (data) {
                        $scope.loggedUser = data;
                    }
                );
            }

            $scope.getAuditLogs = function () {
                $http({
                        method: 'GET',
                        url: '/api/establishment-admin/establishment-audit-log/audit-logs',
                    }
                ).success(function (data) {
                        $scope.auditLogs = data;
                        _.forEach($scope.auditLogs, function (auditLog) {
                                auditLog.date = new moment(auditLog.date).format('DD/MM/YYYY HH:mm');
                            }
                        );
                    }
                );
            }

            $scope.getLoggedUser();
            $scope.getAuditLogs();
        }
    ]
);

/* ESTABLISHMENT PROFILE CONTROLLER*/
establishmentAdminControllers.controller('EstablishmentProfileController', ['$scope', '$http',
        function ($scope, $http) {
            $scope.establishmentProfileActiveMenu = 'active';

            $scope.getLoggedUser = function () {
                $http({
                        method: 'GET',
                        url: '/api/public/get-logged-user',
                    }
                ).success(function (data) {
                        $scope.loggedUser = data;
                    }
                );
            }

            $scope.getProfileSettings = function () {
                $http({
                        method: 'GET',
                        url: '/api/establishment-admin/establishment/profile',
                    }
                ).success(function (data) {
                        $scope.establishment = data;
                    }
                );
            }

            $scope.editProfileSettings = function () {

                var est = $scope.establishment;

                $http({
                        method: 'PUT',
                        url: '/api/establishment-admin/establishment/profile',
                        data: $scope.establishment,
                    }
                )
            }

            $scope.getLoggedUser();
            $scope.getProfileSettings();
        }
    ]
);

/* ESTABLISHMENT EMPLOYEES CONTROLLER*/
establishmentAdminControllers.controller('EstablishmentEmployeesController', ['$scope', '$http',
        function ($scope, $http) {
            $scope.establishmentEmployeesActiveMenu = 'active';

            $scope.getLoggedUser = function () {
                $http({
                        method: 'GET',
                        url: '/api/public/get-logged-user',
                    }
                ).success(function (data) {
                        $scope.loggedUser = data;
                    }
                );
            }

            $scope.getEmployees = function () {
                $http({
                        method: 'GET',
                        url: '/api/establishment-admin/employees',
                    }
                ).success(function (data) {
                        $scope.establishment = data;
                    }
                );
            }


            $scope.getLoggedUser();
            $scope.getEmployees();
        }
    ]
);

/* ESTABLISHMENT EMPLOYEE CONTROLLER*/
establishmentAdminControllers.controller('EstablishmentEmployeeController', ['$scope', '$routeParams', '$http',
        function ($scope, $routeParams, $http) {
            $scope.establishmentEmployeesActiveMenu = 'active';

            $scope.getLoggedUser = function () {
                $http({
                        method: 'GET',
                        url: '/api/public/get-logged-user',
                    }
                ).success(function (data) {
                        $scope.loggedUser = data;
                    }
                );
            }


            $scope.getLoggedUser();
        }
    ]
);