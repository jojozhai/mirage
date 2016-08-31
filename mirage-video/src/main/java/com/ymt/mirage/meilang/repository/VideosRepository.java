/**
 * 
 */
package com.ymt.mirage.meilang.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.ymt.mirage.meilang.domain.Videos;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@Repository
public interface VideosRepository extends PzRepository<Videos> {

	List<Videos> findByVideoId(Long id);

	List<Videos> findBySetId(Long setId, Sort sort);

}
