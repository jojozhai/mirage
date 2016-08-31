/**
 * 
 */
package com.ymt.mirage.apply.repository;

import org.springframework.data.repository.NoRepositoryBean;

import com.ymt.mirage.apply.domain.PendingItem;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月12日
 */
@NoRepositoryBean
public interface PendingItemRepository<T extends PendingItem> extends PzRepository<T> {

}
