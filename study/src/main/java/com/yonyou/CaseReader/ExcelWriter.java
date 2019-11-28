package com.yonyou.CaseReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.yonyou.pojo.ExcelRows;

@Service
public class ExcelWriter{
	
	 static ExcelReader er = new ExcelReader();
	 private  static List<String> CELL_HEADS;//��ͷ
	 
	
	 public List<String> getCellHeads(String []s){
		 CELL_HEADS = new ArrayList<String>();
		 for(String s1 : s) {
			 CELL_HEADS.add(s1);
		 }
		 return CELL_HEADS;
		 
	 }
	 
	 public static  String writerExcel(Object [][]list,String filePath) {
		 String result = "fail";
 		 Workbook workbook = ExcelWriter.exportData(list);
		 FileOutputStream fos = null;
		 try {
		 File exportFile = new File(filePath);
		 if(!exportFile.exists()) {
			 
			 exportFile.createNewFile();
		 }
		 
		 fos = new FileOutputStream(exportFile);
		 workbook.write(fos);
		 fos.flush();
		 result = "success";
			} catch (IOException e) {
				System.out.println("���Excel��������ԭ��"+e.getMessage());
			}finally {
				try {
				if(null != fos) {
					
						fos.close();
					}
				if(null != workbook) {
					workbook.close();
				}
				} catch (IOException e) {
						
					System.out.println("�ر������ʱ��������ԭ��"+e.getMessage());
					}
				}
				
			        return result;
			}
		 
		 
		 
		 
		 
		 
	 
	 
	   /**
	             * ����Excel��д��������Ϣ
	     * @param dataList �����б�
	     * @return д�����ݺ�Ĺ���������
	     */
	 public static Workbook exportData(Object [][]list) {
		
		  
		  // ����xlsx��Excel
		    
	        Workbook workbook = new SXSSFWorkbook();

	        // ��������xls��Excel����ʹ������Ĺ���������ע��������ʱ�ļ���׺��Ҳ�����Ϊxls
	        //Workbook workbook = new HSSFWorkbook();

	        // ����Sheet��д���һ�е���ͷ
	        Sheet sheet = buildDataSheet(workbook);
	        //����ÿ�е���������
	       
	        for (int r = 0;r < list.length; r++) {
	        	Row row = sheet.createRow(r+1);
	            for(int c = 0;c < list[r].length; c++) {            		            
	            //��������� 
	            	Cell cell = row.createCell(c);	            	
	            	cell.setCellValue(list[r][c].toString());
	               
	            
	           
	        }
	        }
	        return workbook;
	    }

	    /**
	     * ����sheet����д���һ�����ݣ���ͷ��
	     * @param workbook ����������
	     * @return �Ѿ�д����ͷ��Sheet
	     */
	    private static Sheet buildDataSheet(Workbook workbook) {
	        Sheet sheet = workbook.createSheet();
	        // ������ͷ���
	        for (int i=0; i<CELL_HEADS.size(); i++) {
	            sheet.setColumnWidth(i, 4000);
	        }
	        // ����Ĭ���и�
	        sheet.setDefaultRowHeight((short) 400);
	        // ����ͷ��Ԫ����ʽ
	        CellStyle cellStyle = buildHeadCellStyle(sheet.getWorkbook());
	        // д���һ�и��е�����
	        Row head = sheet.createRow(0);
	        for (int i = 0; i < CELL_HEADS.size(); i++) {
	            Cell cell = head.createCell(i);
	            cell.setCellValue(CELL_HEADS.get(i));
	            cell.setCellStyle(cellStyle);
	        }
	        return sheet;
	    }

	    /**
	     * ���õ�һ����ͷ����ʽ
	     * @param workbook ����������
	     * @return ��Ԫ����ʽ����
	     */
	    private static CellStyle buildHeadCellStyle(Workbook workbook) {
	        CellStyle style = workbook.createCellStyle();
	        //���뷽ʽ����
	        style.setAlignment(HorizontalAlignment.CENTER);
	        //�߿���ɫ�Ϳ������
	        style.setBorderBottom(BorderStyle.THIN);
	        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // �±߿�
	        style.setBorderLeft(BorderStyle.THIN);
	        style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // ��߿�
	        style.setBorderRight(BorderStyle.THIN);
	        style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // �ұ߿�
	        style.setBorderTop(BorderStyle.THIN);
	        style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // �ϱ߿�
	        //���ñ�����ɫ
	        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        //����������
	        Font font = workbook.createFont();
	        font.setBold(true);
	        style.setFont(font);
	        return style;
	    }
	    public static void main(String[] args) {
	    	ExcelWriter ew = new ExcelWriter();
			er.setTotalCells(6);
			er.setTotalRows(2);
			String [][]params
			= {{"1","2","3","4","5","6","7","8"},{"1","2","3","4","5","6","7","8"}};
		    ExcelWriter.writerExcel(params, "case/report.xlsx");
		}
	 
 }








/*
 * String exportExcelPath = "case/"+new Date()+"report.xlsx";
 * public String writerExcel(String [][]param) {
		FileOutputStream fos = null;
		File exportExcel = new File(exportExcelPath);
		if(exportExcel.exists()) {
			System.out.println("���ļ��Ѵ��ڣ������и��ǲ���");
		}else {
			try {
				exportExcel.createNewFile();
				
			} catch (IOException e) {
				System.out.println("�����ļ�ʱ����"+e.getMessage());
			}
		}
		try {
			fos = new FileOutputStream(exportExcelPath);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return "fail";
	}
 * 
 * */
