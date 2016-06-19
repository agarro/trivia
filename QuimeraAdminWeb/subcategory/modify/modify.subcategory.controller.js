(function () {
    'use strict';

    angular
        .module('app')
        .controller('ModifySubcategoryController', ModifySubcategoryController);

    ModifySubcategoryController.$inject = ['SubcategoryService', '$location', '$routeParams', 'FlashService', '$scope'];
    function ModifySubcategoryController(SubcategoryService, $location, $routeParams, FlashService, $scope) {
        var vm = this;
        vm.subcategory = null;

        vm.modifySubcategory = modifySubcategory;

        initController();

        function initController() {
            loadCurrentSubcategory();
        }

        function loadCurrentSubcategory() {
            SubcategoryService.GetById($routeParams.id)
                .then(function (subcategory) {
                    vm.subcategory = subcategory;
                });
        }

        function modifySubcategory() {
            vm.dataLoading = true;
            SubcategoryService.Update(vm.subcategory)
                .then(function (response) {
                    if (response === "") {
                        FlashService.Success('Subcategoría modificada.', false);
                        $location.path('/subcategories');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }
    }

})();