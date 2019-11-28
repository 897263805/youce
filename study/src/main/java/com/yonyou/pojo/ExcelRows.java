package com.yonyou.pojo;

import org.springframework.stereotype.Component;
/**
 * 实体类
 * 一个excel的行
 * @author A
 *
 */
@Component
public class ExcelRows {
	
	private int NO;
	private String fun;
	private String ele;
	private String value;
	private String key;
	private String flag;
	private String memo;
	private String result;
	private String k1;
	private String k2;
	private String k3;
	private String k4;
	private String k5;
	private String k6;
	
	
	public ExcelRows() {
		super();
	}
	public ExcelRows(int nO, String fun, String ele, String value, String key,
			String result) {
		super();
		NO = nO;
		this.fun = fun;
		this.ele = ele;
		this.value = value;
		this.key = key;

		this.result = result;
	}
	public String getK1() {
		return k1;
	}
	public void setK1(String k1) {
		this.k1 = k1;
	}
	public String getK2() {
		return k2;
	}
	public void setK2(String k2) {
		this.k2 = k2;
	}
	public String getK3() {
		return k3;
	}
	public void setK3(String k3) {
		this.k3 = k3;
	}
	public String getK4() {
		return k4;
	}
	public void setK4(String k4) {
		this.k4 = k4;
	}
	public String getK5() {
		return k5;
	}
	public void setK5(String k5) {
		this.k5 = k5;
	}
	public String getK6() {
		return k6;
	}
	public void setK6(String k6) {
		this.k6 = k6;
	}
	public int getNO() {
		return NO;
	}
	public void setNO(int nO) {
		NO = nO;
	}
	public String getFun() {
		return fun;
	}
	public void setFun(String fun) {
		this.fun = fun;
	}
	public String getEle() {
		return ele;
	}
	public void setEle(String ele) {
		this.ele = ele;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "ExcelRows [NO=" + NO + ", fun=" + fun + ", ele=" + ele + ", value=" + value + ", key=" + key + ", flag="
				+ flag + ", memo=" + memo + ", result=" + result + ", k1=" + k1 + ", k2=" + k2 + ", k3=" + k3 + ", k4="
				+ k4 + ", k5=" + k5 + ", k6=" + k6 + "]";
	}

}
