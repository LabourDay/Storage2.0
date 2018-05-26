package com.storage.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.storage.dao.ManagerDao;
import com.storage.dao.WorkerDao;
import com.storage.model.Manager;
import com.storage.model.Worker;

public class Login extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	private static final long serialVersionUID = -1L;
	Worker worker;						//��ͨԱ��
	Manager manager;					//����Ա
	String imageCode;					//��֤��
	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	String error;						//������Ϣ
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	HttpServletRequest request;
	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	HttpServletResponse response;
	String sType;
	/**
	 * ��¼����
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception{
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		sType = request.getParameter("sType");
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		
			
		System.out.println(sType);
//		System.out.println(username);
//		System.out.println(password);
		
		//�������ͨԱ��
		if(sType.equals("��ͨԱ��")){
			if("".equals(worker.getW_username())||"".equals(worker.getW_password())){
				error = "�û��������벻��Ϊ��";
				return "loginErr";
			}
			if("".equals(imageCode)){
				error = "��֤�벻��Ϊ�գ�";
				return "loginErr";
			}
//			Worker worker = new Worker(username,password);
//			worker.setW_username(username);
//			worker.setW_password(password);
			WorkerDao wDao = new WorkerDao();
			boolean flag = false;
			flag = wDao.isLogin(worker);
			System.out.println(flag);
			
			if(!imageCode.equals(session.getAttribute("sRand"))){
				error = "��֤�����";
				return "loginErr";
			}
			
			if(flag){
				session.setAttribute("username", worker.getW_username());
				session.setAttribute("password", worker.getW_password());
				session.setAttribute("sType", sType);
				
				return "loginSuccess";
			}
			else{
				error = "�û������������";
				return "loginErr";
			}
		}
		
		//����ǹ���Ա
		if(sType.equals("����Ա")){
			if("".equals(manager.getM_name())||"".equals(manager.getM_password())){
				error = "�û��������벻��Ϊ��";
				return "loginErr";
			}
			/*Manager manager = new Manager();
			manager.setM_name(username);
			manager.setM_password(password);*/
			ManagerDao mDao = new ManagerDao();
			boolean flag = false;
			flag = mDao.isLogin(manager);
			System.out.println(flag);
			
			if(!imageCode.equals(session.getAttribute("sRand"))){
				error = "��֤�����";
				return "loginErr";
			}
			
			if(flag){
				session.setAttribute("username", manager.getM_name());
				session.setAttribute("password", manager.getM_password());
				session.setAttribute("sType", sType);
				
				return "loginSuccess";
			}else{
				error = "�û������������";
				return "loginErr";
			}
			
		}
		 
		return super.execute();
	}
	
	//��ת���޸��������
	public String updatePasswdJsp(){
		request.setAttribute("mainpage", "updatePasswd.jsp");
		return "updatePasswdJsp";
	}
	
	//�޸�����
	public String updatePasswd() throws Exception{
		request.setCharacterEncoding("utf-8");
		String un = request.getParameter("un");
		String oldpw = request.getParameter("holdpw");
		String inoldpw = request.getParameter("password");
		String newpw = request.getParameter("newpw1");
		String newpw2 = request.getParameter("newpw2");
		String role = request.getParameter("role");
//		System.out.println(un);
//		System.out.println(oldpw);
//		System.out.println(inoldpw);
//		System.out.println(newpw);
//		System.out.println(newpw2);
//		System.out.println(role);
		String sRet = "�����޸�ʧ��";
		
		if(role.equals("����Ա")){
			if(inoldpw.equals(oldpw)){
				Manager mg = new Manager();
				
				mg.setM_password(newpw);
				mg.setM_name(un);
				ManagerDao mDao = new ManagerDao();
				boolean flag = false;
				flag = mDao.updateManagerPasswd(mg);
				if(flag)
					sRet = "�����޸ĳɹ�";
//				else
//					sRet = "�����޸�ʧ��";
			}
		}else if(role.equals("��ͨԱ��")){
			if(inoldpw.equals(oldpw)){
				Worker worker = new Worker();
				worker.setW_password(newpw);
				worker.setW_username(un);
				WorkerDao wDao = new WorkerDao();
				boolean flag = false;
				flag = wDao.updateWorkerPassWord(worker);
				if(flag)
					sRet = "�����޸ĳɹ�";
//				else
//					sRet = "�����޸�ʧ��";
			}
		}
//		else
//			sRet="�����޸�ʧ��";
		
		request.setAttribute("sRet", sRet);
		if(sRet.equals("�����޸ĳɹ�")){	
			request.setAttribute("mainpage", "updateSuccess.jsp");
			return "updatePasswdSc";
		}
//		if(sRet.equals("�����޸�ʧ��")){		
//			request.setAttribute("mainpage", "updateFail.jsp");
//			return "updatePasswdFi";
//		}
//		return null;
		request.setAttribute("sRet", sRet);
		System.out.println(sRet);
		request.setAttribute("mainpage", "updateFail.jsp");
		return "updatePasswdFi";
		
	}
	
	public String logout(){
		HttpSession session = request.getSession();
		session.invalidate();
		return "logout";
	}
	
	
	//android�˵�¼����
	public String telLogin() throws Exception{
		//Thread.sleep(5000);
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		sType = request.getParameter("sType");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(sType);
		System.out.println(username);
		System.out.println(password);
		Map<String,String> json=new HashMap<String,String>();
		if(sType.equals("��ͨԱ��")){
			Worker worker = new Worker(username,password);
			worker.setW_username(username);
			worker.setW_password(password);
			WorkerDao wDao = new WorkerDao();
			boolean flag = false;
			flag = wDao.isLogin(worker);
			System.out.println(flag);
			
			if(flag){
				json.put("message", "1");		
				byte[] jsonBytes = json.toString().getBytes("utf-8");
				  response.setContentLength(jsonBytes.length);
				  response.getOutputStream().write(jsonBytes);
				  response.getOutputStream().flush();
				  response.getOutputStream().close();
			}
			else{
				json.put("message", "0");
				byte[] jsonBytes = json.toString().getBytes("utf-8");
				  response.setContentLength(jsonBytes.length);
				  response.getOutputStream().write(jsonBytes);
				  response.getOutputStream().flush();
				  response.getOutputStream().close();
			}
		}
		
		if(sType.equals("����Ա")){
			Manager manager = new Manager();
			manager.setM_name(username);
			manager.setM_password(password);
			ManagerDao mDao = new ManagerDao();
			boolean flag = false;
			flag = mDao.isLogin(manager);
			System.out.println(flag);
			if(flag){
				json.put("message", "1");
				byte[] jsonBytes = json.toString().getBytes("utf-8");
				  response.setContentLength(jsonBytes.length);
				  response.getOutputStream().write(jsonBytes);
				  response.getOutputStream().flush();
				  response.getOutputStream().close();
			}else{
				json.put("message", "0");
				byte[] jsonBytes = json.toString().getBytes("utf-8");
				  response.setContentLength(jsonBytes.length);
				  response.getOutputStream().write(jsonBytes);
				  response.getOutputStream().flush();
				  response.getOutputStream().close();
			}
			
		}
		 
		return null;
	}
	
/*	public String getsType() {
		return sType;
	}
	public void setsType(String sType) {
		this.sType = sType;
	}*/
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
		
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}
}
