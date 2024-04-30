//package com.ian.media.util.excel;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.text.DecimalFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.Map.Entry;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.poi.hssf.usermodel.DVConstraint;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFDataValidation;
//import org.apache.poi.hssf.usermodel.HSSFRichTextString;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.Font;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.ss.util.CellRangeAddressList;
//import org.apache.poi.ss.util.RegionUtil;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//
//import com.ian.das.model.AppointmentRecord;
//
///**
// * 文件名称:ExcelUtils.java 文件描述: 项目通用Excel操作工具类 完成日期：2014年6月3日
// * 
// * @author amos
// */
//public class ExcelUtils {
//	private HSSFWorkbook wb;// Excel文件对象
//	private CellStyle titleStyle; // 标题行样式
//	private Font titleFont; // 标题行字体
//	private CellStyle dateStyle; // 日期行样式
//	private Font dateFont; // 日期行字体
//	private CellStyle headStyle; // 表头行样式
//	private Font headFont; // 表头行字体
//	private CellStyle leftTextStyle; // 居左文本样式
//	private Font leftTextFont; // 居左文本字体
//	private CellStyle centerTextStyle; // 居中文本样式
//	private Font centerTextFont; // 居中文本字体
//	private CellStyle rightTextStyle; // 居右文本样式
//	private Font rightTextFont; // 居右文字字体
//
//	/**
//	 * EXCEL导出工具类初始化工作，初始化工具类提供的默认字体，样式等等操作对象
//	 * 
//	 * @param useDefaultSetting
//	 *            是否使用工具类通用配置 如果参数为true，工具类提供的所有字体样式将以默认行为进行初始化
//	 *            如果参数为false，建议通过initFont与initCellStyle方法手动建立需要的字体和单元格样式
//	 */
//	public void init(Boolean useDefaultSetting) {
//		wb = new HSSFWorkbook();
//
//		titleFont = wb.createFont();
//		dateFont = wb.createFont();
//		headFont = wb.createFont();
//		leftTextFont = wb.createFont();
//		centerTextFont = wb.createFont();
//		rightTextFont = wb.createFont();
//		titleStyle = wb.createCellStyle();
//		dateStyle = wb.createCellStyle();
//		headStyle = wb.createCellStyle();
//		leftTextStyle = wb.createCellStyle();
//		centerTextStyle = wb.createCellStyle();
//		rightTextStyle = wb.createCellStyle();
//		if (useDefaultSetting) {
//			useDefaultSettng();
//		}
//	}
//
//	/**
//	 * 初始化标题行字体
//	 */
//	public void initTitleFont() {
//		initFont(titleFont, "宋体", 16, Font.BOLDWEIGHT_BOLD,
//				IndexedColors.BLACK.index);
//	}
//
//	/**
//	 * 初始化日期行字体
//	 */
//	public void initDateFont() {
//		initFont(dateFont, "宋体", 14, Font.BOLDWEIGHT_BOLD,
//				IndexedColors.BLACK.index);
//	}
//
//	/**
//	 * 初始化表头行字体
//	 */
//	public void initHeadFont() {
//		initFont(headFont, "宋体", 14, Font.BOLDWEIGHT_BOLD,
//				IndexedColors.BLACK.index);
//	}
//
//	/**
//	 * 初始化居左文字字体
//	 */
//	public void initLeftTextFont() {
//		initFont(leftTextFont, "宋体", 14, Font.BOLDWEIGHT_NORMAL,
//				IndexedColors.BLACK.index);
//	}
//
//	/**
//	 * 初始化居中文字字体
//	 */
//	public void initCenterTextFont() {
//		initFont(centerTextFont, "宋体", 14, Font.BOLDWEIGHT_NORMAL,
//				IndexedColors.BLACK.index);
//	}
//
//	/**
//	 * 初始化居右文字字体
//	 */
//	public void initRightTextFont() {
//		initFont(rightTextFont, "宋体", 14, Font.BOLDWEIGHT_NORMAL,
//				IndexedColors.BLACK.index);
//	}
//
//	/**
//	 * 定义标题行样式
//	 */
//	public void initTitleStyle() {
//		initCellStyle(titleStyle, titleFont, CellStyle.ALIGN_CENTER,
//				CellStyle.VERTICAL_CENTER, null, null, null, null, null, null,
//				null, null, null);
//	}
//
//	/**
//	 * 定义日期行样式
//	 */
//	public void initDateStyle() {
//		initCellStyle(dateStyle, dateFont, CellStyle.ALIGN_LEFT,
//				CellStyle.VERTICAL_CENTER, null, null, null, null, null, null,
//				null, null, null);
//	}
//
//	/**
//	 * 定义表头行样式
//	 */
//	public void initHeadStyle() {
//		initCellStyle(headStyle, headFont, CellStyle.ALIGN_CENTER,
//				CellStyle.VERTICAL_CENTER, HSSFCellStyle.BORDER_THIN,
//				HSSFCellStyle.BORDER_THIN, HSSFCellStyle.BORDER_THIN,
//				HSSFCellStyle.BORDER_THIN, null, null, null, null, null);
//	}
//
//	/**
//	 * 定义居左文本样式
//	 */
//	public void initLeftTextStyle() {
//		initCellStyle(leftTextStyle, leftTextFont, CellStyle.ALIGN_LEFT,
//				CellStyle.VERTICAL_CENTER, HSSFCellStyle.BORDER_THIN,
//				HSSFCellStyle.BORDER_THIN, HSSFCellStyle.BORDER_THIN,
//				HSSFCellStyle.BORDER_THIN, null, null, null, null, null);
//	}
//
//	/**
//	 * 定义居中文本样式
//	 */
//	public void initCenterTextStyle() {
//		initCellStyle(centerTextStyle, centerTextFont, CellStyle.ALIGN_CENTER,
//				CellStyle.VERTICAL_CENTER, HSSFCellStyle.BORDER_THIN,
//				HSSFCellStyle.BORDER_THIN, HSSFCellStyle.BORDER_THIN,
//				HSSFCellStyle.BORDER_THIN, null, null, null, null, null);
//	}
//
//	/**
//	 * 定义居右文本样式
//	 */
//	public void initRightTextStyle() {
//		initCellStyle(rightTextStyle, rightTextFont, CellStyle.ALIGN_RIGHT,
//				CellStyle.VERTICAL_CENTER, HSSFCellStyle.BORDER_THIN,
//				HSSFCellStyle.BORDER_THIN, HSSFCellStyle.BORDER_THIN,
//				HSSFCellStyle.BORDER_THIN, null, null, null, null, null);
//	}
//
//	/**
//	 * 使用所有默认配置
//	 */
//	public void useDefaultSettng() {
//		initTitleFont();
//		initTitleStyle();
//		initDateFont();
//		initDateStyle();
//		initHeadFont();
//		initHeadStyle();
//		initLeftTextFont();
//		initLeftTextStyle();
//		initCenterTextFont();
//		initCenterTextStyle();
//		initRightTextFont();
//		initRightTextStyle();
//	}
//
//	/**
//	 * @Description: 定义字体通用方法
//	 */
//	public static void initFont(Font font, String fontName,
//			Integer fontHeightInPoints, Short boldWeight, Short color) {
//		if (null != fontName) {
//			font.setFontName(fontName);
//		}
//		if (null != fontHeightInPoints) {
//			font.setFontHeightInPoints(Short.parseShort(String
//					.valueOf(fontHeightInPoints)));
//		}
//		if (null != boldWeight) {
//			font.setBoldweight(boldWeight);
//		}
//		if (null != color) {
//			font.setColor(color);
//		}
//	}
//
//	/**
//	 * 定义单元格样式的通用方法
//	 * 
//	 * @param cellStyle
//	 *            需要设置样式的单元格
//	 * @param font
//	 *            单元格所用字体
//	 * @param alignment
//	 *            文字水平对齐方式
//	 * @param verticalAlignment
//	 *            文字垂直对齐方式
//	 * @param fillBackgroundColor
//	 *            单元格背景色
//	 * @param borderTop
//	 *            单元格顶部边框样式
//	 * @param borderBottom
//	 *            单元格底部边框样式
//	 * @param borderLeft
//	 *            单元格左部边框样式
//	 * @param borderRight
//	 *            单元格右部边框样式
//	 * @param topBorderColor
//	 *            单元格顶部边框颜色
//	 * @param bottomBorderColor
//	 *            单元格底部边框颜色
//	 * @param leftBorderColor
//	 *            单元格左部边框颜色
//	 * @param rightBorderColor
//	 *            单元格右部边框颜色
//	 */
//	public static void initCellStyle(CellStyle cellStyle, Font font,
//			Short alignment, Short verticalAlignment, Short borderTop,
//			Short borderBottom, Short borderLeft, Short borderRight,
//			Short topBorderColor, Short bottomBorderColor,
//			Short leftBorderColor, Short rightBorderColor,
//			Short fillBackgroundColor) {
//		cellStyle.setWrapText(true);// 设置文本自动换行
//		if (null != font) {
//			cellStyle.setFont(font);
//		}
//		if (null != alignment) {
//			cellStyle.setAlignment(alignment);
//		}
//		if (null != verticalAlignment) {
//			cellStyle.setVerticalAlignment(verticalAlignment);
//		}
//		if (null != fillBackgroundColor) {
//			cellStyle.setFillBackgroundColor(fillBackgroundColor);
//		}
//		if (null != borderTop) {
//			cellStyle.setBorderTop(borderTop);
//		}
//		if (null != borderBottom) {
//			cellStyle.setBorderBottom(borderBottom);
//		}
//		if (null != borderLeft) {
//			cellStyle.setBorderLeft(borderLeft);
//		}
//		if (null != borderRight) {
//			cellStyle.setBorderRight(borderRight);
//		}
//		if (null != topBorderColor) {
//			cellStyle.setTopBorderColor(topBorderColor);
//		}
//		if (null != bottomBorderColor) {
//			cellStyle.setBottomBorderColor(bottomBorderColor);
//		}
//		if (null != leftBorderColor) {
//			cellStyle.setLeftBorderColor(leftBorderColor);
//		}
//		if (null != rightBorderColor) {
//			cellStyle.setRightBorderColor(rightBorderColor);
//		}
//	}
//
//	/**
//	 * 自动根据内容调整工作表列宽
//	 * 
//	 * @param sheet
//	 *            需要自动调整列宽的工作表对象
//	 * @param columnNum
//	 *            工作表有效列数
//	 */
//	public static void adjustColumnSize(HSSFSheet sheet, Integer columnNum) {
//		for (int i = 0; i < columnNum; i++) {
//			sheet.autoSizeColumn(i, true);
//		}
//	}
//
//	/**
//	 * 创建自动调整列宽的所有工作表对象
//	 * 
//	 * @param num
//	 *            需要建立的工作表数目
//	 * @param names
//	 *            工作表标签名集合
//	 * @return 工作表集合对象
//	 */
//	public HSSFSheet[] getAutoSizeSheets(int num, String[] names) {
//		HSSFSheet[] sheets = new HSSFSheet[num];
//		for (int i = 0; i < num; i++) {
//			sheets[i] = wb.createSheet(names[i]);
//			// 设置行高默认为45像素
//			sheets[i].setDefaultRowHeight((short) (15 * 40));
//		}
//		// 对每个工作表前20列自动调整列宽
//		for (int i = 0; i < num; i++) {
//			adjustColumnSize(sheets[i], 20);
//		}
//		return sheets;
//	}
//
//	/**
//	 * 创建指定列宽的所有工作表对象
//	 * 
//	 * @param num
//	 *            需要建立的工作表数目
//	 * @param names
//	 *            工作表标签名集合
//	 * @return 工作表集合对象
//	 */
//	public HSSFSheet[] getManualSizeSheets(int num, String[] names,
//			Map<Integer, List<Integer>> columnsWidthInfo) {
//		HSSFSheet[] sheets = new HSSFSheet[num];
//		// 迭代生成多个Sheet
//		for (int i = 0; i < num; i++) {
//			sheets[i] = wb.createSheet(names[i]);
//			// 设置行高默认为45像素
//			sheets[i].setDefaultRowHeight((short) (15 * 40));
//			// 获取当前Sheet定义的各列宽度
//			List<Integer> columnsWidth = columnsWidthInfo.get(i);
//			// 为当前Sheet对象定义各列宽度
//			for (int j = 0; j < columnsWidth.size(); j++) {
//				// 36.5表示一像素单位
//				// sheets[i].setColumnWidth(j, (int) (36.5 *
//				// columnsWidth.get(j)));
//				setColumnWidthByPix(sheets[i], j, columnsWidth.get(j));
//			}
//		}
//
//		return sheets;
//	}
//
//	public static void setColumnWidthByPix(Sheet sheet, int columnIndex,
//			int width) {
//		sheet.setColumnWidth(columnIndex, (int) (36.5 * width));
//	}
//
//	/**
//	 * @Description: 创建表格所有列合并的一行的通用方法
//	 */
//	public void createCommonRow(ExportSetInfo setInfo, HSSFSheet[] sheets,
//			int sheetNum, int startRowIndex, String contents,
//			Integer heightInPoint, CellStyle cellStyle) {
//		HSSFRow dataRow = sheets[sheetNum].createRow(startRowIndex);
//		HSSFCell dataCell = dataRow.createCell(0);
//		if (startRowIndex < 0) {
//			startRowIndex = 0;
//		}
//		CellRangeAddress dataRange = new CellRangeAddress(startRowIndex,
//				startRowIndex, 0,
//				setInfo.getFieldNames().get(sheetNum).length - 1);
//		sheets[sheetNum].addMergedRegion(dataRange);
//		dataRow.setHeightInPoints(heightInPoint);
//		dataCell.setCellStyle(cellStyle);
//		dataCell.setCellValue(contents);
//	}
//
//	/**
//	 * 为特定的工作表创建一块合并区域
//	 * 
//	 * @param sheet
//	 *            创建行的工作表
//	 * @param startRowIndex
//	 *            合并起始行
//	 * @param endRowIndex
//	 *            合并结束行
//	 * @param startColumnIndex
//	 *            合并起始列
//	 * @param endColumnIndex
//	 *            合并结束列
//	 * @param contents
//	 *            合并行的内容
//	 * @param heightInPoint
//	 *            行高
//	 * @param cellStyle
//	 *            该行单元格的样式
//	 * @param hasBorder
//	 *            是否需要边框
//	 */
//	public void createOneRow(HSSFSheet sheet, Integer startRowIndex,
//			Integer endRowIndex, Integer startColumnIndex,
//			Integer endColumnIndex, String contents, Integer heightInPoint,
//			CellStyle cellStyle, Boolean hasBorder) {
//		HSSFRow dataRow = sheet.createRow(startRowIndex);
//		HSSFCell dataCell = dataRow.createCell(startColumnIndex);
//		CellRangeAddress dataRange = new CellRangeAddress(startRowIndex,
//				endRowIndex, startColumnIndex, endColumnIndex);
//		sheet.addMergedRegion(dataRange);
//		dataCell.setCellStyle(cellStyle);
//		if (hasBorder) {
//			setRegionBorder(dataRange, sheet, getWb());
//		}
//		dataRow.setHeightInPoints(heightInPoint);
//		dataCell.setCellValue(contents);
//	}
//
//	/**
//	 * @Description: 创建标题行(需合并单元格)
//	 */
//	public void createTableTitleRow(ExportSetInfo setInfo, HSSFSheet[] sheets,
//			int sheetNum, int startRowIndex) {
//		createCommonRow(setInfo, sheets, sheetNum, startRowIndex, setInfo
//				.getTitles()[sheetNum], 36, titleStyle);
//	}
//
//	/**
//	 * @Description: 创建日期行(需合并单元格)
//	 */
//	public void createTableDateRow(ExportSetInfo setInfo, HSSFSheet[] sheets,
//			int sheetNum, int startRowIndex) {
//		createCommonRow(setInfo, sheets, sheetNum, startRowIndex,
//				new SimpleDateFormat("yyyy-MM-dd").format(new Date()), 32,
//				dateStyle);
//	}
//
//	/**
//	 * @Description: 创建表头行(需合并单元格)
//	 */
//	public void creatTableHeadRow(ExportSetInfo setInfo, HSSFSheet[] sheets,
//			int sheetNum, int startRowIndex) {
//		if (startRowIndex < 0) {
//			startRowIndex = 0;
//		}
//		// 表头
//		HSSFRow headRow = sheets[sheetNum].createRow(startRowIndex);
//		headRow.setHeightInPoints(32);
//		// 列头名称
//		for (int num = 0, len = setInfo.getHeadNames().get(sheetNum).length; num < len; num++) {
//			HSSFCell headCell = headRow.createCell(num);
//			headCell.setCellStyle(headStyle);
//			headCell.setCellValue(setInfo.getHeadNames().get(sheetNum)[num]);
//		}
//	}
//
//	/**
//	 * @Description: 创建内容行的每一列
//	 */
//	public HSSFCell[] getCells(HSSFRow contentRow, int num) {
//		HSSFCell[] cells = new HSSFCell[num];
//
//		for (int i = 0, len = cells.length; i < len; i++) {
//			cells[i] = contentRow.createCell(i);
//		}
//		return cells;
//	}
//
//	/**
//	 * 为合并的单元格设置边框
//	 * 
//	 * @param region
//	 *            合并单元格代表对象
//	 * @param sheet
//	 *            单元格所在sheet对象
//	 * @param wb
//	 *            单元格所在workbook对象
//	 */
//	public static void setRegionBorder(CellRangeAddress region, Sheet sheet,
//			Workbook wb) {
//		RegionUtil
//				.setBorderBottom(HSSFCellStyle.BORDER_THIN, region, sheet, wb);
//		RegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, region, sheet, wb);
//		RegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, region, sheet, wb);
//		RegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, region, sheet, wb);
//	}
//
//	/**
//	 * 导出Excel通用核心方法
//	 * 
//	 * @param setInfo
//	 *            导出的信息封装对象，包括数据，列宽定义等等
//	 * @param titleRowIndex
//	 *            标题行所在索引
//	 * @param dateRowIndex
//	 *            日期行所在索引
//	 * @param headRowIndex
//	 *            列名行所在索引
//	 * @param contentRowIndex
//	 *            数据内容起始行索引
//	 * @param useDefaultSetting
//	 *            是否使用默认样式配置
//	 * @param pattern
//	 *            对时间类数据指定显示模式（如yyyy-MM-dd等）
//	 */
//	public void exportExcel(ExportSetInfo setInfo, Integer titleRowIndex,
//			Integer dateRowIndex, Integer headRowIndex,
//			Integer contentRowIndex, Boolean useDefaultSetting, String pattern) {
//		init(useDefaultSetting);
//		Set<Entry<String, List>> set = setInfo.getObjsMap().entrySet();
//		String[] sheetNames = new String[setInfo.getObjsMap().size()];
//		int sheetNameNum = 0;
//		for (Entry<String, List> entry : set) {
//			sheetNames[sheetNameNum] = entry.getKey();
//			sheetNameNum++;
//		}
//		HSSFSheet[] sheets = null;
//		// 自动调整列宽
//		if (null == setInfo.getColumnWidthMap()
//				|| setInfo.getColumnWidthMap().size() == 0) {
//			sheets = getAutoSizeSheets(setInfo.getObjsMap().size(), sheetNames);
//			// 手工指定列宽
//		} else {
//			sheets = getManualSizeSheets(setInfo.getObjsMap().size(),
//					sheetNames, setInfo.getColumnWidthMap());
//		}
//
//		int sheetNum = 0;
//		for (Entry<String, List> entry : set) {
//			// Sheet
//			List objs = entry.getValue();
//			if (null != titleRowIndex && titleRowIndex > -1) {
//				// 标题行
//				createTableTitleRow(setInfo, sheets, sheetNum, titleRowIndex);
//			}
//			if (null != dateRowIndex && dateRowIndex > -1) {
//				// 日期行
//				createTableDateRow(setInfo, sheets, sheetNum, dateRowIndex);
//			}
//			if (null != headRowIndex && headRowIndex > -1) {
//				// 表头
//				creatTableHeadRow(setInfo, sheets, sheetNum, headRowIndex);
//			}
//			// 表体
//			String[] fieldNames = setInfo.getFieldNames().get(sheetNum);
//			if (null == contentRowIndex || contentRowIndex < 0) {
//				contentRowIndex = 0;
//			}
//			int rowNum = contentRowIndex;
//			for (Object obj : objs) {
//				HSSFRow contentRow = sheets[sheetNum].createRow(rowNum);
//				contentRow.setHeightInPoints(32);
//				HSSFCell[] cells = getCells(contentRow, setInfo.getFieldNames()
//						.get(sheetNum).length);
//				if (fieldNames != null) {
//					for (int num = 0; num < fieldNames.length; num++) {
//						Object[] typeAndValue = ReflectionUtils
//								.invokeGetterMethod(obj, fieldNames[num]);
//						String classType = typeAndValue[0].toString();
//						Object value = typeAndValue[1];
//						// 属于数字类型的值,单元格文本向右对齐
//						if (("Byte,Short,Integer,Long,Float,Double")
//								.indexOf(classType) > -1) {
//							// 设定单元格类型为数字
//							cells[num].setCellType(Cell.CELL_TYPE_NUMERIC);
//							// 如果是序号列，文字居中
//							if ("sn".equals(fieldNames[num])) {
//								cells[num].setCellStyle(centerTextStyle);
//								// 其他数字列，文字居右
//							} else {
//								cells[num].setCellStyle(rightTextStyle);
//							}
//							// 其余类型全当字符串处理，文字居左
//						} else {
//							cells[num].setCellType(Cell.CELL_TYPE_STRING);
//							cells[num].setCellStyle(leftTextStyle);
//						}
//						if (value != null) {
//							// 整数
//							if ("Byte,Short,Integer,Long".indexOf(classType) > -1) {
//								Long parsedValue = Long.parseLong(value
//										.toString());
//								cells[num].setCellValue(parsedValue);
//								// 小数
//							} else if ("Float,Double".indexOf(classType) > -1) {
//								Double parsedValue = Double.parseDouble(value
//										.toString());
//								cells[num].setCellValue(parsedValue);
//								// 时间格式字段处理
//							} else if ("Date,Timestamp".indexOf(classType) > -1) {
//								String defaultPattern = "yyyy-MM-dd";
//								if (null != pattern) {
//									defaultPattern = pattern;
//								}
//								// 英文时间格式字符串转为时间对象
//								Date date = DateUtil
//										.getDateFromForeginStr(value.toString());
//								cells[num].setCellValue(DateUtil.getDateFormat(
//										date, defaultPattern));
//							} else {
//								cells[num].setCellValue(value.toString());
//							}
//						}
//
//					}
//				}
//				rowNum++;
//			}
//			sheetNum++;
//		}
//
//	}
//
//	/**
//	 * @方法名: addBoxList
//	 * @描述: 在excel指定区域生成下拉选择列表
//	 * @作者:amos
//	 * @时间:2014年6月3日
//	 * @参数:@param sheet
//	 * @参数:@param rowIndex
//	 * @参数:@param colIndex
//	 * @参数:@param list
//	 * @参数:@param currentValue
//	 * @返回值：void
//	 */
//	public static void addBoxList(HSSFSheet sheet, int rowIndex, int colIndex,
//			String[] list, String currentValue) {
//		if (list == null) {
//			return;
//		}
//
//		HSSFRow row = sheet.getRow(rowIndex);
//		HSSFCell cell = row.getCell(colIndex);
//		if (StringUtils.isBlank(currentValue)) {
//			currentValue = "";
//		}
//		cell.setCellValue(new HSSFRichTextString(currentValue));
//		CellRangeAddressList regions = new CellRangeAddressList(rowIndex,
//				rowIndex, colIndex, colIndex);
//		DVConstraint constraint = DVConstraint
//				.createExplicitListConstraint(list);
//		HSSFDataValidation data_validation = new HSSFDataValidation(regions,
//				constraint);
//		sheet.addValidationData(data_validation);
//	}
//
//	public HSSFWorkbook getWb() {
//		return wb;
//	}
//
//	public void setWb(HSSFWorkbook wb) {
//		this.wb = wb;
//	}
//
//	public CellStyle getTitleStyle() {
//		return titleStyle;
//	}
//
//	public void setTitleStyle(CellStyle titleStyle) {
//		this.titleStyle = titleStyle;
//	}
//
//	public Font getTitleFont() {
//		return titleFont;
//	}
//
//	public void setTitleFont(Font titleFont) {
//		this.titleFont = titleFont;
//	}
//
//	public CellStyle getDateStyle() {
//		return dateStyle;
//	}
//
//	public void setDateStyle(CellStyle dateStyle) {
//		this.dateStyle = dateStyle;
//	}
//
//	public Font getDateFont() {
//		return dateFont;
//	}
//
//	public void setDateFont(Font dateFont) {
//		this.dateFont = dateFont;
//	}
//
//	public CellStyle getHeadStyle() {
//		return headStyle;
//	}
//
//	public void setHeadStyle(CellStyle headStyle) {
//		this.headStyle = headStyle;
//	}
//
//	public Font getHeadFont() {
//		return headFont;
//	}
//
//	public void setHeadFont(Font headFont) {
//		this.headFont = headFont;
//	}
//
//	public CellStyle getLeftTextStyle() {
//		return leftTextStyle;
//	}
//
//	public void setLeftTextStyle(CellStyle leftTextStyle) {
//		this.leftTextStyle = leftTextStyle;
//	}
//
//	public Font getLeftTextFont() {
//		return leftTextFont;
//	}
//
//	public void setLeftTextFont(Font leftTextFont) {
//		this.leftTextFont = leftTextFont;
//	}
//
//	public CellStyle getCenterTextStyle() {
//		return centerTextStyle;
//	}
//
//	public void setCenterTextStyle(CellStyle centerTextStyle) {
//		this.centerTextStyle = centerTextStyle;
//	}
//
//	public Font getCenterTextFont() {
//		return centerTextFont;
//	}
//
//	public void setCenterTextFont(Font centerTextFont) {
//		this.centerTextFont = centerTextFont;
//	}
//
//	public CellStyle getRightTextStyle() {
//		return rightTextStyle;
//	}
//
//	public void setRightTextStyle(CellStyle rightTextStyle) {
//		this.rightTextStyle = rightTextStyle;
//	}
//
//	public Font getRightTextFont() {
//		return rightTextFont;
//	}
//
//	public void setRightTextFont(Font rightTextFont) {
//		this.rightTextFont = rightTextFont;
//	}
//
//	public static void main(String[] args) throws Exception {
//		List<AppointmentRecord> users = new ArrayList<AppointmentRecord>();
//		users.add(new AppointmentRecord());
////		users.add(new User(2, "李四", true, 6000.879, 37));
////		users.add(new User(3, "王五", true, 7000.106, 47));
////		users.add(new User(4, "赵六", true, 8000.887, 38));
////		users.add(new User(5, "钱七", true, 9000.294, 17));
//		List<String[]> headNames = new ArrayList<String[]>();
//		headNames.add(new String[] { "序号", "用户名", "性别", "月薪", "年龄" });
//		List<String[]> fieldNames = new ArrayList<String[]>();
//		fieldNames.add(new String[] { "sn", "name", "sex", "salary", "age" });
//		LinkedHashMap<Integer, List<Integer>> columnsWidthInfo = new LinkedHashMap<Integer, List<Integer>>();
//		List<Integer> columnsWidth = new ArrayList<Integer>();
//		columnsWidth.add(50);
//		columnsWidth.add(100);
//		columnsWidth.add(105);
//		columnsWidth.add(123);
//		columnsWidth.add(168);
//		columnsWidthInfo.put(0, columnsWidth);
//
//		ExportSetInfo setInfo = new ExportSetInfo();
//		LinkedHashMap<String, List> objsMap = new LinkedHashMap<String, List>();
//		objsMap.put("用户信息表", users);
//		setInfo.setObjsMap(objsMap);
//		setInfo.setFieldNames(fieldNames);
//		setInfo.setTitles(new String[] { "后台用户信息" });
//		setInfo.setHeadNames(headNames);
//		setInfo.setColumnWidthMap(columnsWidthInfo);
//
//		// 将需要导出的数据输出到baos
//		ExcelUtils utils = new ExcelUtils();
//		utils.exportExcel(setInfo, 0, 1, 2, 3, true, null);
//		HSSFWorkbook wb = utils.getWb();
//		wb.write(setInfo.getOut());
//		InputStream excel = new ByteArrayInputStream(setInfo.getOut()
//				.toByteArray());
//		inputStreamToFile(excel, new File("e:/gxTest.xls"));
//	}
//  public void test()
//  {
//
//		List<AppointmentRecord> users = new ArrayList<AppointmentRecord>();
//		AppointmentRecord  ad=new AppointmentRecord();
//		ad.setBrandId("11");
//		ad.setSeriesId("2wdw");
//		users.add(ad);
////		users.add(new AppointmentRecord());
////		users.add(new User(2, "李四", true, 6000.879, 37));
////		users.add(new User(3, "王五", true, 7000.106, 47));
////		users.add(new User(4, "赵六", true, 8000.887, 38));
////		users.add(new User(5, "钱七", true, 9000.294, 17));
//		List<String[]> headNames = new ArrayList<String[]>();
//		headNames.add(new String[] { "序号", "用户名", "性别", "月薪", "年龄" });
//		List<String[]> fieldNames = new ArrayList<String[]>();
//		fieldNames.add(new String[] { "brandId", "seriesId", "seriesName", "modelsName", "modelsName" });
//
//		LinkedHashMap<Integer, List<Integer>> columnsWidthInfo = new LinkedHashMap<Integer, List<Integer>>();
//		List<Integer> columnsWidth = new ArrayList<Integer>();
//		columnsWidth.add(50);
//		columnsWidth.add(100);
//		columnsWidth.add(50);
//		columnsWidth.add(50);
//		columnsWidth.add(50);
//		columnsWidth.add(50);
//		columnsWidth.add(50);
//		columnsWidth.add(50);
//		
//		columnsWidthInfo.put(0, columnsWidth);
//
//		ExportSetInfo setInfo = new ExportSetInfo();
//		LinkedHashMap<String, List> objsMap = new LinkedHashMap<String, List>();
//		objsMap.put("用户信息表", users);
//		setInfo.setObjsMap(objsMap);
//		setInfo.setFieldNames(fieldNames);
//		setInfo.setTitles(new String[] { "后台用户信息" });
//		setInfo.setHeadNames(headNames);
//		setInfo.setColumnWidthMap(columnsWidthInfo);
//
//		// 将需要导出的数据输出到baos
//		ExcelUtils utils = new ExcelUtils();
//		utils.exportExcel(setInfo, 0, null, 1, 2, true, null);
//		HSSFWorkbook wb = utils.getWb();
//		try {
//			wb.write(setInfo.getOut());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		InputStream excel = new ByteArrayInputStream(setInfo.getOut()
//				.toByteArray());
//		inputStreamToFile(excel, new File("e:/gxTest.xls"));
//	  
//  }
//	/**
//	 * 将输入流转化为文件对象（测试用）
//	 * 
//	 * @param ins
//	 *            文件输入流
//	 * @param file
//	 *            目标文件对象
//	 */
//	public static void inputStreamToFile(InputStream ins, File file) {
//		try {
//			OutputStream os = new FileOutputStream(file);
//			int bytesRead = 0;
//			byte[] buffer = new byte[8192];
//			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
//				os.write(buffer, 0, bytesRead);
//			}
//			os.close();
//			ins.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 以像素为单位设置Excel表格行高
//	 * 
//	 * @param row
//	 *            需要设置高度的行
//	 * @param height
//	 *            设置的高度（像素）
//	 */
//	public static void setRowHeightByPix(HSSFRow row, int height) {
//		// 在Excel中，15个单位代表一个像素高度
//		row.setHeight((short) (15 * height));
//	}
//
//	/**
//	 * 将代码生成的Excel文件以流的形式进行复制传输
//	 * 
//	 * @param os
//	 *            输出流
//	 * @param is
//	 *            输入流
//	 * @param wb
//	 *            HSSFWorkBook对象
//	 */
//	public static InputStream copyStream(ByteArrayOutputStream os,
//			InputStream is, HSSFWorkbook wb) {
//		try {
//			wb.write(os);
//			os.flush();
//			byte[] buf = os.toByteArray();
//			is = new ByteArrayInputStream(buf, 0, buf.length);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		return is;
//	}
//
//	/**
//	 * 根据内容长度设置行高 大致标准：34个汉字需要换一行
//	 * 
//	 * @param row
//	 *            需要设置行高的行
//	 * @param contents
//	 *            文本内容
//	 * @param byteNum
//	 *            换行需要满足的字节数
//	 */
//	public static void setRowHeightByContents(HSSFRow row, String contents,
//			Integer byteNum) {
//		Integer contentLength = StringUtil.getStrRealLength(contents);
//		// System.out.println(contentLength);
//		Integer rowNum = contentLength / byteNum + 1;
//		ExcelUtils.setRowHeightByPix(row, rowNum * 30);
//	}
//
//	/**
//	 * @Description: 封装Excel导出的设置信息
//	 */
//	public static class ExportSetInfo {
//		/**
//		 * 需要导出的数据集合 Map的个数即为导出Excel时Sheet的数目
//		 * String对应每一个Sheet的名称，List对应该Sheet的数据
//		 */
//		@SuppressWarnings("unchecked")
//		private LinkedHashMap<String, List> objsMap;
//
//		/**
//		 * 需要导出的每个工作表所有列的宽度值 key为工作表在工作表集合中的索引号 value为当前索引的工作表所有列的宽度集合
//		 */
//		private LinkedHashMap<Integer, List<Integer>> columnWidthMap = new LinkedHashMap<Integer, List<Integer>>();
//
//		/**
//		 * 对应Excel中每个Sheet顶部的大标题名称
//		 */
//		private String[] titles;
//
//		/**
//		 * 对应Excel中每个Sheet底部的标签名称（如Sheet1,Sheet2）
//		 */
//		private String[] sheetNames;
//
//		/**
//		 * 对应EXCEL中每个Sheet中标题行每一列的列名
//		 */
//		private List<String[]> headNames;
//
//		/**
//		 * 对应每一个Sheet需要导出对应这对象的属性名，工具类只会导出数组中列举的属性
//		 */
//
//		private List<String[]> fieldNames;
//
//		/**
//		 * 对应导出EXCEL文件的输出流，用于进行导出等其他操作
//		 */
//		private ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//		@SuppressWarnings("unchecked")
//		public LinkedHashMap<String, List> getObjsMap() {
//			return objsMap;
//		}
//
//		/**
//		 * @param objMap
//		 *            导出数据
//		 * 
//		 *            泛型 String : 代表sheet名称 List : 代表单个sheet里的所有行数据
//		 */
//		@SuppressWarnings("unchecked")
//		public void setObjsMap(LinkedHashMap<String, List> objsMap) {
//			this.objsMap = objsMap;
//		}
//
//		public List<String[]> getFieldNames() {
//			return fieldNames;
//		}
//
//		/**
//		 * @param clazz
//		 *            对应每个sheet里的每行数据的对象的属性名称
//		 */
//		public void setFieldNames(List<String[]> fieldNames) {
//			this.fieldNames = fieldNames;
//		}
//
//		public String[] getTitles() {
//			return titles;
//		}
//
//		/**
//		 * @param titles
//		 *            对应每个sheet里的标题，即顶部大字
//		 */
//		public void setTitles(String[] titles) {
//			this.titles = titles;
//		}
//
//		public List<String[]> getHeadNames() {
//			return headNames;
//		}
//
//		/**
//		 * @param headNames
//		 *            对应每个页签的表头的每一列的名称
//		 */
//		public void setHeadNames(List<String[]> headNames) {
//			this.headNames = headNames;
//		}
//
//		public ByteArrayOutputStream getOut() {
//			return out;
//		}
//
//		/**
//		 * @param out
//		 *            Excel数据将输出到该输出流
//		 */
//		public void setOut(ByteArrayOutputStream out) {
//			this.out = out;
//		}
//
//		public String[] getSheetNames() {
//			return sheetNames;
//		}
//
//		public void setSheetNames(String[] sheetNames) {
//			this.sheetNames = sheetNames;
//		}
//
//		public LinkedHashMap<Integer, List<Integer>> getColumnWidthMap() {
//			return columnWidthMap;
//		}
//
//		public void setColumnWidthMap(
//				LinkedHashMap<Integer, List<Integer>> columnWidthMap) {
//			this.columnWidthMap = columnWidthMap;
//		}
//
//		/**
//		 * @方法名: getValue
//		 * @描述:获取xls格式excel的单元格的值
//		 * @作者:amos
//		 * @时间:2014年6月3日
//		 * @参数:@param xssfCell
//		 * @参数:@return
//		 * @返回值：String
//		 */
//		public static String getValue(XSSFCell xssfCell) {
//			// return String.valueOf(xssfCell.getStringCellValue());
//			if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
//				return String.valueOf(xssfCell.getBooleanCellValue());
//			} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
//				DecimalFormat df = new DecimalFormat("0");
//				return df.format(xssfCell.getNumericCellValue());
//			} else {
//				return String.valueOf(xssfCell.getStringCellValue());
//			}
//		}
//
//	}
//
//	/**
//	 * @方法名: getValue
//	 * @描述: 获取xls格式excel的单元格的值
//	 * @作者:amos
//	 * @时间:2014年6月3日
//	 * @参数:@param hssfCell
//	 * @参数:@return
//	 * @返回值：String
//	 */
//	public static String getValue(HSSFCell hssfCell) {
//		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
//			return String.valueOf(hssfCell.getBooleanCellValue());
//		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
//			// 防止得到的数字变成科学计数形式，直接得到原来的值
//			DecimalFormat df = new DecimalFormat("0");
//			return df.format(hssfCell.getNumericCellValue());
//		} else {
//			return String.valueOf(hssfCell.getStringCellValue());
//		}
//	}
//
//}
