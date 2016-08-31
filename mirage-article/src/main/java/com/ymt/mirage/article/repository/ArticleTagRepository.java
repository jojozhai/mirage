/**
 * 
 */
package com.ymt.mirage.article.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.article.domain.ArticleTag;
import com.ymt.mirage.tag.repository.TagRelationRepository;

/**
 * @author zhailiang
 * @since 2016年6月4日
 */
@Repository
public interface ArticleTagRepository extends TagRelationRepository<ArticleTag> {

}
