'use strict';
angular.module('voteAdminModule').controller('enrollManageCtrl', function($scope, commonService, enrollRestService, id) {
	
	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.isConditionCollapsed = true;
	
	$scope.condition = {activityId:id};
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		condition.sort = "voteCount,desc";
		enrollRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.enrolls = data.content;
		});
	}
	
	$scope.remove = function(enroll) {
		commonService.showConfirm("您确认要删除此报名信息?").result.then(function() {
			enrollRestService.remove({id:enroll.id});
		}).then(function(){
			commonService.showMessage("删除报名信息成功");
			$scope.enrolls.splice($scope.enrolls.indexOf(enroll), 1);
			if($scope.enrolls.length == 0){
				$scope.pageInfo.page = $scope.pageInfo.page - 1;
				$scope.query();
			}
		});
	} 
	
	$scope.cleanCondition = function() {
		$scope.condition = {activityId:1};
		$scope.query();
	}
	
	$scope.changePage = function() {
		$scope.query();
	}
	
	$scope.query();
	
	
}).controller('enrollFormCtrl',function ($scope, $uibModalInstance, enroll, roles) {

	$scope.enroll = enroll;
	
	$scope.save = function(enroll) {
		$uibModalInstance.close(enroll);
	};
	
});