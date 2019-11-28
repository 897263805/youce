package com.yonyou.Unitl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yonyou.CaseAction.KeyWords;
import com.yonyou.pojo.ExcelRows;
import com.yonyou.pojo.ResultOfNCC;

public class Unitl {
	
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
    
   
    
 
  
    
    public static void main(String[] args) {
//    	Unitl ut1 = new Unitl();
//    	ut1.runUIWithInvoke("open","http://172.16.75.119:8097","","","");
//    	
//    	ut1.runUIWithInvoke("wait","//input[@fieldid=\"username\"]","","","");
//    	
//    	ut1.runUIWithInvoke("input","//input[@fieldid=\"username\"]","am","","");
    	
	}
    

}
