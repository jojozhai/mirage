/**
 * 
 */
package com.ymt.mirage.social.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.social.domain.Post;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月10日
 */
@Repository
public interface PostRepository extends PzRepository<Post> {

}
