/**
 * 
 */
package com.ymt.mirage.user.web.controller.weixin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.mirage.user.service.DuibaService;
import com.ymt.mirage.user.support.duiba.CreditConsumeResult;
import com.ymt.mirage.user.support.duiba.CreditTool;
import com.ymt.pz365.framework.core.web.support.SuccessResponse;

/**
 * @author zhailiang
 *
 */
@RestController
@Profile({ "!admin" })
public class DuibaController implements InitializingBean {

	@Value("${mirage.duiba.appKey:}")
	private String appKey;

	@Value("${mirage.duiba.appSecret:}")
	private String appSecret;

	private CreditTool tool;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DuibaService duibaService;

	@Override
	public void afterPropertiesSet() throws Exception {
		tool = new CreditTool(appKey, appSecret);
	}
	
	@RequestMapping(value = "/duiba/autologin", method = RequestMethod.GET)
	public SuccessResponse getLoginUrl() {
		Map<String, String> params = new HashMap<>();
		Long userId = CurrentUserHolder.getCurrentUserId();
		params.put("uid", userId.toString());
		params.put("credits", new Integer(userRepository.findOne(userId).getPoint()).toString());
		String url = tool.buildUrlWithSign("https://www.duiba.com.cn/autoLogin/autologin?", params);
		return new SuccessResponse(url);
	}
	
	@RequestMapping(value = "/duiba/autologin/{id}", method = RequestMethod.GET)
	public void toLoginUrl(@PathVariable Long id, HttpServletResponse response) throws IOException {
		Map<String, String> params = new HashMap<>();
		params.put("uid", id.toString());
		params.put("credits", new Integer(userRepository.findOne(id).getPoint()).toString());
		String url = tool.buildUrlWithSign("https://www.duiba.com.cn/autoLogin/autologin?", params);
		response.sendRedirect(url);
	}
	
	@RequestMapping(value = "/duiba/credit/consume", method = RequestMethod.GET)
	public CreditConsumeResult consumeCredit(HttpServletRequest request) throws Exception {
		return duibaService.consumeCredit(tool.parseCreditConsume(request));
	}
	
	@RequestMapping(value = "/duiba/credit/confirm", method = RequestMethod.GET)
	public String consumeConfirm(HttpServletRequest request) throws Exception {
		duibaService.consumeConfirm(tool.parseCreditNotify(request));
		return "ok";
	}

}
