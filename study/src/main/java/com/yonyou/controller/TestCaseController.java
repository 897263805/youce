package com.yonyou.controller;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.ibatis.annotations.Param;
import org.h2.engine.SysProperties;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.testng.annotations.Test;


import com.alibaba.fastjson.JSONObject;
import com.yonyou.CaseReader.ExcelReader;
import com.yonyou.CaseReader.ExcelWriter;
import com.yonyou.mapping.UploadMapper;
import com.yonyou.pojo.ExCelRowsCateTo;
import com.yonyou.pojo.ExcelRows;
import com.yonyou.pojo.UploadFileResponse;
import com.yonyou.pojo.User;
import com.yonyou.service.FileSeriver;
import com.yonyou.service.TestCaseService;


@Controller
public class TestCaseController {
	
	@Autowired
	TestCaseService testCaseService;
	@Autowired
	UploadFileResponse file;
	@Autowired
	UploadMapper uploadMapper;
	@Autowired
	FileSeriver fileService;
	@Autowired
	ExcelReader er;
	
	@Autowired
	ExcelWriter ew;
	
	@Autowired
	ExCelRowsCateTo ecc;

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ExCelRowsCateTo crt;
	
	@Autowired
	FileController fileController;
	
	/**
	 * 测试用例执行类
	 * @param files 文件列表，支持多个文件
	 * @param model model
	 * @return
	 */
	@RequestMapping(value="/run",method=RequestMethod.POST)
	@ResponseBody
	@CacheEvict
	public String runTestCase(@RequestBody UploadFileResponse []files,Model model) {
		    String []cellHader= {"行号","动作","方法","元素","值","出错后是否进行","备注","结果"};
		    String filePath = "case/report.xlsx";
		    String info = "用例执行成功";
		    String []Case = new String[files.length];
		    int i = 0;
		   
		    //把文件列表里的每一个文件地址放到字符串数组里
		    for(UploadFileResponse file : files) {
		    	Case[i++] = file.getUploadUri()+"\\"+file.getFileName();
		    	System.out.println("==========");
		    }
		    List<ExcelRows> param = null;
            //调用excel的方法
			param = er.read(Case);
			//System.out.println(Case[0].toString());
			//把list转换成二维数组，注意转换类数组的长度是写死的
			Object [][]params = new Object[param.size()][8];
			params = ecc.arrayOflit(param);
			System.out.println(params);
			//接收要写入报告的list
			List<ExcelRows> excelRows = new ArrayList<ExcelRows>();
			try {
			for(Object []s : params) {					
					ExcelRows er = testCaseService.run(Integer.parseInt(s[0].toString()), 
							s[1].toString(), 
							s[2].toString(), 
							s[3].toString(), 
							s[4].toString(), 
							s[5].toString(), 
		  				    s[6].toString(), 
							s[7].toString());
					er.setFlag("");
					er.setMemo("");
					excelRows.add(er);
					if(!"success".equals(er.getResult())) {
						break;
					}
			} 
				}catch (Exception e) {
					//System.out.println("用例执行失败,未知错误");
					e.printStackTrace();
					//return "用例执行失败";
					}finally{
						if(excelRows != null) {
							Object [][]report = crt.arrayOflit(excelRows);
						
						//创建excel的头部，这个回头放数据库，让用户自定义，或者从前端传数据	
			            ew.getCellHeads(cellHader);
						String res = ew.writerExcel(report, filePath);
						
							/*
							 * if( "fail".equals(res)) { return "生成报告失败"; }
							 */
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        String date = sdf.format(new Date())+".xls";
				        //把file转成MultipartFile
						FileItem fileItem = fileService.createFileItem(filePath,"Rep.xls");
				        MultipartFile mfile = new CommonsMultipartFile(fileItem);
				        //上传报告
				        fileController.uploadFile(mfile, model,2);
						//System.out.println(file.toString());
						model.addAttribute("info", info);
						}
					}
			
			return "用例执行完毕,报告生成完毕";
		}
		
	

	/**
	 * 获取当前用户所有的用例
	 * @return
	 */
	@GetMapping("/myCase")
	@ResponseBody
	public List<UploadFileResponse> getMyCase(){
		List<UploadFileResponse> files = null;
		User user = (User)request.getSession().getAttribute("user");
		if(user == null) {
			return null;
		}
		files = uploadMapper.findAllCase(user.getId());
		
		return files; 		
		
	}
	
	/**
	 * 获取当前用户所有的报告
	 * @return
	 */
	@GetMapping("/report")
	@ResponseBody
	public List<UploadFileResponse> getMyRep(){
		List<UploadFileResponse> files = null;
		User user = (User)request.getSession().getAttribute("user");
		if(user == null) {
			return null;
		}
		files = uploadMapper.findRep(user.getId(),"text/plain");
		
		return files; 		
		
	}
	
	@GetMapping("/myRep")
	public String getMyReport(){
//		List<UploadFileResponse> files = null;
//		User user = (User)request.getSession().getAttribute("user");
//		if(user == null) {
//			return null;
//		}
//		files = uploadMapper.findFile(user.getId(), 2);		
		return "repMan"; 		
		
	}
	/**
	 * 跳转页面
	 * @param model
	 * @return
	 */
	@GetMapping("/caseMan")
	public String caseMan(Model model){
		model.addAttribute("info", "123");
		
		return "selectFile"; 		
		
	}
	/**
	 * 获取当前用户所有的用例
	 * @return
	 */
	@GetMapping("/viewUseCases")
	public String getMyCase(Model model){
		List<UploadFileResponse> files = null;
		User user = (User)request.getSession().getAttribute("user");
		if(user == null) {
			return null;
		}
		files = uploadMapper.findAllCase(user.getId());
		model.addAttribute("files", files);
		return "caseMan"; 				
	}
	/**
	 * 删除用例
	 * @return
	 */
	@GetMapping("/deleteCase")
	@ResponseBody
	public String deleteCase(@RequestParam("id") int fileid,Model model) {
		String res = "删除成功";
		int i = fileService.deleteCase(fileid);
		if(i == 0) {
			res = "删除失败";
			
		}
		List<UploadFileResponse> files = this.getMyCase();
		model.addAttribute("files", files);
		
		return res;
	}
	
	

}
