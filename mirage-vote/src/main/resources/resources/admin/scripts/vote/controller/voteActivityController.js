'use strict';
angular.module('voteAdminModule').controller('voteActivityManageCtrl', function($scope, $state, $uibModal, voteActivityRestService, commonService) {
	
	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.rank = function(activity) {
		$uibModal.open({
			templateUrl : 'admin/views/vote/enrollManage.html',
			controller: 'enrollManageCtrl',
			size: 'lg',
			resolve: {
		        id : function() {return activity.id;},
			}
		})
	}
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		voteActivityRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.activities = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({images:[], enrollCount:0, voteCount:0, browseCount:0});
	}
	
	$scope.update = function(activity) {
		$scope.save(activity);
	}
	
	$scope.save = function(activity){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/vote/voteActivityForm.html',
			controller: 'voteActivityFormCtrl',
			resolve: {
				activity : function() {return activity;},
		        tinymceOptions : function() {return $scope.tinymceOptions;}
			}
		}).result.then(function(form){
			if(form.id){
				new voteActivityRestService(form).$save().then(function(){
					commonService.showMessage("修改投票活动信息成功");
				},function(response){
					for (var i = 0; i < $scope.activities.length; i++) {
						if(form.id == $scope.activities[i].id) {
							$scope.activities[i] = voteActivityRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new voteActivityRestService(form).$create().then(function(voteActivity){
					$scope.activities.unshift(voteActivity);
					commonService.showMessage("新建投票活动成功");
				});
			}
		});
	}
	
	$scope.remove = function(activity) {
		commonService.showConfirm("您确认要删除此投票活动?").result.then(function() {
			voteActivityRestService.remove({id:activity.id});
		}).then(function(){
			commonService.showMessage("删除投票活动成功");
			$scope.activities.splice($scope.activities.indexOf(activity), 1);
			if($scope.activities.length == 0){
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

}).controller('voteActivityFormCtrl', function($scope, $uibModalInstance, tinymceOptions, uiUploader, activity) {
	
	$scope.popup1 = {
		opened : false
	};

	$scope.popup2 = {
		opened : false
	};
	
	$scope.open1 = function() {
		$scope.popup1.opened = true;
	};

	$scope.open2 = function() {
		$scope.popup2.opened = true;
	};
	
	$scope.dateOptions = {
		minDate : new Date(),
		startingDay : 1
	};
	
	$scope.vote = activity;
	
	$scope.save = function(poster) {
		$uibModalInstance.close(poster);
	};
	
	$scope.tinymceOptions = tinymceOptions;
	
	$scope.doUpload = function(files){
//		console.log(files);
		uiUploader.addFiles(files);
		uiUploader.startUpload({
            url: 'image/upload',
            onCompleted: function(file, response) {
            	$scope.vote.images = [angular.fromJson(response).content];
            	uiUploader.removeAll();
            	$scope.$apply();
            }
        });
	}
	    
});