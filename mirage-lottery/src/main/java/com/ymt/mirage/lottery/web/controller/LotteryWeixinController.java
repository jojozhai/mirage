/**
 * 
 */
package com.ymt.mirage.lottery.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.lottery.dto.LotteryInfo;
import com.ymt.mirage.lottery.dto.LotteryUserInfo;
import com.ymt.mirage.lottery.service.LotteryService;
import com.ymt.mirage.lottery.service.LotteryUserService;
import com.ymt.mirage.user.web.controller.weixin.CurrentUserHolder;

/**
 * @author lottery
 *
 */
@RestController
@Profile("weixin")
public class LotteryWeixinController {

	@Autowired
	private LotteryService lotteryService;
	
	@Autowired
	private LotteryUserService lotteryUserService;
	
	@RequestMapping(value = "/lottery/{id}", method = RequestMethod.GET)
	public LotteryInfo getInfo(@PathVariable Long id) {
		List<String> colors = new ArrayList<String>();
		LotteryInfo info = lotteryService.getInfo(id);
		for (int i = 0; i < info.getPrizes().size(); i++) {
			if(i%2 == 0){
				colors.add("#FFF4D6");
			}else{
				colors.add("#FFFFFF");
			}
		}
		info.setColors(colors);
		return info;
	}
	
	@RequestMapping(value = "/lottery", method = RequestMethod.POST)
	public void create(@RequestBody LotteryUserInfo info) {
		info.setUserId(CurrentUserHolder.getCurrentUserId());
		lotteryUserService.create(info);
	}
	
	@RequestMapping(value = "/lottery", method = RequestMethod.PUT)
	public void update(@RequestBody LotteryUserInfo info) {
		info.setUserId(CurrentUserHolder.getCurrentUserId());
		lotteryUserService.update(info);
	}

}
