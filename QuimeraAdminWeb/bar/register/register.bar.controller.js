(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterBarController', RegisterBarController);

    RegisterBarController.$inject = ['BarService', '$location', 'FlashService'];
    function RegisterBarController(BarService, $location, FlashService) {
        var vm = this;

        vm.registerBar = registerBar;

        function registerBar() {
            vm.dataLoading = true;
            BarService.Create(vm.bar)
                .then(function (response) {
                    if (response==="") {
                        FlashService.Success('Bar registrado.', false);
                        $location.path('/bars');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }
    }

})();