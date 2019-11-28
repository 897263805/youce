package com.yonyou.RunUI;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.yonyou.CaseAction.KeyWords;
import com.yonyou.pojo.ExcelRows;

/**
 * 用例跑的时候调用的方法
 * @author A
 *
 */
public class RunUI {
	/**
	 * *
	 * @param key 关键字
	 * @param fun 方法
	 * @param element 元素
	 * @param value 值
	 * @param flag 出错是否正常进行
	 * @param NO  行数
	 * @return   返回一个行，加入了结果新参数
	 * @throws Exception
	 */
	 public ExcelRows runUIWithInvoke(String key,String fun, String element,String value,String flag,int NO) throws Exception 
		    {
		        
		    	KeyWords kw = new KeyWords();
		    	//key = key.toUpperCase();
		    	String []param = {key,fun,element,value,flag};
		    	String result = "fail";
		    	if("".equals(key) || key.startsWith("*")) {
		    		
		    		return new ExcelRows(NO,"","","","","无效行");
		    	}else {
		    		
		    			
		    			   Method one;
					        //使用了反射，运行时自动匹配方法名是key的方法
							one = kw.getClass().getDeclaredMethod(key.toUpperCase(),String[].class);
							//找到方法后，传入参数，执行，返回值
							result = one.invoke(kw, new Object[] {param}).toString();
							
						  // String  []infoMes = {"第"+NO+"行：",key,fun,element,value,result};
		    			return new ExcelRows(NO,fun,element,value,key,result);
		    			
		    			    			
		    		}
	    	

		    	
				
		    }

}
