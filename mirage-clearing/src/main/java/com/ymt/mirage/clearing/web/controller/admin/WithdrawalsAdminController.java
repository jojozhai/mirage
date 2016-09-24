/**
 * 
 */
package com.ymt.mirage.clearing.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.clearing.dto.WithdrawalsInfo;
import com.ymt.mirage.clearing.service.WithdrawalsService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile("admin")
public class WithdrawalsAdminController {
	
	@Autowired
	private WithdrawalsService withdrawalsService;

	@RequestMapping(value = "/withdrawals", method = RequestMethod.POST)
	public WithdrawalsInfo create(@RequestBody WithdrawalsInfo withdrawalsInfo) {
		return withdrawalsService.create(withdrawalsInfo);
	}

	@RequestMapping(value = "/withdrawals", method = RequestMethod.GET)
	public Page<WithdrawalsInfo> query(WithdrawalsInfo withdrawalsInfo, Pageable pageable) {
		return withdrawalsService.query(withdrawalsInfo, pageable);
	}
	
	@RequestMapping(value = "/withdrawals/{id}", method = RequestMethod.GET)
	public WithdrawalsInfo getInfo(@PathVariable Long id) {
		return withdrawalsService.getInfo(id);
	}

	@RequestMapping(value = "/withdrawals/{id}", method = RequestMethod.PUT)
	public WithdrawalsInfo update(@RequestBody WithdrawalsInfo withdrawalsInfo) {
		return withdrawalsService.update(withdrawalsInfo);
	}

	@RequestMapping(value = "/withdrawals/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		withdrawalsService.delete(id);
	}
	
}
