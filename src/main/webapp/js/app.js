// Для работы данного примера используется модуль ngRoute, который предоставляет интерфейс для маршрутизации и создания сложных приложений с несколькими представлениями
angular.module("exampleApp", ["ngResource", "ngRoute"])
.constant("baseUrl", "http://localhost:8080/PizzaDelivery/rest/pizza/items/")
.config(function ($locationProvider, $routeProvider) {


    //$locationProvider.html5Mode(true);

    $routeProvider.when("/table", {
        templateUrl: "views/pizza/table.html"
    });

    $routeProvider.when("/edit", {
        templateUrl: "views/pizza/edit.html"
    });

    $routeProvider.when("/create", {
        templateUrl: "views/pizza/edit.html"
    });

    $routeProvider.otherwise({
        templateUrl: "views/pizza/table.html"
    });
})
.controller("defaultCtrl", function ($scope, $http, $resource, $location, baseUrl) {

    // текущее педставление
    $scope.currentView = "table";
    $scope.orderProp = "id";

    $http.get(baseUrl + "/types").success(function (response) {
            $scope.pizzaTypes = response;
        });

    $scope.itemsResource = $resource(baseUrl + ":id", { id: "@id" });

        $scope.order = function (ord) {
            $scope.orderProp = ord;
        }

    // получение всех данных из модели
    $scope.refresh = function () {
        $scope.items = $scope.itemsResource.query();

    }

    // создание нового элемента
    $scope.create = function (item) {
        new $scope.itemsResource(item).$save().then(function (newItem) {
            $scope.items.push(newItem);
            $location.path("/table");
        });
    }

    // обновление элемента
    $scope.update = function (item) {
        //item.$save();
        $http({
            url: baseUrl,
            method: "PUT",
            data: item
        }).success(function (modifiedItem) {
            for (var i = 0; i < $scope.items.length; i++) {
                if ($scope.items[i].id == modifiedItem.id) {
                    $scope.items[i] = modifiedItem;
                    break;
                }
            }
        });

        $location.path("/table");
    }

    // удаление элемента из модели
    $scope.delete = function (item) {
        item.$delete().then(function () {
            $scope.items.splice($scope.items.indexOf(item), 1);
        });
        $location.path("/table");
    }

    // редеактирование существующего или создание нового элемента
    $scope.editOrCreate = function (item) {
        if (item) {
            $scope.currentItem = item;
        } else {
            $scope.currentItem = {};
            $scope.currentItem.type = $scope.pizzaTypes[0];
        }
        $location.path("/edit");
    }

    // сохранение изменений
    $scope.saveEdit = function (item) {
        if (angular.isDefined(item.id)) {
            $scope.update(item);
        } else {
            $scope.create(item);
        }
    }

    // отмена изменений и возврат в представление table
    $scope.cancelEdit = function () {
        if ($scope.currentItem && $scope.currentItem.$get) {
            $scope.currentItem.$get();
        }
        $scope.currentItem = {};
        $location.path("/table");
    }

    $scope.refresh();
});