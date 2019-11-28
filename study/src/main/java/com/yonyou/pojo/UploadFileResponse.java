package com.yonyou.pojo;

import org.springframework.stereotype.Component;

/**
 * 上传文件的实体类
 * @author A
 *
 */
@Component
public class UploadFileResponse {
	private Integer id;
	private String fileName;//文件名
	private String fileDownloadUri;//文件下载路径
	private String fileType;//文件类型
	private long size;//文件大小
	private int userId;//用户id
	private String uploadUri;//用户上传路径
	private String uploadDate;//上传时间
	private int flag = 1;//分辨文档是用例还是报告
	


	
	
public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size, int userId,
			String uploadUri, String uploadDate, int flag) {
		super();
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
		this.userId = userId;
		this.uploadUri = uploadUri;
		this.uploadDate = uploadDate;
		this.flag = flag;
	}
public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size, int userId,
		String uploadUri, String uploadDate) {
	super();
	this.fileName = fileName;
	this.fileDownloadUri = fileDownloadUri;
	this.fileType = fileType;
	this.size = size;
	this.userId = userId;
	this.uploadUri = uploadUri;
	this.uploadDate = uploadDate;

}


public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}


public UploadFileResponse(Integer id, String fileName, String fileDownloadUri, String fileType, long size,
			int userId, String uploadUri, String uploadDate) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
		this.userId = userId;
		this.uploadUri = uploadUri;
		this.uploadDate = uploadDate;
	}


public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	//	public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, String size, String userId,
//			String uploadUri, String uploadDate) {
//		super();
//		this.fileName = fileName;
//		this.fileDownloadUri = fileDownloadUri;
//		this.fileType = fileType;
//		this.size = Integer.parseInt(size);
//		this.userId = Integer.parseInt(userId);
//		this.uploadUri = uploadUri;
//		this.uploadDate = uploadDate;
//	}
	public UploadFileResponse() {
		super();
	}









	@Override
	public String toString() {
		return "ActiveRequest{"+"UploadFileResponse [fileName=" + fileName + ", fileDownloadUri=" + fileDownloadUri + ", fileType="
				+ fileType + ", size=" + size + ", userId=" + userId + ", uploadUri=" + uploadUri + ", uploadDate="
				+ uploadDate + "}";
	}


	
	

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	

	public String getUploadUri() {
		return uploadUri;
	}

	public void setUploadUri(String uploadUri) {
		this.uploadUri = uploadUri;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	

}
