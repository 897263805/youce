package com.yonyou.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 把application的属性和实体类绑定
 * @author A
 *
 */
@ConfigurationProperties(prefix = "file")
public class FileProperties {
	private String upLoadDir;

	public String getUpLoadDir() {
		return upLoadDir;
	}

	public void setUpLoadDir(String upLoadDir) {
		this.upLoadDir = upLoadDir;
	}
	

}
