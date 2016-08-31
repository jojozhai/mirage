/**
 * 
 */
package com.ymt.mirage.social.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.social.domain.Praise;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月22日
 */
@Repository
public interface PraiseRepository extends PzRepository<Praise> {

    Praise findByCreaterIdAndTargetId(Long currentUserId, Long targetId);

}
