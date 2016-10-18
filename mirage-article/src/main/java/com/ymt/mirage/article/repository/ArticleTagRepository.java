/**
 * 
 */
package com.ymt.mirage.article.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ymt.mirage.article.domain.ArticleTag;
import com.ymt.mirage.tag.repository.TagRelationRepository;

/**
 * @author zhailiang
 * @since 2016年6月4日
 */
@Repository
public interface ArticleTagRepository extends TagRelationRepository<ArticleTag> {

    @Query("from ArticleTag at where at.target.enable = true and ((at.tag.id = ?1 and at.target.top = true) or (at.tag.id = ?1 and at.target.business = false))")
    Page<ArticleTag> findByTagIdWithTop(Long tagId, Pageable pageable);

}
