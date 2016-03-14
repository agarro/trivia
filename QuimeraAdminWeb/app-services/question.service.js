(function () {
    'use strict';

    angular
        .module('app')
        .factory('QuestionService', QuestionService);

    QuestionService.$inject = ['$http'];
    function QuestionService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;

        return service;

        function GetAll() {
            return $http.get('http://localhost:8080/questions/getAll').then(handleSuccess, handleError('Error getting all questions'));
        }

        function GetById(id) {
            return $http.post('http://localhost:8080/questions/getById', id).then(handleSuccess, handleError('Error getting question by id'));
        }

        function Create(question) {
            return $http.post('http://localhost:8080/questions/insert', question).then(handleSuccess, handleError('Error creating question'));
        }

        function Update(question) {
            return $http.post('http://localhost:8080/questions/update', question).then(handleSuccess, handleError('Error updating question'));
        }

        function Delete(question) {
            return $http.post('http://localhost:8080/questions/delete', question).then(handleSuccess, handleError('Error deleting question'));
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
