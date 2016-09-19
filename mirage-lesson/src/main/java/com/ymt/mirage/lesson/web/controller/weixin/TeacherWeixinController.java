/**
 * 
 */
package com.ymt.mirage.lesson.web.controller.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.lesson.dto.TeacherInfo;
import com.ymt.mirage.lesson.service.TeacherService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile({"weixin","app"})
public class TeacherWeixinController {
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
	public TeacherInfo getInfo(@PathVariable Long id) {
		return teacherService.getInfo(id);
	}

}
