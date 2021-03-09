package com.selenium.auto.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * TODO:拿来即用，附加一个excel工具类
 *
 * @author Joe-Tester
 * @time 2021年3月9日
 * @file ExcelUtils.java
 */
public class ExcelUtils {

	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	private static XSSFCell cell;
	private static XSSFRow row;
	private static FileInputStream excelFile;

	/**
	 * 加载excel
	 * 
	 * @param path
	 */
	public static void setExcelFile(String path) {

		try {
			excelFile = new FileInputStream(path);
			ExcelWBook = new XSSFWorkbook(excelFile);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 加载excel
	 * 
	 * @param path
	 * @param sheetName
	 * @throws Exception
	 */
	public static void setExcelFile(String path, String sheetName)
			throws Exception {
		try {
			FileInputStream ExcelFile = new FileInputStream(path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * 读取excel测试数据返回二维数组
	 * 
	 * @param tableName
	 * @return
	 */
	public static String[][] getTestData(String tableName) {
		String[][] testData = null;

		try {
			//
			DataFormatter formatter = new DataFormatter();
			//
			XSSFCell[] boundaryCells = findCells(tableName);
			//
			XSSFCell startCell = boundaryCells[0];
			//
			XSSFCell endCell = boundaryCells[1];
			//
			int startRow = startCell.getRowIndex() + 1;
			int endRow = endCell.getRowIndex() - 1;
			int startCol = startCell.getColumnIndex() + 1;
			int endCol = endCell.getColumnIndex() - 1;

			testData = new String[endRow - startRow + 1][endCol - startCol + 1];

			for (int i = startRow; i < endRow + 1; i++) {
				for (int j = startCol; j < endCol + 1; j++) {
					// testData[i-startRow][j-startCol] =
					// ExcelWSheet.getRow(i).getCell(j).getStringCellValue();
					//
					Cell cell = ExcelWSheet.getRow(i).getCell(j);
					testData[i - startRow][j - startCol] = formatter
							.formatCellValue(cell);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回数组
		return testData;
	}

	/**
	 * 获取单元格的值
	 * 
	 * @param RowNum
	 * @param ColNum
	 * @return
	 * @throws Exception
	 */
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			cell.setCellType(CellType.STRING);
			String cellData = cell.getStringCellValue();
			return cellData;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 获取excel中对应单元格的值
	 * 
	 * @param rownum
	 *            行（从0开始）
	 * @param cellnum
	 *            列（从0开始）
	 * @param sheetName
	 *            sheet名
	 * @return
	 */
	public static String getCellData(int rownum, int cellnum, String sheetName) {
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		cell = ExcelWSheet.getRow(rownum).getCell(cellnum);
		cell.setCellType(CellType.STRING);
		String cellData = cell.getStringCellValue();
		return cellData;
	}

	/**
	 * 获取单元格值
	 * 
	 * @param RowNum
	 * @param ColNum
	 * @return
	 * @throws Exception
	 */
	public static String getDateCellData(int RowNum, int ColNum)
			throws Exception {
		try {
			cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			Date dateValue = cell.getDateCellValue();
			String dateStringFormat = df.format(dateValue);

			return dateStringFormat;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 查找单元格
	 * 
	 * @param tableName
	 * @return
	 */
	public static XSSFCell[] findCells(String tableName) {
		DataFormatter formatter = new DataFormatter();
		String pos = "begin";
		XSSFCell[] cells = new XSSFCell[2];

		for (Row row : ExcelWSheet) {
			for (Cell cell : row) {
				// if (tableName.equals(cell.getStringCellValue())) {
				if (tableName.equals(formatter.formatCellValue(cell))) {
					if (pos.equalsIgnoreCase("begin")) {
						//
						cells[0] = (XSSFCell) cell;
						pos = "end";
					} else {
						//
						cells[1] = (XSSFCell) cell;
					}
				}
			}
		}
		// 返回一个单元格数组
		return cells;
	}

	/**
	 * 将测试结果写入excel
	 * 
	 * @param result
	 *            测试结果
	 * @param rownum
	 *            行（从0开始）
	 * @param cellnum
	 *            列（从0开始）
	 * @param path
	 *            excel文件路径
	 * @param sheetName
	 *            sheet名
	 */
	public static void setCellData(String result, int rownum, int cellnum,
			String path, String sheetName) {
		try {
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			row = ExcelWSheet.getRow(rownum);
			// cell = row.getCell(cellnum, row.MissingCellPolicy);
			cell = row.getCell(cellnum);
			if (cell == null) {
				cell = row.createCell(cellnum);
				cell.setCellValue(result);
			} else {
				cell.setCellValue(result);
			}
			FileOutputStream fileOut = new FileOutputStream(path);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			ExcelWBook = new XSSFWorkbook(new FileInputStream(path));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 结果写入excel
	 * 
	 * @param Result
	 * @param RowNum
	 * @param ColNum
	 * @throws Exception
	 */
	public static void setCellData(String Result, int RowNum, int ColNum)
			throws Exception {

		// 读取配置文件
		Properties properties = new Properties();

		try {
			// 加载工程的配置文件
			properties.load(new FileInputStream(System.getProperty("user.dir")
					+ "/src/test/resources/config.properties"));
			// 读取配置文件的关键字
			String excelPath = properties.getProperty("excel.path");

			row = ExcelWSheet.getRow(RowNum);
			// cell = ExcelWSheet.getRow(RowNum).getCell(ColNum,
			// row.RETURN_BLANK_AS_NULL);
			cell = row.getCell(ColNum);
			if (cell == null) {
				cell = row.createCell(ColNum);
				cell.setCellValue(Result);
			} else {
				cell.setCellValue(Result);
			}

			// Open the file to write the results
			FileOutputStream fileOut = new FileOutputStream(excelPath);

			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * 结果写入单元格
	 * 
	 * @param Result
	 *            ：结果
	 * @param RowNum
	 *            ：行号
	 * @param ColNum
	 *            ：列号
	 * @throws Exception
	 */
	public static void setCellData(double Result, int RowNum, int ColNum)
			throws Exception {
		// 读取配置文件
		Properties properties = new Properties();
		try {
			// 加载工程的配置文件
			properties.load(new FileInputStream(System.getProperty("user.dir")
					+ "/src/test/resources/config.properties"));
			// 读取配置文件的关键字
			String excelPath = properties.getProperty("excel.path");
			row = ExcelWSheet.getRow(RowNum);
			cell = row.getCell(ColNum);
			if (cell == null) {
				cell = row.createCell(ColNum);
				cell.setCellValue(Result);
			} else {
				cell.setCellValue(Result);
			}

			// Open the file to write the results
			FileOutputStream fileOut = new FileOutputStream(excelPath);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * 获取excel的sheet的最后一行号
	 * 
	 * @param sheetName
	 * @return
	 */
	public static int getLastRownum(String sheetName) {
		int row = 0;
		try {
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			row = ExcelWSheet.getLastRowNum();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return row;
	}

}
