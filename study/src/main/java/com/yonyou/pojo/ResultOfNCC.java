package com.yonyou.pojo;

public class ResultOfNCC {
	public String sl;//上行流量
	public String xl;//下行流量
	public String sqlC;//sql数
	public String ljs;//连接数
	public int times;//�第几次执行
	public String optionName;//操作名称
	
	
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public String getSl() {
		return sl;
	}
	public void setSl(String sl) {
		this.sl = sl;
	}
	public String getXl() {
		return xl;
	}
	public void setXl(String xl) {
		this.xl = xl;
	}
	public String getSqlC() {
		return sqlC;
	}
	public void setSqlC(String sqlC) {
		this.sqlC = sqlC;
	}
	public String getLjs() {
		return ljs;
	}
	public void setLjs(String ljs) {
		this.ljs = ljs;
	}
	@Override
	public String toString() {
		return "ResultOfNCC [sl=" + sl + ", xl=" + xl + ", sqlC=" + sqlC + ", ljs=" + ljs + "]";
	}
	

}
