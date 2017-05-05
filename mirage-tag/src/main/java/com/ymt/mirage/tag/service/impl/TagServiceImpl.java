/**
 * 
 */
package com.ymt.mirage.tag.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.tag.domain.Tag;
import com.ymt.mirage.tag.domain.TagRelation;
import com.ymt.mirage.tag.domain.Tagable;
import com.ymt.mirage.tag.domain.Taged;
import com.ymt.mirage.tag.dto.TagInfo;
import com.ymt.mirage.tag.dto.TagedInfo;
import com.ymt.mirage.tag.repository.TagRelationRepository;
import com.ymt.mirage.tag.repository.TagRepository;
import com.ymt.mirage.tag.repository.TagedRepository;
import com.ymt.mirage.tag.service.TagService;
import com.ymt.pz365.data.jpa.repository.PzRepository;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@Service("tagService")
@Transactional
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private TagedRepository tagedRepository;
	
	@Autowired
	private ApplicationContext applicationContext;

	/* (non-Javadoc)
	 * @see com.idea.ams.service.TagService#getTagTree(java.lang.Long, com.idea.ams.domain.Admin)
	 */
	@Override
	public TagInfo getTagTree(Long id) {
	    
		Tag root;
		if(id == null) {
		    root = tagRepository.findByName("根节点");
		}else{
		    root = tagRepository.getOne(id);
		}
		
		if(root == null){
			root = new Tag();
			root.setName("根节点");
			tagRepository.save(root);
		}
		return root.toTree();
	}

	@Override
	public TagInfo getInfo(Long id) {
		Tag tag = tagRepository.findOne(id);
		TagInfo tagInfo = new TagInfo();
		BeanUtils.copyProperties(tag, tagInfo);
		if(tag.getParent() != null){
			tagInfo.setParentId(tag.getParent().getId());
			tagInfo.setParentName(tag.getParent().getName());
		}
		return tagInfo;
	}

	@Override
	public TagInfo create(Long parentId, TagInfo info) {
		Tag parent = tagRepository.findOne(parentId);
		if(parent == null){
			parent = tagRepository.findByName("根节点");
		}
		Tag tag = new Tag();
		BeanUtils.copyProperties(info, tag);
		parent.addChild(tag);
		info.setId(tagRepository.save(tag).getId());
		return info;		
	}

	@Override
	public TagInfo update(TagInfo info) {
		Tag tag = tagRepository.findOne(info.getId());
		BeanUtils.copyProperties(info, tag);
		return info;
	}

	@Override
	public void delete(Long id) {
		tagRepository.delete(id);
	}
	/* (non-Javadoc)
	 * @see com.idea.ams.service.TagService#move(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void move(Long id, Long target) {
		Tag tag = tagRepository.findOne(id);
		Tag parent = tagRepository.findOne(target);
		tag.setParent(parent);
		tagRepository.save(tag);
	}

	/* (non-Javadoc)
	 * @see com.idea.ams.service.TagService#move(java.lang.Long, boolean)
	 */
	@Override
	public Long move(Long id, boolean up) {
		Tag tag = tagRepository.findOne(id);
		int index = tag.getIndex();
		List<Tag> childs = tag.getParent().getChilds();
		for (int i = 0; i < childs.size(); i++) {
			Tag current = childs.get(i);
			if(current.getId().equals(id)) {
				if(up){
					if(i != 0) {
						Tag pre = childs.get(i - 1);
						tag.setIndex(pre.getIndex());
						pre.setIndex(index);
						tagRepository.save(pre);
					}
				}else{
					if(i != childs.size()-1) {
						Tag next = childs.get(i + 1);
						tag.setIndex(next.getIndex());
						next.setIndex(index);
						tagRepository.save(next);
					}
				}
			}
		}
		tagRepository.save(tag);
		return tag.getParent().getId();
	}

	@Override
	public List<TagInfo> getTagOptions() {
		List<TagInfo> result = new ArrayList<>();
		Tag root = tagRepository.findByName("根节点");
		for (Tag child : root.getChilds()) {
			for (Tag leaf : child.getChilds()) {
				TagInfo info = new TagInfo();
				BeanUtils.copyProperties(leaf, info);
				info.setParentId(child.getId());
				info.setParentName(child.getName());
				result.add(info);
			}
		}
		return result;
	}

	@Override
	public void addTag(String target, Long targetId, List<TagInfo> tags) {
		tagedRepository.delete(tagedRepository.findByTargetAndTargetId(target, targetId));
		if(CollectionUtils.isNotEmpty(tags)){
			for (TagInfo tagInfo : tags) {
				Taged taged = new Taged();
				taged.setTarget(target);
				taged.setTargetId(targetId);
				taged.setTag(tagRepository.getOne(tagInfo.getId()));
				tagedRepository.save(taged);
			}
		}
	}

	@Override
	public List<TagInfo> getTags(TagedInfo info) {
		String target = info.getTarget();
		Long targetId = info.getTargetId();
		List<Taged> tags = tagedRepository.findByTargetAndTargetId(target, targetId);
		return QueryResultConverter.convert(tags, new AbstractDomain2InfoConverter<Taged, TagInfo>() {
			@Override
			protected void doConvert(Taged domain, TagInfo info) throws Exception {
				Tag tag = domain.getTag();
				BeanUtils.copyProperties(tag, info);
				if(tag.getParent() != null){
					info.setParentId(tag.getParent().getId());
					info.setParentName(tag.getParent().getName());
				}
			}
		});
	}

	@Override
	public List<TagInfo> getChildTag(Long parentId) {
		Tag parent;
		if(parentId == null){
			parent = tagRepository.findByName("根节点");
		}else{
			parent = tagRepository.findOne(parentId);
		}
		return QueryResultConverter.convert(parent.getChilds(), TagInfo.class);
	}

	
	@Override
	public void addTag(Tagable domain, List<TagInfo> tags) throws Exception {
	    addTag(domain, tags, Class.forName(domain.getClass().getName()+"Tag"));
	}
	

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void addTag(Tagable domain, List<TagInfo> tags, Class<?> clazz) throws Exception  {
        String clazzName = clazz.getSimpleName();
        TagRelationRepository repository = (TagRelationRepository) applicationContext.getBean(StringUtils.uncapitalize(clazzName)+"Repository");
        repository.delete(repository.findByTargetId(domain.getId()));
	    if(CollectionUtils.isNotEmpty(tags)){
            for (TagInfo tagInfo : tags) {
                TagRelation taged = (TagRelation) clazz.newInstance();
                taged.setTarget(domain);
                taged.setTag(tagRepository.getOne(tagInfo.getId()));
                repository.save(taged);
            }
        }
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<TagInfo> getTags(Tagable tagable) {
		List<TagInfo> result = new ArrayList<TagInfo>();
		List<TagRelation> tags = tagable.getTags();
		for (TagRelation tag : tags) {
			TagInfo info = new TagInfo();
			BeanUtils.copyProperties(tag.getTag(), info);
			result.add(info);
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<TagInfo> getTags(String target, Long targetId) {
		PzRepository repository = (PzRepository) applicationContext.getBean(target+"Repository");
		return getTags((Tagable) repository.findOne(targetId));
	}

}
