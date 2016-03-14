(function () {
    'use strict';

    angular
        .module('app')
        .controller('ModifyQuestionController', ModifyQuestionController);

    ModifyQuestionController.$inject = ['QuestionService', '$location', '$routeParams', 'FlashService', '$scope'];
    function ModifyQuestionController(QuestionService, $location, $routeParams, FlashService, $scope) {
        var vm = this;
        vm.question = null;

        vm.modifyQuestion = modifyQuestion;

        initController();

        function initController() {
            loadCurrentQuestion();
        }

        function loadCurrentQuestion() {
            QuestionService.GetById($routeParams.id)
                .then(function (question) {
                    vm.question = question;
                });
        }

        function modifyQuestion() {
            vm.dataLoading = true;
            QuestionService.Update(vm.question)
                .then(function (response) {
                    if (response==="") {
                        FlashService.Success('Pregunta modificada.', true);
                        $location.path('/questions');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }

        $scope.addItem = function(item){
            $scope.vm.question.options.push(item);
            $scope.newItem = null;
        }

    }



})();