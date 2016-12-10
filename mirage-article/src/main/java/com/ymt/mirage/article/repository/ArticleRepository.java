/**
 * 
 */
package com.ymt.mirage.article.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.article.domain.Article;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年6月4日
 */
@Repository
public interface ArticleRepository extends PzRepository<Article> {

    List<Article> findByEnableIsFalseAndEnableDateBefore(Date date);

}
