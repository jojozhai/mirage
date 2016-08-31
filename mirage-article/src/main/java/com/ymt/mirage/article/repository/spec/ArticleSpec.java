/**
 * 
 */
package com.ymt.mirage.article.repository.spec;

import com.ymt.mirage.article.domain.Article;
import com.ymt.mirage.article.dto.ArticleInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年6月4日
 */
public class ArticleSpec extends PzSimpleSpecification<Article, ArticleInfo> {

	public ArticleSpec(ArticleInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Article> queryWraper) {
		addLikeCondition(queryWraper, "title");
		addEqualsCondition(queryWraper, "enable");
	}

}
