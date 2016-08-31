/**
 * 
 */
package com.ymt.mirage.poster.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.poster.domain.Poster;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@Repository
public interface PosterRepository extends PzRepository<Poster> {

	Poster findByKey(String key);

}
