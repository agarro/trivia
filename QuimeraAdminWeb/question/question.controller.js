(function () {
    'use strict';

    angular
        .module('app')
        .controller('QuestionController', QuestionController);

    QuestionController.$inject = ['UserService', 'QuestionService', '$rootScope'];
    function QuestionController(UserService, QuestionService, $rootScope) {
        var vm = this;

        vm.user = null;
        vm.allQuestions = [];
        vm.deleteQuestion = deleteQuestion;

        initController();

        function initController() {
            loadCurrentUser();
            loadAllQuestions();
        }

        function loadCurrentUser() {
            UserService.GetByUsername($rootScope.globals.currentUser.username)
                .then(function (user) {
                    vm.user = user;
                });
        }

        function loadAllQuestions() {
            QuestionService.GetAll()
                .then(function (questions) {
                    vm.allQuestions = questions;
                });
        }

        function deleteQuestion(id) {
            QuestionService.Delete(id)
                .then(function () {
                    loadAllQuestions();
                });
        }
    }

})();