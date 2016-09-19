/**
 * 
 */
package com.ymt.mirage.tag.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.tag.dto.TagInfo;
import com.ymt.mirage.tag.service.TagService;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@RestController
@Profile({"weixin", "app"})
public class TagWeixinController {

	@Autowired
	private TagService tagService;
	
	@RequestMapping(value = "/tag", method = RequestMethod.GET)
	public TagInfo getTagTree(Long rootId){
		return tagService.getTagTree(rootId);
	}
	
	@RequestMapping(value = "/tag/{id}", method = RequestMethod.GET)
	public TagInfo getInfo(@PathVariable Long id){
		return tagService.getInfo(id);
	}
	
	@RequestMapping(value = "/tag/child", method = RequestMethod.GET)
	public List<TagInfo> getChildTag(Long parentId){
		return tagService.getChildTag(parentId);
	}
}
