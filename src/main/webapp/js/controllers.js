var booksExample = [
    	    {
    	        id:'s1',
    	        iconS:'../img/books-1673578_1280.png',
    	        name: "Book1",
    	        author: "Author1",
    	        language: "Language1",
    	        year: 1999,
    	        publishingHouse: "house1",
    	        description: "fun book",
    	        numPages: 78,
    	        price: "$12",
    	        impression: "Cool book"
    	    },
    	    {
    	        id:'s2',
    	        iconS:'../img/books-1673578_1280.png',
    	        name: "Book2",
    	        author: "Author1",
    	        language: "Language1",
    	        year: 1999,
    	        publishingHouse: "house1",
    	        description: "fun book",
    	        numPages: 78,
    	        price: "$12",
    	        impression: "Cool book"
    	    },
    	    {
    	        id:'s3',
    	        iconS:'../img/books-1673578_1280.png',
    	        name: "Book3",
    	        author: "Author1",
    	        language: "Language1",
    	        year: 1999,
    	        publishingHouse: "house1",
    	        description: "fun book",
    	        numPages: 78,
    	        price: "$12",
    	        impression: "Cool book"
    	    },
    	    {
    	        id:'s4',
    	        iconS:'../img/books-1673578_1280.png',
    	        name: "Book4",
    	        author: "Author1",
    	        language: "Language1",
    	        year: 1999,
    	        publishingHouse: "house1",
    	        description: "fun book",
    	        numPages: 78,
    	        price: "$12",
    	        impression: "Cool book"
    	    },
    	    {
    	        id:'s5',
    	        iconS:'../img/books-1673578_1280.png',
    	        name: "Book5",
    	        author: "Author1",
    	        language: "Language1",
    	        year: 1999,
    	        publishingHouse: "house1",
    	        description: "fun book",
    	        numPages: 78,
    	        price: "$12",
    	        impression: "Cool book"
    	    },
    	    {
    	        id:'s6',
    	        iconS:'../img/books-1673578_1280.png',
    	        name: "Book6",
    	        author: "Author1",
    	        language: "Language1",
    	        year: 1999,
    	        publishingHouse: "house1",
    	        description: "fun book",
    	        numPages: 78,
    	        price: "$12",
    	        impression: "Cool book"
    	    }

    	];

angular.module('bookUnity')
  .controller('SetCtrl', ['$scope','$rootScope', '$http', function ($scope, $rootScope ,$http) {
      $scope.user = Object.assign({},$rootScope.user);   
      $scope.done = function () {
          $http.post('/v2/account', JSON.stringify($scope.user))
          .then(
            function (response) { console.log("success");},
            function (failure) { console.log("failed :(", failure); });
      }
  }]);
 
angular.module('bookUnity')
.controller('AddBookCtrl', ['$scope','$http', function ($scope ,$http) {
    $scope.book = {};
    $scope.genre = {}
    $scope.done = function () {
        $http.post('/v2/book', { params: $scope.book },
          function (response) { console.log("success");},
          function (failure) { console.log("failed :(", failure); });
    }
}]); 

angular.module('bookUnity')
.controller('SearchCtrl',['$scope','$http',function ($scope,  $http) {
	$scope.search = function() {
        $http.get('/v2/book/find', { params: {category : $scope.category, 
        								 genre : $scope.genre,
        								 author : $scope.author,
        								 year : $scope.yearOfIssue}
        }).then(
          function (response) { 
        	 $scope.books = response.data;
        },
          function (failure) { console.log("failed :(", failure); });
	}
//       $scope.books = booksExample;
}]); 

angular.module('bookUnity')
.controller('ShowCtrl',['$scope','$rootScope','$http',function ($scope,$rootScope, $http) {
 /*       $http.get('/v2/book/showBooks', { params:{ login: $rootScope.currentUser }},
          function (response) { 
        	 $scope.books = response.data;
        },
          function (failure) { console.log("failed :(", failure); });*/
       $scope.books = booksExample;
}]); 

angular.module('bookUnity')
.controller('LoginCtrl',['$scope','$rootScope','$http',function ($scope,$rootScope, $http) {	
	$scope.login="";
        $scope.login1 = function() {
        	$rootScope.currentUser = $scope.login;
        };
}]); 


angular.module('bookUnity')
.directive("books", function () {
    return {
        restrict: "A",
        template:   '<div class="col-xs-12 col-md-6 col-lg-4" ng-repeat="item in books">'+
    '<div class="thumbnail book-card2" >'+
    '<a target="_self" class="linker">'+
       ' <img src="../img/books-1673578_1280.png">' +
        '<div class="about-video"> ' +
            '<div class="video-description">' +
                '<div class="name">{{item.name}}</div>'+
               ' <div> '+
                   ' <span class="name">Author: </span> '+
                   ' <span class="ex">{{item.author}}</span> '+
               '</div>'+
               '<div>'+
                   ' <span class="name">Language: </span> '+
                   ' <span class="ex">{{item.language}}</span> '+
               '</div>'+
               '<div>'+
                    '<span class="name">Year: </span>'+
                    '<span class="ex">{{item.yearOfIssue}}</span>'+
               '</div>'+
               '<div>'+
                    '<span class="name">Publishing house: </span>'+
                    '<span class="ex">{{item.publishingHouse}}</span>'+
               '</div>'+
               '<div>'+
                    '<span class="name">Description: </span>'+
                    '<span class="ex">{{item.description}}</span>'+
               '</div>'+
               '<div>'+
                    '<span class="name">Number of Pages: </span>'+
                    '<span class="ex">{{item.numberOfPages}}</span>'+
               '</div>'+
               '<div>'+
                    '<span class="name">Price: </span>'+
                    '<span class="ex">{{item.price}}</span>'+
               '</div>'+
               '</div>'+
          '</div>'+
    '</a>'+
    '<p class="ex">{{item.impression}}</p>'+
'</div>'+
'</div>'
}

});