/**
 * 
 */
package com.ymt.mirage.challenge.service.impl;

import java.io.InputStream;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ymt.mirage.challenge.domain.Challenge;
import com.ymt.mirage.challenge.domain.UserChallenge;
import com.ymt.mirage.challenge.dto.ChallengeStatus;
import com.ymt.mirage.challenge.dto.ChallengeType;
import com.ymt.mirage.challenge.dto.Remind;
import com.ymt.mirage.challenge.dto.UserChallengeInfo;
import com.ymt.mirage.challenge.repository.ChallengeRepository;
import com.ymt.mirage.challenge.repository.UserChallengeRepository;
import com.ymt.mirage.challenge.service.UserChallengeService;
import com.ymt.mirage.social.domain.Post;
import com.ymt.mirage.social.dto.PostInfo;
import com.ymt.mirage.social.service.PostService;
import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.pz365.framework.aliyun.oss.OssFileService;
import com.ymt.pz365.framework.param.service.ParamService;
import com.ymt.pz365.framework.weixin.service.WeixinService;

/**
 * @author zhailiang
 * @since 2016年5月11日
 */
@Service("userChallengeService")
@Transactional
public class UserChallengeServiceImpl implements UserChallengeService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ChallengeRepository challengeRepository;
	
	@Autowired
	private UserChallengeRepository userChallengeRepository;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ParamService paramService;
	
	@Autowired
	private WeixinService weixinService;
	
	@Autowired
	private OssFileService ossFileService;
	
	/* (non-Javadoc)
	 * @see com.ymt.mirage.challenge.service.UserChallengeService#create(com.ymt.mirage.challenge.dto.UserChallengeInfo)
	 */
	@Override
	public UserChallengeInfo create(UserChallengeInfo userChallengeInfo) throws Exception {
		UserChallenge userChallenge = new UserChallenge();
		
		if(ChallengeType.OFFICAL.equals(userChallengeInfo.getType())) {
			Challenge challenge = challengeRepository.getOne(userChallengeInfo.getChallengeId());
			userChallenge.setChallenge(challenge);
			userChallenge.setName(challenge.getName());
			userChallenge.setDays(challenge.getDays());
			userChallenge.setType(ChallengeType.OFFICAL);
			challenge.setParticipatorCount(challenge.getParticipatorCount() + 1);
		}else{
			userChallenge.setName(userChallengeInfo.getName());
			userChallenge.setDays(userChallengeInfo.getDays());
			userChallenge.setType(ChallengeType.PERSONAL);
			userChallenge.setPrivacy(userChallengeInfo.getPrivacy());
		}
		
		User user = userRepository.getOne(userChallengeInfo.getUserId());
		userChallenge.setUser(user);
		userChallenge.setDesc("");
		userChallenge.setPledge(userChallengeInfo.getPledge());
		userChallenge.setStartDate(new Date());
		userChallenge.setRemind(userChallengeInfo.getRemind());
		userChallenge.setFinishDate(new DateTime().plusDays(userChallenge.getDays()).toDate());
		userChallenge.setStatus(ChallengeStatus.INIT);
		userChallengeRepository.save(userChallenge);
		
		InputStream qrcode = weixinService.getTempQrcode(userChallenge.getId());
		String qrcodeOssUrl = ossFileService.uploadImage(qrcode);
		userChallenge.setQrcodeOssUrl(qrcodeOssUrl);
		userChallengeRepository.save(userChallenge);
		
		userChallengeInfo.setId(userChallenge.getId());
		userChallengeInfo.setQrcodeOssUrl(qrcodeOssUrl);
		
		PostInfo postInfo = new PostInfo();
		postInfo.setContainerId(userChallenge.getId());
		postInfo.setContainerType(Post.CONTAINER_TYPE_CHALLENGE);
		String template = paramService.getParam(ParamService.PARAM_CHALLENGE_POST_DEFAULT_CONTENT).getValue();
		String content = String.format(template, user.getNickname(), userChallenge.getPledge().intValue(), userChallenge.getName(), userChallenge.getPledge().intValue());
		postInfo.setContent(content);
		postInfo.setCreaterHeadimgurl(user.getHeadimgurl());
		postInfo.setCreaterId(user.getId());
		postInfo.setCreaterName(user.getNickname());
		postInfo.setCreaterType(Post.USER_TYPE_USER);
		postInfo.setImages(paramService.getParam(ParamService.PARAM_CHALLENGE_POST_DEFAULT_IMAGE).getValue());
		postService.create(postInfo);
		
		return userChallengeInfo;
	}

	@Override
	public UserChallengeInfo getInfo(Long id) {
		UserChallenge userChallenge = userChallengeRepository.findOne(id);
		UserChallengeInfo info = new UserChallengeInfo();
		BeanUtils.copyProperties(userChallenge, info);
		info.setUserId(userChallenge.getUser().getId());
		info.setUserNickname(userChallenge.getUser().getNickname());
		info.setUserHeadimgurl(userChallenge.getUser().getHeadimgurl());
		info.setOverseerResidue(userChallenge.getPledge().intValue() - userChallenge.getOverseerCount());
		if(userChallenge.getChallenge() != null) {
			info.setChallengeId(userChallenge.getChallenge().getId());
		}
		return info;
	}

	@Override
	public void updatePrivacy(Long id) {
		UserChallenge userChallenge = userChallengeRepository.findOne(id);		
		userChallenge.setPrivacy(!userChallenge.isPrivacy());
		userChallengeRepository.save(userChallenge);
	}

	@Override
	public void updateRemind(Long id, Remind remind) {
		UserChallenge userChallenge = userChallengeRepository.findOne(id);	
		userChallenge.setRemind(remind);
		userChallengeRepository.save(userChallenge);
	}

}
