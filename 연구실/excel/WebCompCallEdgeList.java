import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class WebCompCallEdgeList extends ExcelDataType{
	private static final String FILE_NAME_SUFFIX = "_EdgeList_1ModeNet.xlsx";
	private static final Object fileMonitor = new Object();
	
	private  String sourceComp;
	private  String targetComp;
	private  String callType;
	
	public WebCompCallEdgeList() {}
	
	public WebCompCallEdgeList(String sourceComp,String targetComp,String callType){
		this.sourceComp = sourceComp;
		this.targetComp = targetComp;
		this.callType = callType;
	}
	
	@Override
    public String getFileNameSuffix() {
		return FILE_NAME_SUFFIX;
	}
	
	@Override
    public void setInitData(List<String> headers) {
		headers.add("source");
		headers.add("target");
		headers.add("callType");
	}

	@Override
    protected void writeRow(XSSFRow row) {
		row.createCell(0).setCellValue(sourceComp);
		row.createCell(1).setCellValue(targetComp);
		row.createCell(2).setCellValue(callType);		
   	}
	
	@Override
    public Object getFileMonitor(){
		return fileMonitor;
	}
	
}

