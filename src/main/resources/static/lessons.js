var app = angular.module("angularDemo", []);

app.filter("reverse", function(){
    return function(text){
        return text.split("").reverse().join("");
    }
});

app.filter("upperCase", function(){
    return function(text){
        return text.toUpperCase();
    }
});

app.controller("MyController", function($scope, filmService){
    $scope.films = filmService.getFilms();
    $scope.data = {
        message: ""
    }
});

app.factory("filmService", function(){
    return{
        getFilms: function(){
            return[
                {
                    "name": "Twisted",
                    "initialReleaseDate": "2019"
                },
                
                {
                    "name": "Sisters",
                    "initialReleaseDate": "2018"
                },
                
                {
                    "name": "The Watchers",
                    "initialReleaseDate": "2017"
                },
                
                {
                    "name": "Brothers ;))",
                    "initialReleaseDate": "2016"
                }
            ]
        }
    }
});

app.directive("superman", function(){
    return{
        restrict: "E",
        template: "<div>Привет, я новая директива</div>"
        //,templateUrl:
    }
});

app.directive("enter", function(){
    return function(scope, element){
        element.bind("mouseenter", function(){
            console.log("Мышь наведена");
        });
    }
});

app.directive("hightlight",function(){
    return function(scope, element, attribute){
        element.bind("mouseenter", function(){
            element.addClass(attribute.hightlight);
        });
        element.bind("mouseleave", function(){
            element.removeClass(attribute.hightlight);
        });
    }
});

app.directive("task",function(){
    return{
        restrict:"E",
        template:"<input type='text' ng-model='task'>{{ task }}"
    }
});
