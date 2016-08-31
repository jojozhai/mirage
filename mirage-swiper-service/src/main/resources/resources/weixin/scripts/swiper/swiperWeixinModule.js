'use strict';
//平台管理模块的配置
angular.module('swiperWeixinModule',[]).config(function() {
	//路由配置
//服务配置
}).service("swiperRestService", function($resource, commonService){
	var config = commonService.getDefaultRestSetting();
	return $resource("swiper/:id", {id:"@id"}, config);
}).directive('mirageSwiper', function(swiperRestService){
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
        	var type = attrs['mirage-swiper'];
        	swiperRestService.query({type: type, sort: 'index,asc', enable: true}).$promise.then(function(result){
        		var swipers = result.content;
        		
                var wrapper = $('<div class="swiper-wrapper"></div>');
                for (var i = 0; i < swipers.length; i++) {
					var swiper = $('<div class="swiper-slide"><a href="'+swipers[i].link+'"><img src="'+swipers[i].image+'"  /></a></div>');
					wrapper.append(swiper);
				}
                
        		var pager = $('<div class="swiper-pagination"></div>');
        		element.append(wrapper);
            	element.append(pager);
            	
            	var swiper = new Swiper('.swiper-container', {
            	    pagination: '.swiper-pagination',
            	    paginationClickable: true,
            	    autoplay : 3000,
            	});
        	});
        }
      }
});