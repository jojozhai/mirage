/**
 * 
 */
package com.ymt.mirage.apply.domain;

import javax.persistence.Entity;

/**
 * @author zhailiang
 * @since 2016年5月12日
 */
@Entity
public class Message extends PendingItem {

	/**
	 * 是否已读
	 */
	private boolean readed;

	/**
	 * @return the readed
	 */
	public boolean isReaded() {
		return readed;
	}

	/**
	 * @param readed the readed to set
	 */
	public void setReaded(boolean readed) {
		this.readed = readed;
	}
	
}
