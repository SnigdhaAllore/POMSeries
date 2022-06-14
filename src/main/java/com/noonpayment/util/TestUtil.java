package com.noonpayment.util;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public final class TestUtil {

	private TestUtil(){
	}

	public static Object[][] getTestData(String sheetName) {
		Object[][] data=null;
		DataFormatter fmt = new DataFormatter();
			try (Workbook workbook = new XSSFWorkbook(new FileInputStream("./src/main/java/com/noonpayment/testdata/CheckOut.xlsx"))) {
				Sheet sheet = workbook.getSheet(sheetName);
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = fmt.formatCellValue(sheet.getRow(i + 1).getCell(k));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}