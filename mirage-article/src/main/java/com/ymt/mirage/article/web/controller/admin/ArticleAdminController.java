/**
 * 
 */
package com.ymt.mirage.article.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.article.dto.ArticleInfo;
import com.ymt.mirage.article.service.ArticleService;
import com.ymt.pz365.framework.core.web.support.SuccessResponse;

/**
 * @author zhailiang
 * @since 2016年6月4日
 */
@RestController
@Profile("admin")
public class ArticleAdminController {
	
	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "/article", method = RequestMethod.POST)
	public ArticleInfo create(@RequestBody ArticleInfo articleInfo) throws Exception {
		return articleService.create(articleInfo);
	}

	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public Page<ArticleInfo> query(ArticleInfo articleInfo, Pageable pageable) {
		return articleService.query(articleInfo, pageable);
	}

	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
	public ArticleInfo getInfo(@PathVariable Long id) throws Exception {
		return articleService.getInfo(id);
	}

	@RequestMapping(value = "/article/{id}", method = RequestMethod.PUT)
	public ArticleInfo update(@RequestBody ArticleInfo articleInfo) throws Exception {
		return articleService.update(articleInfo);
	}
	
	@RequestMapping(value = "/article/{id}/content", method = RequestMethod.PUT)
    public SuccessResponse updateContent(@PathVariable Long id, String content) throws Exception {
        articleService.update(id, content);
        return new SuccessResponse();
    }

	@RequestMapping(value = "/article/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		articleService.delete(id);
	}

}
