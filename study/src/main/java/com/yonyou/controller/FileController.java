package com.yonyou.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yonyou.mapping.UploadMapper;
import com.yonyou.pojo.UploadFileResponse;
import com.yonyou.pojo.User;
import com.yonyou.service.FileSeriver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class FileController {
	//日志
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileSeriver fileService;
	
	@Autowired
	private UploadMapper uploadMapper;
	@Autowired
	HttpServletRequest request; 
	
	/**
	 * ，返回一个文件
	 * @param file 文件
	 * @param model model
	 * @return  视图
	 */
	
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file
			,Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fileName = fileService.storeFile(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFile/")
				.path(fileName)
				.toUriString();
		User user = (User)request.getSession().getAttribute("user"); 
		return   new UploadFileResponse(fileName,fileDownloadUri,
				file.getContentType(),file.getSize(),user.getId(),
				fileService.getFileStorageLocation().toString(),sdf.format(new Date()).toString());
				
	}
	
//	@PostMapping("/uploadMultipleFiles")
//	public List<UploadFileResponse> uploadMulipleFiles(@RequestParam("files") MultipartFile[] files,Model model){
//		
//		return Arrays.stream(files,model)
//				.map(this::uploadFile)
//				.collect(Collectors.toList());
//	}
	/***
	 * 上传文件
	 * @param file
	 * @param model
	 * @param flag
	 * @return
	 */
	@PostMapping("/uploadFile")
	public String  uploadFile(@RequestParam("file") MultipartFile file
			,Model model,@RequestParam(value="id",defaultValue="1",required=false) int flag) {
		UploadFileResponse files = this.uploadFile(file, model);
		//uploadMapper.Insert(files);
	    
		int i = fileService.insert(files);
		if(i == 0) {
			//request.getSession().setAttribute("info", "您上传的文件名重复");
		    model.addAttribute("error", "您上传的文件名重复");
			return "upload";
		}
		return "upload";
		
	}
		
	
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,HttpServletRequest request){
		//Load file as Resource
		Resource resource = fileService.loadFileAsResource(fileName);
		
		//Try to detemine file's content type
		String contentType= null;
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			
			logger.info("不能找到文件类型");
		}
		
		//Fallback to the default content tyoe if type could not be detemined
		if(contentType == null){
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
		
	}
	

}
