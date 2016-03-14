(function () {
    'use strict';

    angular
        .module('app')
        .controller('QuestionController', QuestionController);

    QuestionController.$inject = ['QuestionService', '$location', 'FlashService'];
    function QuestionController(QuestionService, $location, FlashService) {
        var vm = this;

        vm.allQuestions = [];
        vm.deleteQuestion = deleteQuestion;

        initController();

        function initController() {
            loadAllQuestions();
        }

        function loadAllQuestions() {
            QuestionService.GetAll()
                .then(function (questions) {
                    vm.allQuestions = questions;
                });
        }

        function deleteQuestion(question) {
            QuestionService.Delete(question)
                .then(function (response) {
                    if (response==="") {
                        FlashService.Success('Pregunta eliminada.', true);
                        loadAllQuestions();
                        $location.path('/questions');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }
    }

})();