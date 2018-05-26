package com.storage.action;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.storage.dao.WorkerDao;
import com.storage.model.Worker;

public class WorkerAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Worker worker;
	private List<Worker> list;
	List<Worker> infolist=new ArrayList<Worker>();
	
	public List<Worker> getInfolist() {
		return infolist;
	}

	public void setInfolist(List<Worker> infolist) {
		this.infolist = infolist;
	}

	@Override
	public String execute() throws Exception {
		WorkerDao dao = new WorkerDao();
		this.setList(dao.selectAll());
		request.setAttribute("mainpage", "worker/workInfo.jsp");
		return SUCCESS;
	}
	
	public String workerFindId() throws UnsupportedEncodingException{
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		System.out.println(id);
		WorkerDao dao = new WorkerDao();
//		this.setList(dao.selectById(id));
//		List<Worker> infolist=new ArrayList<Worker>();
		infolist = dao.selectById(id);
		request.setAttribute("mainpage", "worker/workUpdate.jsp");
		return "find";
	}
	
	public String workerUpdate(){
		boolean flag = false;
		WorkerDao dao = new WorkerDao();
		flag = dao.workerUpdate(worker);
		String sRet = worker.getW_id()+"修改失败";
		if(flag)
			sRet = worker.getW_id()+"修改成功";
		request.setAttribute("sRet", sRet);
		request.setAttribute("mainpage", "worker/submitWorker.jsp");
		return "workerUpdate";
	}
	
	public String workerDel(){
		String id = request.getParameter("id");
		boolean flag = false;
		WorkerDao dao = new WorkerDao();
		flag = dao.workerDel(id);
		String sRet = id+"删除失败";
		if(flag)
			sRet = id+"删除成功";
		request.setAttribute("sRet", sRet);
		request.setAttribute("mainpage", "worker/submitWorker.jsp");
		return "workerDel";
	}
	
	public String workerInsert(){
		boolean flag =false;
		WorkerDao dao = new WorkerDao();
		flag = dao.addWork(worker);
		String sRet = "添加失败";
		if(flag)
			sRet = "添加成功";
		request.setAttribute("sRet", sRet);
		request.setAttribute("mainpage", "worker/submitWorker.jsp");
		return "workerInsert";
	}
	
	public String insertJsp(){
		request.setAttribute("mainpage", "worker/workInsert.jsp");
		return "insertWkJsp";
	}
	
	
	public void telWorkerMsg() throws Exception {
		WorkerDao dao = new WorkerDao();
		list=dao.selectAll();
		Gson gson=new Gson();
		String jsonStr=gson.toJson(list);
		System.out.print(jsonStr);
		response.getOutputStream().write(jsonStr.getBytes("utf-8"));
	}
	
	public void telWorkerSelectById() throws Exception{
		String id=request.getParameter("id");
		WorkerDao dao=new WorkerDao();
		list=dao.selectById(id);
		Gson gson=new Gson();
		String jsonStr=gson.toJson(list);
		System.out.print(jsonStr);
		response.getOutputStream().write(jsonStr.getBytes("utf-8"));
	}
	
	
	public List<Worker> getList() {
		return list;
	}
	public void setList(List<Worker> list) {
		this.list = list;
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

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

}
