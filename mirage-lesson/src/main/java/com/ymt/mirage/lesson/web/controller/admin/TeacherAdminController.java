/**
 * 
 */
package com.ymt.mirage.lesson.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@Profile("admin")
public class TeacherAdminController {
	
	@Autowired
	private TeacherService teacherService;

	@RequestMapping(value = "/teacher", method = RequestMethod.POST)
	public TeacherInfo create(@RequestBody TeacherInfo teacherInfo) {
		return teacherService.create(teacherInfo);
	}

	@RequestMapping(value = "/teacher", method = RequestMethod.GET)
	public Page<TeacherInfo> query(TeacherInfo teacherInfo, Pageable pageable) {
		return teacherService.query(teacherInfo, pageable);
	}
	
	@RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
	public TeacherInfo getInfo(@PathVariable Long id) {
		return teacherService.getInfo(id);
	}

	@RequestMapping(value = "/teacher/{id}", method = RequestMethod.PUT)
	public TeacherInfo update(@RequestBody TeacherInfo teacherInfo) {
		return teacherService.update(teacherInfo);
	}

	@RequestMapping(value = "/teacher/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		teacherService.delete(id);
	}
	
	@RequestMapping(value = "/teacher/all", method = RequestMethod.GET)
    public List<TeacherInfo> findAll() {
        return teacherService.findAll();
    }
}
