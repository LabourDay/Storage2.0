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
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import com.storage.dao.yuangongDao;

import com.storage.model.yuangong;

public class yuangongAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	private static final long serialVersionUID  = 1L;
	HttpServletRequest request;
	HttpServletResponse response;
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
	HashMap<String, Object> listid=new HashMap<String, Object>();
	public ArrayList<HashMap<String, Object>> getList() {
		return list;
	}

	public HashMap<String, Object> getListid() {
		return listid;
	}

	public void setListid(HashMap<String, Object> listid) {
		this.listid = listid;
	}

	public void setList(ArrayList<HashMap<String, Object>> list) {
		this.list = list;
	}
	
	public String yuangongMessage() throws Exception{
		request.setCharacterEncoding("UTF-8");
		yuangongDao gDao = new yuangongDao();
		ResultSet rs = gDao.queryGoods(null);
		
		int iPageSize =4, iTotal = 0,iPageCnt =0 , iPage = 1;
		try{
			rs.last();
			iTotal = rs.getRow();
			iPageCnt = iTotal%iPageSize == 0?iTotal/iPageSize:iTotal/iPageSize+1;
			ResultSetMetaData rsData = rs.getMetaData();
			int iNumbOfCol = rsData.getColumnCount();
			rs.beforeFirst();
			int count =0;
			while(rs!=null && rs.next() && count<iPageSize){
				LinkedHashMap rsMap = new LinkedHashMap(iNumbOfCol);
				for(int i=1; i<=iNumbOfCol;i++){
					rsMap.put(rsData.getColumnName(i), rs.getObject(i));
				}
				list.add(rsMap);
				count++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		request.setAttribute("iPage", iPage);
		request.setAttribute("iPageCnt", iPageCnt);
		request.setAttribute("mainpage", "yuangong/yuangongMessage.jsp");
		return "yuangongMessage";
	}

	public String yuangongDelete() throws Exception{
		yuangongDao gDao = new yuangongDao();
		String id = request.getParameter("Id");
		boolean flag =false;
		flag = gDao.deleteGoods(id);
		String sRet = null;
		if(flag)
			sRet = id + "Ա����Ϣɾ���ɹ�";
		else
			sRet = id + "Ա����Ϣɾ��ʧ��";
		request.setAttribute("sRet", sRet);
		request.setAttribute("mainpage", "yuangong/submityuangong.jsp");
		return "yuangongDelete";
	}
	
	public String yuangongFindId() throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		String id=request.getParameter("Id");

		yuangongDao gDao=new yuangongDao();
		ResultSet rs = gDao.queryGid(id);
		try{
			if(rs!=null && rs.next()){
				listid.put("w_id", rs.getString("w_id"));
				listid.put("w_username", rs.getString("w_username"));
				listid.put("w_password", rs.getString("w_password"));
				listid.put("w_truename", rs.getString("w_truename"));
				listid.put("w_tel", rs.getString("w_tel"));
				listid.put("w_mail", rs.getString("w_mail"));
				listid.put("l_num", rs.getString("l_num"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		request.setAttribute("mainpage", "yuangong/yuangongUpdate.jsp");
		return "yuangongFindId";
	}
	
	public String yuangongSearch() throws Exception{
		int iPageSize=4,iPage=1,iPageCnt=0;
		request.setCharacterEncoding("UTF-8");;
		String strKey=request.getParameter("strKey");
//		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		yuangongDao gdao=new yuangongDao();
		ResultSet rs=gdao.queryGoods(strKey);
		try {
			rs.last();
			int rsRow=rs.getRow();
			rs.beforeFirst();
			iPageCnt=rsRow%iPageSize==0?rsRow/iPageSize:rsRow/iPageSize+1;
			int count=0;
			while(rs!=null && rs.next() && count<iPageSize){
			
				LinkedHashMap<String, Object> map=new LinkedHashMap<String, Object>();
				map.put("w_id", rs.getString("w_id"));
				map.put("w_username", rs.getString("w_username"));
				map.put("w_password", rs.getString("w_password"));			
				map.put("w_truename", rs.getString("w_truename"));		
				map.put("w_tel", rs.getString("w_tel"));
				map.put("w_mail", rs.getString("w_mail"));
				map.put("l_num", rs.getString("l_num"));
						
				list.add(map);
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("iPage", iPage);
		request.setAttribute("iPageCnt", iPageCnt);
		request.setAttribute("mainpage", "yuangong/yuangongMessage.jsp");
		HttpSession hs=request.getSession();
		hs.setAttribute("strKey", strKey);
		return "yuangongSearch";
	}
	
	public String insertJsp() throws Exception{
		request.setAttribute("mainpage", "yuangong/yuangongInsert.jsp");
		return "insertJsp";
	}
	
	public String yuangongInsert() throws Exception{
		request.setCharacterEncoding("UTF-8");
		yuangong goods = new yuangong();
		yuangongDao gDao = new yuangongDao();
		
		goods.setW_id(request.getParameter("w_id"));
		goods.setW_username(request.getParameter("w_username"));
		goods.setW_password(request.getParameter("w_password"));
		goods.setW_truename(request.getParameter("w_truename"));
		goods.setW_tel(request.getParameter("w_tel"));
		goods.setW_mail(request.getParameter("w_mail"));
		goods.setL_num(Integer.parseInt(request.getParameter("l_num")));
		
		boolean flag = false;
		String sRet = null;
		flag = gDao.insertyuangong(goods);
		if(flag) 
			sRet = goods.getW_username()+"�û����ӳɹ�";
		else
			sRet = goods.getW_username()+"�û�����ʧ��";
		request.setAttribute("sRet", sRet);
		request.setAttribute("mainpage", "yuangong/submityuangong.jsp");
		return "yuangongInsert";
	}
	
	public String yuangongUpdate() throws Exception{
		String w_id = request.getParameter("w_id");
		String w_username = request.getParameter("w_username");
		String w_password = request.getParameter("w_password");
		String w_truename = request.getParameter("w_truename");
		String w_tel = request.getParameter("w_tel");
		String w_mail = request.getParameter("w_mail");
		int l_num = Integer.parseInt(request.getParameter("l_num"));
		
		yuangong goods = new yuangong();
		goods.setW_id(w_id);
		goods.setW_username(w_username);
		goods.setW_password(w_password);
		goods.setW_truename(w_truename);
		goods.setW_tel(w_tel);
		goods.setW_mail(w_mail);
		goods.setL_num(l_num);
		
		yuangongDao gDao = new yuangongDao();
		boolean flag = false;
		flag=gDao.updateyuangong(goods);
		String sRet = null;
		if(flag)
			sRet=w_id+"Ա����Ϣ�޸ĳɹ�";
		else
			sRet=w_id+"Ա����Ϣ�޸ĳɹ�";
		request.setAttribute("sRet", sRet);
		request.setAttribute("mainpage", "yuangong/submityuangong.jsp");
		return "yuangongUpdate";
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	public String yuangongPage() throws Exception{
//		iPageSizeÿҳ�����У�iPageCntҳ����  iPage��ǰ�����ţ�  beforePageȫ��¼������  ����Ϊ����ҳ����ת��ʾ��һҳ��һҳ����
		int iPageSize = 4, beforePage = 0, iPageCnt = 0, iPage = 1;
		HttpSession hs = request.getSession();
		
		iPage = Integer.parseInt(request.getParameter("iPage"));
		
		String strKey = request.getParameter("strKey");
		yuangongDao gDao = new yuangongDao();
		ResultSet rs = gDao.queryGoods(strKey);
		try{
			rs.last();
			beforePage = rs.getRow();
			rs.absolute((iPage-1)*iPageSize);
			iPageCnt = beforePage%iPageSize == 0 ? beforePage/iPageSize : (beforePage/iPageSize)+1;			
			ResultSetMetaData rsData = rs.getMetaData();
			int rscount = rsData.getColumnCount();
			int count = 0;
			while(rs != null && rs.next() && count<iPageSize){
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				for(int i=1 ; i<=rscount ; i++){
					map.put(rsData.getColumnName(i), rs.getObject(i));				
				}
				list.add(map);
				count++;
			}
			while(rs!=null&&rs.next()&&count<iPageSize){
				LinkedHashMap<String, Object> map=new LinkedHashMap<String, Object>();
				for(int i=1;i<=rscount;i++){
					map.put(rsData.getColumnName(i), rs.getObject(i));
				}
				list.add(map);
				count++;
			}
		
		} catch(SQLException e){
			e.printStackTrace();
		}
		hs.setAttribute("strKey", strKey);
		request.setAttribute("list", list);
		request.setAttribute("iPage", iPage);
		request.setAttribute("iPageCnt", iPageCnt);
		request.setAttribute("mainpage", "yuangong/yuangongMessage.jsp");
		return "yuangongPage";
	}
	

	public String goodsSearch() throws Exception{
//		����Ϊ�����û���������ݼ������ݿ��е����ݵĹ���
//		iPageSizeÿҳ�����У�iPageCntҳ����  iPage��ǰ�����ţ�  beforePageȫ��¼������  ����Ϊ����ҳ����ת��ʾ��һҳ��һҳ����
//      ��Ŀǰ�Ĺ�����ͨ���ֶ�����   ��ʵ����  ������Ҫ�鵽��������
		int iPageSize=4,iPage=1,iPageCnt=0;
		request.setCharacterEncoding("UTF-8");;
		String strKey=request.getParameter("strKey");

		yuangongDao gdao=new yuangongDao();
		ResultSet rs=gdao.queryGoods(strKey);
		try {
			rs.last();
			int rsRow=rs.getRow();
			rs.beforeFirst();
			iPageCnt=rsRow%iPageSize==0?rsRow/iPageSize:rsRow/iPageSize+1;
			int count=0;
			while(rs!=null && rs.next() && count<iPageSize){
			
				LinkedHashMap<String, Object> map=new LinkedHashMap<String, Object>();
				map.put("w_id", rs.getString("w_id"));
				map.put("w_username", rs.getString("w_username"));
				map.put("w_password", rs.getString("w_password"));			
				map.put("w_truename", rs.getString("w_truename"));		
				map.put("w_tel", rs.getString("w_tel"));
				map.put("w_mail", rs.getString("w_mail"));
				map.put("l_num", rs.getString("l_num"));
						
				list.add(map);
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("iPage", iPage);
		request.setAttribute("iPageCnt", iPageCnt);
		request.setAttribute("mainpage", "yuangong/yuangongMessage.jsp");
		HttpSession hs=request.getSession();
		hs.setAttribute("strKey", strKey);
		return "yuangongSearch";
	}
	
	//��׿����ҳ  ��ѯ�����ݿ�����ݴ�����׿����ʾ����
	public String telyuangongMessage() throws Exception{
		request.setCharacterEncoding("UTF-8");
		yuangongDao gDao = new yuangongDao();
		ResultSet rs = gDao.queryGoods(null);
		
		try{
			rs.last();
			ResultSetMetaData rsData = rs.getMetaData();
			int iNumbOfCol = rsData.getColumnCount();
			rs.beforeFirst();
			int count =0;
			while(rs!=null && rs.next()){
				LinkedHashMap rsMap = new LinkedHashMap(iNumbOfCol);
				for(int i=1; i<=iNumbOfCol;i++){
					rsMap.put(rsData.getColumnName(i), rs.getObject(i));
				}
				list.add(rsMap);
				count++;
			}
			Gson gson = new Gson();
			String jsonStr=gson.toJson(list);
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		
		return null;
	}
	public void telyuangongSearch() throws Exception{
// (��׿�˸����ֶ���������)
		request.setCharacterEncoding("UTF-8");;
		String strKey=request.getParameter("strKey");
		System.out.println(strKey);
		yuangongDao gdao=new yuangongDao();
		ResultSet rs=gdao.queryGoods(strKey);
		try {
			rs.last();
			rs.beforeFirst();
			int count = 0;
			while(rs!=null && rs.next()){
				HashMap<String, Object> map=new HashMap<String, Object>();
				map.put("w_id", rs.getString("w_id"));
				map.put("w_username", rs.getString("w_username"));
				map.put("w_password", rs.getString("w_password"));
				map.put("w_truename", rs.getString("w_truename"));
				map.put("w_tel", rs.getString("w_tel"));
				map.put("w_mail", rs.getString("w_mail"));
				map.put("l_num", rs.getString("l_num"));
				list.add(map);
				count++;
			}
			Gson gson = new Gson();
			String jsonStr=gson.toJson(list);
//			��jsonstr���봫��response�з��ذ�׿��
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void telyuangongInsert() throws Exception{
		//��׿�˴�������url��Ӧ��Ҫ��������ݲ��뵽���ݿ���   
		request.setCharacterEncoding("UTF-8");
		yuangong goods = new yuangong();
		yuangongDao gDao = new yuangongDao();
		Map<String,String> json=new HashMap<String,String>();
		//request��ȡ��׿�˴�����������
		goods.setW_id(request.getParameter("w_id"));
		goods.setW_username(request.getParameter("w_username"));
		goods.setW_password(request.getParameter("w_password"));
		goods.setW_truename(request.getParameter("w_truename"));
		goods.setW_tel(request.getParameter("w_tel"));
		goods.setW_mail(request.getParameter("w_mail"));
		goods.setL_num(Integer.parseInt(request.getParameter("l_num")));
        
		
		System.out.println(goods.getW_id());
		System.out.println(goods.getL_num());

		boolean flag = false;
	
		flag = gDao.anzuoinsertyuangong(goods);
		if(flag){
			json.put("message", "1");
		} else{
			json.put("message", "0");
		}
		byte[] jsonBytes = json.toString().getBytes("utf-8");
		//д��response����
		response.setContentLength(jsonBytes.length);
		response.getOutputStream().write(jsonBytes);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	public void telyuangongDelete() throws Exception{
		yuangongDao gDao = new yuangongDao();
//		��ȡǰ̨���͹�����w_id
		
		String w_id = request.getParameter("w_id");
		boolean flag =false;
		flag = gDao.deleteyuangong(w_id);
		Map<String,String> json=new HashMap<String,String>();
		if(flag){
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
	
	public void telyuangongUpdate() throws Exception{
//		��ȡ��׿�˴�������Ҫ�޸ĵ������У���������ݿ�����޸Ĳ���1  ������һ�� ��DAO����
		
		String w_id = request.getParameter("w_id");
		String w_username = request.getParameter("w_username");
		String w_password = request.getParameter("w_password");
		String w_truename = request.getParameter("w_truename");
		String w_tel = request.getParameter("w_tel");
		String w_mail = request.getParameter("w_mail");
		int l_num = Integer.parseInt(request.getParameter("l_num"));
		System.out.println("w_id"+"w_username"+"w_password"+"w_truename"+"w_tel"+"w_mail"+"l_num");
		yuangong goods = new yuangong();
		goods.setW_id(w_id);
		goods.setW_username(w_username);
		goods.setW_password(w_password);
		goods.setW_truename(w_truename);
		goods.setW_tel(w_tel);
		goods.setW_mail(w_mail);
		goods.setL_num(l_num);
		
		yuangongDao gDao = new yuangongDao();
		boolean flag = false;
		flag=gDao.updateyuangong2(goods);
		Map<String,String> json=new HashMap<String,String>();
		if(flag){
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
}
