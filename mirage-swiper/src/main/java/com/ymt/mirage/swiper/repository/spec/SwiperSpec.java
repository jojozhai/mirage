/**
 * 
 */
package com.ymt.mirage.swiper.repository.spec;

import com.ymt.mirage.swiper.domain.Swiper;
import com.ymt.mirage.swiper.dto.SwiperInfo;
import com.ymt.pz365.data.jpa.repository.spec.PzSimpleSpecification;
import com.ymt.pz365.data.jpa.repository.spec.QueryWraper;

/**
 * @author zhailiang
 * @since 2016年5月23日
 */
public class SwiperSpec extends PzSimpleSpecification<Swiper, SwiperInfo> {

	public SwiperSpec(SwiperInfo condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Swiper> queryWraper) {
		addEqualsCondition(queryWraper, "type");
		addEqualsCondition(queryWraper, "enable");
	}

}
