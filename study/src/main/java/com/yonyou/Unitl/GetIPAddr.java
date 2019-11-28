package com.yonyou.Unitl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class GetIPAddr {
	
	public static String getipAddr(HttpServletRequest request) {
		
		String ipAddress = null;
try {
		ipAddress =  request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                // ��������ȡ�������õ�IP
                try {
                    ipAddress = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        // ͨ�����������������һ��IPΪ�ͻ�����ʵIP,���IP����','�ָ�
        if (ipAddress != null) {
            if (ipAddress.contains(",")) {
                return ipAddress.split(",")[0];
            } else {
                return ipAddress;
            }
        } else {
            return "";
        }
    } catch (Exception e) {
        e.printStackTrace();
        return "";
    }	
		

	}
	

}
