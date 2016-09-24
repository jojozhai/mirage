'use strict';
//平台管理模块的配置
angular.module('withdrawalsAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.withdrawalsManage', {
		url: "/withdrawalsManage",
		controller: "withdrawalsManageCtrl",
		templateUrl: "admin/views/withdrawalsManage.html"
	});
//服务配置
}).service("withdrawalsRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.findAll = {url:"withdrawals/all", method:"GET", isArray:true};
	return $resource("withdrawals/:id", {id:"@id"}, config);
//控制器
}).controller('withdrawalsManageCtrl', function($scope, $uibModal, withdrawalsRestService, commonService) {

	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		withdrawalsRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.withdrawalss = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({});
	}
	
	$scope.update = function(withdrawals) {
		$scope.save(withdrawals);
	}
	
	$scope.save = function(withdrawals){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/withdrawalsForm.html',
			controller: 'withdrawalsFormCtrl',
			resolve: {
		        withdrawals : function() {return withdrawals;},
			}
		}).result.then(function(form){
			if(form.id){
				new withdrawalsRestService(form).$save().then(function(){
					commonService.showMessage("提现发放成功");
				},function(response){
					for (var i = 0; i < $scope.withdrawalss.length; i++) {
						if(form.id == $scope.withdrawalss[i].id) {
							$scope.withdrawalss[i] = withdrawalsRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new withdrawalsRestService(form).$create().then(function(withdrawals){
					$scope.withdrawalss.unshift(withdrawals);
					commonService.showMessage("新建提现成功");
				});
			}
		});
	}
	
	$scope.remove = function(withdrawals) {
		commonService.showConfirm("您确认要删除此提现?").result.then(function() {
			withdrawalsRestService.remove({id:withdrawals.id});
		}).then(function(){
			commonService.showMessage("删除提现成功");
			$scope.withdrawalss.splice($scope.withdrawalss.indexOf(withdrawals), 1);
			if($scope.withdrawalss.length == 0){
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
	
}).controller('withdrawalsFormCtrl',function ($scope, $uibModalInstance, withdrawals, commonService) {
	
	$scope.withdrawals = withdrawals;
	
	$scope.tinymceOptions = commonService.getDefaultTinymceOptions();
	
	$scope.save = function(withdrawals) {
		$uibModalInstance.close(withdrawals);
	};
	
	$scope.doUpload = function(files){
		commonService.uploadImage(files, $scope, function(imageUrl){
			$scope.withdrawals.image = imageUrl;
		})		
	}
	
}).filter('withdrawalsState', function(){
	return function (text) {
		if(text == 'INIT'){
			return "未发放";
		}else if(text == 'FINISH'){
			return "已发放";
		}
        return "未知";
    }
});