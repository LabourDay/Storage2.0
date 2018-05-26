package com.storage.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.storage.dao.BaseDao;
import com.storage.dao.StorageDao;

public class StoTiaoAction extends ActionSupport{
	private static final long serialVersionUID = -8484703376138242403L;
	HttpServletRequest request=ServletActionContext.getRequest();
	HashMap<String, Object> map=new HashMap<String, Object>();
	
	Connection conn=null;
	PreparedStatement psmt=null;
	ResultSet rs=null;
	
	public String Add(){
		request.setAttribute("mainpage", "storage/storageAdd.jsp");
		return SUCCESS;
	}
	
	public String Info(){
		request.setAttribute("mainpage", "storage/storageInfo.jsp");
		return SUCCESS;
	}
	
	public String Updata(){
		String id=request.getParameter("id");
		System.out.println(id);
		String sql = "select * from StorageInfo where sto_id = " + id;

		conn = BaseDao.GetConnect();
		try {
			psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			if(rs!=null && rs.next()){
				map.put("sto_id", rs.getInt("sto_id"));
				map.put("sto_name", rs.getString("sto_name"));
				map.put("sto_type", rs.getString("sto_type"));
				map.put("sto_money", rs.getFloat("sto_money"));
				map.put("sto_addr", rs.getString("sto_addr"));
			}
		}catch (SQLException e) {
			System.out.println("fail");
		}
		
		request.setAttribute("mainpage", "storage/storageUpdata.jsp");
		return SUCCESS;
	}
	
	public HashMap<String, Object> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Object> map) {
		this.map = map;
	}
	
}
