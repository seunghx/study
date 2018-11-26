package web;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;
import excel.*;

public class InitExcelListener implements ServletContextListener {
	
	
	public void contextInitialized(ServletContextEvent sce){	
		ServletContext sc= sce.getServletContext();
		createExcel(sc);
	}
	
	public void contextDestroyed(ServletContextEvent sce){
		
	}
	
	private void createExcel(ServletContext sc){
		String path= sc.getRealPath("/WEB-INF/excelfolder/");
		String dirPath=path+"\\\\";
		
		String appName= sc.getContextPath();
		String fileName = dirPath+appName.substring(1);

		ExcelFileType dataType1= new WebCollabMainNodeSetFile();
		ExcelFileType dataType2= new WebCompCallEdgeListFile();
		
		ExcelHandler handler1= new ExcelHandler(fileName, dataType1);
		ExcelHandler handler2= new ExcelHandler(fileName, dataType2);
		
		handler1.create();
		handler2.create();
	}
}
