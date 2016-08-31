/**
 * 
 */
package com.ymt.mirage.vote.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.vote.dto.EnrollInfo;


/**
 * @author zhailiang
 * @since 2016年4月29日
 */
public interface EnrollService {
	
	/**
	 * 创建报名
	 * 
	 * @param enrollInfo 报名信息
	 * @return 报名id
	 * 
	 * @author zhailiang
	 * @since 2016年4月5日
	 */
	EnrollInfo create(EnrollInfo enrollInfo);

	/**
	 * 查询报名
	 * 
	 * @param condition 查询条件
	 * @param pageable 分页信息
	 * @return 
	 * @author zhailiang
	 * @since 2016年4月5日
	 */
	Page<EnrollInfo> query(EnrollInfo condition, Pageable pageable) ;

	/**
	 * 获取报名详细信息
	 * 
	 * @param id 
	 * @return
	 * @author zhailiang
	 * @since 2016年4月5日
	 */
	EnrollInfo getInfo(Long id);

	/**
	 * 修改报名信息
	 * 
	 * @param enrollInfo
	 * 
	 * @author zhailiang
	 * @since 2016年4月5日
	 */
	EnrollInfo update(EnrollInfo enrollInfo);

	/**
	 * 删除报名
	 * 
	 * @param id
	 * @author zhailiang
	 * @since 2016年4月5日
	 */
	void delete(Long id);
	/**
	 * 投票
	 * @param id
	 * @param username
	 * @author zhailiang
	 * @since 2016年5月1日
	 */
	String vote(Long id, Long userId);
	/**
	 * 查询
	 * @param key
	 * @return
	 * @author zhailiang
	 * @param activityId 
	 * @since 2016年5月2日
	 */
	EnrollInfo search(Long activityId, String key);

}
