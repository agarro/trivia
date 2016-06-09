(function () {
    'use strict';

    angular
        .module('app')
        .controller('TriviaController', TriviaController);

    TriviaController.$inject = ['TriviaService', '$location', '$interval', '$scope'];
    function TriviaController(TriviaService, $location, $interval, $scope) {
        var vm = this;
        var interval = null;

        vm.triviaActive = false;

        //var INTERVAL_QUESTION = 15;

        vm.currentQuestionPosition = 0;

        vm.elapsedTime = 0;

        vm.showCountdown = false;

        vm.showQuestionLabel = false;

        vm.correctAnswer = null;

        vm.trivia = {};
        vm.statusTrivia = null;

        vm.currentQuestion = null;

        vm.options = null;

        vm.currentQuestionPosition = 0;

        vm.getCurrentQuestion = getCurrentQuestion;
        vm.getStatusTrivia = getStatusTrivia;
        vm.getCurrentTrivia = getCurrentTrivia;
        vm.getElapsedTime = getElapsedTime;

        initController();

        function initController() {
            vm.triviaActive = false;
            getStatusTrivia()
            getCurrentTrivia();
            getCurrentQuestionPosition();
            getElapsedTime();
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

        function getElapsedTime() {
            TriviaService.GetElapsedTime()
                .then(function (response) {
                    vm.elapsedTime = response;
                });
        }

        function getCurrentTrivia() {
            TriviaService.GetCurrentTrivia()
                .then(function (response) {
                    if (response.data != "" && vm.statusTrivia != 'READY' && vm.statusTrivia != 'TERMINATED') {
                        vm.currentTrivia = response.data;
                        vm.triviaActive = true;
                    } else {
                        vm.currentTrivia = {};
                        vm.dataLoading = false;
                        vm.triviaActive = false;

                    }
                });
        }

        function getStatusTrivia() {
            TriviaService.GetStatusTrivia()
                .then(function (response) {
                    if (response != "") {
                        vm.statusTrivia = response.message;

                        if (vm.statusTrivia == 'SHOWING_OPTIONS') {
                            vm.showCountdown = true;
                        } else {
                            vm.showCountdown = false;
                        }

                        if (vm.statusTrivia == 'SHOWING_OPTIONS' || vm.statusTrivia == 'SHOWING_QUESTION' || vm.statusTrivia == 'SHOWING_ANSWER') {
                            vm.showQuestionLabel = true;
                        } else {
                            vm.showQuestionLabel = false;
                        }

                    } else {
                        vm.dataLoading = false;
                    }
                });
        }

        function getCurrentQuestionPosition() {
            TriviaService.GetCurrentQuestionPosition()
                .then(function (response) {
                    vm.currentQuestionPosition = response.data;
                });
        }


        $(function () {
            interval = $interval(function () {
                getStatusTrivia();


                if (vm.triviaActive == false) {
                    getCurrentTrivia();
                } else {
                    getElapsedTime();

                    getCurrentQuestionPosition();

                    //if (vm.countdown > 0) {
                    //    vm.countdown--;
                    //}

                    if (vm.statusTrivia == 'SHOWING_OPTIONS') {
                        vm.options = vm.currentQuestion.options;
                    } else if (vm.statusTrivia == 'SHOWING_CORRECT_ANSWER') {
                        vm.options = [];
                        vm.options.push(vm.currentQuestion.correctAnswer);
                    } else {
                        vm.options = null;
                        getCurrentQuestion();
                    }

                    if (vm.statusTrivia == 'SHOWING_BANNER') {

                        if (vm.currentQuestionPosition == 5) {
                            $location.path('/banner/0');
                        } else if (vm.currentQuestionPosition == 10) {
                            $location.path('/banner/1');
                        } else if (vm.currentQuestionPosition == 15) {
                            $location.path('/banner/2');
                        }
                    }


                }


                if (vm.statusTrivia == 'TERMINATED') {
                    vm.triviaActive = false;
                }
            }, 500);

        });
        $scope.$on('$destroy', function () {
            $interval.cancel(interval);
        });


    }

})();