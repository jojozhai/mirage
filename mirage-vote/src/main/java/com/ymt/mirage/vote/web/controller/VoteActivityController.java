/**
 * 
 */
package com.ymt.mirage.vote.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.vote.dto.VoteActivityInfo;
import com.ymt.mirage.vote.service.VoteActivityService;

/**
 * @author voteActivity
 *
 */
@RestController
public class VoteActivityController {

	@Autowired
	private VoteActivityService voteActivityService;
	
	/**
	 * @param voteActivityInfo
	 * @return
	 * @author zhailiang
	 * @since 2016年5月2日
	 */
	@RequestMapping(value = "/voteActivity", method = RequestMethod.POST)
	public VoteActivityInfo create(@RequestBody VoteActivityInfo voteActivityInfo) {
		return voteActivityService.create(voteActivityInfo);
	}

	/**
	 * @param voteActivityInfo
	 * @param pageable
	 * @return
	 * @author zhailiang
	 * @since 2016年5月2日
	 */
	@RequestMapping(value = "/voteActivity", method = RequestMethod.GET)
	public Page<VoteActivityInfo> query(VoteActivityInfo voteActivityInfo, Pageable pageable) {
		return voteActivityService.query(voteActivityInfo, pageable);
	}
	
	/**
	 * @param id
	 * @author zhailiang
	 * @since 2016年5月2日
	 */
	@RequestMapping(value = "/voteActivity/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		voteActivityService.delete(id);
	}

	/**
	 * @param voteActivityInfo
	 * @return
	 * @author zhailiang
	 * @since 2016年4月30日
	 */
	@RequestMapping(value = "/voteActivity/{id}", method = RequestMethod.PUT)
	public VoteActivityInfo update(@RequestBody VoteActivityInfo voteActivityInfo) {
		return voteActivityService.update(voteActivityInfo);
	}
	/**
	 * @param id
	 * @return
	 * @author zhailiang
	 * @since 2016年4月30日
	 */
	@RequestMapping(value = "/voteActivity/{id}", method = RequestMethod.GET)
	public VoteActivityInfo getInfo(@PathVariable Long id) {
		return voteActivityService.getInfo(id);
	}

}
