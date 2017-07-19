package com.tc.ta.util;

import javax.servlet.http.HttpServletResponse;


/**
 * Created by raoyunfei on 9/10/15.
 */
public class ExcelUtil {

	public static void generateOrderRateExcel(HttpServletResponse response ) throws Exception {
//		response.setContentType("application/x-msdownload");
//		XSSFWorkbook wb = new XSSFWorkbook();
//		XSSFSheet s = wb.createSheet();
//		wb.setSheetName(0, "订单评价");
//		// the first row is title.
//		XSSFRow firstRow = s.createRow(0);
//		XSSFCell cell2 = firstRow.createCell(0);
//		cell2.setCellValue("评价id");
//		cell2 = firstRow.createCell(1);
//		cell2.setCellValue("阿姨花名");
//		cell2 = firstRow.createCell(2);
//		cell2.setCellValue("阿姨名称");
//		cell2 = firstRow.createCell(3);
//		cell2.setCellValue("订单ID");
//		cell2 = firstRow.createCell(4);
//		cell2.setCellValue("客户号码");
//		cell2 = firstRow.createCell(5);
//		cell2.setCellValue("总分");
//		cell2 = firstRow.createCell(6);
//		cell2.setCellValue("评价类型");
//		cell2 = firstRow.createCell(7);
//		cell2.setCellValue("评价内容");
//		cell2 = firstRow.createCell(8);
//		cell2.setCellValue("好评");
//		cell2 = firstRow.createCell(9);
//		cell2.setCellValue("差评");
//		cell2 = firstRow.createCell(10);
//		cell2.setCellValue("评价时间");
//		cell2 = firstRow.createCell(11);
//		cell2.setCellValue("奖励金额");
//		cell2 = firstRow.createCell(12);
//		cell2.setCellValue("评价来源");
//		cell2 = firstRow.createCell(13);
//		cell2.setCellValue("回复用户");
//		cell2 = firstRow.createCell(14);
//		cell2.setCellValue("回复时间");
//		cell2 = firstRow.createCell(15);
//		cell2.setCellValue("回复内容");
//
//		// set the validate.
//		int j = 1;
//		Iterator<OrderRate> it = orderRateList.iterator();
//		while (it.hasNext()) {
//			OrderRate orderRate = it.next();
//			XSSFRow row = s.createRow(j);
//			XSSFCell _cell3 = row.createCell(0);
//			_cell3.setCellValue(orderRate.getId());
//			_cell3 = row.createCell(1);
//			_cell3.setCellValue(orderRate.getAuntName());
//			_cell3 = row.createCell(2);
//			_cell3.setCellValue(orderRate.getAuntRealName());
//			_cell3 = row.createCell(3);
//			_cell3.setCellValue(orderRate.getOrderId());
//			_cell3 = row.createCell(4);
//			_cell3.setCellValue(orderRate.getClientPhoneNumber());
//			_cell3 = row.createCell(5);
//			_cell3.setCellValue(orderRate.getScore().doubleValue());
//			_cell3 = row.createCell(6);
//			_cell3.setCellValue(orderRate.getStateStr());
//			_cell3 = row.createCell(7);
//			_cell3.setCellValue(orderRate.getComments());
//			_cell3 = row.createCell(8);
//			_cell3.setCellValue(orderRate.getGoodTitles());
//			_cell3 = row.createCell(9);
//			_cell3.setCellValue(orderRate.getBadTitles());
//			_cell3 = row.createCell(10);
//			_cell3.setCellValue(DateUtil.format2ymdhms(orderRate.getAddTime()));
//			_cell3 = row.createCell(11);
//			_cell3.setCellValue(orderRate.getBonusMoney().doubleValue());
//			_cell3 = row.createCell(12);
//			_cell3.setCellValue(orderRate.getSourceStr());
//			_cell3 = row.createCell(13);
//			_cell3.setCellValue(orderRate.getSysReplyUserId());
//			_cell3 = row.createCell(14);
//			_cell3.setCellValue(DateUtil.format2ymdhms(orderRate.getSysReplyTime()));
//			_cell3 = row.createCell(15);
//			_cell3.setCellValue(orderRate.getSysReplyContent());
//			j++;
//
//		}
//		String inlineType = "attachment"; // 是否内联附件
//		String downFileName = "dataFile.xlsx";
//		response.setHeader("Content-Disposition", inlineType + ";filename=\"" + downFileName + "\"");
//		OutputStream out = null;
//		try {
//			out = response.getOutputStream();
//			wb.write(out);
//			out.flush();
//		} catch (IOException e) {
//			throw new Exception("export order rate excel error!");
//		} finally {
//			if (null != out) {
//				try {
//					out.close();
//				} catch (IOException e) {
//				}
//			}
//		}
	}

}
