package com.yonyou.RunUI;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.yonyou.CaseAction.KeyWords;
import com.yonyou.pojo.ExcelRows;

/**
 * �����ܵ�ʱ����õķ���
 * @author A
 *
 */
public class RunUI {
	/**
	 * *
	 * @param key �ؼ���
	 * @param fun ����
	 * @param element Ԫ��
	 * @param value ֵ
	 * @param flag �����Ƿ���������
	 * @param NO  ����
	 * @return   ����һ���У������˽���²���
	 * @throws Exception
	 */
	 public ExcelRows runUIWithInvoke(String key,String fun, String element,String value,String flag,int NO) throws Exception 
		    {
		        
		    	KeyWords kw = new KeyWords();
		    	//key = key.toUpperCase();
		    	String []param = {key,fun,element,value,flag};
		    	String result = "fail";
		    	if("".equals(key) || key.startsWith("*")) {
		    		
		    		return new ExcelRows(NO,"","","","","��Ч��");
		    	}else {
		    		
		    			
		    			   Method one;
					        //ʹ���˷��䣬����ʱ�Զ�ƥ�䷽������key�ķ���
							one = kw.getClass().getDeclaredMethod(key.toUpperCase(),String[].class);
							//�ҵ������󣬴��������ִ�У�����ֵ
							result = one.invoke(kw, new Object[] {param}).toString();
							
						  // String  []infoMes = {"��"+NO+"�У�",key,fun,element,value,result};
		    			return new ExcelRows(NO,fun,element,value,key,result);
		    			
		    			    			
		    		}
	    	

		    	
				
		    }

}
