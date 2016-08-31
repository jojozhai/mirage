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

import com.ymt.mirage.lottery.dto.LotteryInfo;
import com.ymt.mirage.lottery.service.LotteryService;

/**
 * @author lottery
 *
 */
@RestController
@Profile("admin")
public class LotteryAdminController {

	@Autowired
	private LotteryService lotteryService;

	@RequestMapping(value = "/lottery", method = RequestMethod.POST)
	public LotteryInfo create(@RequestBody LotteryInfo lotteryInfo) {
		return lotteryService.create(lotteryInfo);
	}

	@RequestMapping(value = "/lottery", method = RequestMethod.GET)
	public Page<LotteryInfo> query(LotteryInfo lotteryInfo, Pageable pageable) {
		return lotteryService.query(lotteryInfo, pageable);
	}
	
	@RequestMapping(value = "/lottery/{id}", method = RequestMethod.GET)
	public LotteryInfo getInfo(@PathVariable Long id) {
		return lotteryService.getInfo(id);
	}

	@RequestMapping(value = "/lottery/{id}", method = RequestMethod.PUT)
	public LotteryInfo update(@RequestBody LotteryInfo lotteryInfo) {
		return lotteryService.update(lotteryInfo);
	}

	@RequestMapping(value = "/lottery/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		lotteryService.delete(id);
	}

}
