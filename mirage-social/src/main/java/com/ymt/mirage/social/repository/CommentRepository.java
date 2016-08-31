/**
 * 
 */
package com.ymt.mirage.social.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.ymt.mirage.social.domain.Comment;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月22日
 */
@Repository
public interface CommentRepository extends PzRepository<Comment> {

    Page<Comment> findByTargetAndTargetIdAndReplyToIdIsNull(String target, Long targetId, Pageable pageable);

    List<Comment> findByReplyToId(Long id, Sort sort);

    List<Comment> findByTargetAndTargetId(String target, Long id);

    List<Comment> findByCommentId(Long id, Sort sort);

}
