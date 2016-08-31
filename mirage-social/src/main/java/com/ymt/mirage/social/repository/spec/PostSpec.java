/**
 * 
 */
package com.ymt.mirage.social.repository.spec;

import com.ymt.mirage.social.domain.Post;
import com.ymt.mirage.social.dto.PostInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年5月11日
 */
public class PostSpec extends PzSimpleSpecification<Post, PostInfo> {

	public PostSpec(PostInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Post> queryWraper) {
		addEqualsCondition(queryWraper, "containerType");
		addEqualsCondition(queryWraper, "containerId");
	}

}
