/*
 * 项目名称：bwk-core
 * 类名称: OrderExcelWriter
 * 创建时间: 2016年11月23日 下午3:39:27
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.order.spi.xls;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymt.mirage.order.domain.Order;
import com.ymt.pz365.data.jpa.spi.order.OrderGoodsService;

import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@Component("orderExcelWriter")
public class OrderExcelWriter extends AbstractExcelWriter<Order> {

    @Autowired
    private OrderGoodsService orderGoodsService;
    
    @Override
    protected void writeLine(WritableSheet sheet, Order data, int rowIndex, WritableCellFormat normalCellFormat,
            WritableCellFormat hightLightCellFormat) throws Exception {
        
        String name = orderGoodsService.getGoodsInfo(data.getProducts().get(0).getGoodsId()).getName();
        
        sheet.addCell(new Label(0, rowIndex, name, normalCellFormat));
        sheet.addCell(new Label(1, rowIndex, data.getId().toString(), normalCellFormat));
        sheet.addCell(new Label(2, rowIndex, new DateTime(data.getCreatedTime()).toString("yyyy-MM-dd"), normalCellFormat));
        sheet.addCell(new Label(3, rowIndex, data.getState().toString(), normalCellFormat));
        sheet.addCell(new Label(4, rowIndex, data.getUser().getNickname(), normalCellFormat));
        sheet.addCell(new Label(4, rowIndex, data.getUser().getWeixin(), normalCellFormat));
        sheet.addCell(new Label(5, rowIndex, data.getUser().getRealname(), normalCellFormat));
        sheet.addCell(new Label(6, rowIndex, data.getUser().getMobile(), normalCellFormat));
        sheet.addCell(new Label(7, rowIndex, data.getUser().getAddress(), normalCellFormat));
        
    }
    
    @Override
    protected List<String> getHeader(Order data) throws Exception {
        List<String> headers = new ArrayList<>();
        headers.add("商品名称");
        headers.add("订单编号");
        headers.add("下单时间");
        headers.add("订单状态");
        headers.add("微信昵称");
        headers.add("微信号");
        headers.add("姓名");
        headers.add("手机号");
        headers.add("收货地址");
        return headers;
    }

}
