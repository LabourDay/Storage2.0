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
		request.setAttribute("sRet", "ɾ���ֿ���Ϣʧ�ܣ���ȷ���òֿ����Ʒ�ѱ���գ������޷�ɾ���ֿ���Ϣ��");
		request.setAttribute("mainpage", "storage/displayFail.jsp");
		System.out.print("Action fail");
		return INPUT;
	}

}
