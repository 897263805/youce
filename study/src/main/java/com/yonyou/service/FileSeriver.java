package com.yonyou.service;




import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.FileItemFactory;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.yonyou.mapping.UploadMapper;
import com.yonyou.pojo.FileException;
import com.yonyou.pojo.FileProperties;
import com.yonyou.pojo.UploadFileResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSeriver {
	
	private final Path fileStorageLocation;//文件在本地存储的地址
	
	@Autowired
	UploadFileResponse file;
	
	@Autowired
	UploadMapper uploadMapper;
	
	public Path getFileStorageLocation() {
		return fileStorageLocation;
	}
	@Autowired
	public FileSeriver(FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUpLoadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    /*
     * 存储文件到系�??
     * 
     * @param file 文件�??
     * 
     * @return 文件�??
     */
	public String storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			throw new FileException("文件名不合法"+fileName);
			}
		
		Path targetLocation = this.fileStorageLocation.resolve(fileName);
		try {
			Files.copy(file.getInputStream(), targetLocation,StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (IOException e) {
			
			throw new FileException("不能存储此文�??"+fileName);
		}
		
	}
	
	/**
	 * 
	 * 加载文件
	 * @param fileName 文件�??
	 * @return 文件
	 */

	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource  resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				return resource;
			}else {
				throw new FileException("文件未找到"+fileName);
			}
		} catch (MalformedURLException  ex) {
			 throw new FileException("File not found " + fileName, ex);
		}				
	}

      public int insert(UploadFileResponse f) {
		/*
		 * file = uploadMapper.findByUserFileNameAndUserID(f.getFileName(),
		 * f.getUserId()); if(file != null) { return 1; }
		 */
    	  uploadMapper.deleteCaseByName(f.getFileName());
		  uploadMapper.Insert(f);
		  return 1;
	}
      public int deleteCase(int id) {
    	  int i = 1;
    	  try {
    		  uploadMapper.deleteCaseById(id);
		} catch (Exception e) {
			i = 0;
		}  	  
    	  return i;
      }
      
      /**
       * 为了使File转换成MultiPartFile
       * @param filePath
       * @return 获取FileItem对象
       */
      public FileItem createFileItem(String filePath,String fileName) {
    	    FileItemFactory factory = new DiskFileItemFactory(16, null);
    	    String textFieldName = "textField";
    	    int num = filePath.lastIndexOf(".");
    	    String extFile = filePath.substring(num);
    	    FileItem item = factory.createItem(textFieldName, "text/plain", true, fileName);
    	    File newfile = new File(filePath);
    	    int bytesRead = 0;
    	    byte[] buffer = new byte[8192];
    	    try {
    	        FileInputStream fis = new FileInputStream(newfile);
    	        OutputStream os = item.getOutputStream();
    	        while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
    	            os.write(buffer, 0, bytesRead);
    	        }
    	        os.close();
    	        fis.close();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	    return item;
    	}

}
