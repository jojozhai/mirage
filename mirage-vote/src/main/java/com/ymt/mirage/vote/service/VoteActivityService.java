/**
 * 
 */
package com.ymt.mirage.vote.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.vote.dto.VoteActivityInfo;

/**
 * @author zhailiang
 * @since 2016年4月29日
 */
public interface VoteActivityService {
	
	/**
	 * 创建投票活动
	 * 
	 * @param voteActivityInfo 投票活动信息
	 * @return 投票活动id
	 * 
	 * @author zhailiang
	 * @since 2016年4月5日
	 */
	VoteActivityInfo create(VoteActivityInfo voteActivityInfo);

	/**
	 * 查询投票活动
	 * 
	 * @param condition 查询条件
	 * @param pageable 分页信息
	 * @return 
	 * @author zhailiang
	 * @since 2016年4月5日
	 */
	Page<VoteActivityInfo> query(VoteActivityInfo condition, Pageable pageable) ;

	/**
	 * 获取投票活动详细信息
	 * 
	 * @param id 
	 * @return
	 * @author zhailiang
	 * @since 2016年4月5日
	 */
	VoteActivityInfo getInfo(Long id);

	/**
	 * 修改投票活动信息
	 * 
	 * @param voteActivityInfo
	 * 
	 * @author zhailiang
	 * @since 2016年4月5日
	 */
	VoteActivityInfo update(VoteActivityInfo voteActivityInfo);

	/**
	 * 删除投票活动
	 * 
	 * @param id
	 * @author zhailiang
	 * @since 2016年4月5日
	 */
	void delete(Long id);

}
