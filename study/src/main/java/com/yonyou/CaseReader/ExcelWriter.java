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
	 private  static List<String> CELL_HEADS;//表头
	 
	
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
				System.out.println("输出Excel出错，错误原因："+e.getMessage());
			}finally {
				try {
				if(null != fos) {
					
						fos.close();
					}
				if(null != workbook) {
					workbook.close();
				}
				} catch (IOException e) {
						
					System.out.println("关闭输出流时出错，错误原因："+e.getMessage());
					}
				}
				
			        return result;
			}
		 
		 
		 
		 
		 
		 
	 
	 
	   /**
	             * 生成Excel并写入数据信息
	     * @param dataList 数据列表
	     * @return 写入数据后的工作簿对象
	     */
	 public static Workbook exportData(Object [][]list) {
		
		  
		  // 生成xlsx的Excel
		    
	        Workbook workbook = new SXSSFWorkbook();

	        // 如需生成xls的Excel，请使用下面的工作簿对象，注意后续输出时文件后缀名也需更改为xls
	        //Workbook workbook = new HSSFWorkbook();

	        // 生成Sheet表，写入第一行的列头
	        Sheet sheet = buildDataSheet(workbook);
	        //构建每行的数据内容
	       
	        for (int r = 0;r < list.length; r++) {
	        	Row row = sheet.createRow(r+1);
	            for(int c = 0;c < list[r].length; c++) {            		            
	            //输出行数据 
	            	Cell cell = row.createCell(c);	            	
	            	cell.setCellValue(list[r][c].toString());
	               
	            
	           
	        }
	        }
	        return workbook;
	    }

	    /**
	     * 生成sheet表，并写入第一行数据（列头）
	     * @param workbook 工作簿对象
	     * @return 已经写入列头的Sheet
	     */
	    private static Sheet buildDataSheet(Workbook workbook) {
	        Sheet sheet = workbook.createSheet();
	        // 设置列头宽度
	        for (int i=0; i<CELL_HEADS.size(); i++) {
	            sheet.setColumnWidth(i, 4000);
	        }
	        // 设置默认行高
	        sheet.setDefaultRowHeight((short) 400);
	        // 构建头单元格样式
	        CellStyle cellStyle = buildHeadCellStyle(sheet.getWorkbook());
	        // 写入第一行各列的数据
	        Row head = sheet.createRow(0);
	        for (int i = 0; i < CELL_HEADS.size(); i++) {
	            Cell cell = head.createCell(i);
	            cell.setCellValue(CELL_HEADS.get(i));
	            cell.setCellStyle(cellStyle);
	        }
	        return sheet;
	    }

	    /**
	     * 设置第一行列头的样式
	     * @param workbook 工作簿对象
	     * @return 单元格样式对象
	     */
	    private static CellStyle buildHeadCellStyle(Workbook workbook) {
	        CellStyle style = workbook.createCellStyle();
	        //对齐方式设置
	        style.setAlignment(HorizontalAlignment.CENTER);
	        //边框颜色和宽度设置
	        style.setBorderBottom(BorderStyle.THIN);
	        style.setBottomBorderColor(IndexedColors.BLACK.getIndex()); // 下边框
	        style.setBorderLeft(BorderStyle.THIN);
	        style.setLeftBorderColor(IndexedColors.BLACK.getIndex()); // 左边框
	        style.setBorderRight(BorderStyle.THIN);
	        style.setRightBorderColor(IndexedColors.BLACK.getIndex()); // 右边框
	        style.setBorderTop(BorderStyle.THIN);
	        style.setTopBorderColor(IndexedColors.BLACK.getIndex()); // 上边框
	        //设置背景颜色
	        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        //粗体字设置
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
			System.out.println("该文件已存在，将进行覆盖操作");
		}else {
			try {
				exportExcel.createNewFile();
				
			} catch (IOException e) {
				System.out.println("创建文件时出错！"+e.getMessage());
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
