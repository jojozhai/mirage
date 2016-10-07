'use strict';
//平台管理模块的配置
angular.module('umeditorModule',[]).config(function($stateProvider) {
	//路由配置
	$stateProvider.state('index.umeditor', {
		url: "/umeditor?target&targetId&targetProp",
		controller: "umeditorCtrl",
		templateUrl: "admin/views/umeditor.html"
	});
//服务配置
}).service("umeditorRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	config.getValue = {url:"domain/property", method:"GET"};
	config.setValue = {url:"domain/property", method:"PUT"};
	return $resource("domain/:id", {id:"@id"}, config);
//控制器
}).controller('umeditorCtrl', function($scope, domain, params, umeditorRestService, commonService) {

	$scope.params = params;
	
}).directive('mirageUmeditor', function(commonService, umeditorRestService) {return {
	restrict : 'A',
	link : function(scope, element, attrs) {
		
		UM.plugins['save'] = function () {
		    UM.commands[ 'save' ] = {
		        execCommand: function (cmdName) {
		        	scope.params.value = UM.getEditor('myEditor').getContent();
		        	umeditorRestService.setValue(scope.params).$promise.then(function(){
		        		commonService.showMessage("保存成功");
		        	})
		        },
		        queryCommandState: function (cmdName) {
		            return 0;
		        },
		        notNeedUndo: 1
		    };
		};
		
		var um = UM.getEditor('myEditor');
		
		umeditorRestService.getValue(scope.params).$promise.then(function(result){
			um.setContent(result.content, false);
		})
		
	}
}});