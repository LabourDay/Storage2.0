package com.storage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.storage.dao.StorDao;
import com.storage.model.Storage;

public class StorAction extends ActionSupport{
	private static final long serialVersionUID = -4149993028729686564L;
	HttpServletRequest request=ServletActionContext.getRequest();
	HttpServletResponse response=ServletActionContext.getResponse();
	private Storage storage;
	StorDao sdao=new StorDao();
	String rte=null;
	public String execute(){
		return null;
		
	}
	
	/**
	 * 增加出入库信息
	 * @return
	 */
	public String insert(){
		System.out.println(storage.getG_id());
		String str=sdao.insert(storage);
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			System.out.println(str);
				pw.print(str);
				pw.close();
			return SUCCESS;
		
	}
	
	/**
	 * 查询出入库信息
	 * @return
	 */
	public String selectInventory(){
		HttpServletRequest req=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String vtype="";
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("0".equals(storage.getV_type())){
			if(session.getAttribute("vtype")!=null){
				vtype=(String) session.getAttribute("vtype");
				session.removeAttribute("vtype");
			}
		}else{
			vtype=storage.getV_type();
			session.setAttribute("vtype", vtype);
		}
		
		System.out.println("就问你6不6"+vtype);
		request.setAttribute("vtype", vtype);
		
		pw.write(sdao.select(vtype));
		pw.close();
		return "success";
	}
	
	
	public Storage getStorage() {
		return storage;
	}
	public void setStorage(Storage storage) {
		this.storage = storage;
	}
	
	
	
}
