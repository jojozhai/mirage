/**
 * 
 */
package com.ymt.mirage.tag.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.tag.dto.TagInfo;
import com.ymt.mirage.tag.dto.TagedInfo;
import com.ymt.mirage.tag.service.TagService;
import com.ymt.pz365.framework.core.web.support.SuccessResponse;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@RestController
@Profile("admin")
public class TagAdminController {

	@Autowired
	private TagService tagService;
	
	@RequestMapping(value = "/tag", method = RequestMethod.GET)
	public TagInfo getTagTree(){
		return tagService.getTagTree();
	}
	
	@RequestMapping(value = "/tags", method = RequestMethod.GET)
	public List<TagInfo> getTags(TagedInfo info){
		return tagService.getTags(info);
	}
	
	@RequestMapping(value = "/tags/info", method = RequestMethod.GET)
	public List<TagInfo> getTagInfos(TagedInfo info){
		return tagService.getTags(info.getTarget(), info.getTargetId());
	}
	
	@RequestMapping(value = "/tag/options", method = RequestMethod.GET)
	public List<TagInfo> getTagOptions(){
		return tagService.getTagOptions();
	}
	
	@RequestMapping(value = "/tag/{id}", method = RequestMethod.GET)
	public TagInfo getTagInfo(@PathVariable Long id){
		return tagService.getInfo(id);
	}
	
	@RequestMapping(value = "/tag", method = RequestMethod.POST)
	public TagInfo create(@RequestBody TagInfo info){
		Long parentId = info.getParentId();
		if(parentId == null) {
			parentId = 0L;
		}
		return tagService.create(parentId, info);
	}
	
	@RequestMapping(value = "/tag/child", method = RequestMethod.GET)
	public List<TagInfo> getChildTag(Long parentId){
		return tagService.getChildTag(parentId);
	}
	
	@RequestMapping(value = "/tag/{id}", method = RequestMethod.PUT)
	public TagInfo update(@RequestBody TagInfo info){
		return tagService.update(info);
	}
	
	@RequestMapping(value = "/tag/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id){
		tagService.delete(id);
	}
	/**
	 * 将一个菜单移到另一个菜单下
	 * @param id
	 * @param target
	 */
	@RequestMapping(value = "/tag/{id}/move", method = RequestMethod.GET)
	public void move(@PathVariable Long id, Long target){
		tagService.move(id, target);
	}
	/**
	 * 菜单上移
	 * @param id
	 */
	@RequestMapping(value = "/tag/{id}/move/up", method = RequestMethod.POST)
	public SuccessResponse moveUp(@PathVariable Long id){
		return new SuccessResponse(tagService.move(id, true));
	}
	/**
	 * 菜单下移
	 * @param id
	 */
	@RequestMapping(value = "/tag/{id}/move/down", method = RequestMethod.POST)
	public SuccessResponse moveDown(@PathVariable Long id){
		return new SuccessResponse(tagService.move(id, false));
	}
}
