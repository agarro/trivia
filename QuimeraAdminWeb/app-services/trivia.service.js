(function () {
    'use strict';

    angular
        .module('app')
        .factory('TriviaService', TriviaService);

    TriviaService.$inject = ['$http'];
    function TriviaService($http) {

        var url = 'http://quimera-test-env.us-west-2.elasticbeanstalk.com';
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
        service.SetCurrentTrivia = SetCurrentTrivia;
        service.GetCurrentTrivia = GetCurrentTrivia;

        return service;

        function GetAll() {


            return $http.get(url + '/trivia/getAll').then(handleSuccess, handleError('Error getting all trivia'));
        }

        function GetCurrentQuestion() {
            return $http.get(url + '/trivia/getCurrentQuestion').then(handleSuccess, handleError('Error getting current question'));
        }

        function StartTrivia() {
            return $http.get(url + '/trivia/start').then(handleSuccess, handleError('Error starting trivia'));
        }

        function StopTrivia() {
            return $http.get(url + '/trivia/stop').then(handleSuccess, handleError('Error stopping trivia'));
        }

        function GetStatusTrivia() {
            return $http.get(url + '/trivia/getStatus').then(handleSuccess, handleError('Error getting status trivia'));
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

        function SetCurrentTrivia(id) {
            return $http.post(url + '/trivia/setCurrentTrivia', id).then(successCallback, handleError('Error setting trivia by id'));
        }

        function GetCurrentTrivia() {
            return $http.get(url + '/trivia/getCurrentTrivia').then(successCallback, handleError('Error getting current trivia'));
        }

        // private functions

        function successCallback(response) {
            return response;
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
