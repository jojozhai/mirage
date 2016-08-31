/**
 * 
 */
package com.ymt.mirage.poster.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.poster.domain.UserPosterScan;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@Repository
public interface UserPosterScanRepository extends PzRepository<UserPosterScan> {

	UserPosterScan findByUserPosterIdAndScanerId(Long userPosterId, Long scanerId);

}
