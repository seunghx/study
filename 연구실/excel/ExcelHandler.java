import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class ExcelHandler {
	private String dirPath;
	private String appName;
	private ExcelDataType dataType;
	
	
	public ExcelHandler(String dirPath, String appName,ExcelDataType dataType){
		this.dirPath = dirPath;
		this.appName = appName;
		this.dataType=dataType;
	}
	
	private boolean isExcelFileExist(){
		if(Files.exists(getFilePath()))	return true;
		else	return false;
	}
	
	public void create(){
		if(isExcelFileExist()) return;
		else createExcelFile();
	}
	
	private void createExcelFile(){
		try(OutputStream fos = Files.newOutputStream(getFilePath())) {
			dataType.create(fos);
		}catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	public  void doHandle(){
		synchronized(dataType.getFileMonitor()){
			readExcel();
			writeExcel();
		}
	}

	private void readExcel(){
		try(InputStream fis = Files.newInputStream(getFilePath())) {
			dataType.read(fis);
		}catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private void writeExcel(){
		try(OutputStream fos = Files.newOutputStream(getFilePath())) {
			dataType.write(fos);
		}catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private Path getFilePath(){
		return Paths.get(dirPath, appName, dataType.getFileNameSuffix());
	}
}
