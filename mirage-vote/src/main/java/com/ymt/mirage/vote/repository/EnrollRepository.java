/**
 * 
 */
package com.ymt.mirage.vote.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.vote.domain.Enroll;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年4月29日
 */
@Repository
public interface EnrollRepository extends PzRepository<Enroll> {

	List<Enroll> findByNameOrNumber(String key, Integer integer);

	List<Enroll> findByName(String key);

	List<Enroll> findByActivityIdAndNumber(Long activityId, Integer number);

	List<Enroll> findByActivityIdAndNameLike(Long activityId, String key);

}
