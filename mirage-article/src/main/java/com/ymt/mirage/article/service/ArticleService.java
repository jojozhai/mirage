/**
 * 
 */
package com.ymt.mirage.article.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ymt.mirage.article.dto.ArticleInfo;

/**
 * @author zhailiang
 * @since 2016年6月4日
 */
public interface ArticleService {
	                                                                                                                                           
	Page<ArticleInfo> query(ArticleInfo articleInfo, Pageable pageable);
	
	ArticleInfo create(ArticleInfo articleInfo) throws Exception;

	ArticleInfo getInfo(Long id) throws Exception;
	
	ArticleInfo getInfo(Long id, Long currentUserId) throws Exception;

	ArticleInfo update(ArticleInfo articleInfo) throws Exception;

	void delete(Long id);

    void update(Long id, String content);

    

}
