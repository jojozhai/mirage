/**
 * 
 */
package com.ymt.mirage.poster.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.poster.domain.UserPoster;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@Repository
public interface UserPosterRepository extends PzRepository<UserPoster> {

	UserPoster findByUserIdAndPosterId(Long userId, Long posterId);

}
