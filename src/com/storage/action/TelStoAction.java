package com.storage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.storage.dao.StorageDao;
import com.storage.model.WareHouse;

public class TelStoAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 3702279802318852779L;
	HttpServletRequest request;
	HttpServletResponse response;
	List<WareHouse> list=new ArrayList<WareHouse>();
	

	public void telStoSelect() throws Exception {
		StorageDao dao=new StorageDao();
		list=dao.StoSelect();
		Gson gson=new Gson();
		String jsonStr=gson.toJson(list);
		System.out.print(jsonStr);
		response.getOutputStream().write(jsonStr.getBytes("utf-8"));
	}
	
	public void telStoSelectById() throws Exception{
		String id=request.getParameter("id");
		StorageDao dao=new StorageDao();
		list=dao.StoSelectById(id);
		Gson gson=new Gson();
		String jsonStr=gson.toJson(list);
		System.out.print(jsonStr);
		response.getOutputStream().write(jsonStr.getBytes("utf-8"));
	}
	
	public void telStoDelete() throws Exception{
		String id=request.getParameter("id");
		StorageDao dao=new StorageDao();
		boolean f =false;
		f=dao.StoDel(id);
		Map<String,String> json=new HashMap<String,String>();
		if(f){
			json.put("message", "1");
		} else{
			json.put("message", "0");
		}
		byte[] jsonBytes = json.toString().getBytes("utf-8");
		response.setContentLength(jsonBytes.length);
		response.getOutputStream().write(jsonBytes);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	public void telStoAdd() throws Exception{
		WareHouse sto = new WareHouse();
		StorageDao dao = new StorageDao();
		Map<String,String> json=new HashMap<String,String>();
		sto.setSto_id(Integer.parseInt(request.getParameter("sto_id")));
		sto.setSto_name(request.getParameter("sto_name"));
		sto.setSto_type(request.getParameter("sto_type"));
		sto.setSto_money(Float.parseFloat(request.getParameter("sto_money")));
		sto.setSto_addr(request.getParameter("sto_addr"));
		boolean f = false;
		f = dao.addSto(sto);
		if(f){
			json.put("message", "1");
		} else{
			json.put("message", "0");
		}
		byte[] jsonBytes = json.toString().getBytes("utf-8");
		response.setContentLength(jsonBytes.length);
		response.getOutputStream().write(jsonBytes);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	public void telStoUpdate() throws Exception{
		int id = Integer.parseInt(request.getParameter("sto_id"));
		String name = request.getParameter("sto_name");
		String type = request.getParameter("sto_type");
		float money = Float.parseFloat(request.getParameter("sto_money"));
		String addr = request.getParameter("sto_addr");
		WareHouse sto = new WareHouse();
		StorageDao dao = new StorageDao();
		sto.setSto_id(id);
		sto.setSto_name(name);
		sto.setSto_type(type);
		sto.setSto_money(money);
		sto.setSto_addr(addr);
		boolean f = false;
		f = dao.StoUpData(sto);
		Map<String,String> json=new HashMap<String,String>();
		if(f){
			json.put("message", "1");
		} else{
			json.put("message", "0");
		}
		byte[] jsonBytes = json.toString().getBytes("utf-8");
		response.setContentLength(jsonBytes.length);
		response.getOutputStream().write(jsonBytes);
		response.getOutputStream().flush();
		response.getOutputStream().close();	
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
}
