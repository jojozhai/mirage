'use strict';
//平台管理模块的配置
angular.module('swiperAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.swiperManage', {
		url: "/swiperManage",
		controller: "swiperManageCtrl",
		templateUrl: "admin/views/swiper/swiperManage.html"
	});
//服务配置
}).service("swiperRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	return $resource("swiper/:id", {id:"@id"}, config);
//控制器
}).controller('swiperManageCtrl', function($scope, $uibModal, swiperRestService, commonService) {

	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		swiperRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.swipers = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({});
	}
	
	$scope.update = function(swiper) {
		$scope.save(swiper);
	}
	
	$scope.save = function(swiper){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/swiper/swiperForm.html',
			controller: 'swiperFormCtrl',
			resolve: {
		        swiper : function() {return swiper;},
			}
		}).result.then(function(form){
			if(form.id){
				new swiperRestService(form).$save().then(function(){
					commonService.showMessage("修改轮播图信息成功");
				},function(response){
					for (var i = 0; i < $scope.swipers.length; i++) {
						if(form.id == $scope.swipers[i].id) {
							$scope.swipers[i] = swiperRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new swiperRestService(form).$create().then(function(swiper){
					$scope.swipers.unshift(swiper);
					commonService.showMessage("新建轮播图成功");
				});
			}
		});
	}
	
	$scope.remove = function(swiper) {
		commonService.showConfirm("您确认要删除此轮播图?").result.then(function() {
			swiperRestService.remove({id:swiper.id});
		}).then(function(){
			commonService.showMessage("删除轮播图成功");
			$scope.swipers.splice($scope.swipers.indexOf(swiper), 1);
			if($scope.swipers.length == 0){
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
	
}).controller('swiperFormCtrl',function ($scope, $uibModalInstance, swiper, commonService) {
	
	$scope.swiper = swiper;
	
	$scope.save = function(swiper) {
		$uibModalInstance.close(swiper);
	};
	
	$scope.doUpload = function(files){
		commonService.uploadImage(files, $scope, function(imageUrl){
			$scope.swiper.image = imageUrl;
		})		
	}
	
});