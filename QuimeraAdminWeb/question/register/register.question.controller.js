(function () {
    'use strict';

    angular
        .module('app')
        .controller('QuestionRegisterController', QuestionRegisterController);

    QuestionRegisterController.$inject = ['QuestionService', '$location', 'FlashService', '$scope'];
    function QuestionRegisterController(QuestionService, $location, FlashService, $scope) {
        var vm = this;
        vm.question = {};
        vm.question.options = [];
        vm.registerQuestion = registerQuestion;

        function registerQuestion() {
            vm.dataLoading = true;
            QuestionService.Create(vm.question)
                .then(function (response) {
                    if (response==="") {
                        FlashService.Success('Pregunta registrada.', true);
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