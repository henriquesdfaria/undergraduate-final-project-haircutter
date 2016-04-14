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
                        url: '/api/public/get-logged-user'
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
                        data: $scope.establishment
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
                        url: '/api/public/get-logged-user'
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
                        $scope.employees = data;
                    }
                );
            }


            $scope.getLoggedUser();
            $scope.getEmployees();
        }
    ]
);

/* CREATE ESTABLISHMENT PROFILE CONTROLLER*/
establishmentAdminControllers.controller('CreateEstablishmentEmployeeController', ['$scope', '$http', '$location',
        function ($scope, $http, $location) {

            $scope.establishmentProfileActiveMenu = 'active';

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

            $scope.create = function (employee) {
                $http({
                        method: 'POST',
                        url: '/api/establishment-admin/employee',
                        data: employee
                    }
                ).success(function () {
                        $location.path('#/establishment/employees');
                    }
                ).error(function () {
                    $scope.internalError = true;
                });
            }

            $scope.getLoggedUser();
        }
    ]
);

/* ESTABLISHMENT EMPLOYEE CONTROLLER*/
establishmentAdminControllers.controller('EstablishmentEmployeeController', ['$scope', '$routeParams', '$http', '$location',
        function ($scope, $routeParams, $http, $location) {
            $scope.establishmentEmployeesActiveMenu = 'active';

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


            $scope.getEmployee = function () {
                $http({
                    method: 'GET',
                    url: '/api/establishment-admin/employee/' + $routeParams.employeeId
                }).success(function (data) {
                    $scope.employee = data;
                });
            }

            $scope.save = function (employee) {
                $http({
                        method: 'PUT',
                        url: '/api/establishment-admin/employee',
                        data: $scope.employee
                    }
                ).success(function () {
                        $location.path('#/establishment/employees');
                    }
                ).error(function () {
                    $scope.internalError = true;
                });
            }

            $scope.getLoggedUser();
            $scope.getEmployee();
        }
    ]
);