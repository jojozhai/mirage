/**
 * 
 */
package com.ymt.mirage.social.repository.spec;

import com.ymt.mirage.social.domain.Comment;
import com.ymt.mirage.social.dto.CommentInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年5月22日
 */
public class CommentSpec extends PzSimpleSpecification<Comment, CommentInfo> {

	public CommentSpec(CommentInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Comment> queryWraper) {
		addLikeCondition(queryWraper, "content");
		addEqualsCondition(queryWraper, "target");
		addEqualsCondition(queryWraper, "targetId");
	}

}
