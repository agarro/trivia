(function () {
    'use strict';

    angular
        .module('app')
        .controller('TriviaController', TriviaController);

    TriviaController.$inject = ['TriviaService', '$interval', '$scope', 'GameManagerService', '$rootScope'];
    function TriviaController(TriviaService, $interval, $scope, GameManagerService, $rootScope) {

        var vm = this;
        var elapsedTimeInterval = null;

        var checkingTrivia = null;

        vm.progressBarValue = 100;

        vm.questionPosition = 0;

        var triviaStarted = false;

        var MAX_ELAPSED_TIME = 5;

        vm.elapsedTime = MAX_ELAPSED_TIME;

        vm.correctAnswer = null;

        vm.trivia = null;
        vm.statusTrivia = null;

        vm.currentQuestion = null;

        vm.options = null;

        vm.questionPosition = 0;

        vm.description = null;

        function getTrivia() {
            TriviaService.GetCurrentTrivia()
                .then(function (response) {
                    if (response.data != "") {
                        vm.dataLoading = true;
                        vm.trivia = response.data;
                    } else {
                        vm.trivia = null;
                        vm.dataLoading = false;
                    }
                });
        }

        function triviaStart() {
            triviaStarted = true;
            vm.statusTrivia = 'STARTING_TRIVIA';
            setTimeout(nextQuestion, 500);
        }

        function nextQuestion() {
            vm.statusTrivia = 'SHOWING_QUESTION';
            vm.questionPosition++;
            vm.currentQuestion = vm.trivia.questions[vm.questionPosition - 1];
            vm.currentQuestion.currentQuestionPosition = vm.questionPosition;
            GameManagerService.SetCurrentQuestion(vm.currentQuestion);
            GameManagerService.SetStatus(vm.statusTrivia);
            setTimeout(showingOptions, 5000);
        }

        function showingOptions() {
            vm.statusTrivia = 'SHOWING_OPTIONS';
            GameManagerService.SetStatus(vm.statusTrivia);
            vm.progressBarValue = 100;

            vm.options = vm.currentQuestion.options;

            setTimeout(countdown, 1000);
        }

        function countdown() {
            elapsedTimeInterval = $interval(function () {
                GameManagerService.SetElapsedTime(vm.elapsedTime);
                if (vm.elapsedTime > 0) {
                    vm.elapsedTime = vm.elapsedTime - 1;
                    vm.progressBarValue = (vm.elapsedTime/MAX_ELAPSED_TIME)*100;
                } else {
                    $interval.cancel(elapsedTimeInterval);
                }
            }, 1000);

            setTimeout(showingCorrectAnswer, MAX_ELAPSED_TIME*1000 + 1000);
        }

        function showingCorrectAnswer() {
            vm.elapsedTime = MAX_ELAPSED_TIME;
            vm.progressBarValue = 100;
            vm.statusTrivia = 'SHOWING_CORRECT_ANSWER';
            GameManagerService.SetStatus(vm.statusTrivia);
            vm.options = [];
            vm.options.push(vm.currentQuestion.correctAnswer);
            setTimeout(showingDescription, 5000);
        }

        function showingDescription() {
            vm.statusTrivia = 'SHOWING_DESCRIPTION';
            GameManagerService.SetStatus(vm.statusTrivia);
            setTimeout(showingPartialWinners, 5000);
        }

        function showingPartialWinners() {
            vm.options = null;
            vm.statusTrivia = 'SHOWING_PARTIAL_WINNERS';
            GameManagerService.SetStatus(vm.statusTrivia);
            if (vm.questionPosition == 5 || vm.questionPosition == 10 || vm.questionPosition == 15) {
                setTimeout(showingBanners, 5000);
            } else {
                setTimeout(nextQuestion, 5000);
            }
        }

        function showingBanners() {

            vm.statusTrivia = 'SHOWING_BANNER';
            GameManagerService.SetStatus(vm.statusTrivia);
            if (vm.questionPosition == 5) {
                vm.urlBanner = vm.trivia.banners[0].url;
                setTimeout(nextQuestion, 5000);
            } else if (vm.questionPosition == 10) {
                vm.urlBanner = vm.trivia.banners[1].url;
                setTimeout(nextQuestion, 5000);
            } else if (vm.questionPosition == 15) {
                vm.urlBanner = vm.trivia.banners[2].url;
                setTimeout(showingFinalWinners, 5000);
            }
        }

        function showingFinalWinners() {
            vm.questionPosition = 0;
            vm.currentQuestion = null;
            vm.statusTrivia = 'SHOWING_FINAL_WINNERS';
            GameManagerService.SetStatus(vm.statusTrivia);
            setTimeout(finishTrivia, 5000);
        }

        function finishTrivia() {

            triviaStarted = false;
        }

        $(function () {
            checkingTrivia = $interval(function () {
                if (vm.trivia == null) {
                    vm.statusTrivia = 'WAITING_TRIVIA';
                    getTrivia();
                } else if(!triviaStarted){
                    triviaStart();
                }
            }, 1000);
        });

        $scope.$on('$destroy', function () {
            $interval.cancel(checkingTrivia);
        });


    }

})();