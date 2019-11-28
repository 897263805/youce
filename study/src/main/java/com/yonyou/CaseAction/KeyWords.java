package com.yonyou.CaseAction;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;



import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.Test;

import com.yonyou.controller.UserController;
import com.yonyou.pojo.ResultOfNCC;

public class KeyWords {

	static WebDriver driver;
	 static int i = 1;
	 String driverPath = "tools/chromedriver.exe";
	 public static final String success = "success";
	//public static final String fail = "fail";
	 String result = "fail";
	 int timeout = 3;
	 int sleepTime = 2000;
	 //�洢ncc������ʵ����
	 static List<ResultOfNCC> ronccList = new ArrayList<ResultOfNCC>();
	 //����ʵ���࣬���ڱ�ķ�����ȡ����
	 public static List<ResultOfNCC> getRonccList() {
		 System.out.println("�ұ�ִ����"+ronccList.toString());
		return ronccList;
	}
	public static void setRonccList(List<ResultOfNCC> ronccList) {
		KeyWords.ronccList = ronccList;
	}
	
	/**
	 * *
	 * @param fun ��λԪ�صķ�ʽ xpath id class
	 * @param ele Ԫ��
	 * @return ����һ��Ԫ��
	 */
	public static WebElement judgeFun(String fun,String ele) {
		 WebElement wb = null;
		 switch (fun) {
			case "id":
				
			   return driver.findElement(By.id(ele));
				 				
			case "xpath":
				
				return driver.findElement(By.xpath(ele));
				
			case "class":
				
				return driver.findElement(By.className(ele));
				
			default:
				return wb;
			}			 
	 
	 }
	/**
	 * ��������ķ���
	 * @param param
	 * @return
	 */
	 public String OPEN(String[] param) {		 
		 System.setProperty("webdriver.chrome.driver",driverPath);
		 //driver = new ChromeDriver();
		// driver.get(param[3]);
			try {
				String url = UserController.getIP();
				driver =  new RemoteWebDriver(new URL("http://"+url+":4444/wd/hub"),
							 DesiredCapabilities.chrome());
			} catch (MalformedURLException e) {
				
				e.printStackTrace();
			}
			driver.get(param[3]);
			 result = success;
			 return result;
		 
	 } 
	 
