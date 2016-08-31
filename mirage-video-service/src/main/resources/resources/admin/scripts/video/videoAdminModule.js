'use strict';
//平台管理模块的配置
angular.module('videoAdminModule',['tagAdminModule','videoSetAdminModule']).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.videoManage', {
		url: "/videoManage",
		controller: "videoManageCtrl",
		templateUrl: "admin/views/video/videoManage.html"
	});
//服务配置
}).service("videoRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	return $resource("video/:id", {id:"@id"}, config);
//控制器
}).controller('videoManageCtrl', function($scope, $uibModal, videoRestService, tagRestService, commonService) {

	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		videoRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.videos = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({
			selection: false,
			buyerCount: 0,
			playCount: 0
		});
	}
	
	$scope.update = function(video) {
		$scope.save(video);
	}
	
	$scope.save = function(video){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/video/videoForm.html',
			controller: 'videoFormCtrl',
			resolve: {
		        video : function() {
		        	if(video.id){
		        		video = videoRestService.get({
		        			id : video.id
		        		});
		        	}
		        	return video;
		        },
		        tinymceOptions : function() {return $scope.tinymceOptions;}
			}
		}).result.then(function(form){
			if(form.id){
				new videoRestService(form).$save().then(function(result){
					commonService.showMessage("修改视频信息成功");
					for (var i = 0; i < $scope.videos.length; i++) {
						if(form.id == $scope.videos[i].id) {
							$scope.videos[i] = result;
							break;
						}
					}
				},function(response){
					for (var i = 0; i < $scope.videos.length; i++) {
						if(form.id == $scope.videos[i].id) {
							$scope.videos[i] = videoRestService.get({id:form.id});
							break;
						}
					}
				});
			}else{
				new videoRestService(form).$create().then(function(video){
					$scope.videos.unshift(video);
					commonService.showMessage("新建视频成功");
				});
			}
		});
	}
	
	$scope.remove = function(video) {
		commonService.showConfirm("您确认要删除此视频?").result.then(function() {
			videoRestService.remove({id:video.id});
		}).then(function(){
			commonService.showMessage("删除视频成功");
			$scope.videos.splice($scope.videos.indexOf(video), 1);
			if($scope.videos.length == 0){
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
	
}).controller('videoFormCtrl',function ($scope, $uibModalInstance, video, uiUploader, tagRestService, videoSetRestService) {
	
	$scope.video = video;
	
	$scope.tags = tagRestService.toOptions();
	
	$scope.sets = videoSetRestService.findAll();
	
	$scope.save = function(video) {
		$uibModalInstance.close(video);
	};
	
	$scope.doUpload = function(files){
//		console.log(files);
		uiUploader.addFiles(files);
		uiUploader.startUpload({
            url: 'image/upload',
            onCompleted: function(file, response) {
            	$scope.video.image = angular.fromJson(response).content;
            	uiUploader.removeAll();
            	$scope.$apply();
            }
        });
	}
	
});