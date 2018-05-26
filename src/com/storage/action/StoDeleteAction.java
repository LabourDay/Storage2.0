package com.storage.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.storage.dao.StorageDao;

public class StoDeleteAction extends ActionSupport {

	private static final long serialVersionUID = 1973821890871857148L;
	HttpServletRequest request=ServletActionContext.getRequest();
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		boolean f=false;
		String id=request.getParameter("id");
		StorageDao dao=new StorageDao();
		f=dao.StoDel(id);
		if(f){
		return SUCCESS;
		}
		request.setAttribute("sRet", "删除仓库信息失败，请确保该仓库的物品已被清空，否则无法删除仓库信息！");
		request.setAttribute("mainpage", "storage/displayFail.jsp");
		System.out.print("Action fail");
		return INPUT;
	}

}
