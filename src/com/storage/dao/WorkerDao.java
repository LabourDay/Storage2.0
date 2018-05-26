package com.storage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.storage.model.WareHouse;
import com.storage.model.Worker;

public class WorkerDao extends BaseDao {
	private Connection conn = GetConnect();
	private PreparedStatement pmst;

	/**
	 * 登录
	 * 
	 * @param worker
	 * @return
	 */
	public boolean isLogin(Worker worker) {
		boolean flag = false;
		String sql = "select * from WorkerInfo where w_username=? and w_password=?";
		try {
			pmst = conn.prepareStatement(sql);
			pmst.setString(1, worker.getW_username());
			pmst.setString(2, worker.getW_password());
			ResultSet rs = pmst.executeQuery();
			if (rs.next()) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/*
	 * 插入员工信息
	 */
	public boolean addWork(Worker work) {
		boolean flag = false;
		String sql = "insert into WorkerInfo(w_id,w_username,w_password,w_truename,w_sex,w_birth,w_tel)"
				+ "values(?,?,?,?,?,?,?)";
		conn = GetConnect();
		try {
			pmst = conn.prepareStatement(sql);
			pmst.setString(1, work.getW_id());
			pmst.setString(2, work.getW_username());
			pmst.setString(3, work.getW_password());
			pmst.setString(4, work.getW_truename());
			pmst.setString(5, work.getW_sex());
			pmst.setString(6, work.getW_birth());
			pmst.setString(7, work.getW_tel());

			int i = pmst.executeUpdate();
			if (i == 1) {
				flag = true;
			}
			if (pmst != null) {
				pmst.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			System.out.println("fail");
			e.printStackTrace();
		}

		return flag;
	}
	
	public List<Worker> selectAll() {
		List<Worker> infolist=new ArrayList<Worker>();
		String sql = "select * from WorkerInfo ";
		conn = GetConnect();
		try {
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while(rs.next() && rs!=null){
				Worker worker = new Worker();
				worker.setW_id(rs.getString("w_id"));
				worker.setW_username(rs.getString("w_username"));
				worker.setW_truename(rs.getString("w_truename"));
				worker.setW_sex(rs.getString("w_sex"));
				worker.setW_birth(rs.getString("w_birth"));
				worker.setW_tel(rs.getString("w_tel"));
				infolist.add(worker);
				System.out.println(infolist);
			}

		} catch (SQLException e) {
			System.out.println("fail");
		}

		return infolist;

	}
	
	public List<Worker> selectById(String id){
		List<Worker> infolist=new ArrayList<Worker>();
		String where = "";
		if (!(id.equals(""))) {
			where = " where w_id=" + id;
		}

		String sql = "select * from WorkerInfo" + where;
		conn = GetConnect();
		try {
			pmst = conn.prepareStatement(sql);
			ResultSet rs = pmst.executeQuery();
			while(rs.next()){
				Worker worker = new Worker();
				worker.setW_id(rs.getString("w_id"));
				worker.setW_username(rs.getString("w_username"));
				worker.setW_truename(rs.getString("w_truename"));
				worker.setW_sex(rs.getString("w_sex"));
				worker.setW_birth(rs.getString("w_birth"));
				worker.setW_tel(rs.getString("w_tel"));
				infolist.add(worker);
			}
		} catch (SQLException e) {
			System.out.println("Dao fail");
			e.printStackTrace();
		}
		
		return infolist;
	}
	
	public boolean workerDel(String id) {
		boolean f=false;
		System.out.println("员工id"+id);
		String sql = "delete from WorkerInfo where w_id="+id;
		conn = GetConnect();
		try{
			pmst = conn.prepareStatement(sql);
			int i = pmst.executeUpdate();
			if (i > 0) {
				f = true;
			}
			if(pmst!=null){
				pmst.close();
			}
			if(conn!=null){
				conn.close();
			}
		}catch(SQLException e){
			System.out.println("fail");
		}
		
		return f;
	
	}
	
	public boolean workerUpdate(Worker worker){
		boolean f=false;
		String sql = "update WorkerInfo set w_truename=?,w_sex=?,w_birth=?,w_tel=? where w_id=?";
		conn = GetConnect();
		try{
			pmst = conn.prepareStatement(sql);
			pmst.setString(1, worker.getW_truename());
			pmst.setString(2, worker.getW_sex());
			pmst.setString(3, worker.getW_birth());
			pmst.setString(4, worker.getW_tel());
			pmst.setString(5, worker.getW_id());
			
			int i = pmst.executeUpdate();
			if(i == 1){
				f=true;
			}
			if(pmst!=null){
				pmst.close();
			}
			if(conn!=null){
				conn.close();
			}
			
		}catch(SQLException e){
			System.out.println("鏁版嵁搴撴搷浣滃け璐ワ紒");
		}
		System.out.println(f);
		return f;
	}

	/**
	 * 注册
	 * 
	 * @param worker
	 * @return
	 */
	public boolean register(Worker worker) {
		boolean flag = false;
		String sql = "insert into WorkerInfo"
				+ "(w_id,w_username,w_password,w_truename,w_sex,w_birth,w_tel)"
				+ "values(?,?,?,?,?,?,?)";
		try {
			pmst = conn.prepareStatement(sql);
			pmst.setString(1, worker.getW_id());
			pmst.setString(2, worker.getW_username());
			pmst.setString(3, worker.getW_password());
			pmst.setString(4, worker.getW_truename());
			pmst.setString(5, worker.getW_sex());
			pmst.setString(6, worker.getW_birth());
			pmst.setString(7, worker.getW_tel());
			int num = pmst.executeUpdate();
			if (num == 1)
				flag = true;
			if (pmst != null)
				pmst.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 修改密码
	 * 
	 * @param worker
	 * @return
	 */
	public boolean updateWorkerPassWord(Worker worker) {
		boolean flag = false;
		String sql = "update WorkerInfo set w_password=? where w_username=?";
		conn = GetConnect();
		try {
			pmst = conn.prepareStatement(sql);
			pmst.setString(1, worker.getW_password());
			pmst.setString(2, worker.getW_username());
			int num = pmst.executeUpdate();
			if (num == 1) {
				flag = true;
			}
			if (pmst != null) {
				pmst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
