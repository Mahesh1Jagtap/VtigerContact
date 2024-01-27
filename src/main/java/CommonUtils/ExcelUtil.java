package CommonUtils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public String getDataFromExcel(String Sheetname, int  rownum, int cellnum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("src\\test\\resources\\LiveProhectVtiger1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String value = wb.getSheet(Sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();
		return value;
	}
}
