/**
 * 
 */
package com.ymt.mirage.user.web.controller.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.user.dto.CollectInfo;
import com.ymt.mirage.user.service.CollectService;
import com.ymt.pz365.framework.core.web.support.SuccessResponse;

/**
 * @author zhailiang
 * @since 2016年5月3日
 */
@RestController
@Profile({"weixin", "app"})
public class CollectController {
	
	@Autowired
	private CollectService collectService;
	
	@RequestMapping(value = "/collect", method = RequestMethod.POST)
	public void collect(@RequestBody CollectInfo collectInfo) throws Exception{
		collectInfo.setUserId(CurrentUserHolder.getCurrentUserId());
		collectService.collect(collectInfo);
	}
	
	@RequestMapping(value = "/collect", method = RequestMethod.GET)
	public SuccessResponse isCollect(CollectInfo collectInfo) throws Exception{
		collectInfo.setUserId(CurrentUserHolder.getCurrentUserId());
		return new SuccessResponse(collectService.isCollect(collectInfo));
	}
	
}
