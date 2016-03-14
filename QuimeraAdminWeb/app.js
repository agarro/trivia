(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/', {
                controller: 'HomeController',
                templateUrl: 'home/home.view.html',
                controllerAs: 'vm'
            })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login/login.view.html',
                controllerAs: 'vm'
            })

            .when('/users', {
                controller: 'UserController',
                templateUrl: 'user/user.view.html',
                controllerAs: 'vm'
            })

            .when('/user/modify/:id', {
                controller: 'ModifyUserController',
                templateUrl: 'user/modify/modify.user.view.html',
                controllerAs: 'vm'
            })

            .when('/user/register', {
                controller: 'UserRegisterController',
                templateUrl: 'user/register/register.user.view.html',
                controllerAs: 'vm'
            })

            .when('/questions', {
                controller: 'QuestionController',
                templateUrl: 'question/question.view.html',
                controllerAs: 'vm'
            })

            .when('/question/modify/:id', {
                controller: 'ModifyQuestionController',
                templateUrl: 'question/modify/modify.question.view.html',
                controllerAs: 'vm'
            })

            .when('/question/register', {
                controller: 'QuestionRegisterController',
                templateUrl: 'question/register/register.question.view.html',
                controllerAs: 'vm'
            })

            .when('/trivia', {
                controller: 'TriviaController',
                templateUrl: 'trivia/trivia.view.html',
                controllerAs: 'vm'
            })

            .when('/trivia/register', {
                controller: 'RegisterTriviaController',
                templateUrl: 'trivia/register/register.trivia.view.html',
                controllerAs: 'vm'
            })

            .when('/trivia/modify/:id', {
                controller: 'ModifyTriviaController',
                templateUrl: 'trivia/modify/modify.trivia.view.html',
                controllerAs: 'vm'
            })

            .when('/settings', {
                controller: 'ControlPanelController',
                templateUrl: 'control-panel/control-panel.view.html',
                controllerAs: 'vm'
            })

            .otherwise({ redirectTo: '/' });
    }

    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
    function run($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register']) === -1;
            var loggedIn = $rootScope.globals.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
        });
    }

})();