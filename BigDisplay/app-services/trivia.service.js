(function () {
    'use strict';

    angular
        .module('app')
        .factory('TriviaService', TriviaService);

    TriviaService.$inject = ['$http', '$rootScope'];
    function TriviaService($http, $rootScope) {

        var url = $rootScope.url;
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;
        return service;

        function GetAll() {
            return $http.get(url + '/trivia/getAll').then(handleSuccess, handleError('Error getting all trivia'));
        }

        function GetById(id) {
            return $http.post(url + '/trivia/getById', id).then(handleSuccess, handleError('Error getting trivia by id'));
        }

        function Create(trivia) {
            return $http.post(url + '/trivia/insert', trivia).then(handleSuccess, handleError('Error creating trivia'));
        }

        function Update(trivia) {
            return $http.post(url + '/trivia/update', trivia).then(handleSuccess, handleError('Error updating trivia'));
        }

        function Delete(trivia) {
            return $http.post(url + '/trivia/delete', trivia).then(handleSuccess, handleError('Error deleting trivia'));
        }

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return {success: false, message: error};
            };
        }
    }

})();
