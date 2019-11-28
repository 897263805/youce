package com.yonyou.service;

import org.testng.annotations.Test;

import com.yonyou.CaseAction.KeyWords;
import com.yonyou.CaseReader.ExcelReader;
import com.yonyou.CaseReader.ExcelWriter;
import com.yonyou.RunUI.RunUI;
import com.yonyou.pojo.ExCelRowsCateTo;
import com.yonyou.pojo.ExcelRows;
import com.yonyou.pojo.ResultOfNCCCaseTo;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.DataProvider;


@Service
public class TestCaseService {
		  
	 ExcelWriter writer = new ExcelWriter();
	 //static ExCelRowsCateTo ccrst = new ExCelRowsCateTo();
	// static ExcelReader er = new ExcelReader();
	// static String []filePath = {
			//"C:\\Users\\A\\eclipse-workspace\\study\\case\\login.xls"
			
					 	 
//	 };
	// static int i = 0;
	// static List<ExcelRows> excelRows = er.read(filePath);
	// static Object [][]excelV = ccrst.arrayOflit(excelRows);
		 
	 //调用反射方法，后续优化，可以省略一步
  //@Test(dataProvider = "dp")
  public ExcelRows run(int NO,String key,String fun, String element,String value,String flag,String memo,String param) throws Exception {
	  String []result;
	  ExcelRows er = new ExcelRows();  
	  RunUI ru = new RunUI();
	  er = ru.runUIWithInvoke(key,fun, element, value, flag,NO);
//	  if(!"success".equals(result)) {
//		  System.out.println("第"+NO+"行测试用例失败:"+result);
//	  }
		//excelV[i][excelV[i].length-1] = result;
		//i++;
          
	  
	  if("".equals(key))  {
		  System.out.println("第"+NO+"行是无效行");
	  }
	  return er;
  }
  
//  @AfterSuite
//  public void ExportRep() {
//	  ResultOfNCCCaseTo roct = new ResultOfNCCCaseTo();
//	  String []s= {"行号","动作","方法","元素","值","出错后是否进行","备注","结果"};
//	  writer.getCellHeads(s);
//	 String resultOfExcel = writer.writerExcel(excelV, "case/report.xlsx");
//	  System.out.println(resultOfExcel);	  
//	  KeyWords kw = new KeyWords();
//	  String []s1= {"次数","上行流量","下行流量","连接数","Sql数","操作名称"};
//	  writer.getCellHeads(s1);
//	  Object [][]l = roct.arrayOflit(kw.getRonccList());
//	  String resultOfExcel1 = writer.writerExcel(l, "case/reportOfliul.xlsx");
//	  System.out.println("==========="+l.toString());
//	  System.out.println(resultOfExcel1);
//	  
//	  
//  }

  //@DataProvider
//  public Object[][] dp() {
////	  
////	  Object[][] list = reader.read(filePath);
////	  for(int i = 0;i < list.length;i++) {
////			System.out.print("第"+i+"行");
////			for(int j = 0; j < list[i].length;j++) {
////				System.out.print("       "+list[i][j]);
////			}
////			
////			System.out.println();
////		}
//	 
//   // return  ccrst.arrayOflit(excelRows);
//  }
}
