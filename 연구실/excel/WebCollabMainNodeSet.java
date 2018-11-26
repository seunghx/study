import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;

public class WebCollabMainNodeSet extends ExcelDataType {
    
	private static final Object fileMonitor = new Object();
	private static final String FILE_NAME_SUFFIX = "_WebCollabMainNodeSet.xlsx";
	
	private String compName;
	
	public WebCollabMainNodeSet() {}
    
	public WebCollabMainNodeSet(String compName) {
		this.compName= compName;
	}
	
	@Override
    public String getFileNameSuffix() {
		return FILE_NAME_SUFFIX;
	}
	
	@Override
    protected void setInitData(List<String> headers) {
		headers.add("COMP_NAME");		
	}
	@Override
    public Object getFileMonitor(){
		return fileMonitor;
	}

	@Override protected void writeRow(XSSFRow row) {
		row.createCell(0).setCellValue(compName);
	}	
}
