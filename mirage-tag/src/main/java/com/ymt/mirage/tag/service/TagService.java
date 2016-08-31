/**
 * 
 */
package com.ymt.mirage.tag.service;

import java.util.List;

import com.ymt.mirage.tag.domain.Tagable;
import com.ymt.mirage.tag.dto.TagInfo;
import com.ymt.mirage.tag.dto.TagedInfo;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
public interface TagService {

	public void addTag(String target, Long targetId, List<TagInfo> tags);
	public List<TagInfo> getTags(TagedInfo info);
	/**
	 * 获取分类标签树
	 *
	 * @param userId 用户ID
	 * @return DhtmlxTree 分类标签树对象
	 * @date  2015年7月10日下午7:08:26
	 * @since 1.0.0
	*/
	public TagInfo getTagTree();
	/**
	 * @return
	 * @author zhailiang
	 * @since 2016年5月15日
	 */
	public List<TagInfo> getTagOptions();

	/**
	 * 根据分类标签ID获取分类标签信息
	 *
	 * @param id 分类标签ID
	 * @return TagInfo 分类标签信息
	 * @date  2015年7月10日下午7:01:48
	 * @since 1.0.0
	*/
	public TagInfo getInfo(Long id);

	/**
	 * 新增分类标签
	 *
	 * @param parentId 父级分类标签ID
	 * @param info 页面传入的分类标签信息
	 * @return TagInfo 分类标签信息
	 * @date  2015年7月10日下午7:01:51
	 * @since 1.0.0
	*/
	public TagInfo create(Long parentId, TagInfo info);

	/**
	 * 更新分类标签
	 *
	 * @param info 页面传入的分类标签信息
	 * @return TagInfo 分类标签信息
	 * @date  2015年7月10日下午7:01:54
	 * @since 1.0.0
	*/
	public TagInfo update(TagInfo info);

	/**
	 * 根据指定ID删除分类标签信息
	 *
	 * @param id 分类标签ID
	 * @date  2015年7月10日下午7:01:57
	 * @since 1.0.0
	*/
	public void delete(Long id);
	/**
	 * 改变分类标签的父节点
	 * @param id
	 * @param target
	 */
	public void move(Long id, Long target);
	/**
	 * 上移/下移分类标签
	 * @param id
	 * @param up
	 */
	public Long move(Long id, boolean up);
	/**
	 * @return
	 * @author zhailiang
	 * @since 2016年5月18日
	 */
	public List<TagInfo> getChildTag(Long parentId);
	/**
	 * @param clazz
	 * @param id
	 * @param tags
	 * @author zhailiang
	 * @throws Exception 
	 * @since 2016年6月4日
	 */
	public void addTag(Tagable domain, List<TagInfo> tags) throws Exception;
	public List<TagInfo> getTags(Tagable article);
	
	public List<TagInfo> getTags(String target, Long targetId);

}
