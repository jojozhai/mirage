/**
 * 
 */
package com.ymt.mirage.user.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.user.domain.Collect;
import com.ymt.mirage.user.dto.CollectInfo;
import com.ymt.mirage.user.repository.CollectRepository;
import com.ymt.mirage.user.service.CollectService;
import com.ymt.pz365.data.jpa.repository.PzRepository;

/**
 * @author zhailiang
 * @since 2016年5月21日
 */
@Service("collectService")
@Transactional
public class CollectServiceImpl implements CollectService {
	
	@Autowired
	private ApplicationContext applicationContext;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void collect(CollectInfo collectInfo) throws Exception {
 
		String targetClassName = StringUtils.substringAfterLast(collectInfo.getTarget(), ".");
		String collectClassName = targetClassName+"Collect";
		
		String domainPackage = StringUtils.substringBeforeLast(collectInfo.getTarget(), ".");
		
		Class collectClass = Class.forName(domainPackage+"."+collectClassName);
		
		PzRepository targetRepository = (PzRepository) applicationContext.getBean(StringUtils.uncapitalize(targetClassName)+"Repository");
		CollectRepository collectRepository = (CollectRepository) applicationContext.getBean(StringUtils.uncapitalize(collectClassName)+"Repository");
		
		Collect collectDomain = collectRepository.findByUserIdAndTargetId(collectInfo.getUserId(), collectInfo.getTargetId());
		if(collectInfo.isCollect()) {
			if(collectDomain == null) {
				collectDomain = (Collect) collectClass.newInstance();
				Object target = targetRepository.getOne(collectInfo.getTargetId());
				collectDomain.setTarget(target);
				collectDomain.setUserId(collectInfo.getUserId());
				collectRepository.save(collectDomain);
			}
		}else{
			if(collectDomain != null) {
				collectRepository.delete(collectDomain);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isCollect(CollectInfo collectInfo) throws Exception {
		String targetClassName = StringUtils.substringAfterLast(collectInfo.getTarget(), ".");
		String collectClassName = targetClassName+"Collect";
		CollectRepository collectRepository = (CollectRepository) applicationContext.getBean(StringUtils.uncapitalize(collectClassName)+"Repository");
		Collect collectDomain = collectRepository.findByUserIdAndTargetId(collectInfo.getUserId(), collectInfo.getTargetId());
		return collectDomain != null;
	}

}
