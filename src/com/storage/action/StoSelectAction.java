package com.storage.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.storage.dao.StorageDao;
import com.storage.model.WareHouse;

public class StoSelectAction extends ActionSupport implements ServletResponseAware{
	private static final long serialVersionUID = -6394256737357053422L;
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response ;
	public List<WareHouse> list ;
//	public List<Storage> infolist=new ArrayList<Storage>();
	

	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		StorageDao dao=new StorageDao();
		this.setList(dao.StoSelect());
		request.setAttribute("mainpage", "storage/storageInfo.jsp");
		return SUCCESS;
	}
	
	public void telStorageMsg() throws Exception{
		System.out.println("有人来了");
		StorageDao dao = new StorageDao();
		List<WareHouse> infolist=new ArrayList<WareHouse>();
//		this.setList(dao.StoSelect());
//		this.list = getList();
		infolist = dao.StoSelect();
		Gson gson = new Gson();
		String jsonStr=gson.toJson(infolist);
		System.out.println(jsonStr);
		response.getOutputStream().write(jsonStr.getBytes("utf-8"));
	}
	
	public List<WareHouse> getList() {
		return list;
	}
	public void setList(List<WareHouse> list) {
		this.list = list;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}


}
