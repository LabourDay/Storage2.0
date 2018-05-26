package com.storage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.storage.model.Manager;
import com.storage.model.Worker;

public class ManagerDao extends BaseDao {
	private Connection conn =GetConnect();	
	private  PreparedStatement pmst;
	/**
	 * µÇÂ¼
	 * @param worker
	 * @return
	 */
	public boolean isLogin(Manager manager) {
		boolean flag=false;
		  String sql="select * from ManagerInfo where m_name=? and m_password=?";
		  try{
			  pmst=conn.prepareStatement(sql);
			  pmst.setString(1, manager.getM_name());
			  pmst.setString(2, manager.getM_password());
			  ResultSet rs=pmst.executeQuery();
			  if(rs.next()){
					flag=true;				
				}
			
			  
			 }catch (SQLException e){
				 e.printStackTrace();
			 }
			   
			  return flag;	  
	}
	
	public boolean updateManagerPasswd(Manager manager){
		boolean flag = false;
		String sql = "update ManagerInfo set m_password=? where m_name=?";
		conn = GetConnect();
		try{
			pmst = conn.prepareStatement(sql);
			pmst.setString(1, manager.getM_password());
			pmst.setString(2, manager.getM_name());
			int num = pmst.executeUpdate();
			if(num == 1){
				flag = true;
			}
			if(pmst != null){
				pmst.close();
			}
			if(pmst != null){
				pmst.close();
			}
		} catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
}
