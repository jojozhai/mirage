/**
 * 
 */
package com.ymt.mirage.lesson.web.controller.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.lesson.dto.LessonInfo;
import com.ymt.mirage.lesson.dto.LessonUserInfo;
import com.ymt.mirage.lesson.service.LessonService;
import com.ymt.mirage.lesson.service.LessonUserService;
import com.ymt.mirage.user.web.controller.weixin.CurrentUserHolder;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile({"weixin","app"})
public class LessonWeixinController {
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
    private LessonUserService lessonUserService;

	@RequestMapping(value = "/lesson", method = RequestMethod.GET)
	public Page<LessonInfo> query(LessonInfo lessonInfo, Pageable pageable) {
		return lessonService.query(lessonInfo, pageable);
	}
	
	@RequestMapping(value = "/lesson/signUp", method = RequestMethod.POST)
    public void signUp(@RequestBody LessonUserInfo info) throws Exception {
	    info.setUserId(CurrentUserHolder.getCurrentUserId());
        lessonUserService.create(info);
    }
	
	@RequestMapping(value = "/lesson/{id}", method = RequestMethod.GET)
	public LessonInfo getInfo(@PathVariable Long id) {
		return lessonService.getInfo(id, CurrentUserHolder.getCurrentUserId());
	}
}
