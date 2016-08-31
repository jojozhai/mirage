/**
 * 
 */
package com.ymt.mirage.tag.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.tag.domain.Tag;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@Repository
public interface TagRepository extends PzRepository<Tag> {

	Tag findByName(String name);

}
