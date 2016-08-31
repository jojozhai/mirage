/**
 * 
 */
package com.ymt.mirage.vote.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.mirage.vote.domain.Enroll;
import com.ymt.mirage.vote.domain.Vote;
import com.ymt.mirage.vote.domain.VoteActivity;
import com.ymt.mirage.vote.dto.EnrollInfo;
import com.ymt.mirage.vote.repository.EnrollRepository;
import com.ymt.mirage.vote.repository.VoteActivityRepository;
import com.ymt.mirage.vote.repository.VoteRepository;
import com.ymt.mirage.vote.repository.spec.EnrollSpec;
import com.ymt.mirage.vote.service.EnrollService;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;
import com.ymt.pz365.framework.core.exception.PzException;

/**
 * @author zhailiang
 * @since 2016年4月29日
 */
@Service("enrollService")
@Transactional
public class EnrollServiceImpl implements EnrollService {
	
	@Autowired
	private EnrollRepository enrollRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VoteActivityRepository voteActivityRepository;
	
	/* (non-Javadoc)
	 * @see com.ymt.pz365.message.service.EnrollAdminService#create(com.ymt.pz365.message.dto.EnrollInfo)
	 */
	@Override
	public EnrollInfo create(EnrollInfo enrollInfo) {
		
		Enroll enroll = new Enroll();
//		BeanUtils.copyProperties(enrollInfo, enroll);
		VoteActivity activity = voteActivityRepository.findOne(enrollInfo.getActivityId());
		
		if(new DateTime().isAfter(activity.getEndTime().getTime())) {
			throw new PzException("报名失败,活动已结束");
		}
		
		activity.setEnrollCount(activity.getEnrollCount() + 1);
		
		EnrollInfo condition = new EnrollInfo();
		condition.setActivityId(enrollInfo.getActivityId());
		
//		PageRequest numberCondition = new PageRequest(0, 1, Direction.DESC, "number");
//		Page<Enroll> max = enrollRepository.findByActivityId(activity.getId(), numberCondition);
//		if(CollectionUtils.isEmpty(max.getContent())){
//			enroll.setNumber(1);
//		}else{
//			enroll.setNumber(max.getContent().get(0).getNumber() + 1);
//		}
		
		enroll.setNumber(new Long(enrollRepository.count(new EnrollSpec(condition))).intValue() + 1);
		enroll.setName(enrollInfo.getName());
		enroll.setActivity(activity);
		enroll.setDesc(enrollInfo.getDesc());
		enroll.setImages(enrollInfo.getImages());
		enroll.setUserId(enrollInfo.getUserId());
		enrollRepository.save(enroll);
		
		User user = userRepository.findOne(enrollInfo.getUserId());
		if(user != null){
			user.setRealname(enrollInfo.getName());
			user.setMobile(enrollInfo.getMobile());
			userRepository.save(user);
		}
		
		return getInfo(enroll.getId());
	}

	/* (non-Javadoc)
	 * @see com.ymt.pz365.message.service.EnrollAdminService#query(com.ymt.pz365.message.dto.EnrollInfo, org.springframework.data.domain.Pageable)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<EnrollInfo> query(EnrollInfo condition, Pageable pageable) {
		Page<Enroll> pageData = enrollRepository.findAll(new EnrollSpec(condition), pageable);
		return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<Enroll, EnrollInfo>() {
			@Override
			protected void doConvert(Enroll domain, EnrollInfo info) {
				info.setActivityId(domain.getActivity().getId());
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.ymt.pz365.message.service.EnrollAdminService#getInfo(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public EnrollInfo getInfo(Long id) {
		EnrollInfo enrollInfo = new EnrollInfo();
		Enroll enroll = enrollRepository.findOne(id);
		BeanUtils.copyProperties(enroll, enrollInfo);
		enrollInfo.setActivityId(enroll.getActivity().getId());
		enrollInfo.setActivityName(enroll.getActivity().getName());
		return enrollInfo;
	}

	/* (non-Javadoc)
	 * @see com.ymt.pz365.message.service.EnrollAdminService#update(com.ymt.pz365.message.dto.EnrollInfo)
	 */
	@Override
	public EnrollInfo update(EnrollInfo enrollInfo) {
		Enroll enroll = enrollRepository.findOne(enrollInfo.getId());
		BeanUtils.copyProperties(enrollInfo, enroll);
		enrollRepository.save(enroll);
		return enrollInfo;
	}

	/* (non-Javadoc)
	 * @see com.ymt.pz365.message.service.EnrollAdminService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		Enroll enroll = enrollRepository.findOne(id);
		VoteActivity activity = enroll.getActivity();
		activity.setEnrollCount(activity.getEnrollCount() - 1);
		enrollRepository.delete(id);
	}
	
	public static void main(String[] args) {
		System.out.println(new DateTime().withTimeAtStartOfDay());
	}

	@Override
	public String vote(Long enrollId, Long userId) {
		
//		Vote vote = voteRepository.findByEnrollIdAndUserId(enrollId, userId);
//		if(vote == null) {
		
		Enroll enroll = enrollRepository.findOne(enrollId);
			
		if(new DateTime().isAfter(enroll.getActivity().getEndTime().getTime())) {
			throw new PzException("投票失败,活动已结束");
		}
		
		Pageable pageable = new PageRequest(0, 5, new Sort(Direction.DESC, "createdTime"));
		List<Vote> votes = voteRepository.findByUserId(userId, pageable).getContent();
		if(CollectionUtils.isNotEmpty(votes)){
			User user = userRepository.findOne(userId);
			if(StringUtils.isNotBlank(user.getRealname()) &&
					StringUtils.isNotBlank(user.getMobile())){
				if(votes.size() == 5){
					Vote lastVote = votes.get(4);
					boolean todayVotedisable = new DateTime().withTimeAtStartOfDay().isBefore(lastVote.getCreatedTime().getTime());
					if(todayVotedisable){
						throw new PzException("每天投票不能超过5张");
					}
				}
			}else{
				Vote lastVote = votes.get(0);
				boolean todayVoted = new DateTime().plusDays(-1).isBefore(lastVote.getCreatedTime().getTime());
				if(todayVoted){
					return "info";
				}
			}
		}
		
		Vote vote = new Vote();
		VoteActivity activity = enroll.getActivity();
		vote.setEnrollId(enrollId);
		vote.setActivityId(activity.getId());
		vote.setUserId(userId);
		voteRepository.save(vote);
		
		activity.setVoteCount(activity.getVoteCount() + 1);
		voteActivityRepository.save(activity);
		
		enroll.setVoteCount(enroll.getVoteCount() + 1);
		enrollRepository.save(enroll);
		
		return "success";
			
//		}else{
//			throw new PzException("您已经为此选手投过票了，不能重复投票");
//		}		
	}

	@Override
	public EnrollInfo search(Long activityId, String key) {
		List<Enroll> enrolls = null;
		if(NumberUtils.isNumber(key)){
			try {
				enrolls = enrollRepository.findByActivityIdAndNumber(activityId,new Integer(key));
			} catch (Exception e) {}
		}else{
			enrolls = enrollRepository.findByActivityIdAndNameLike(activityId, "%"+key+"%");
		}
		if(CollectionUtils.isNotEmpty(enrolls) && enrolls.size() == 1){
			return getInfo(enrolls.get(0).getId());
		}else{
			throw new PzException("未找到数据,请重新输入查询条件");
		}
	}
	
}
