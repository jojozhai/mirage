/**
 * 
 */
package com.ymt.mirage.lottery.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.lottery.dto.LotteryUserInfo;
import com.ymt.mirage.lottery.service.LotteryUserService;

/**
 * @author lotteryUser
 *
 */
@RestController
@Profile("admin")
public class LotteryUserAdminController {

	@Autowired
	private LotteryUserService lotteryUserService;

	@RequestMapping(value = "/lotteryUser", method = RequestMethod.POST)
	public LotteryUserInfo create(@RequestBody LotteryUserInfo lotteryUserInfo) {
		return lotteryUserService.create(lotteryUserInfo);
	}

	@RequestMapping(value = "/lotteryUser", method = RequestMethod.GET)
	public Page<LotteryUserInfo> query(LotteryUserInfo lotteryUserInfo, Pageable pageable) {
		return lotteryUserService.query(lotteryUserInfo, pageable);
	}
	
	@RequestMapping(value = "/lotteryUser/{id}", method = RequestMethod.GET)
	public LotteryUserInfo getInfo(@PathVariable Long id) {
		return lotteryUserService.getInfo(id);
	}

	@RequestMapping(value = "/lotteryUser/{id}", method = RequestMethod.PUT)
	public LotteryUserInfo update(@RequestBody LotteryUserInfo lotteryUserInfo) {
		return lotteryUserService.update(lotteryUserInfo);
	}

	@RequestMapping(value = "/lotteryUser/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		lotteryUserService.delete(id);
	}

}
