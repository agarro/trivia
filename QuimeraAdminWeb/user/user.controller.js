(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);

    UserController.$inject = ['UserService', '$location', 'FlashService'];
    function UserController(UserService, $location, FlashService) {
        var vm = this;

        vm.allUsers = [];
        vm.deleteUser = deleteUser;

        initController();

        function initController() {
            loadAllUsers();
        }

        function loadAllUsers() {
            UserService.GetAll()
                .then(function (users) {
                    vm.allUsers = users;
                });
        }

        function deleteUser(user) {
            UserService.Delete(user)
                .then(function (response) {
                    if (response==="") {
                        FlashService.Success('Usuario eliminado.', true);
                        loadAllUsers();
                        $location.path('/users');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }

    }

})();