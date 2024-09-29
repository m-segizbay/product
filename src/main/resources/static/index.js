angular.module('app', []).controller('indexController', function ($scope, $http){


    const contextPath = 'http://localhost:8189/app';


    $scope.loadStudents = function (){
        $http.get(contextPath + '/students')
            .then(function (response){
                console.log(response);
                $scope.StudentsList = response.data;
            });
    };

    $scope.deleteStudent = function (studentId){
        $http.get(contextPath + '/students/delete/'+studentId)
            .then(function (response){
                $scope.loadStudents();
            });
    }

    $scope.changeScore = function (studentId, delta){
        $http({
            url: contextPath + '/students/change_score',
            method: 'GET',
            params: {
                studentId: studentId,
                delta: delta
            }
        }).then(function (response){
            $scope.loadStudents();
        })
    }

    $scope.createStudent = function (){
        console.log($scope.newStudent);
        $http.post(contextPath + '/students', $scope.newStudent)
            .then(function (response){
                $scope.loadStudents();
                $scope.newStudent = null;
            })
    }

    $scope.loadStudents();
})