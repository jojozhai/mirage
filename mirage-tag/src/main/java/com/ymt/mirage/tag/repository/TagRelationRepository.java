/**
 * 
 */
package com.ymt.mirage.tag.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import com.ymt.mirage.tag.domain.TagRelation;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年6月4日
 */
@SuppressWarnings("rawtypes")
@NoRepositoryBean
public interface TagRelationRepository<T extends TagRelation> extends PzRepository<T> {

	List<T> findByTargetId(Long id);
	
	Page<T> findByTagId(Long tagId, Pageable pageable);

}
