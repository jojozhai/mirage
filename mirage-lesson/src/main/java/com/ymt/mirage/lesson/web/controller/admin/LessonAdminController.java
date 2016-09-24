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

import com.ymt.mirage.lesson.dto.LessonInfo;
import com.ymt.mirage.lesson.service.LessonService;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile("admin")
public class LessonAdminController {
	
	@Autowired
	private LessonService lessonService;

	@RequestMapping(value = "/lesson", method = RequestMethod.POST)
	public LessonInfo create(@RequestBody LessonInfo lessonInfo) throws Exception {
		return lessonService.create(lessonInfo);
	}

	@RequestMapping(value = "/lesson", method = RequestMethod.GET)
	public Page<LessonInfo> query(LessonInfo lessonInfo, Pageable pageable) {
		return lessonService.query(lessonInfo, pageable);
	}
	
	@RequestMapping(value = "/lesson/{id}", method = RequestMethod.GET)
	public LessonInfo getInfo(@PathVariable Long id) {
		return lessonService.getInfo(id);
	}

	@RequestMapping(value = "/lesson/{id}", method = RequestMethod.PUT)
	public LessonInfo update(@RequestBody LessonInfo lessonInfo) throws Exception {
		return lessonService.update(lessonInfo);
	}

	@RequestMapping(value = "/lesson/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		lessonService.delete(id);
	}
	
	@RequestMapping(value = "/lesson/all", method = RequestMethod.GET)
    public List<LessonInfo> findAll() {
        return lessonService.findAll();
    }
}
