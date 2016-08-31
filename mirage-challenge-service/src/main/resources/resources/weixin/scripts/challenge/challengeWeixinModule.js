'use strict';
//平台管理模块的配置
angular.module('challengeWeixinModule',['socialWeixinModule', 'applyWeixinModule']).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('app', {
		abstract: true,
		controller: "appMainCtrl",
		templateUrl: "weixin/views/challenge/main.html"
	}).state('app.index', {
		url: "/index",
		controller: "indexCtrl",
		templateUrl: "weixin/views/challenge/index.html"
	}).state('app.my', {
		url: "/my",
		controller: "myCtrl",
		templateUrl: "weixin/views/challenge/my.html"
	}).state('app.onlookers', {
		url: "/onlookers",
		controller: "onlookersCtrl",
		templateUrl: "weixin/views/challenge/onlookers.html"
	}).state('app.challenge', {
		abstract: true,
		url: "/challenge",
		templateUrl: "weixin/views/challenge/challengeMain.html"
	}).state('app.challenge.list', {
		url: "/list",
		controller: "challengeListCtrl",
		templateUrl: "weixin/views/challenge/challengeList.html"
	}).state('app.challenge.details', {
		url: "/details?id",
		controller: "challengeDetailsCtrl",
		templateUrl: "weixin/views/challenge/challengeDetails.html"
	}).state('app.signUp', {
		abstract: true,
		url: "/signUp?id&type",
		controller: "signUpCtrl",
		templateUrl: "weixin/views/challenge/signUp.html"
	}).state('app.signUp.offical', {
		url: "/offical",
		controller: "signUpOfficalCtrl",
		templateUrl: "weixin/views/challenge/signUpOffical.html"
	}).state('app.signUp.personal', {
		url: "/personal",
		controller: "signUpPersonalCtrl",
		templateUrl: "weixin/views/challenge/signUpPersonal.html"
	});
//服务配置
}).service("challengeRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.findAll = {url:"challenge/all", method:"GET", isArray:true};
	return $resource("challenge/:id", {id:"@id"}, config);
}).service("challengeTaskRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.findByChallenge = {url:"challenge/:id/task", method:"GET", isArray:true};
	return $resource("challenge/:id", {id:"@id"}, config);
}).service("participatorRestService", function($resource, commonService){
	return $resource("participator/:id", {id:"@id"}, commonService.getDefaultRestSetting());
}).service("userChallengeRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.updateRemind = {url:"userChallenge/:id/remind", method:"PUT"};
	config.updatePrivacy = {url:"userChallenge/:id/privacy", method:"PUT"};
	return $resource("userChallenge/:id", {id:"@id"}, config);
	//控制器
}).controller('appMainCtrl', function($scope, userRestService, commonService) {
	
	$scope.config = {
		showFooter : true
	}
	
}).controller('challengeListCtrl', function($scope, $state, challengeRestService, commonService) {
	
	$scope.config.showFooter = true;
	
	$scope.challenges = challengeRestService.findAll();
	
	$scope.toInfoPage = function(challenge) {
		$state.go("app.signUp.offical", {id:challenge.id, type:"OFFICAL"});
	}
	
}).controller('challengeDetailsCtrl', function($scope, $stateParams, 
		userChallengeRestService, participatorRestService, postRestService, socialRestService, commonService) {
	
	$scope.config.showFooter = false;
	
	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	userChallengeRestService.get({id:$stateParams.id}).$promise.then(function(result){
		$scope.userChallenge = result;
		if(result.status == "PROGRESSING"){
			$scope.participator = participatorRestService.get({id:$stateParams.id});
		}
	});
	
	$scope.posts = [];
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition({
			containerType:'challenge',
			containerId:$stateParams.id
		}, $scope.pageInfo);
		postRestService.query(condition).$promise.then(function(data){
			$scope.posts = $scope.posts.concat(data.content);
			$scope.scrollable = true;
		});
	}
	
	$scope.scrollable = true;
	
	$scope.getNextPage = function() {
		if($scope.scrollable){
			$scope.scrollable = false;
			if($scope.posts.length != 0){
				$scope.pageInfo.page = $scope.pageInfo.page + 1;
				$scope.query();
			}
		}
	}
	
	$scope.query();
	
	$scope.praise = function(post) {
		socialRestService.praise({
			target: "post",
			targetId: post.id
		}).$promise.then(function(result){
			if(result != null){
				post.praises.push(result);
			}
		});
	}
	
	var currentPost;
	
	$scope.showCommentView = function(post) {
		currentPost = post;
		$scope.currentView = "comment";
		$scope.comment = {};
	}
	
	$scope.reply = function(post, comment){
		$scope.showCommentView(post);
		$scope.comment.content = "回复 "+comment.createrName+":";
		$scope.comment.replyToId = comment.createrId;
		$scope.comment.replyToName = comment.createrName;
	}
	
	$scope.doComment = function(comment){
		comment.target = "post";
		comment.targetId = currentPost.id;
		socialRestService.comment(comment).$promise.then(function(result){
			$scope.currentView = "info";
			currentPost.comments.push(result);
		});
	}
	
	$scope.showPostView = function() {
		$scope.currentView = "post";
		$scope.post = {};
	}
	
	$scope.showView = function(view){
		$scope.currentView = view;
	}

	$scope.saveRemind = function() {
		var remind = $scope.userChallenge.remind;
		remind.id = $scope.userChallenge.id;
		userChallengeRestService.updateRemind(remind).$promise.then(function(result){
			$scope.showView('info');
		});
	}
	
	$scope.changePrivacy = function(userChallengeId) {
		userChallengeRestService.updatePrivacy({id:userChallengeId}).$promise.then(function(result){
			$scope.userChallenge.privacy = !$scope.userChallenge.privacy;
		});
	}
	
	$scope.doPost = function(post){
		post.createrType = "user";
		post.containerId = $stateParams.id;
		post.containerType = 'challenge';
		postRestService.create(post).$promise.then(function(result){
			$scope.currentView = "info";
			$scope.posts.unshift(result);
		});
	}
	
	$scope.currentView = "info";
	
}).controller('signUpCtrl', function($scope, $state, $stateParams, userChallengeRestService) {
	
	$scope.currentView = "info";
	
	$scope.showView = function(view){
		$scope.currentView = view;
	}
	
	$scope.saveRemind = function() {
		$scope.showView('info');
	}
	
	$scope.userChallenge = {
		type: $stateParams.type,
		challengeId: $stateParams.id,
		pledge : 30,
		days : 1,
		privacy : false,
		remind : {
			remind: true,
			remindHour: "9",
			remindMinute: "00",
			remindMon: true,
			remindTue: true,
			remindWed: true,
			remindThu: true,
			remindFri: true,
			remindSat: true,
			remindSun: true,
		}
	}
	
	$scope.choicePledge = function(pledge) {
		$scope.userChallenge.pledge = pledge;
	}
	
	$scope.signUp = function() {
		userChallengeRestService.create($scope.userChallenge).$promise.then(function(result){
			$state.go("app.challenge.details", {id:result.id})
		});
	}
	
}).controller('signUpOfficalCtrl', function($scope, $stateParams, challengeRestService, challengeTaskRestService) {
	
	$scope.config.showFooter = false;
	$scope.challenge = challengeRestService.get({id:$stateParams.id})
	$scope.tasks = challengeTaskRestService.findByChallenge({id:$stateParams.id});
	
}).controller('signUpPersonalCtrl', function($scope) {
	
}).controller('indexCtrl', function($scope) {
	
}).controller('myCtrl', function($scope) {
	
}).controller('onlookersCtrl', function($scope) {
	
});