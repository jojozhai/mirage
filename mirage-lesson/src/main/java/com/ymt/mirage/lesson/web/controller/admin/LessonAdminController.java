/**
 * 
 */
package com.ymt.mirage.lesson.web.controller.admin;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ymt.pz365.framework.web.controller.DownloadController;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile("admin")
public class LessonAdminController extends DownloadController {
	
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
	
	@RequestMapping(value = "/lesson/{id}/export", method = RequestMethod.GET)
    public void export(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
	    File folder = new File(request.getServletContext().getRealPath("")+"/xls");
	    if(!folder.exists()) {
	        folder.mkdirs();
	    }
	    File file = new File(folder, UUID.randomUUID().toString()+".xls");
	    String name = lessonService.export(id, file);
        downloadFile(request, response, file.getAbsolutePath(), name+"报名信息.xls");
    }

	@RequestMapping(value = "/lesson/{id}", method = RequestMethod.PUT)
	public LessonInfo update(@RequestBody LessonInfo lessonInfo) throws Exception {
		return lessonService.update(lessonInfo);
	}
	
	@RequestMapping(value = "/lesson/{id}/content", method = RequestMethod.PUT)
    public LessonInfo updateContent(@RequestBody LessonInfo lessonInfo) throws Exception {
        return lessonService.updateContent(lessonInfo);
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
