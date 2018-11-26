
import java.util.List;
import java.util.ArrayList;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class ExcelDataType {
	protected XSSFWorkbook workbook;
	protected XSSFSheet sheet;

	public void create(OutputStream fos) throws IOException{
		workbook= new XSSFWorkbook();
	    sheet = workbook.createSheet();
	    
		XSSFRow row = sheet.createRow(0);	
		
		List<String> headers = new ArrayList<>();
		setInitData(headers);
		
		int i=0;
		
		for(String header : headers) {
			row.createCell(i++).setCellValue(header); 
		}
	
		
		try {
			workbook.write(fos);
		}catch(IOException e){
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			try {
				workbook.close();
			}catch(IOException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}	
	
	public void read(InputStream fis) throws IOException{
		try{
			workbook=new XSSFWorkbook(fis);
			sheet=workbook.getSheetAt(0);
		}catch(IOException e){
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	protected void write(OutputStream fos) throws IOException{
		XSSFRow row = sheet.createRow(sheet.getLastRowNum()+1);
		writeRow(row);
		
		try {
			workbook.write(fos);
		}catch(IOException e){
			e.printStackTrace();
			throw e;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			try {
				workbook.close();
			}catch(IOException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
	

	public abstract Object getFileMonitor();
	public abstract String getFileNameSuffix();
	protected abstract void writeRow(XSSFRow row);
	protected abstract void setInitData(List<String> headers);
}
