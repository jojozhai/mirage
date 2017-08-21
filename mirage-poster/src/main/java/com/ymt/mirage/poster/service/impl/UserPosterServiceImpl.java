/**
 * 
 */
package com.ymt.mirage.poster.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import com.ymt.mirage.poster.domain.Poster;
import com.ymt.mirage.poster.domain.UserPoster;
import com.ymt.mirage.poster.domain.UserPosterScan;
import com.ymt.mirage.poster.dto.UserPosterInfo;
import com.ymt.mirage.poster.repository.PosterRepository;
import com.ymt.mirage.poster.repository.UserPosterRepository;
import com.ymt.mirage.poster.repository.UserPosterScanRepository;
import com.ymt.mirage.poster.repository.spec.UserPosterSpec;
import com.ymt.mirage.poster.service.UserPosterService;
import com.ymt.mirage.user.domain.User;
import com.ymt.mirage.user.repository.UserRepository;
import com.ymt.mirage.user.service.UserService;
import com.ymt.pz365.data.jpa.support.AbstractDomain2InfoConverter;
import com.ymt.pz365.data.jpa.support.QueryResultConverter;
import com.ymt.pz365.framework.aliyun.oss.OssFileService;
import com.ymt.pz365.framework.param.service.ParamService;
import com.ymt.pz365.framework.weixin.service.WeixinService;
import com.ymt.pz365.framework.weixin.support.message.TemplateMessage;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@Service("userPosterService")
@Transactional
public class UserPosterServiceImpl implements UserPosterService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PosterRepository posterRepository;
	
	@Autowired
	private UserPosterRepository userPosterRepository;
	
	@Autowired
	private UserPosterScanRepository userPosterScanRepository;
	
	@Autowired
	private WeixinService weixinService;
	
	@Autowired
	private OssFileService ossFileService;
	
	private CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	
	@Value("${aliyun.oss.endpoint.img}")
	private String imgEndpoint;
	
	@Autowired
	private ParamService paramService;
	
	@Autowired
	private UserService userService;
	
//	@Value("${poster.user.point.change.template.id:TOvgurSIjs6kWIzQ-y74yYlAVfzhvDPr5iBFujQ7jVA}")
//	@Value("${poster.user.point.change.template.id:AC3Dl1nIaYyDDidzfyaZL4NEULC-Xkhwz--XE7GKlhs}")
//	@Value("${poster.user.point.change.template.id:9uCLyMmViz6cYmM_ea0Hw2ofHa4gB5yf2WozMY72OC8}")
	@Value("${poster.user.point.change.template.id:mjhB2XsB7E5Bphgk6A5boANseyJJjKd7WZ8f1sK8YEE}")
	private String pointChangeMessageTemplateId;
	
