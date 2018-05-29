var app = angular.module("NewsManagement", []);

// Controller Part
app.controller("NewsController", function($scope, $http) {


    $scope.newsList = [];
    $scope.news = {
        id: 1,
        name: "",
        content: ""
    };

    // Now load the data from server
    _refreshNewsData();

    // HTTP POST/PUT methods for add/edit news
    // Call: http://localhost:8080/list
    $scope.submitNews = function() {

        var method = "";
        var url = "";
        if ($scope.news.id === -1) {
            method = "POST";
            url = '/list';
        } else {
            method = "PUT";
            url = '/list/'+$scope.news.id;
        }

        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.news),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    $scope.createNews = function() {
        _clearFormData();
    }

    // HTTP DELETE- delete news by Id
    // Call: http://localhost:8080/list/{newsId}
    $scope.deleteNews = function(news) {
        $http({
            method: 'DELETE',
            url: '/list/' + news.id
        }).then(_success, _error);
    };

    // In case of edit
    $scope.editNews = function(editNews) {
        $scope.news.id = editNews.id;
        $scope.news.name = editNews.name;
        $scope.news.content = editNews.content;
    };

    // Private Method
    // HTTP GET- get all news collection
    // Call: http://localhost:8080/list
    function _refreshNewsData() {
        $http({
            method: 'GET',
            url: '/list'
        }).then(
            function(res) { // success
                $scope.newsList = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    function _success(res) {
        _refreshNewsData();
        _clearFormData();
    }

    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }

    // Clear the form
    function _clearFormData() {
        $scope.news.id = -1;
        $scope.news.name = "";
        $scope.news.content = ""
    }
});
