package web;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;

import java.nio.file;
import java.nio.file.Path;
import java.nio.file.Paths;

import excel.*;

public class InitExcelListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce){
		ServletContext sc= sce.getServletContext();
		createExcel(sc);
	}
	
	public void contextDestroyed(ServletContextEvent sce){}
	
	private void createExcel(ServletContext sc){
		String dirPath= sc.getRealPath("/WEB-INF/excelfolder/").substring(1);
		
		String appName= sc.getContextPath();
	
		ExcelDataType dataType1= new WebCollabMainNodeSetFile("/WebContainer");
		ExcelDataType dataType2= new WebCompCallEdgeList();
		
		ExcelHandler handler1= new ExcelHandler(dirPath, appName, dataType1);
		ExcelHandler handler2= new ExcelHandler(dirPath, appName, dataType2);
		
		handler1.create();
		handler2.create();
		
		handler1.doHandle();
	}
}
