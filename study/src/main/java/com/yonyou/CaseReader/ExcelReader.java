package com.yonyou.CaseReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressBase.CellPosition;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import com.yonyou.Unitl.Unitl;
import com.yonyou.pojo.ExcelRows;





@Service
public class ExcelReader {
	
	
	
	/** ������ */
    private int totalRows = 0;
    /** ������ */
    private int totalCells = 0;
    /** ������Ϣ */
    private String errorInfo;
    /** ���췽�� */
    public ExcelReader() {
    }
	
	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalCells() {
		return totalCells;
	}

	public void setTotalCells(int totalCells) {
		this.totalCells = totalCells;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	
	public boolean validateExcel(String filePath) {
		/** ����ļ����Ƿ�Ϊ�ջ����Ƿ���Excel��ʽ���ļ� */
		if(filePath == null 
				) {
			
			errorInfo = "�ļ�������excel��ʽ";
			return false;
			
		}
		 /** ����ļ��Ƿ���� */
		File file = new File(filePath);
		
		if(file == null || !file.exists()) {
			errorInfo = "�ļ�������";
			return false;
			
		}
		return true;	
	}
	
	public List<ExcelRows> read(String []filePath){
		List<ExcelRows> listRows =null;
		Unitl ut = new Unitl();
		for(String path : filePath) {
			InputStream is = null;
			//��֤�ļ��Ƿ�Ϸ�
			try {
			if(!validateExcel(path)) {
				System.out.println(errorInfo);
				return null;
			}
			//�ж��ļ����ͣ���2003����2007
			boolean isExcel2003 = false;
			if(ut.isExcel2003(path)) {
				isExcel2003 = true;
			}
			
			//���ñ����ṩ�ĸ�������ȡ�ķ���
			File file = new File(path);
			
				is = new FileInputStream(file);
				listRows = read(is,isExcel2003);
				is.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(is !=null) {
					try {
						is.close();
					} catch (IOException e) {
						is = null;
						e.printStackTrace();
					}
				}
										
			}
		}
		
		return listRows;
	}
	
			
	public List<ExcelRows> read(InputStream inputStream,boolean isExcel2003){
		List<ExcelRows> dataList  = null;
		try {
		Workbook wb = null;
		
		if(isExcel2003) {
				wb = new HSSFWorkbook(inputStream);
				dataList = read(wb);
			} else {
				wb = new XSSFWorkbook(inputStream);
				dataList = read(wb);
			} 		
		
		}catch (IOException e) {
				
				e.printStackTrace();
			}
		
		return dataList;
		
	}
		

	
	public List<ExcelRows> read(Workbook wb){
		List<ExcelRows> listRows = new ArrayList<ExcelRows>();
		
		//�õ���һ��sheetҳ
		Sheet sheet = wb.getSheetAt(0);
		//�õ�sheet������		
	    this.totalRows = sheet.getPhysicalNumberOfRows();
	    //�õ�Excel������
	    if(this.totalRows >= 1 && sheet.getRow(2) != null) {
	    	this.totalCells = sheet.getRow(2).getPhysicalNumberOfCells()+1;
	    }
	    Object matrix[][] = new Object[this.totalRows-2][this.totalCells+1];
	    //ѭ��excel����
	    for(int r = 2; r < this.totalRows;r++) {
	    	ExcelRows er = new ExcelRows();
	    	er.setNO(r-1);
	    	//matrix[r-2][0] = r-1;
	    	Row row = sheet.getRow(r);
	    	if(row == null) {
	    		continue;
	    	}
	    	int count = 0;
	    	//ѭ��Excel��
	    	
	    	int cellNum = 0;
	    	er.setKey(getCellValue(row,cellNum++));
	    	er.setFun(getCellValue(row,cellNum++));
	    	er.setEle(getCellValue(row,cellNum++));
	    	er.setValue(getCellValue(row,cellNum++));
	    	er.setFlag(getCellValue(row,cellNum++));
	    	er.setMemo(getCellValue(row,cellNum++));
	    	er.setResult("fail");
    		listRows.add(er);   	
    	}
        return listRows;
    		
	    	
	    }
			
    public static String getCellValue(Row row,int num) {
    	Cell cell = row.getCell(num);
    	String cellValue = "";
		if(cell != null) {	
			cell.setCellType(CellType.STRING);
			cellValue = cell.getStringCellValue();
			}
    	return cellValue;   	
    } 
	
	public static void main(String[] args) {
        ExcelReader read = new ExcelReader();
		String []s3 = {"C:\\Users\\A\\eclipse-workspace\\study\\case\\login.xls","C:\\Users\\A\\eclipse-workspace\\study\\case\\report.xlsx"};
        read.read(s3);
//	for(ExcelRows s : listRows) {
//			System.out.println(s.toString());
//	}
        Unitl ut = new Unitl();
        //Object [][]s = ut.arrayOflit(listRows);
//        for(Object []s1 : s) {
//        	for(Object s2 : s1) {
//        		System.out.print(s2.toString());
//        	}
//        }
		
	
		
	}
	

	
	
			

}
