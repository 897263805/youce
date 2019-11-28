package com.yonyou.pojo;

import java.util.List;

public class ResultOfNCCCaseTo {
	  /*
     * 把list转换成二维数组，待优化
     */
    public Object[][] arrayOflit(List<ResultOfNCC> list){
    	Object[][] dataList = new Object[list.size()][6];
    	for(int i = 0; i < list.size(); i++) {
    		int num = 0;
    		dataList[i][num++] = list.get(i).getTimes();
    		dataList[i][num++] = list.get(i).getSl();
    		dataList[i][num++] = list.get(i).getXl();
    		dataList[i][num++] = list.get(i).getLjs();
    		dataList[i][num++] = list.get(i).getSqlC();    		
    		dataList[i][num++] = list.get(i).getOptionName();
    	}
    	System.out.println("�ұ�ִ���ˣ�����ת��ά����");
    	return dataList;
    }

}
