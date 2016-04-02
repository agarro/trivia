(function () {
    'use strict';

    angular
        .module('app')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};
        var url = 'http://quimera-test-env.us-west-2.elasticbeanstalk.com';
        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;

        return service;

        function GetAll() {
            return $http.get(url + '/users/getAll').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.post(url + '/users/getById', id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByUsername(username) {
            return $http.post(url + '/users/getByUsername', username).then(handleSuccess, handleError('Error getting user by username'));
        }

        function Create(user) {
            return $http.post(url + '/users/insert', user).then(handleSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            return $http.post(url + '/users/update', user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(user) {
            return $http.post(url + '/users/delete', user).then(handleSuccess, handleError('Error deleting user'));
        }

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }

})();
