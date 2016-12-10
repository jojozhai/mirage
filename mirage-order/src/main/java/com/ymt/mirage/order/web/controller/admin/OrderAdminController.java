/**
 * 
 */
package com.ymt.mirage.order.web.controller.admin;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ymt.mirage.order.dto.OrderInfo;
import com.ymt.mirage.order.service.OrderService;
import com.ymt.pz365.framework.web.controller.DownloadController;

/**
 * @author zhailiang
 * @since 2016年5月5日
 */
@RestController
@Profile("admin")
public class OrderAdminController extends DownloadController {
	
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public Page<OrderInfo> query(OrderInfo orderInfo, Pageable pageable) {
		return orderService.query(orderInfo, pageable);
	}
	
	@RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
    public OrderInfo update(@RequestBody OrderInfo lessonInfo) throws Exception {
        return orderService.update(lessonInfo);
    }
	
	@RequestMapping(value = "/order/export", method = RequestMethod.GET)
    public void export(OrderInfo orderInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        File folder = new File(request.getServletContext().getRealPath("")+"/xls");
        if(!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(folder, UUID.randomUUID().toString()+".xls");
        orderService.export(orderInfo, file);
        downloadFile(request, response, file.getAbsolutePath(), "订单信息.xls");
    }
	
}
