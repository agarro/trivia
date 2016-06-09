(function () {
    'use strict';

    angular
        .module('app')
        .controller('BannerController', BannerController);

    BannerController.$inject = ['TriviaService', '$location', '$interval', '$routeParams', '$scope'];
    function BannerController(TriviaService, $location, $interval, $routeParams, $scope) {
        var vm = this;
        var interval = null;

        vm.countdown = 0;

        vm.urlBanner = null;

        vm.statusTrivia = null;
        vm.currentBanners = null;

        vm.getStatusTrivia = getStatusTrivia;
        vm.getCurrentBanners = getCurrentBanners;
        vm.getElapsedTime = getElapsedTime;


        initController();

        function initController() {
            vm.triviaActive = false;
            getStatusTrivia()
            getElapsedTime();
            getCurrentBanners();

        }

        function getElapsedTime() {
            TriviaService.GetElapsedTime()
                .then(function (response) {
                    vm.elapsedTime = response;
                });
        }

        function getCurrentBanners() {
            TriviaService.GetCurrentBanners()
                .then(function (response) {
                    if (response != "") {
                        vm.currentBanners = response.data;
                        vm.dataLoading = true;
                        vm.urlBanner = vm.currentBanners[$routeParams.id].url;
                    } else {
                        vm.currentBanners = {};
                        vm.dataLoading = false;
                    }
                });
        }

        function getStatusTrivia() {
            TriviaService.GetStatusTrivia()
                .then(function (response) {
                    if (response != "") {
                        vm.statusTrivia = response.message;
                    } else {
                        vm.dataLoading = false;
                    }
                });
        }

        $(function () {
            interval = $interval(function () {
                getStatusTrivia();
                getElapsedTime();
                if (vm.statusTrivia != 'SHOWING_BANNER') {
                    vm.urlBanner = null;
                    $location.path('/trivia');
                }
            }, 500);

        });

        $scope.$on('$destroy', function () {
            $interval.cancel(interval);
        });


    }

})();