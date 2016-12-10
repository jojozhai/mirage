/**
 * 
 */
package com.ymt.mirage.lesson.spi.xls;

import java.io.File;
import java.util.List;

/**
 * @author zhailiang
 *
 */
public interface ExcelWriter<T> {
	
	void write(List<T> data, File file);

}
