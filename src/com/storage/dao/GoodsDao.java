package com.storage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.storage.model.Goods;

public class GoodsDao extends BaseDao {
	private Connection conn=null;
	private PreparedStatement psmt=null;
	private ResultSet rs=null;
	public ResultSet queryGoods(String strKey) {
		String sql="select * from GoodsInfo where g_name like ?";
		if(conn==null) conn=GetConnect();
		if(strKey!=null && !strKey.trim().equals("")){
			try{
				psmt=conn.prepareStatement(sql,
						ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
				psmt.setString(1, "%"+strKey+"%");
				rs=psmt.executeQuery();
			}catch(SQLException e){
				e.printStackTrace();
			}
		} else {
			sql="select * from GoodsInfo";
			try{
				Statement stmt;
				stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
				rs=stmt.executeQuery(sql);
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	public ResultSet queryGid(String g_id) {
		if(conn==null) conn=GetConnect();
		String sql="select * from GoodsInfo where g_id=?";
		try{
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, g_id);
			rs=psmt.executeQuery();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean insertGoods(Goods goods) {
		boolean flag=false;
		conn=GetConnect();
		String sql="insert into GoodsInfo(g_id,g_name,g_unit,sto_id,g_num,g_price)"
					+"values(?,?,?,?,?,?)";
		try{
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, goods.getG_id());
			psmt.setString(2, goods.getG_name());
			psmt.setString(3, goods.getG_unit());
			psmt.setInt(4, goods.getSto_id());
			psmt.setInt(5, goods.getG_num());
			psmt.setFloat(6, goods.getG_price());
			int num=psmt.executeUpdate();
			if(num==1){
				flag=true;
			}
			if(psmt!=null){
				psmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean deleteGoods(String g_id) {
		boolean flag=false;
		String sql="delete from GoodsInfo where g_id=?";
		conn=GetConnect();
		try{
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, g_id);
			int num=psmt.executeUpdate();
			if(num==1){
				flag=true;
			}
			if(psmt!=null){
				psmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public ResultSet checkName(String key){
		String sql="select * from GoodsInfo where g_name=?";
		if(conn==null) conn=GetConnect();
		try{
			psmt=conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			psmt.setString(1, key);
			rs=psmt.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}

	
	public boolean updateGoods(Goods goods) {
		boolean flag=false;
		String sql="update GoodsInfo set g_name=?,g_unit=?,sto_id=?,g_num=?,g_price=? where g_id=?";
		conn=GetConnect();
		try{
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, goods.getG_name());
			psmt.setString(2, goods.getG_unit());
			psmt.setInt(3, goods.getSto_id());
			psmt.setInt(4, goods.getG_num());
			psmt.setFloat(5, goods.getG_price());
			psmt.setString(6, goods.getG_id());
			int num=psmt.executeUpdate();
			if(num==1){
				flag=true;
			}
			if(psmt!=null){
				psmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
}
