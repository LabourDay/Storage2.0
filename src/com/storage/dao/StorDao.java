package com.storage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.storage.model.Storage;

public class StorDao extends BaseDao{
	Connection conn=null;
	PreparedStatement psmt=null;
	ResultSet rs=null;
	Statement stmt=null;
	public String insert(Storage storage){
	
		conn=GetConnect();
		int cue=0;
		int uptat=0;
		int amount;
		String str="商品不存在";
		String gname=null;
			String sqlty="select * from GoodsInfo where g_id=?";
			try {
				psmt=conn.prepareStatement(sqlty);
				psmt.setString(1, storage.getG_id());
				rs=psmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(rs!=null&&rs.next()){
					gname=rs.getString("g_name");
					if("入库".equals(storage.getV_type())){
						amount=rs.getInt("g_num")+storage.getV_amount();
						String upsql="update GoodsInfo set g_num=? where g_id=?";
						String insql="insert into Storage(g_id,g_name,v_type,v_amount,v_time,v_state) values(?,?,?,?,?,'1')";
						psmt=conn.prepareStatement(upsql);
						psmt.setInt(1, amount);
						psmt.setString(2, storage.getG_id());
						uptat=psmt.executeUpdate();
						psmt=conn.prepareStatement(insql);
						psmt.setString(1, storage.getG_id());
						psmt.setString(2, gname);
						psmt.setString(3, storage.getV_type());
						psmt.setInt(4, storage.getV_amount());
						psmt.setString(5, storage.getV_time());
						cue=psmt.executeUpdate();
						str="操作成功";
					}else{
						amount=rs.getInt("g_num")-storage.getV_amount();
						if(amount>0){
							String upsql="update GoodsInfo set g_num=? where g_id=?";
							String outsql="insert into Storage(g_id,g_name,v_type,v_amount,v_time,v_state) values(?,?,?,?,?,'1')";
							psmt=conn.prepareStatement(upsql);
							psmt.setInt(1, amount);
							psmt.setString(2, storage.getG_id());
							uptat=psmt.executeUpdate();
							psmt=conn.prepareStatement(outsql);
							psmt.setString(1, storage.getG_id());
							psmt.setString(2, gname);
							psmt.setString(3, storage.getV_type());
							psmt.setInt(4, storage.getV_amount());
							psmt.setString(5, storage.getV_time());
							cue=psmt.executeUpdate();
							str="操作成功";
						}else{
							
							str="库存不足";
						
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("return="+str);
				return str;
	}
	
	
	/**
	 * 查询出入库信息
	 * 请求路径http://localhost:8080/Storage/StorAction!selectInventory
	 */
	public String select(String vtype){
		conn=GetConnect();
		StringBuilder sb = new StringBuilder();
		StringBuilder sb_plus = new StringBuilder();
		String str=null;
		int total=10;
		String sql="select * from Storage where v_type like '%"+vtype+"%'";
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			sb.append("{\"total\":" + total + ",\"rows\":[");
			while (rs!=null&&rs.next()) {
				String s_id = rs.getString("s_id");
				String g_id = rs.getString("g_id");
				String g_name = rs.getString("g_name");
				String v_type = rs.getString("v_type");
				String v_amount = rs.getString("v_amount");
				String v_time = rs.getString("v_time");
				str = "{\"s_id\":\"" + s_id + "\",\"g_id\":\"" + g_id + "\",\"g_name\":\"" + g_name+ "\",\"v_type\":\"" 
				+ v_type + "\",\"v_amount\":\"" + v_amount+ "\",\"v_time\":\"" + v_time + "\"},";
				sb_plus.append(str);
			}
			if(sb_plus.toString().length()>=1){
			str = sb_plus.toString().substring(0, sb_plus.toString().length() - 1);
			}
			sb.append(str + "]}");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * app查询出入库信息
	 * 请求路径http://localhost:8080/Storage2.0/TelStorAction!telGoodsMessage
	 */
	
	public ResultSet telselect(String vtype){
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		conn=GetConnect();
		System.out.println("+++++++++++");
		String sql="select * from Storage";
		try {
			stmt=conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery(sql);
			rs.absolute(0);
			while (rs!=null&&rs.next()) {
				HashMap map=new HashMap<String, Object>();
		//		map.put("s_id",rs.getString("s_id"));
				map.put("g_name",rs.getString("g_name"));
				map.put("g_id",rs.getString("g_id"));				
				map.put("v_type",rs.getString("v_type"));
				map.put("v_amount",rs.getString("v_amount"));
				map.put("v_time",rs.getString("v_time"));
				/*
				String s_id = rs.getString("s_id");
				String g_id = rs.getString("g_id");
				String g_name = rs.getString("g_name");
				String v_type = rs.getString("v_type");
				String v_amount = rs.getString("v_amount");
				String v_time = rs.getString("v_time");
				*/
				list.add(map);
			}
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i));
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public List<Storage> select_msg() {
		List<Storage> infolist=new ArrayList<Storage>();
		String sql = "select * from Storage ";
		conn = GetConnect();
		try {
			psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next() && rs!=null){
				Storage sto=new Storage();
				sto.setG_id(rs.getString("g_id"));
				sto.setG_name(rs.getString("g_name"));
				sto.setV_type(rs.getString("v_type"));
				sto.setV_amount(rs.getInt("v_amount"));
				sto.setV_time(rs.getString("v_time"));
				infolist.add(sto);
//				sto.setSto_id(Integer.parseInt(rs.getString("sto_id")));
//				sto.setSto_name(rs.getString("sto_name"));
//				sto.setSto_type(rs.getString("sto_type"));
////				sto.setSto_money(rs.getFloat("sto_money"));
//				sto.setSto_money(Float.parseFloat(rs.getString("sto_money")));
//				sto.setSto_addr(rs.getString("sto_addr"));
//				infolist.add(sto);
//				System.out.println(infolist);
			}

		} catch (SQLException e) {
			System.out.println("fail");
		}

		return infolist;

	}
	
	
}
