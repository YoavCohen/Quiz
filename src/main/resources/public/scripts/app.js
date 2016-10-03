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
    }).when('/yoav', {
        templateUrl: 'views/get.html',
        controller: 'AllCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});


 app.controller('AllCtrl', function ($scope, $http, $location, $route) {
    $http.get('/api/v1/getall').success(function (data) {
        $scope.todos = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

    $scope.todoStatusChanged = function (todo) {
        console.log(todo);
        $http.put('/api/v1/todos/' + todo.id, todo).success(function (data) {
            console.log('status changed');
            $location.path('/yoav');
            $route.reload();
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }

}


);



 app.controller('ListCtrl', function ($scope, $http, $location ,$route) {

        $http.get('/api/v1/getallcategories').success(function (data) {
        $scope.categories = data;
        for(var k=0;k<$scope.categories.length;k++)
          $scope.selection.push($scope.categories[k]);
    }).error(function (data, status) {
        console.log('Error ' + data)
    })







    $http.get('/api/v1/todos').success(function (data) {
        $scope.todos = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    });




  $scope.selection = [];
  $scope.toggleSelection = function toggleSelection(fruitName) {
  var idx = $scope.selection.indexOf(fruitName);

    // is currently selected
    if (idx > -1) {
      $scope.selection.splice(idx, 1);
    }

    // is newly selected
    else {
      $scope.selection.push(fruitName);
    }
  };

  Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
}




    $scope.todoStatusChanged = function (todo) {
        console.log(todo);
        $http.put('/api/v1/todos/' + todo.id, todo).success(function (data) {
            console.log('status changed');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }




    $scope.getans = function(form){
        var counter=0;
        for (var j = 1 ;j < $scope.numberOfQuestions+1; j++) {
            var x = "tr " + j.toString();
            var radios = document.getElementsByName(j.toString());
            for (var i = 0, length = radios.length; i < length; i++) {
                    var selector = 'label[for=' + radios[i].id + ']'
                    var label = document.querySelector(selector);
                if (radios[i].checked) {

                    if(radios[i].value.localeCompare($scope.todos[j-1].coranswer)==0)
                        counter++;
                    else{
                        radios[i].style.color="red";
                        label.style.color="red";
                    }
            }
                else{
                    if(radios[i].value.localeCompare($scope.todos[j-1].coranswer)==0){
                        label.style.color="#0DFF92";
                    }
                }
        }
    }

        
        var text = "you got write: ";
        text=text + counter.toString();
        document.getElementById("modalText").innerHTML = text.toString();

        $('#myModal').modal();
        if(counter== $scope.numberOfQuestions)
            $('#myModal').data('bs.modal').$backdrop.css('background-color','green');
        else
            $('#myModal').data('bs.modal').$backdrop.css('background-color','red'); 
}

    
    $scope.ref = function(ref){
             $http.post('/api/v1/getfilterd' , $scope.selection).success(function (data) {
            $scope.todos = data;
    });
            window.scrollTo(0, 0);

}
});

 app.controller('CreateCtrl', function ($scope, $http, $location) {
    $scope.todo = {
        done: false
    };



        $http.get('/api/v1/getallcategories').success(function (data) {
        $scope.categories = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })




    $scope.createTodo = function () {
        console.log($scope.todo);
        $http.post('/api/v1/todos', $scope.todo).success(function (data) {
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});