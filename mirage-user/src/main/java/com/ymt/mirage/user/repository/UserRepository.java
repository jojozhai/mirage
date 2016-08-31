/**
 * 
 */
package com.ymt.mirage.user.repository;

import org.springframework.stereotype.Repository;

import com.ymt.mirage.user.domain.User;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月3日
 */
@Repository
public interface UserRepository extends PzRepository<User> {

	User findByUsername(String username);

	User findByWeixinUnionId(String unionid);

	User findByWeixinOpenId(String openid);

}
