'use strict';
//平台管理模块的配置
angular.module('videoWeixinModule',[]).config(function($stateProvider) {
	
//服务配置
}).service("videoRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	return $resource("video/:id", {id:"@id"}, config);
//控制器
}).directive('mriageVideo', function($stateParams, videoRestService) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			videoRestService.get({id:$stateParams.id}).$promise.then(function(result){
				var player = new prismplayer(
				{
					id : attrs["id"],
					source : result.playUrl,
					autoplay : false,
					width : "100%",
					cover : result.image
				});
				player.on("play", function(x){
					if(!result.hasPermission){
						toastr["error"]("您无权播放此视频");
						player.pause();
					}
				});
			});
		}
	}
});