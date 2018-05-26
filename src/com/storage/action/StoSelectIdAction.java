package com.storage.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.storage.dao.StorageDao;
import com.storage.model.WareHouse;

public class StoSelectIdAction extends ActionSupport {

	private static final long serialVersionUID = 1096449288355362028L;
	HttpServletRequest request=ServletActionContext.getRequest();
	
	public List<WareHouse> list ;
	public List<WareHouse> infolist=new ArrayList<WareHouse>();
	private String id=request.getParameter("selectsto");

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println(id);
		
		StorageDao dao=new StorageDao();
		this.setList(dao.StoSelectById(id));
		request.setAttribute("mainpage", "storage/storageInfo.jsp");
		return SUCCESS;
	}
	
	public List<WareHouse> getList() {
		return list;
	}

	public void setList(List<WareHouse> list) {
		this.list = list;
	}

}
