angular.module('app', []).controller('indexController', function ($scope, $http){


    const contextPath = 'http://localhost:8189/app/api/v1';

    $scope.loadStudents = function (pageIndex= 1){
        $http({
            url: contextPath + '/students',
            method: 'GET',
            params: {
                name_part: $scope.filter ? $scope.filter.name_part : null,
                min_score: $scope.filter ? $scope.filter.min_score : null,
                max_score: $scope.filter ? $scope.filter.max_score : null
            }
        }).then(function (response) {
            $scope.StudentsList = response.data.content;
        });
    };


    $scope.deleteStudent = function (studentId){
        $http.delete(contextPath + '/students/'+studentId)
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