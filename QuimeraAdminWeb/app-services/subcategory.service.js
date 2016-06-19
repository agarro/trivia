(function () {
    'use strict';

    angular
        .module('app')
        .factory('SubcategoryService', SubcategoryService);

    SubcategoryService.$inject = ['$http', '$rootScope'];
    function SubcategoryService($http, $rootScope) {
        var service = {};
        var url = $rootScope.url;
        service.GetAll = GetAll;
        service.GetById = GetById;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;

        return service;

        function GetAll() {
            return $http.get(url + '/subcategory/getAll').then(handleSuccess, handleError('Error getting all subcategories'));
        }

        function GetById(id) {
            return $http.post(url + '/subcategory/getById', id).then(handleSuccess, handleError('Error getting subcategory by id'));
        }

        function Create(subcategory) {
            return $http.post(url + '/subcategory/insert', subcategory).then(handleSuccess, handleError('Error creating subcategory'));
        }

        function Update(subcategory) {
            return $http.post(url + '/subcategory/update', subcategory).then(handleSuccess, handleError('Error updating subcategory'));
        }

        function Delete(subcategory) {
            return $http.post(url + '/subcategory/delete', subcategory).then(handleSuccess, handleError('Error deleting subcategory'));
        }

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
