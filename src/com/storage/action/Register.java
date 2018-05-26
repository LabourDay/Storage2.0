package com.storage.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.storage.dao.WorkerDao;
import com.storage.model.Worker;

public class Register extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = -1L;
	HttpServletRequest request;
	private Worker worker;
	
	public String execute() throws Exception{
		ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		String role = ServletActionContext.getRequest().getParameter("role");
		if(role.equals("worker")){
			worker = new Worker();
//			Worker worker = new Worker();
			WorkerDao wDao = new WorkerDao();
			String id = ServletActionContext.getRequest().getParameter("id");
			String username = ServletActionContext.getRequest().getParameter("username");
			String password = ServletActionContext.getRequest().getParameter("password");
			String truename = ServletActionContext.getRequest().getParameter("truename");
			String sex = ServletActionContext.getRequest().getParameter("sex");
			String birth = ServletActionContext.getRequest().getParameter("birth");
			String tel = ServletActionContext.getRequest().getParameter("tel");

			worker.setW_id(id);
			worker.setW_username(username);
			worker.setW_password(password);
			worker.setW_truename(truename);
			worker.setW_sex(sex);
			worker.setW_birth(birth);
			worker.setW_tel(tel);	
			boolean flag = false;
			flag = wDao.register(worker);
			if(flag){
				request.setAttribute("success", "¹§Ï²£¬×¢²á³É¹¦£¡¼´½«·µ»ØµÇÂ¼½çÃæ");
				return "registerSuccess";
			}
		}
		request.setAttribute("fail", "×¢²áÊ§°Ü");
		return "registerFail";
	}
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
}