	 /**
		 * ��������ķ���
		 * @param param
		 * @return
		 */
		 public String OPENY(String[] param) {		 
			 System.setProperty("webdriver.chrome.driver",driverPath);
			driver = new ChromeDriver();
		    driver.get(param[3]);
			driver.get(param[3]);
			result = success;
			return result;
			 
		 } 
	 /**
		 * �л�����
		 * @param param
		 * @return
		 */
	 public String SWITCH(String[] param) {
		 try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		 Set<String> windows = driver.getWindowHandles();
		 List list = new ArrayList(windows);
		 //���ַ������������ָ�
		 if(!"".equals(param[3])) {
			 String []s = param[3].split(":");
			 System.out.println(s[0]+s[1]);
			 //�ж��ǰ�index����title�ķ�ʽ��λ����
			 switch (s[0]) {
			case "index":
				driver.switchTo().window(list.get(Integer.parseInt(s[1])-1).toString());
				System.out.println("----------"+list.get(Integer.parseInt(s[1])-1).toString());
				result = success;
				break;
			case "title":
				//�������еĴ��ڣ�ֱ���ҵ�����Ҫ��
				for(Object ob : list) {
					driver.switchTo().window(ob.toString());
					if(s[1].equals(driver.getTitle())) {
						result = success;
						break;
					}
				}			
			default:
				
				 return result;
		   }
			 //����Ƕ�λframe��ܵķ���
     	}else if(!"".equals(param[2])) {
     		WebElement frame = judgeFun(param[1], param[2]);
     		driver.switchTo().frame(frame);
     		result = success;
     		
     	}
		 return result;

	 }	 
	 /**
	  * ����¼�
	  * @param param
	  * @return
	  */
	 public String CLICK(String []param) {	
		 try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		 judgeFun(param[1],param[2]).click();
		 result = success;
		 return result;
		 
	 }	 
	 /**
	  * �����¼�
	  * @param param
	  * @return
	  */
	 public String INPUT(String []param) {
		 try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		 judgeFun(param[1],param[2]).sendKeys(param[3]);;
		 result = success;
		 return result;

	 }	 
	 public static WebDriver getDriver() {
		return driver;
	}
	 public static void setDriver(WebDriver driver) {
		KeyWords.driver = driver;
	}
	 /**
	  * �ȴ�
	  * @param param
	  * @return
	  */
	 public String WAIT(final String []param) {	
		 boolean flag = false;
		 //�ж���ֱ�ӵȴ�ʱ�仹�ǵȴ�ĳ��Ԫ�س���
		 if(!"".equals(param[2])) {			 
			 WebDriverWait wait = new WebDriverWait(driver,timeout);
			 flag = wait.until(new ExpectedCondition<Boolean>() {
	         
				public Boolean apply(WebDriver input) {
					
					return judgeFun(param[1],param[2]).isDisplayed();

				}
				 
			 });
		 }else {
			 try {
				Thread.sleep(Integer.parseInt(param[3]));
				flag = true;
			} catch (NumberFormatException e) {				
				e.printStackTrace();
				System.out.println("==============�ȴ�ʱ���ʽ����================");
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			 
		 }
		 
		 if(flag) {
			 result = success;
		 }
		 
		 
		 return result;
		 
	 }
	 /**
	  * ����
	  * @param param
	  * @return
	  */
	 public String CHECKPOINT(String []param) {
		 try {
			 //��˯һ�ᣬ����Ҫ����Ԫ��û����
			Thread.sleep(sleepTime/2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 WebElement ele = judgeFun(param[1], param[2]);
		 String value = ele.getText();
		 if(param[3] != null && param[3].equals(value)) {
			 System.out.println("�ı�У��ɹ����ڴ�ֵΪ��"+param[3]+"��ʵ��ֵΪ��"+value);
			 result = success;
			 return result;
		 }
		 System.out.println("�ı�У��ʧ�ܣ��ڴ�ֵΪ��"+param[3]+"��ʵ��ֵΪ��"+value);		 
		 return result;
	 }
	 
	 /**
	  * д������ֻ֧��ncc��sprҳ�棬�������Ż�
	  * @param param
	  * @return
	  */
	 public String WRITE(String []param) {
		String []value = {"key","xpath","/html/body/table[3]/tbody/tr[2]/td[4]"};
		WAIT(value);
		
		ResultOfNCC roc = new ResultOfNCC();
		roc.setTimes(i);
		roc.setOptionName(param[3]);
		 roc.setLjs(
			driver.findElement(By.xpath(
		    "/html/body/table[3]/tbody/tr[2]/td[4]"
		     )).getText());
		 roc.setXl(
					driver.findElement(By.xpath(
				    "/html/body/table[3]/tbody/tr[2]/td[16]"
				     )).getText());
		 roc.setSl(
					driver.findElement(By.xpath(
				    "/html/body/table[3]/tbody/tr[2]/td[15]"
				     )).getText());
		 roc.setSqlC(
					driver.findElement(By.xpath(
				    "/html/body/table[3]/tbody/tr[2]/td[10]"
				     )).getText());
		 
		 System.out.println(roc.toString());
		 ronccList.add(roc);
		 result = success;
		 return result;
	 }
	 /**
	  * ģ���������
	  * @param param
	  * @return
	  */
	 public String KEY(String []param) {
		 try {
			Robot ro = new Robot();			
		    ro.setAutoDelay(1000);
		 switch (param[3]) {
		case "enter":
			ro.keyPress(KeyEvent.VK_ENTER);
			ro.keyRelease(KeyEvent.VK_ENTER);
			result = success;
			break;
		case "esc":
			ro.keyPress(KeyEvent.VK_ESCAPE);
			ro.keyRelease(KeyEvent.VK_ESCAPE);
			result = success;
			break;
		case "F1":
			ro.keyPress(KeyEvent.VK_F1);
			ro.keyRelease(KeyEvent.VK_F1);
			result = success;
			break;
		case "F5":
			ro.keyPress(KeyEvent.VK_F5);
			ro.keyRelease(KeyEvent.VK_F5);
			result = success;
			break;
		case "F4":
			ro.keyPress(KeyEvent.VK_F4);
			ro.keyRelease(KeyEvent.VK_F4);
			result = success;
			break;
		default:
			System.out.println("Ŀǰ�ݲ�֧�ִ˿�ݼ�"+param[3]);
			break;
			
		}
		 } catch (AWTException e) {
			System.out.println("������");
		}
		 return result;
	 }
	 /**
	  * �رյ�ǰ���������
	  * @param param
	  * @return
	  */
	 public String CLOSE(String []param) {
		 driver.close();	
		 result = success;
		 return result;
	 }
	 
	 /**
	  * �˳������
	  * @param param
	  * @return
	  */
	 public String QUIT(String []param) {
		 driver.quit();
		 i++;
		 result = success;
		 return result;
	 }
	 /***
	  * ģ�����Ĳ�����Ŀǰ��δ֧�֣��������Ż�
	  * @param param
	  * @return
	  */
	 public String MOUSEMOVE(String []param) {		 
		 result = success;
		 return result;
	 }

	
	 
	 @Test
	 public void test() {
		 
		
		 //open("http://172.16.75.119:8097");
		// wait("//input[@fieldid=\"username\"]");
		 //input("//input[@fieldid=\"username\"]","am");
		 
		 
	 }
	
	

}
