package com.storage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.storage.model.yuangong;

public class yuangongDao extends BaseDao {
	private Connection conn=null;
	private PreparedStatement psmt=null;
	private ResultSet rs=null;
	public ResultSet queryGoods(String strKey) {
		String sql="select * from TWorkerInfo where w_truename like ?";
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
			sql="select * from TWorkerInfo";
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
	
	public ResultSet queryGid(String w_id) {
		if(conn==null) conn=GetConnect();
		String sql="select * from TWorkerInfo where w_id=?";
		try{
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, w_id);
			rs=psmt.executeQuery();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	public boolean updateyuangong(yuangong goods) {
		boolean flag=false;
		String sql="update TWorkerInfo set w_username=?,w_password=?,w_truename=?,w_tel=?,w_mail=?,l_num=? where w_id=?";
		conn=GetConnect();
		try{
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, goods.getW_username());
			psmt.setString(2, goods.getW_password());
			psmt.setString(3, goods.getW_truename());
			psmt.setString(4, goods.getW_tel());
			psmt.setString(5, goods.getW_mail());
			psmt.setInt(6, goods.getL_num());
			psmt.setString(7, goods.getW_id());
			
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
	
	public boolean insertyuangong(yuangong goods) {
		boolean flag=false;
		conn=GetConnect();
		String sql="insert into TWorkerInfo(w_id,w_username,w_password,w_truename,w_tel,w_mail,l_num)"
					+"values(?,?,?,?,?,?,?)";
		try{
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, goods.getW_id());
			psmt.setString(2, goods.getW_username());
			psmt.setString(3, goods.getW_password());
			psmt.setString(4, goods.getW_truename());
			psmt.setString(5, goods.getW_tel());
			psmt.setString(6, goods.getW_mail());
			psmt.setInt(7, goods.getL_num());
			
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
	public boolean deleteGoods(String w_id) {
		boolean flag=false;
		String sql="delete from TWorkerInfo where w_id=?";
		conn=GetConnect();
		try{
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, w_id);
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
	public boolean anzuoinsertyuangong(yuangong goods) {
		boolean flag=false;
		conn=GetConnect();
		String sql="insert into TWorkerInfo(w_id,w_username,w_password,w_truename,w_tel,w_mail,l_num)"
					+"values(?,?,?,?,?,?,?)";
		
		try{
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, goods.getW_id());
			psmt.setString(2, goods.getW_username());
			psmt.setString(3, goods.getW_password());
			psmt.setString(4, goods.getW_truename());
			psmt.setString(5, goods.getW_tel());
			psmt.setString(6, goods.getW_mail());
			psmt.setInt(7, goods.getL_num());
			
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
	public boolean deleteyuangong(String w_id) {
		boolean flag=false;
		String sql="delete from TWorkerInfo where w_id=?";
		conn=GetConnect();
		try{
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, w_id);
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
	
	public boolean updateyuangong2(yuangong goods) {
		//安卓端要修改的数据行的各种数据最终传到这  用于修改数据库中的数据行的内容
//		获取安卓端传过来的要修改的数据行，将其对数据库进行修改操作2
		boolean flag=false;
		String sql="update TWorkerInfo set w_username=?,w_password=?,w_truename=?,w_tel=?,w_mail=?,l_num=? where w_id=?";
		conn=GetConnect();
		try{
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, goods.getW_username());
			psmt.setString(2, goods.getW_password());
			psmt.setString(3, goods.getW_truename());
			psmt.setString(4, goods.getW_tel());
			psmt.setString(5, goods.getW_mail());
		    psmt.setInt(6, goods.getL_num());
			psmt.setString(7, goods.getW_id());

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
	
