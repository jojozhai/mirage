'use strict';
//平台管理模块的配置
angular.module('voteWeixinModule',['weixin', 'userWeixinModule', 'akoenig.deckgrid', 'ui.bootstrap']).config(function($stateProvider) {
	//路由定义
	$stateProvider.state('vote', {
		url: "/vote?id",
		controller: 'voteMainCtrl',
		templateUrl: "weixin/views/vote/main.html"
	}).state('vote.activity', {
		url: "/activity",
		controller: 'voteActivityCtrl',
		templateUrl: "weixin/views/vote/activity.html",
    }).state('vote.enroll', {
		url: "/enroll",
		controller: 'voteEnrollCtrl',
		templateUrl: "weixin/views/vote/enroll.html", 
    }).state('vote.vote', {
		url: "/vote/:enrollId",
		controller: 'voteVoteCtrl',
		templateUrl: "weixin/views/vote/vote.html", 
    }).state('vote.user', {
		url: "/user",
		controller: 'voteUserInfoCtrl',
		templateUrl: "weixin/views/vote/info.html", 
    });

}).service("voteActivityRestService", function($resource, commonService){
	return $resource("voteActivity/:id", {id:"@id"}, commonService.getDefaultRestSetting());
}).service("enrollRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.vote = {method: "POST", url:"enroll/:id/vote"};
	config.search = {method: "GET", url:"enroll/search"};
	return $resource("enroll/:id", {id:"@id"}, config);
}).controller("voteMainCtrl", function($scope, $state, $stateParams, $rootScope, $location, $http, voteActivityRestService, enrollRestService, userRestService, commonService){
	
	$scope.user = userRestService.current();
	
	$scope.gotoEnrollPage = function() {
		if($scope.user.id) {
			$state.go("vote.enroll");
		}else{
			$scope.tipFollow();
		}
	}
	
	$scope.tipFollow = function() {
		_system._follow(true);
	}
	
	voteActivityRestService.get({id:$stateParams.id}).$promise.then(function(result){
		$scope.activity = result;
//		angular.element("#loadingDiv").hide();
	});
	
	$scope.vote = function(enroll) {
		if($scope.user.id) {
			enrollRestService.vote({id: enroll.id}).$promise.then(function(result){
				if(result.content == "info"){
					toastr["info"]("<a href='#/vote/user?id="+$stateParams.id+"'>每天只能投一票哦，点击这里完善信息后可以多次投票，还可获得神秘大奖!</a>")
				}else{
					commonService.showMessage("投票成功");
					$scope.activity.voteCount = $scope.activity.voteCount + 1;
					enroll.voteCount = enroll.voteCount + 1;
				}
			});
		}else{
			$scope.tipFollow();
		}
	}
	
	$scope.getShareConfig = function(type, id) {
		return {
			title: "我是"+$scope.user.nickname+",我在参加'好多车友'的投票活动,求助力!",
			desc: "报名参加或参与投票都有机会赢取大奖哦",
			link: "https://open.weixin.qq.com/connect/oauth2/authorize" +
					"?appid=wx2622b448b854003a" +
					"&redirect_uri=http%3A%2F%2Fapp.haoduocheyou.com%2Fweixin%2Fweixin%2Foauth" +
					"&response_type=code" +
					"&scope=snsapi_base" +
					"&state="+type+"_"+id+"#wechat_redirect",
			imgUrl: $scope.activity.images[0]
		}
	}
	
}).controller("voteActivityCtrl", function($scope, $state, $stateParams, enrollRestService, commonService, weixinService){
	
	weixinService.initWx(function(){
		var config = $scope.getShareConfig("activity", $stateParams.id);
		weixinService.shareConfig(config.title, config.desc, config.link, config.imgUrl);
	});
	
	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.view = "new";
	$scope.grid = "flow";
	
	$scope.query = function() {
		if($scope.view == 'new'){
			$scope.pageInfo.sort = "createdTime,desc";
		}else{
			$scope.pageInfo.sort = "voteCount,desc";
		}
		var condition = commonService.buildPageCondition({activityId:$stateParams.id}, $scope.pageInfo);
		enrollRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.enrolls = data.content;
		});
	}
	
	$scope.changePage = function() {
		$scope.query();
	}
	
	$scope.changeGrid = function(view) {
		$scope.view = view;
		if(view == 'new'){
			$scope.grid = "flow";
		}else{
			if(view == 'rank'){
				$scope.grid = "flow";
			}else if(view == 'rank300'){
				$scope.grid = "grid";
			}
		}
		$scope.query();
	}
	
	$scope.search = function(key) {
		$scope.condition = "";
		enrollRestService.search({key:key, activityId: $stateParams.id}).$promise.then(function(data){
			$state.go("vote.vote", {enrollId: data.id})
		});
	}
	
	$scope.query();
	
}).controller("voteEnrollCtrl", function($scope, $state, $stateParams, $http, $location, uiUploader, enrollRestService, commonService, weixinService){
	
	weixinService.initWx(function(){
		var config = $scope.getShareConfig("activity", $stateParams.id);
		weixinService.shareConfig(config.title, config.desc, config.link, config.imgUrl);
	});
	
	if(!$scope.user.id) {
		$state.go("vote.activity");
	}
	
	$scope.enroll = {images:[]};
	
	$scope.save = function(enroll) {
		enroll.activityId = $scope.activity.id;
		if(enroll.images.length == 0){
			toastr["warning"]("请上传图片");
		}else{
			new enrollRestService(enroll).$create().then(function(enroll){
				commonService.showMessage("报名成功");
				$scope.activity.enrollCount = $scope.activity.enrollCount + 1;
				$state.go("vote.activity");
			});
		}
	}
	
	$scope.choiceImage = function(){
		if($scope.enroll.images.length == 3){
			toastr["warning"]("最多只能上传三张图片");
		}else{
			weixinService.wxUpload($scope.enroll.images, 3);
		}
	}
    
}).controller("voteVoteCtrl", function($scope, $stateParams, $location, enrollRestService, weixinService){
	
	enrollRestService.get({id: $stateParams.enrollId}).$promise.then(function(result){
		 $scope.enroll = result;
		 weixinService.initWx(function(){
			var config = $scope.getShareConfig("vote", $stateParams.enrollId);
			weixinService.shareConfig(config.title, config.desc, config.link, config.imgUrl);
		 });
	});
	
	$scope.search = function(key) {
		$scope.condition = "";
		enrollRestService.search({key:key, activityId: $stateParams.id}).$promise.then(function(data){
			$scope.enroll = data;
		});
	}
	
}).controller("voteUserInfoCtrl", function($scope, $state, userRestService){
	
	$scope.updateUserInfo = function(user){
		userRestService.updateProperty({name:"realname", value: user.name}).$promise.then(function(){
			userRestService.updateProperty({name:"mobile", value: user.mobile}).$promise.then(function(){
				$state.go("vote.activity");
			});
		});
	}
	
});