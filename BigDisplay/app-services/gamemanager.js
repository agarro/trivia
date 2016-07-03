/**
 * Created by Manu on 29/06/2016.
 */
(function () {
    'use strict';

    angular
        .module('app')
        .factory('GameManagerService', GameManagerService);

    GameManagerService.$inject = ['$http', '$rootScope'];
    function GameManagerService($http, $rootScope) {
        var service = {};
        var url = $rootScope.url;
        var idBar = $rootScope.globals.currentUser.idBar;
        service.SetCurrentQuestion = SetCurrentQuestion;
        service.SetStatus = SetStatus;
        service.SetElapsedTime = SetElapsedTime;
        //service.Delete = Delete;

        return service;

        function SetCurrentQuestion(question) {
            return $http.put(url + '/game/currentQuestion?idBar=' + idBar, question).then(handleSuccess, handleError('Error setting question'));
        }

        function SetStatus(status) {
            return $http.put(url + '/game/status?idBar=' + idBar, status).then(handleSuccess, handleError('Error setting status'));
        }

        function SetElapsedTime(elapsedTime) {
            return $http.put(url + '/game/elapsedTime?idBar=' + idBar, elapsedTime).then(handleSuccess, handleError('Error setting elapsed time'));
        }

        //
        //function Create(banner) {
        //    return $http.post(url + '/banners/insert', banner).then(handleSuccess, handleError('Error creating banner'));
        //}
        //
        //function Update(banner) {
        //    return $http.post(url + '/banners/update', banner).then(handleSuccess, handleError('Error updating banner'));
        //}
        //
        //function Delete(banner) {
        //    return $http.post(url + '/banners/delete', banner).then(handleSuccess, handleError('Error deleting banner'));
        //}

        // private functions

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
