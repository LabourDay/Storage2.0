package com.storage.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.storage.dao.StorageDao;
import com.storage.model.WareHouse;

public class StoUpdataAction extends ActionSupport {
	
	private static final long serialVersionUID = -6157437126561387775L;
	private WareHouse storage;
	HttpServletRequest request=ServletActionContext.getRequest();
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		boolean f=false;
		StorageDao dao=new StorageDao();
		f=dao.StoUpData(storage);
		if(f){
			System.out.print("SUCCESS");
			return SUCCESS;
		}
		request.setAttribute("sRet", "该行为执行失败！请稍候再试。请确保输入正确或逻辑无误。");
		request.setAttribute("mainpage", "storage/displayFail.jsp");
		System.out.print("Action fail");
		return INPUT;
	}
	
	
	public WareHouse getStorage() {
		return storage;
	}
	public void setStorage(WareHouse storage) {
		this.storage = storage;
	}
	
}
