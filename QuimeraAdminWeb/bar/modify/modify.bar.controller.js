(function () {
    'use strict';

    angular
        .module('app')
        .controller('ModifyBarController', ModifyBarController);

    ModifyBarController.$inject = ['BarService', '$location', '$routeParams', 'FlashService'];
    function ModifyBarController(BarService, $location, $routeParams, FlashService) {
        var vm = this;
        vm.bar = null;

        vm.modifyBar = modifyBar;

        initController();

        function initController() {
            loadCurrentBar();
        }

        function loadCurrentBar() {
            BarService.GetById($routeParams.id)
                .then(function (bar) {
                    vm.bar = bar;
                });
        }

        function modifyBar() {
            vm.dataLoading = true;
            BarService.Update(vm.bar)
                .then(function (response) {
                    if (response==="") {
                        FlashService.Success('Bar modificado.', false);
                        $location.path('/bars');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }
    }



})();