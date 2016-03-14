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

        vm.myColor = 'transparent';

        initController();

        function initController() {
            loadStatusTrivia();
        }

        function loadStatusTrivia() {
            TriviaService.GetStatusTrivia()
                .then(function (response) {
                    if (response != "") {
                        vm.statusTrivia = response.message;
                    } else {
                        vm.dataLoading = false;
                    }
                });
        }

        function startTrivia() {
            TriviaService.StartTrivia()
                .then(function (response) {
                    if (response.message === "RUNNABLE") {
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
                        $location.path('/settings');
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

        function setStatusColor(){

            if(vm.statusTrivia ==="READY"){
                vm.myColor = 'green';
            } else if(vm.statusTrivia ==="RUNNABLE") {
                vm.myColor = '#FFCC00';
            } else if(vm.statusTrivia ==="STOPPED") {
                vm.myColor = 'red';
            }
        }

        $(function(){

            interval = $interval(function () {
                getStatusTrivia();
                getCurrentQuestion();
            }, 1000);

        });
        $scope.$on('$destroy', function() {
            $interval.cancel(interval);
        });
    }

})();