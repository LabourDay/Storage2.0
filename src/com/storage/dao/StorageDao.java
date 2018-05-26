package com.storage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.storage.model.WareHouse;



public class StorageDao extends BaseDao {
	private Connection conn=null;
	private PreparedStatement psmt=null;
	private ResultSet rs=null;

	public boolean addSto(WareHouse sto){
		boolean flag = false;
		String sql = "insert into StorageInfo(sto_id,sto_name,sto_type,sto_money,sto_addr) values(?,?,?,?,?)";	
		conn = GetConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, sto.getSto_id());
			psmt.setString(2, sto.getSto_name());
			psmt.setString(3, sto.getSto_type());
			psmt.setFloat(4, sto.getSto_money());
			psmt.setString(5, sto.getSto_addr());
			
			int i = psmt.executeUpdate();
			if (i == 1) {
				flag = true;
			}
			if(psmt!=null){
				psmt.close();
			}
			if(conn!=null){
				conn.close();
			}

		} catch (SQLException e) {
			System.out.println("fail");
		}

		return flag;
	}
	
	public List<WareHouse> StoSelect() {
		List<WareHouse> infolist=new ArrayList<WareHouse>();
		String sql = "select * from StorageInfo ";
		conn = GetConnect();
		try {
			psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next() && rs!=null){
				WareHouse sto=new WareHouse();
				sto.setSto_id(Integer.parseInt(rs.getString("sto_id")));
				sto.setSto_name(rs.getString("sto_name"));
				sto.setSto_type(rs.getString("sto_type"));
//				sto.setSto_money(rs.getFloat("sto_money"));
				sto.setSto_money(Float.parseFloat(rs.getString("sto_money")));
				sto.setSto_addr(rs.getString("sto_addr"));
				infolist.add(sto);
				System.out.println(infolist);
			}

		} catch (SQLException e) {
			System.out.println("fail");
		}

		return infolist;

	}
	
	
	
	
//	public List select(){
//		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
//		String sql = "select * from StorageInfo ";
//		conn = GetConnect();
//		try {
//			psmt = conn.prepareStatement(sql);
//			ResultSet rs = psmt.executeQuery();
//			rs.last();
//			rs.beforeFirst();
//			int count = 0;
//			while(rs.next()){
//				HashMap<String, Object> map=new HashMap<String, Object>();
//				map.put("_id", rs.getInt("sto_id"));
//				map.put("_name", rs.getString("sto_name"));
//				map.put("_type", rs.getString("sto_type"));
//				map.put("_money", rs.getFloat("sto_money"));
//				map.put("_addr", rs.getString("sto_addr"));
//				list.add(map);
//				count++;
//			}
//
//		} catch (SQLException e) {
//			System.out.println("fail");
//		}
//		
//		return list;
//	}
	
	
	
	
	public List StoSelectById(String id){
		List<WareHouse> infolist=new ArrayList<WareHouse>();
		String where = "";
		if (!(id.equals(""))) {
			where = " where sto_id=" + id;
		}

		String sql = "select * from StorageInfo" + where;
		conn = GetConnect();
		try {
			psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				WareHouse sto=new WareHouse();
				sto.setSto_id(rs.getInt("sto_id"));
				sto.setSto_name(rs.getString("sto_name"));
				sto.setSto_type(rs.getString("sto_type"));
				sto.setSto_money(rs.getFloat("sto_money"));
				sto.setSto_addr(rs.getString("sto_addr"));
				infolist.add(sto);
			}
		} catch (SQLException e) {
			System.out.println("Dao fail");
		}
		
		return infolist;
	}
	
	public boolean StoDel(String id) {
		boolean f=false;
		System.out.println("删除操作的id值："+id);
		String sql = "delete from StorageInfo where sto_id="+id;
		conn = GetConnect();
		try{
			psmt = conn.prepareStatement(sql);
			int i = psmt.executeUpdate();
			if (i > 0) {
				f = true;
			}
			if(psmt!=null){
				psmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		}catch(SQLException e){
			System.out.println("fail");
		}
		
		return f;
	
	}
	
	public boolean StoUpData(WareHouse sto){
		boolean f=false;
		String sql = "update StorageInfo set sto_name=?,sto_type=?,sto_money=?,sto_addr=? where sto_id=?";
		conn = GetConnect();
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sto.getSto_name());
			psmt.setString(2, sto.getSto_type());
			psmt.setFloat(3, sto.getSto_money());
			psmt.setString(4, sto.getSto_addr());
			psmt.setInt(5, sto.getSto_id());
			
			int i = psmt.executeUpdate();
			if(i == 1){
				f=true;
			}
			if(psmt!=null){
				psmt.close();
			}
			if(conn!=null){
				conn.close();
			}
			
		}catch(SQLException e){
			System.out.println("数据库操作失败！");
		}
		System.out.println(f);
		return f;
	}
	

}
