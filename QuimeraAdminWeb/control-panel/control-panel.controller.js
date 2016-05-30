(function () {
    'use strict';

    angular
        .module('app')
        .controller('ControlPanelController', ControlPanelController);

    ControlPanelController.$inject = ['TriviaService', '$location', 'FlashService', '$interval', '$scope'];
    function ControlPanelController(TriviaService, $location, FlashService, $interval, $scope) {
        var vm = this;
        var interval = null;
        vm.statusTrivia = null;
        vm.currentQuestion = null;
        vm.getCurrentQuestion = getCurrentQuestion;
        vm.startTrivia = startTrivia;
        vm.stopTrivia = stopTrivia;
        vm.getStatusTrivia = getStatusTrivia;
        vm.getCurrentTrivia = getCurrentTrivia;
        vm.currentQuestionPosition = 0;

        vm.myColor = 'transparent';

        initController();

        function initController() {
            getStatusTrivia()
            getCurrentTrivia();
            getCurrentQuestionPosition();
        }

        function startTrivia() {
            TriviaService.StartTrivia()
                .then(function (response) {
                    if (response.message != null) {
                        FlashService.Success('Trivia iniciada.', false);
                        $location.path('/settings');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }

        function stopTrivia() {
            TriviaService.StopTrivia()
                .then(function (response) {
                    if (response.message === "READY") {
                        FlashService.Success('Trivia finalizada.', false);

                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }

        function getCurrentQuestion() {
            TriviaService.GetCurrentQuestion()
                .then(function (response) {
                    if (response != "") {
                        vm.currentQuestion = response;
                    } else {
                        vm.currentQuestion = {};
                        vm.dataLoading = false;
                    }
                });
        }

        function getCurrentTrivia() {
            TriviaService.GetCurrentTrivia()
                .then(function (response) {
                    if (response != "") {
                        vm.currentTrivia = response.data;
                    } else {
                        vm.currentTrivia = {};
                        FlashService.Error('Ninguna trivia ha sido seleccionada.');
                        vm.dataLoading = false;
                    }
                });
        }

        function getStatusTrivia() {
            TriviaService.GetStatusTrivia()
                .then(function (response) {
                    if (response != "") {
                        vm.statusTrivia = response.message;
                        setStatusColor();
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }

        function getCurrentQuestionPosition(){
            TriviaService.GetCurrentQuestionPosition()
                .then(function(response){
                   vm.currentQuestionPosition = response.data;
                });
        }

        function setStatusColor() {

            if (vm.statusTrivia === "READY") {
                vm.myColor = 'green';
            } else if (vm.statusTrivia === "ROUNDFINISHED") {
                vm.myColor = 'blue';
            } else if (vm.statusTrivia === "TERMINATED") {
                vm.myColor = 'red';
            } else {
                vm.myColor = '#FFCC00';
            }
        }

        $(function () {

            interval = $interval(function () {
                getStatusTrivia();
                getCurrentQuestion();
                getCurrentQuestionPosition();
            }, 1000);

        });
        $scope.$on('$destroy', function () {
            $interval.cancel(interval);
        });
    }

})();