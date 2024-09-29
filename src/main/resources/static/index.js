angular.module('app', []).controller('indexController', function ($scope, $http){


    const contextPath = 'http://product_app:8080/app/api/v1';

    $scope.loadProducts = function (pageIndex= 1){
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductList = response.data.content;
        });
    };

    $scope.loadBasket = function (){
        $http.get(contextPath + '/baskets')
            .then(function (response) {
                console.log(response.data);
                $scope.BasketList = response.data;
            })
    };


    $scope.addProduct = function (productId){
        $http.get(contextPath + '/baskets/'+productId + '/add')
            .then(function (response){
                $scope.loadBasket();
            });
    }

    $scope.deleteProduct = function (basketId){
        $http.delete(contextPath + '/baskets/'+basketId)
            .then(function (response){
                $scope.loadBasket();
            });
    }


    $scope.createProduct = function (){
        $http.post(contextPath + '/products', $scope.newProduct)
            .then(function (response){
                $scope.loadProducts();
                $scope.newProduct = null;
            })
    }

    $scope.changeCount = function (basketId, digit){

        const basketItem = $scope.BasketList.find(item => item.id === basketId);

        if (digit === -1 && basketItem.countProducts <= 1) {
            $scope.deleteProduct(basketId);
            return;
        };


        $http({
            url: contextPath + '/baskets/change_count',
            method: 'GET',
            params: {
                basketId: basketId,
                digit: digit
            }
        }).then(function (response){
            $scope.loadBasket();
        });
    };

    $scope.loadProducts();
    $scope.loadBasket();
})