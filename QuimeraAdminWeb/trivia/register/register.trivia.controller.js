(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterTriviaController', RegisterTriviaController);

    RegisterTriviaController.$inject = ['QuestionService', 'TriviaService', '$location', 'FlashService', '$scope'];
    function RegisterTriviaController(QuestionService, TriviaService, $location, FlashService) {
        var vm = this;

        vm.trivia = {
            rounds: 1,
        }
        vm.allQuestions = [];

        vm.registerTrivia = registerTrivia;
        vm.checkedQuestions = checkedQuestions;

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

        function registerTrivia() {
            vm.dataLoading = true;
            TriviaService.Create(vm.trivia)
                .then(function (response) {
                    if (response==="") {
                        FlashService.Success('Trivia registrada.', false);
                        $location.path('/trivia');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }

        function checkedQuestions() {
            //To reset selected question
            vm.trivia.questions = [];

            var aux = $('.questionSelected:checked').toArray();

            aux.forEach(function(option){
                vm.trivia.questions.push(JSON.parse(option.value));
            });

        }

    }

})();