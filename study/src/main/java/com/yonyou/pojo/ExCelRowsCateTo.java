package com.yonyou.pojo;


import java.util.List;

import org.springframework.stereotype.Component;



@Component
public class ExCelRowsCateTo {
	
	 /*
     * 把list转为二维数组，因为list对象有用不到的值，所以二维数组的长度不固定，后续会优化
     */
    public Object[][] arrayOflit(List<ExcelRows> list){
    	Object[][] dataList = new Object[list.size()][8];
    	for(int i = 0; i < list.size(); i++) {
    		int num = 0;
    		dataList[i][num++] = list.get(i).getNO();
    		dataList[i][num++] = list.get(i).getKey();
    		dataList[i][num++] = list.get(i).getFun();
    		dataList[i][num++] = list.get(i).getEle();
    		dataList[i][num++] = list.get(i).getValue();
    		dataList[i][num++] = list.get(i).getFlag();
    		dataList[i][num++] = list.get(i).getMemo();
    		dataList[i][num++] = list.get(i).getResult();
    		System.out.println(num);
    	}
    	return dataList;
    	
    }
    
    /*
     * ��ά����תΪ����ʵ�ֶ����ά����ĺϲ�
     */
//    public List<ExcelRows> listOfArray(String []param){
//    	List<ExcelRows> excelRows = new ArrayList<ExcelRows>();
//    	ExcelReader exr = new ExcelReader();
//    	for(String filePath : param) {
//    		Object [][]value = exr.read(filePath);       	
//        	for(int i = 0; i < value.length; i++) {
//        		ExcelRows er = new ExcelRows();       		
//        		int c = 0;
//        		er.setNO(Integer.parseInt(value[i][c++].toString()));
//        		er.setKey(null == value[i][c++].toString() ? "" : value[i][c++].toString());
//        		er.setFun(null == value[i][c++].toString() ? "" : value[i][c++].toString());
//        		er.setEle(null == value[i][c++].toString() ? "" : value[i][c++].toString());
//        		er.setValue(null == value[i][c++].toString() ? "" : value[i][c++].toString());
//        		er.setFlag(null == value[i][c++].toString() ? "" : value[i][c++].toString());
//        		er.setMemo(null == value[i][c++].toString() ? "" : value[i][c++].toString());
//        		er.setResult("fail");
//        		excelRows.add(er);		
//        	}
//    	}  	
//    	return excelRows;
//    }

}
