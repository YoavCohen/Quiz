/**
 * Created by shekhargulati on 10/06/14.
 */

 var app = angular.module('todoapp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
    ]);

 app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/list.html',
        controller: 'ListCtrl'
    }).when('/create', {
        templateUrl: 'views/create.html',
        controller: 'CreateCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});




 app.controller('ListCtrl', function ($scope, $http) {
    $http.get('/api/v1/todos').success(function (data) {
        $scope.todos = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })



    $scope.todoStatusChanged = function (todo) {
        console.log(todo);
        $http.put('/api/v1/todos/' + todo.id, todo).success(function (data) {
            console.log('status changed');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }

    $scope.getans = function(form){
        for (var j = 1 ;j < 11; j++) {
            var x = "tr " + j.toString();
            var radios = document.getElementsByName(j.toString());
            for (var i = 0, length = radios.length; i < length; i++) {
                if (radios[i].checked) {
                    if(radios[i].value.localeCompare($scope.todos[j-1].coranswer)==0)
                      document.getElementsByName(x)[0].style.backgroundColor="green";
                    else{
                        document.getElementsByName(x)[0].style.backgroundColor="red";
                    }
            }
        }
    }
}
});

 app.controller('CreateCtrl', function ($scope, $http, $location) {
    $scope.todo = {
        done: false
    };


    $scope.createTodo = function () {
        alert("1");
        console.log($scope.todo);
        $http.post('/api/v1/todos', $scope.todo).success(function (data) {
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});