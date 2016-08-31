/**
 * 
 */
package com.ymt.mirage.apply.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.apply.domain.Apply;

/**
 * @author zhailiang
 * @since 2016年5月12日
 */
@Repository
public interface ApplyRepository extends PendingItemRepository<Apply> {

}
