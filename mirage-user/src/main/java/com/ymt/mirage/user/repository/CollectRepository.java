/**
 * 
 */
package com.ymt.mirage.user.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;

import com.ymt.mirage.user.domain.Collect;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月21日
 */
@NoRepositoryBean
public interface CollectRepository<T extends Collect<?>> extends PzRepository<T> {

	T findByUserIdAndTargetId(Long userId, Long targetId);
	
	List<T> findByUserId(Long userId, Sort sort);
	
}
