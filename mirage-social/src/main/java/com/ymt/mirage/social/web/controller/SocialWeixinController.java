/**
 * 
 */
package com.ymt.mirage.social.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.social.dto.PraiseInfo;
import com.ymt.mirage.social.service.SocialService;
import com.ymt.mirage.user.web.controller.weixin.CurrentUserHolder;
import com.ymt.pz365.framework.core.exception.PzException;
import com.ymt.pz365.framework.core.web.support.SuccessResponse;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile({"weixin","app"})
public class SocialWeixinController {
	
	@Autowired
	private SocialService socialService;
	
	@RequestMapping(value = "/praise", method = RequestMethod.GET)
    public SuccessResponse getPraise(String target, Long targetId) throws Exception {
        Long currentUserId = CurrentUserHolder.getCurrentUserId();
        if(currentUserId == null) {
            throw new PzException("不能识别当前用户");
        }
        return new SuccessResponse(socialService.getPraise(target, targetId, currentUserId));
    }

	@RequestMapping(value = "/praise", method = RequestMethod.POST)
	public SuccessResponse praise(@RequestBody PraiseInfo praiseInfo) throws Exception {
		Long currentUserId = CurrentUserHolder.getCurrentUserId();
		if(currentUserId == null) {
			throw new PzException("不能识别当前用户");
		}
		return new SuccessResponse(socialService.praise(praiseInfo, currentUserId));
	}
	
	@RequestMapping(value = "/praise", method = RequestMethod.PUT)
    public SuccessResponse removePraise(@RequestBody PraiseInfo praiseInfo) throws Exception {
        Long currentUserId = CurrentUserHolder.getCurrentUserId();
        if(currentUserId == null) {
            throw new PzException("不能识别当前用户");
        }
        return new SuccessResponse(socialService.praise(praiseInfo, currentUserId, false));
    }

}
