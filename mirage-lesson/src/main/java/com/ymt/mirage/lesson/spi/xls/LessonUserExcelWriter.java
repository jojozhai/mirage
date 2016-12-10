/*
 * 项目名称：bwk-core
 * 类名称: LessonUserExcelWriter
 * 创建时间: 2016年11月23日 下午3:39:27
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.lesson.spi.xls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ymt.mirage.lesson.domain.LessonUser;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Component
public class LessonUserExcelWriter extends AbstractExcelWriter<LessonUser> {

    @Override
    protected void writeLine(WritableSheet sheet, LessonUser data, int rowIndex, WritableCellFormat normalCellFormat,
            WritableCellFormat hightLightCellFormat) throws Exception {
        sheet.addCell(new Label(0, rowIndex, data.getLesson().getName(), normalCellFormat));
        sheet.addCell(new Label(1, rowIndex, data.getUser().getRealname(), normalCellFormat));
        sheet.addCell(new Label(2, rowIndex, data.getUser().getSexDesc(), normalCellFormat));
        sheet.addCell(new Label(3, rowIndex, data.getUser().getJob(), normalCellFormat));
        sheet.addCell(new Label(4, rowIndex, data.getUser().getMobile(), normalCellFormat));
        sheet.addCell(new Label(5, rowIndex, data.getUser().getWeixin(), normalCellFormat));
        sheet.addCell(new Label(6, rowIndex, data.getUser().getCity(), normalCellFormat));
        sheet.addCell(new Label(7, rowIndex, data.isOnline()?"线上":"线下", normalCellFormat));
        
    }
    
    @Override
    protected List<String> getHeader(LessonUser data) throws Exception {
        List<String> headers = new ArrayList<>();
        headers.add("课程名称");
        headers.add("姓名");
        headers.add("性别");
        headers.add("职业");
        headers.add("手机号");
        headers.add("微信号");
        headers.add("城市");
        headers.add("线上");
        return headers;
    }

}
