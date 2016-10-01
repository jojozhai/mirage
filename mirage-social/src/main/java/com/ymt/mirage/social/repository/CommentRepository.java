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

    Page<Comment> findByTargetAndTargetIdAndReplyToIdIsNullAndDisable(String target, Long targetId, boolean disable, Pageable pageable);

    List<Comment> findByReplyToIdAndDisable(Long id, boolean disable, Sort sort);

    List<Comment> findByTargetAndTargetIdAndDisable(String target, Long id, boolean disable);

    List<Comment> findByCommentIdAndDisable(Long id, boolean disable, Sort sort);

}
