(function () {
    'use strict';

    angular
        .module('app')
        .factory('TriviaService', TriviaService);

    TriviaService.$inject = ['$http'];
    function TriviaService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;
        service.StopTrivia = StopTrivia;
        service.StartTrivia = StartTrivia;
        service.GetStatusTrivia = GetStatusTrivia;
        service.GetCurrentQuestion = GetCurrentQuestion;

        return service;

        function GetAll() {
            return $http.get('http://localhost:8080/trivia/getAll').then(handleSuccess, handleError('Error getting all trivia'));
        }

        function GetCurrentQuestion() {
            return $http.get('http://localhost:8080/trivia/getCurrentQuestion').then(handleSuccess, handleError('Error getting current question'));
        }

        function StartTrivia() {
            return $http.get('http://localhost:8080/trivia/start').then(handleSuccess, handleError('Error starting trivia'));
        }

        function StopTrivia() {
            return $http.get('http://localhost:8080/trivia/stop').then(handleSuccess, handleError('Error stopping trivia'));
        }

        function GetStatusTrivia() {
            return $http.get('http://localhost:8080/trivia/getStatus').then(handleSuccess, handleError('Error getting status trivia'));
        }

        function GetById(id) {
            return $http.post('http://localhost:8080/trivia/getById', id).then(handleSuccess, handleError('Error getting trivia by id'));
        }

        function Create(trivia) {
            return $http.post('http://localhost:8080/trivia/insert', trivia).then(handleSuccess, handleError('Error creating trivia'));
        }

        function Update(trivia) {
            return $http.post('http://localhost:8080/trivia/update', trivia).then(handleSuccess, handleError('Error updating trivia'));
        }

        function Delete(trivia) {
            return $http.post('http://localhost:8080/trivia/delete', trivia).then(handleSuccess, handleError('Error deleting trivia'));
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
