'use strict';
//平台管理模块的配置
angular.module('articleAdminModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.articleManage', {
		url: "/articleManage",
		controller: "articleManageCtrl",
		templateUrl: "admin/views/article/articleManage.html"
	});
//服务配置
}).service("articleRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	return $resource("article/:id", {id:"@id"}, config);
//控制器
}).controller('articleManageCtrl', function($scope, $uibModal, articleRestService, commonService){
	
	$scope.pageInfo = commonService.getDefaultPageSetting();
	
	$scope.query = function() {
		var condition = commonService.buildPageCondition($scope.condition, $scope.pageInfo);
		articleRestService.query(condition).$promise.then(function(data){
			$scope.pageInfo.totalElements = data.totalElements;
			$scope.articles = data.content;
		});
	}
	
	$scope.create = function() {
		$scope.save({
			business: false,
			enable: false,
			top: false,
			readCount: 1000
		});
	}
	
	$scope.update = function(article) {
		$scope.save(article);
	}	
	
	$scope.save = function(article){
		$uibModal.open({
			size: "lg",
			templateUrl : 'admin/views/article/articleForm.html',
			controller: 'articleFormCtrl',
			resolve: {
		        article : function() {
		        	if(article.id){
		        		article = articleRestService.get({
		        			id : article.id
		        		});
		        	}
		        	return article;
		        },
		        tinymceOptions : function() {return commonService.getDefaultTinymceOptions();}
			}
		}).result.then(function(form){
			if(form.id){
				new articleRestService(form).$save().then(function(result){
					commonService.showMessage("修改视频信息成功");
					for (var i = 0; i < $scope.articles.length; i++) {
						if(form.id == $scope.articles[i].id) {
							$scope.articles[i] = result;
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
				new articleRestService(form).$create().then(function(article){
					$scope.articles.unshift(article);
					commonService.showMessage("新建资讯成功");
				});
			}
		});
	}
	
	
	
	$scope.remove = function(article) {
		commonService.showConfirm("您确认要删除此资讯?").result.then(function() {
			articleRestService.remove({id:article.id}).$promise.then(function(){
				commonService.showMessage("删除资讯成功");
				$scope.articles.splice($scope.articles.indexOf(article), 1);
				if($scope.articles.length == 0){
					$scope.pageInfo.page = $scope.pageInfo.page - 1;
					$scope.query();
				}
			});
		});
	} 
	
	$scope.cleanCondition = function() {
		$scope.condition = {};
		$scope.query();
	}
	
	$scope.query();
	
}).controller('articleFormCtrl',function ($scope, $uibModalInstance, tinymceOptions, article, commonService, tagRestService) {

	$scope.popup1 = {
		opened : false
	};

	$scope.open1 = function() {
		$scope.popup1.opened = true;
	};
	
	$scope.dateOptions = {
		startingDay : 1
	};
	
	$scope.article = article;
	
	$scope.tags = tagRestService.getChildTags();
		
	$scope.tinymceOptions = tinymceOptions;
	
	$scope.save = function(article) {
		$uibModalInstance.close(article);
	};
	
	$scope.doUpload = function(files){
		commonService.uploadImage(files, $scope, function(result){
			$scope.article.image = result;
		});
	}
	
	$scope.doUpload2 = function(files){
		commonService.uploadImage(files, $scope, function(result){
			$scope.article.image2 = result;
		});
	}
	
});