//	@Value("${poster.user.point.active.template.id:731feS0_zCAPj5D0QX2Q46i7l5bpwkDvoRfHf3yMuwc}")
//	@Value("${poster.user.point.active.template.id:cxneLZQR0_ztvFXuDVZACZ7OpFFsfAYxbDhfpGsKRG8}")
	@Value("${poster.user.point.active.template.id:RbGkvW4NhBlQBSuuSOxbHwTSxOLkr9fxjPVceUmI-fM}")
	private String pointActiveMessageTemplateId;
	
	@Value("${poster.default.point:1}")
	private int posterPoint;
	
	public static void main(String[] args) {
	    System.out.println(2 << 4);
    }
	
	private Set<String> keys = new HashSet<>();
	
	/* (non-Javadoc)
	 * @see com.ymt.mirage.poster.service.UserPosterService#create(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void create(User user, Long posterId) throws Exception {
	    
	    logger.info("userId:"+user.getId());
		logger.info("posterId:"+posterId);
		
		String key = user.getId()+""+posterId;
		
		try {
			if(!keys.contains(key)) {
			    
			    keys.add(key);
			    
//			    User user = userRepository.findOne(userId);
		        
		        if(user != null) {
		            logger.info(ReflectionToStringBuilder.toString(user));
		            Poster poster = posterRepository.findOne(posterId);
		            weixinService.pushTextMessage(user.getWeixinOpenId(), "正在生成海报,请稍候...");
		            
		            UserPoster userPoster = userPosterRepository.findByUserIdAndPosterId(user.getId(), posterId);
		            
		            if(userPoster == null) {
		                userPoster = generateNewUserPoster(user, poster);
		            }else{
		                if(userPoster.isExpired()){
		                    userPoster.setExpiredTime(new DateTime().plusDays(3).toDate());
		                    setUserPosterProperties(user, poster, userPoster);
		                }
		            }
		            
		            weixinService.pushTextMessage(user.getWeixinOpenId(), poster.getGeneratedTip(userPoster.getExpiredTime()));
		            weixinService.pushImageMessage(user.getWeixinOpenId(), userPoster.getWeixinMediaId());
		        }
		        
		        keys.remove(key);
		        
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			keys.remove(key);
		}
		
		
		
		
		
	}

	/**
	 * 生成新的用户海报
	 * @param user
	 * @param poster
	 * @return
	 * @throws Exception
	 * @author zhailiang
	 * @since 2016年5月9日
	 */
	private UserPoster generateNewUserPoster(User user, Poster poster) throws Exception {
		
		UserPoster userPoster = saveUserPoster(user, poster);
		
		return setUserPosterProperties(user, poster, userPoster);
	}

    private UserPoster setUserPosterProperties(User user, Poster poster, UserPoster userPoster) throws URISyntaxException, IOException, Exception {
        InputStream qrcode = weixinService.getTempQrcode(userPoster.getId());
		
		String qrcodeOssUrl = ossFileService.uploadImage(qrcode);
		String headimgOssUrl = getUserHeadimgOssUrl(user, poster);
		String userPosterOssUrl = getUserPosterOssUrl(poster, qrcodeOssUrl, headimgOssUrl);
		String weixinMediaId = weixinService.uploadImage(getImageInputStreamByUrl(userPosterOssUrl));
		
		userPoster.setWeixinMediaId(weixinMediaId);
		userPoster.setQrcodeOssUrl(qrcodeOssUrl);
		userPoster.setOssUrl(userPosterOssUrl);
		userPosterRepository.save(userPoster);
		
		return userPoster;
    }

	/**
	 * 保存用户海报信息
	 * @param user
	 * @param poster
	 * @return
	 * @author zhailiang
	 * @since 2016年5月9日
	 */
	private UserPoster saveUserPoster(User user, Poster poster) {
		UserPoster userPoster = new UserPoster();
		userPoster.setUser(user);
		userPoster.setPoster(poster);
		//海报要作为临时素材上传到微信，只能存在3天.
		userPoster.setExpiredTime(new DateTime().plusDays(3).toDate());
		userPosterRepository.save(userPoster);
		return userPoster;
	}

	/**
	 * 获得海报头像的ossUrl
	 * @param user
	 * @param poster
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @author zhailiang
	 * @since 2016年5月9日
	 */
	private String getUserHeadimgOssUrl(User user, Poster poster) throws IOException, URISyntaxException {
		String headimgOssUrl = user.getHeadimgOssUrl();
		if(StringUtils.isBlank(headimgOssUrl)){
			headimgOssUrl = ossFileService.uploadImage(getImageInputStreamByUrl(user.getHeadimgurl()));
			user.setHeadimgOssUrl(headimgOssUrl);
		}
		
		if(poster.isIncircleHead()) {
			String headimgOssUrlForIncircle = user.getHeadimgOssUrlForIncircle();
			if(StringUtils.isBlank(headimgOssUrlForIncircle)){
				String incircleHeadimgUrl = getImageServiceUrlForOssObject(headimgOssUrl)+"@1000-1ci.png";
				headimgOssUrlForIncircle = ossFileService.uploadImage(getImageInputStreamByUrl(incircleHeadimgUrl));
				user.setHeadimgOssUrlForIncircle(headimgOssUrlForIncircle);
			}
			return headimgOssUrlForIncircle;
		}
		return headimgOssUrl;
	}
	

	/**
	 * 获得最终的用户海报的ossUrl
	 * @param poster
	 * @param qrcodeOssUrl
	 * @param headimgOssUrl
	 * @return
	 * @author zhailiang
	 * @since 2016年5月9日
	 */
	@SuppressWarnings("deprecation")
	private String getUserPosterOssUrl(Poster poster, String qrcodeOssUrl, String headimgOssUrl) {
		String url = getImageServiceUrlForOssObject(poster.getImage());
		StringBuffer userPosterImage = new StringBuffer(url);
		userPosterImage.append("@");
		userPosterImage.append(getWotermarkUrl(qrcodeOssUrl, poster.getQrcodeLeft(), poster.getQrcodeTop(), poster.getQrcodeScale()));
		userPosterImage.append(URLEncoder.encode("|"));
		userPosterImage.append(getWotermarkUrl(headimgOssUrl, poster.getHeadLeft(), poster.getHeadTop(), poster.getHeadScale()));
		return userPosterImage.toString();
	}

	/**
	 * 将标准的ossurl转成调用图片服务的url
	 * @param ossObjectUrl
	 * @return
	 * @author zhailiang
	 * @since 2016年5月9日
	 */
	private String getImageServiceUrlForOssObject(String ossObjectUrl) {
		return "http://"+imgEndpoint+"/"+StringUtils.substringAfterLast(ossObjectUrl, "/");
	}
	
	/**
	 * 生成水印的url
	 * @param ossUrl
	 * @param x
	 * @param y
	 * @param watermarkScale
	 * @return
	 * @author zhailiang
	 * @since 2016年5月9日
	 */
	private String getWotermarkUrl(String ossUrl, int x, int y, int watermarkScale) {
		StringBuffer result = new StringBuffer("watermark=1&");
		result.append("object="+getWatermarkObject(ossUrl+"@"+watermarkScale+"P")+"&");
		result.append("t=100&");
		result.append("p=1&");
		result.append("x="+x+"&");
		result.append("y="+y);
		return result.toString();
	}

	/**
	 * 生成水印url中object属性的的值
	 * @param qrcodeOssUrl
	 * @return
	 * @author zhailiang
	 * @since 2016年5月9日
	 */
	private String getWatermarkObject(String qrcodeOssUrl) {
		String key = StringUtils.substring(qrcodeOssUrl, StringUtils.indexOf(qrcodeOssUrl, "/", 8) + 1);
//		String key = StringUtils.substringAfterLast(qrcodeOssUrl, "/");
		return Base64.encodeBase64URLSafeString(key.getBytes());
	}
	
	/**
	 * 根据url读取图片输入流
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException
	 * @author zhailiang
	 * @since 2016年5月9日
	 */
	private InputStream getImageInputStreamByUrl(String url) throws IOException, URISyntaxException{
		return httpClient.execute(new HttpGet(new URI(url))).getEntity().getContent();
	}

	@Override
	public Page<UserPosterInfo> query(UserPosterInfo condition, Pageable pageable) {
		Page<UserPoster> pageData = userPosterRepository.findAll(new UserPosterSpec(condition), pageable);
		return QueryResultConverter.convert(pageData, pageable, new AbstractDomain2InfoConverter<UserPoster, UserPosterInfo>() {
			@Override
			protected void doConvert(UserPoster domain, UserPosterInfo info) {
				info.setNickname(domain.getUser().getNickname());
			}
		});
	}

	@Override
	public void delete(Long id) {
		userPosterRepository.delete(id);
	}

	/**
	 * 为海报发送者增加积分
	 * 
	 * @param senderUserPosterId
	 * @param scanerId
	 * @throws RestClientException
	 * @throws URISyntaxException
	 * @author zhailiang
	 * @since 2016年5月9日
	 */
	@Override
	public void addSenderPoint(Long senderUserPosterId , Long scanerId) throws Exception {
		UserPosterScan userPosterScan = userPosterScanRepository.findByUserPosterIdAndScanerId(senderUserPosterId, scanerId);
		if(userPosterScan == null) {
			UserPoster senderUserPoster = userPosterRepository.findOne(senderUserPosterId);
			User scaner = userRepository.findOne(scanerId);
			createUserPosterScan(senderUserPoster, scaner);
			
			//每个人只能送出一次积分?
//			List<UserPosterScan> history = userPosterScanRepository.findByUserPosterPosterIdAndScanerId(senderUserPoster.getPoster().getId(), scanerId);
//			if(CollectionUtils.isEmpty(history)) {
				doAddSenderPoint(senderUserPoster, scaner);
//			}
			
		}
	}

	/**
	 * 创建海报扫描记录
	 * @param senderUserPoster
	 * @param scaner
	 * @author zhailiang
	 * @since 2016年5月9日
	 */
	private void createUserPosterScan(UserPoster senderUserPoster, User scaner) {
		UserPosterScan userPosterScan;
		userPosterScan = new UserPosterScan();
		userPosterScan.setUserPoster(senderUserPoster);
		userPosterScan.setScaner(scaner);
		userPosterScanRepository.save(userPosterScan);
	}

	/**
	 * 为发送者增加分数
	 * @param senderUserPoster
	 * @param scaner
	 * @throws URISyntaxException
	 * @author zhailiang
	 * @since 2016年5月9日
	 */
	private void doAddSenderPoint(UserPoster senderUserPoster, User scaner) throws Exception {
		User sender = senderUserPoster.getUser();
		if(!sender.getId().equals(scaner.getId())) {
			senderUserPoster.setPointCount(senderUserPoster.getPointCount() + posterPoint);
			
			userService.changePoint(sender.getId(), posterPoint, "创城代言人");
			
			//发送海报激活事件
			sendPosterActiveMessage(senderUserPoster);
		}
	}

	/**
	 * 向发送者发送海报激活的消息
	 * @param senderUserPoster
	 * @throws URISyntaxException
	 * @author zhailiang
	 * @since 2016年5月9日
	 */
	private void sendPosterActiveMessage(UserPoster senderUserPoster) throws Exception {
		if(senderUserPoster.getPointCount() >= senderUserPoster.getPoster().getActivePoint()) {
//			if(!senderUserPoster.isActive()) {
				senderUserPoster.setActive(true);
				weixinService.pushTemplateMessage(buildActiveMessage(senderUserPoster, posterPoint));
//			}
		}
	}
	
	private TemplateMessage buildActiveMessage(UserPoster userPoster, Integer amount) {
//		String appName = paramService.getParam("app.name").getValue();
		String url = userPoster.getPoster().getActiveUrl(userPoster.getUser().getId());
		TemplateMessage templateMessage = new TemplateMessage(userPoster.getUser().getWeixinOpenId(), pointChangeMessageTemplateId, url);
		templateMessage.addValue("first", "好厉害，您的积分已经达标，恭喜您成为创城大使，感谢您对美丽马驹桥活动的支持。点击这个文本框，就可以到积分商城去兑换礼品啦~~~");
		templateMessage.addValue("keyword1", "创城随手拍");
		templateMessage.addValue("keyword2", "新增鲜花积分"+amount+"分，累计鲜花积分"+userPoster.getUser().getPoint()+"分");
		templateMessage.addValue("keyword3", "线上办理");
		templateMessage.addValue("remark", "美丽城市，全民共建！");
		return templateMessage;
	}

	/**
	 * 构建海报激活的模板消息
	 * @param userPoster
	 * @return
	 * @author zhailiang
	 * @since 2016年5月9日
	 */
//	private TemplateMessage buildActiveMessage(UserPoster userPoster) {
//		String appName = paramService.getParam("app.name","霸王课").getValue();
//		User user = userPoster.getUser();
//		Poster poster = userPoster.getPoster();
//		TemplateMessage templateMessage = new TemplateMessage(user.getWeixinOpenId(), pointActiveMessageTemplateId);
//		templateMessage.setUrl(poster.getActiveUrl(user.getId()));
//		templateMessage.addValue("first", appName+"积分服务提醒：");
//		templateMessage.addValue("keyword1", poster.getActivePoint()+"分");
//		templateMessage.addValue("keyword2", new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
//		templateMessage.addValue("remark", poster.getActiveTip());
//		return templateMessage;
//	}

}
