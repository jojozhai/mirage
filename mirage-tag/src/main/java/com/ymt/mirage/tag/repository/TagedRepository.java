/**
 * 
 */
package com.ymt.mirage.tag.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.tag.domain.Taged;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@Repository
public interface TagedRepository extends PzRepository<Taged> {

	List<Taged> findByTargetAndTargetId(String target, Long targetId);

	List<Taged> findByTagFullIdLikeAndTarget(String tagFullId, String name);

}
