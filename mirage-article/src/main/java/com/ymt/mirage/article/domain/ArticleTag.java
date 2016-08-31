/**
 * 
 */
package com.ymt.mirage.article.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.ymt.mirage.tag.domain.Tag;
import com.ymt.mirage.tag.domain.TagRelation;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年6月4日
 */
@Entity
public class ArticleTag extends DomainImpl implements TagRelation<Article>{
	
	@ManyToOne
	private Article target;
	
	@ManyToOne
	private Tag tag;
	
	/**
	 * @return the target
	 */
	public Article getTarget() {
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(Article target) {
		this.target = target;
	}
	/**
	 * @return the tag
	 */
	public Tag getTag() {
		return tag;
	}
	/**
	 * @param tag the tag to set
	 */
	public void setTag(Tag tag) {
		this.tag = tag;
	}

}
