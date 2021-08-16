app.controller("order-ctrl", function($scope, $http){
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};

    $scope.initialize = function(){
        //load products
        $http.get("/rest/orders").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate)
            })  
        });
        //load categories
        $http.get("/rest/accounts").then(resp => {
            $scope.cates = resp.data;
        });
    }



    //khởi đầu
    $scope.initialize();


    //xóa form
    $scope.reset = function(){
        $scope.form = { 
            createDate: new Date(),

        };
    }

    //hiển thị lên form
    $scope.edit = function(item){
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show')
    }

    //thêm sản phẩm mới
    $scope.update = function(){
        var item = angular.copy($scope.form);
        $http.put(`/rest/orders/${item.id}`, item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);   
            $scope.items[index] = item;
            alert("Cập nhật đơn hàng thành công!");
        }).catch(error => {
            alert("Lỗi cập nhật đơn hàng!");
            console.log("Error",error);
        });
    }

    //xóa sản phẩm
    $scope.delete = function(item){
        $http.delete(`/rest/orders/${item.id}`).then(resp => { 
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert("Xóa đơn hàng thành công!");
        }).catch(error => {
            alert("Lỗi xóa đơn hàng!");
            console.log("Error",error);
        });
    }


    $scope.pager = {
        page: 0,
        size: 20,
        get items(){
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count(){
            return Math.ceil(1.0 * $scope.items.length / this.size);
        },
        first(){
            this.page = 0;
        },
        prev(){
            this.page--;
            if(this.page < 0){
                this.last();
            }
        },
        next(){
            this.page++;
            if(this.page >= this.count){
                this.first();
            }
        },
        last(){
            this.page = this.count - 1;
        }
    }

    

});