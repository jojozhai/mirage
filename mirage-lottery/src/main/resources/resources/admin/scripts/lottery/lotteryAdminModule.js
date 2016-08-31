'use strict';
//平台管理模块的配置
angular.module('lotteryAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.lotteryManage', {
		url: "/lotteryManage",
		controller: "lotteryManageCtrl",
		templateUrl: "admin/views/lottery/lotteryManage.html"
	});
//服务配置
}).service("lotteryRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	return $resource("lottery/:id", {id:"@id"}, config);
}).service("lotteryUserRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	return $resource("lotteryUser/:id", {id:"@id"}, config);
}).controller('lotteryManageCtrl', function($scope, $uibModal, lotteryRestService, commonService){
	
	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		lotteryRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.lotterys = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({
			prizes: []
		});
	}
	
	$scope.update = function(lottery) {
		$scope.save(lottery);
	}
	
	$scope.save = function(lottery){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/lottery/lotteryForm.html',
			controller: 'lotteryFormCtrl',
			resolve: {
		        lottery : function() {return lottery;},
			}
		}).result.then(function(form){
			if(form.id){
				new lotteryRestService(form).$save().then(function(){
					commonService.showMessage("修改抽奖信息成功");
				},function(response){
					for (var i = 0; i < $scope.lotterys.length; i++) {
						if(form.id == $scope.lotterys[i].id) {
							$scope.lotterys[i] = lotteryRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new lotteryRestService(form).$create().then(function(lottery){
					$scope.lotterys.unshift(lottery);
					commonService.showMessage("新建抽奖成功");
				});
			}
		});
	}
	
	$scope.remove = function(lottery) {
		commonService.showConfirm("您确认要删除此抽奖?").result.then(function() {
			lotteryRestService.remove({id:lottery.id});
		}).then(function(){
			commonService.showMessage("删除抽奖成功");
			$scope.lotterys.splice($scope.lotterys.indexOf(lottery), 1);
			if($scope.lotterys.length == 0){
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
	
	$scope.rank = function(lottery) {
		$uibModal.open({
			templateUrl : 'admin/views/lottery/lotteryUserManage.html',
			controller: 'lotteryUserManageCtrl',
			size: 'lg',
			resolve: {
		        id : function() {return lottery.id;},
			}
		})
	}
	
}).controller('lotteryUserManageCtrl',function ($scope, $uibModalInstance, id, commonService, lotteryUserRestService) {
	
	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.isConditionCollapsed = true;
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		condition.lotteryId = id;
		lotteryUserRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.lotteryUsers = data.content;
		});
	}

	$scope.query();
	
}).controller('lotteryFormCtrl',function ($scope, $uibModalInstance, lottery, commonService) {
	
	$scope.popup1 = {
		opened : false
	};

	$scope.open1 = function() {
		$scope.popup1.opened = true;
	};

	$scope.dateOptions = {
		minDate : new Date(),
		startingDay : 1
	};

	$scope.lottery = lottery;
	
	$scope.tinymceOptions = commonService.getDefaultTinymceOptions();
	
	$scope.save = function(lottery) {
		$uibModalInstance.close(lottery);
	};
	
	$scope.addPrize = function(newPrize){
		if($scope.lottery.prizes.indexOf(newPrize) == -1){
			$scope.lottery.prizes.push(newPrize);
			$scope.newPrize = "";
		}else{
			newPrize = newPrize + " ";
			$scope.addPrize(newPrize);
		}
	}
	
	$scope.addPrize2 = function(e){
		var keycode = window.event?e.keyCode:e.which;
        if(keycode==13){
        	$scope.addPrize($scope.newPrize);
        }
	}
	
	$scope.removePrize = function(prize){
		$scope.lottery.prizes.splice($scope.lottery.prizes.indexOf(prize), 1);
	}
});