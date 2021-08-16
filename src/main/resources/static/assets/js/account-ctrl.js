app.controller("account-ctrl", function($scope, $http){
 //thêm tài khoản mới
 $scope.form = {};
 $scope.items = [];

    $scope.create = function(){
    var item = angular.copy($scope.form);
    $http.post(`/rest/accounts`, item).then(resp => {
           $scope.items.push(resp.data);
           $scope.reset();  
           alert("Tạo tài khoản thành công!");
       }).catch(error =>{
           alert("Tạo tài khoản thất bại!");
           console.log("Error",error);
       });
    }

    $scope.reset = function(){
        $scope.form = { 

        };
    }
 
});