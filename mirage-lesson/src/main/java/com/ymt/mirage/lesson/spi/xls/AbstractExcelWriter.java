/**
 * 
 */
package com.ymt.mirage.lesson.spi.xls;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * @author zhailiang
 *
 */
public abstract class AbstractExcelWriter<T> implements ExcelWriter<T> {

	@Override
	public void write(List<T> datas, File file) {
		
		if(datas == null) {
			datas = new ArrayList<T>();
		}
		
		int rowIndex = 1;

		WritableWorkbook workbook = null;
		try {
			workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet(getSheetName(), 0);

			WritableCellFormat misMatchCellFormat = getHeightCellFormat();

			WritableCellFormat cellFormat = getNormalCellFormat();

			T first = null;
			if(CollectionUtils.isNotEmpty(datas)){
			    first = datas.get(0);
			}
			writeHeader(getHeader(first), sheet, cellFormat);

			for (T data : datas) {
				writeLine(sheet, data, rowIndex, cellFormat, misMatchCellFormat);
				rowIndex++;
			}
			workbook.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭 Excel 工作薄对象
			try {
				if (workbook != null) {
					workbook.close();
				}
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	protected abstract void writeLine(WritableSheet sheet, T data, int rowIndex, WritableCellFormat normalCellFormat, WritableCellFormat hightLightCellFormat) throws Exception;

	protected String getSheetName() {
		return "data1";
	}

	private WritableCellFormat getHeightCellFormat() throws WriteException {
		WritableCellFormat misMatchCellFormat = getNormalCellFormat();
		misMatchCellFormat.setBackground(Colour.YELLOW2);
		return misMatchCellFormat;
	}

	private WritableCellFormat getNormalCellFormat() {
		WritableCellFormat cellFormat = new WritableCellFormat();
		return cellFormat;
	}
	
	/**
	 * 获取表头
	 * @return
	 */
	protected abstract List<String> getHeader(T data) throws Exception ;

	/**
	 * @param task
	 * @param sheet
	 * @param cellFormat
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	protected void writeHeader(List<String> headers, WritableSheet sheet, WritableCellFormat cellFormat) throws Exception {
		for (int i = 0; i < headers.size(); i++) {
			String header = headers.get(i);
			sheet.addCell(new Label(i, 0, header, cellFormat));
		}
	}

}
