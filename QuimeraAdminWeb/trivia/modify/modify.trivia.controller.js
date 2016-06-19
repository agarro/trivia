(function () {
    'use strict';

    angular
        .module('app')
        .controller('ModifyTriviaController', ModifyTriviaController);

    ModifyTriviaController.$inject = ['TriviaService', 'QuestionService', 'BannerService', '$location', '$routeParams', 'FlashService', 'SubcategoryService', 'CategoryService'];
    function ModifyTriviaController(TriviaService, QuestionService, BannerService, $location, $routeParams, FlashService, SubcategoryService, CategoryService) {
        var vm = this;
        vm.trivia = null;
        vm.subcategorySelected = [];
        vm.modifyTrivia = modifyTrivia;
        vm.checkedQuestions = checkedQuestions;
        vm.containsQuestions = containsQuestions;
        vm.allQuestions = [];
        vm.bannersSelected = [];
        vm.loadAllCategories = loadAllCategories;
        vm.loadAllSubcategories = loadAllSubcategories;
        initController();

        function initController() {
            loadAllBanners();
            loadCurrentQuestion();
            loadAllQuestions();
            loadAllSubcategories();
            loadAllCategories();
        }

        function loadAllQuestions() {
            QuestionService.GetAll()
                .then(function (questions) {
                    vm.allQuestions = questions;
                });
        }

        function loadAllBanners() {
            BannerService.GetAll()
                .then(function (banners) {
                    vm.allBanners = banners;
                });
        }

        function loadCurrentQuestion() {
            TriviaService.GetById($routeParams.id)
                .then(function (trivia) {
                    vm.trivia = trivia;

                    vm.trivia.banners.forEach(function (outerBanner) {
                        vm.allBanners.forEach(function (innerBanner) {
                            if (outerBanner.idBanner === innerBanner.idBanner) {
                                vm.bannersSelected.push(innerBanner);
                            }
                        });
                    });
                });
        }

        function modifyTrivia() {
            vm.dataLoading = true;
            TriviaService.Update(vm.trivia)
                .then(function (response) {
                    if (response === "") {
                        FlashService.Success('Trivia modificada.', false);
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

            aux.forEach(function (option) {
                vm.trivia.questions.push(JSON.parse(option.value));
            });

        }

        function containsQuestions(question) {
            if (vm.trivia != null && vm.trivia.questions != null) {
                for (var i = 0; i < vm.trivia.questions.length; i++) {
                    if (angular.equals(vm.trivia.questions[i], question)) {
                        return true;
                    }
                }
            }
            return false;
        }

        function loadAllCategories() {
            CategoryService.GetAll()
                .then(function (categories) {
                    vm.allCategories = categories;
                });
        }

        function loadAllSubcategories() {
            SubcategoryService.GetAll()
                .then(function (subcategories) {
                    vm.allSubcategories = subcategories;
                });
        }

    }


})();