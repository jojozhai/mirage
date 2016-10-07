'use strict';
//平台管理模块的配置
angular.module('orderAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.orderManage', {
		url: "/orderManage",
		controller: "orderManageCtrl",
		templateUrl: "admin/views/orderManage.html"
	});
//服务配置
}).service("orderRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	return $resource("order/:id", {id:"@id"}, config);
//控制器
}).controller('orderManageCtrl', function($scope, $uibModal, orderRestService, commonService) {

	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		orderRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.orders = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({});
	}
	
	$scope.update = function(order) {
		$scope.save(order);
	}
	
	$scope.updateState = function(order, state) {
		commonService.showConfirm("您确认要修改订单状态?").result.then(function() {
			orderRestService.save({id: order.id, state: state}).$promise.then(function(){
				order.state = state;
				commonService.showMessage("修改订单状态成功");
			});
		})
	}
	
	$scope.save = function(order){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/orderForm.html',
			controller: 'orderFormCtrl',
			resolve: {
		        order : function() {return order;},
			}
		}).result.then(function(form){
			if(form.id){
				new orderRestService(form).$save().then(function(){
					commonService.showMessage("修改订单信息成功");
				},function(response){
					for (var i = 0; i < $scope.orders.length; i++) {
						if(form.id == $scope.orders[i].id) {
							$scope.orders[i] = orderRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new orderRestService(form).$create().then(function(order){
					$scope.orders.unshift(order);
					commonService.showMessage("新建订单成功");
				});
			}
		});
	}
	
	$scope.remove = function(order) {
		commonService.showConfirm("您确认要删除此订单?").result.then(function() {
			orderRestService.remove({id:order.id});
		}).then(function(){
			commonService.showMessage("删除订单成功");
			$scope.orders.splice($scope.orders.indexOf(order), 1);
			if($scope.orders.length == 0){
				$scope.pageInfo.page = $scope.pageInfo.page - 1;
				$scope.query();
			}
		});
	} 
	
	$scope.cleanCondition = function() {
		$scope.condition = {};
		$scope.query();
	}
	
	$scope.query();
	
}).controller('orderFormCtrl',function ($scope, $uibModalInstance, order, commonService) {
	
	$scope.order = order;
	
	$scope.tinymceOptions = commonService.getDefaultTinymceOptions();
	
	$scope.save = function(order) {
		$uibModalInstance.close(order);
	};
	
	$scope.doUpload = function(files){
		commonService.uploadImage(files, $scope, function(imageUrl){
			$scope.order.image = imageUrl;
		})		
	}
	
}).filter('orderState', function(){
	return function (text) {
		if(text == 'INIT'){
			return "未支付";
		}else if(text == 'PAYED'){
			return "已支付";
		}else if(text == 'WORKING'){
			return "工作中";
		}else if(text == 'CANCEL'){
			return "已取消";
		}else if(text == 'COMPLETE'){
			return "工作完成";
		}else if(text == 'FINISH'){
			return "订单完成";
		}
        return "未知";
    }
});