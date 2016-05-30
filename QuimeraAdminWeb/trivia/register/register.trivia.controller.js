(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterTriviaController', RegisterTriviaController);

    RegisterTriviaController.$inject = ['QuestionService', 'TriviaService', 'BannerService', '$location', 'FlashService', '$scope'];
    function RegisterTriviaController(QuestionService, TriviaService, BannerService, $location, FlashService) {
        var vm = this;

        vm.trivia = {
            rounds: 1
        };
        vm.allQuestions = [];
        vm.allBanners = [];
        vm.bannersSelected = [];
        vm.registerTrivia = registerTrivia;
        vm.checkedQuestions = checkedQuestions;

        initController();

        function initController() {
            loadAllQuestions();
            loadAllBanners();
        }

        function loadAllQuestions() {
            QuestionService.GetAll()
                .then(function (questions) {
                    vm.allQuestions = questions;
                });
        }

        function loadAllBanners(){
            BannerService.GetAll()
                .then(function (banners) {
                    vm.allBanners = banners;
                });
        }

        function registerTrivia() {
            vm.dataLoading = true;
            vm.trivia.banners = [];
            vm.bannersSelected.forEach(function(banner){
                vm.trivia.banners.push(JSON.parse(banner));
            });
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