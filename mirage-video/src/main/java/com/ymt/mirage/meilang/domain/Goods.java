/**
 * 
 */
package com.ymt.mirage.meilang.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;

import com.ymt.mirage.social.dto.Commentable;
import com.ymt.pz365.data.jpa.domain.DomainImpl;

/**
 * @author zhailiang
 * @since 2016年5月15日
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Goods extends DomainImpl implements Commentable {
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 简介
	 */
	@Lob
	private String desc;
	/**
	 * 精选
	 */
	private boolean selection;
	/**
	 * 价格
	 */
	private BigDecimal price;
	/**
	 * 图片
	 */
	@Column(length = 1000)
	private String image;
	/**
	 * 购买人次
	 */
	private int buyerCount;
	/**
	 * 评论
	 */
	@Lob
	private String comment;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * @return the selection
	 */
	public boolean isSelection() {
		return selection;
	}
	/**
	 * @param selection the selection to set
	 */
	public void setSelection(boolean selection) {
		this.selection = selection;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the buyerCount
	 */
	public int getBuyerCount() {
		return buyerCount;
	}
	/**
	 * @param buyerCount the buyerCount to set
	 */
	public void setBuyerCount(int buyerCount) {
		this.buyerCount = buyerCount;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}
