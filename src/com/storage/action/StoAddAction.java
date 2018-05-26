package com.storage.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.storage.dao.StorageDao;
import com.storage.model.WareHouse;

public class StoAddAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private WareHouse storage;
	HttpServletRequest request=ServletActionContext.getRequest();
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		boolean f=false;
		String sRet = null;
		StorageDao dao=new StorageDao();
		f=dao.addSto(storage);
		if(f){
			System.out.print("SUCCESS");
			return SUCCESS;
		}
		sRet =storage.getSto_name()+"仓库的信息增加失败";
		request.setAttribute("sRet", sRet);
		request.setAttribute("mainpage", "storage/displayFail.jsp");
		System.out.print("fail");
		return INPUT;
	}

	public WareHouse getStorage() {
		return storage;
	}

	public void setStorage(WareHouse storage) {
		this.storage = storage;
	}

}
