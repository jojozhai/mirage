/**
 * 
 */
package com.ymt.mirage.user.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.user.domain.Level;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月3日
 */
@Repository
public interface LevelRepository extends PzRepository<Level> {

}
