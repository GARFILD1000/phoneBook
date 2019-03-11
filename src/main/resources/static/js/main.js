
var springApp = angular.module("springApp", []);

springApp.controller("appController", function($scope, $http){
    $scope.persons = [];
    $scope.boolValue = true;
    $scope.buttonText = 'Добавить';
    $scope.orderField = 'id';
    $scope.orderInc = false;
    $http.get("http://localhost:8080/api/legalpersons").then(
        function (response){
            console.log(response);
            $scope.persons = response.data;
        },
        function (error){

        }
    );

    $scope.response = {};
    $scope.addPerson = function(newPerson){
        $http.post("http://localhost:8080/api/legalpersons", newPerson).then(
            function (response){
                console.log(response);
                $http.get("http://localhost:8080/api/legalpersons").then(
                    function (response){
                        console.log(response);
                        $scope.persons = response.data;
                    },
                    function (error){

                    }
                );
            },
            function (error){

            }

        );
        $scope.newPerson = null;
    };

    $scope.deletePerson = function(id){
        console.log("Number of deleted: "+id);
        $http.delete("http://localhost:8080/api/legalpersons/"+id.toString()).then(
            function (response){
                removeByAttr($scope.persons, "id", id);
            },
            function (error){

            }
        );

    };


    $scope.editPerson = function(person){
        console.log("Number of edited: "+person.id);
        $scope.newPerson = {};
        for (var key in person) {
            $scope.newPerson[key] = person[key];
        }
        $scope.boolValue = false;
    };

    $scope.editedPerson = function(editedPerson){
        console.log("Number of edited: "+editedPerson.id);
        $http.put("http://localhost:8080/api/legalpersons/"+editedPerson.id.toString(), editedPerson).then(
            function (response){
                console.log(response);
                if (response.data) {
                    $scope.boolValue = true;
                    var person = {};
                    for (var key in editedPerson) {
                        person[key] = editedPerson[key];
                    }
                    removeByAttr($scope.persons, "id", editedPerson.id);
                    $scope.newPerson = null;
                    $scope.persons.push(person);
                }
            },
            function (error){
            }
        );
    };
    $scope.cancelEditPerson = function(editedPerson){
        $scope.boolValue = true;
        $scope.newPerson = null;
    }
});

var removeByAttr = function(arr, attr, value){
    var i = arr.length;
    while(i--){
        if( arr[i]
            && arr[i].hasOwnProperty(attr)
            && (arguments.length > 2 && arr[i][attr] === value ) ){
            arr.splice(i,1);
        }
    }
    return arr;
};

springApp.directive("hoverClass",function(){
    return function(scope, element, attribute){
        element.bind("mouseenter", function(){
            element.addClass(attribute.hoverClass);
        });
        element.bind("mouseleave", function(){
            element.removeClass(attribute.hoverClass);
        });
    }
});



