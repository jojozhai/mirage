/**
 * 
 */
package com.ymt.mirage.apply.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.apply.domain.Message;

/**
 * @author zhailiang
 * @since 2016年5月12日
 */
@Repository
public interface MessageRepository extends PendingItemRepository<Message> {

}