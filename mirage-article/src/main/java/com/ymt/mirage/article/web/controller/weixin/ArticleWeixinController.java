/**
 * 
 */
package com.ymt.mirage.article.web.controller.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.article.dto.ArticleInfo;
import com.ymt.mirage.article.service.ArticleService;

/**
 * @author zhailiang
 * @since 2016年6月4日
 */
@RestController
@Profile({"weixin","app"})
public class ArticleWeixinController {
	
	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public Page<ArticleInfo> query(ArticleInfo articleInfo, Pageable pageable) {
		return articleService.query(articleInfo, pageable);
	}

	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
	public ArticleInfo getInfo(@PathVariable Long id) throws Exception {
		return articleService.getInfo(id);
	}

}
