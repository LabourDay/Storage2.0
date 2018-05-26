package com.storage.action;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.storage.dao.GoodsDao;
import com.storage.dao.StorDao;
import com.storage.model.Goods;
import com.storage.model.Storage;

public class TelStorAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
	public String execute(){
		System.out.println("--------------------------");
		return null;
		
	}
	public String telIOMessage() throws Exception{
		System.out.println("出入库信息：请求成功！");
		StorDao dao = new StorDao();
		List<Storage> infolist=new ArrayList<Storage>();
//		this.setList(dao.StoSelect());
//		this.list = getList();
		infolist = dao.select_msg();
		Gson gson = new Gson();
		String jsonStr=gson.toJson(infolist);
		System.out.println(jsonStr);
		response.getOutputStream().write(jsonStr.getBytes("utf-8"));
	//	request.setCharacterEncoding("UTF-8");
//		StorDao sDao = new StorDao();
//		ResultSet rs = sDao.telselect(null);
//		
//		try{
//			rs.last();
//			ResultSetMetaData rsData = rs.getMetaData();
//			int iNumbOfCol = rsData.getColumnCount();
//			rs.beforeFirst();
//			int count =0;
//			while(rs!=null && rs.next()){
//				LinkedHashMap rsMap = new LinkedHashMap(iNumbOfCol);
//				for(int i=1; i<=iNumbOfCol;i++){
//					rsMap.put(rsData.getColumnName(i), rs.getObject(i));
//					System.out.println(rsMap);
//				}
//				list.add(rsMap);
//				count++;
//			}
//			Gson gson = new Gson();
//			String jsonStr=gson.toJson(list);
//			System.out.println(jsonStr);
//			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
//		}catch(SQLException e){
//			e.printStackTrace();
//			
//		}
		
		return null;
	}

	//出入库操作
	public void telIOInsert() throws Exception{
		System.out.println("添加出入库：请求成功！");
		request.setCharacterEncoding("UTF-8");
		//System.out.println(request.getParameter("v_amount"));
		
		
		Storage storage = new Storage();
		StorDao sDao = new StorDao();
		Map<String,String> json=new HashMap<String,String>();
		
		storage.setG_id(request.getParameter("g_id"));
		storage.setG_name(request.getParameter("g_name"));
		storage.setV_type(request.getParameter("v_type"));
		storage.setV_amount(Integer.parseInt(request.getParameter("v_amount")));
		storage.setV_time(request.getParameter("v_time"));
		System.out.println(request.getParameter("v_amount"));
		
	
		String flag =null;
		flag = sDao.insert(storage);
		
//		if(flag != null){
//			json.put("message", "1");
//		} else{
//			json.put("message", "0");
//		}
		json.put("flag",flag);
		byte[] jsonBytes = json.toString().getBytes("utf-8");
		response.setContentLength(jsonBytes.length);
		response.getOutputStream().write(jsonBytes);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
}
