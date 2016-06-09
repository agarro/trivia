(function () {
    'use strict';

    angular
        .module('app')
        .factory('BannerService', BannerService);

    BannerService.$inject = ['$http', '$rootScope'];
    function BannerService($http, $rootScope) {
        var service = {};
        var url = $rootScope.url;

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;

        return service;

        function GetAll() {
            return $http.get(url + '/banners/getAll').then(handleSuccess, handleError('Error getting all banners'));
        }

        function GetById(id) {
            return $http.post(url + '/banners/getById', id).then(handleSuccess, handleError('Error getting banner by id'));
        }

        function Create(banner) {
            return $http.post(url + '/banners/insert', banner).then(handleSuccess, handleError('Error creating banner'));
        }

        function Update(banner) {
            return $http.post(url + '/banners/update', banner).then(handleSuccess, handleError('Error updating banner'));
        }

        function Delete(banner) {
            return $http.post(url + '/banners/delete', banner).then(handleSuccess, handleError('Error deleting banner'));
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
