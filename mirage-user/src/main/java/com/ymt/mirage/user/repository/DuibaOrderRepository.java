/**
 * 
 */
package com.ymt.mirage.user.repository;

import com.ymt.mirage.user.domain.DuibaOrder;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 *
 */
public interface DuibaOrderRepository extends PzRepository<DuibaOrder> {

	DuibaOrder findByOrderNum(String orderNum);

}
