/**
 * 
 */
package com.ymt.mirage.lesson.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.lesson.dto.LessonUserInfo;
import com.ymt.mirage.lesson.service.LessonUserService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile("admin")
public class LessonUserAdminController {
	
	@Autowired
	private LessonUserService lessonUserService;

	@RequestMapping(value = "/lessonUser", method = RequestMethod.GET)
	public Page<LessonUserInfo> query(LessonUserInfo lessonUserInfo, Pageable pageable) {
		return lessonUserService.query(lessonUserInfo, pageable);
	}
	
}
