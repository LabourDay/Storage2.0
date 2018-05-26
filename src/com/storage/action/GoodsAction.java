package com.storage.action;

import java.io.PrintWriter;
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
import com.storage.dao.GoodsDao;
import com.storage.dao.StorageDao;
import com.storage.model.Goods;
import com.storage.model.WareHouse;


public class GoodsAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private static final long serialVersionUID  = 1L;
	private Goods goods;
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
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
	/**
	 * ��ȡ��������Ʒ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String goodsMessage() throws Exception{
		request.setCharacterEncoding("UTF-8");
		GoodsDao gDao = new GoodsDao();
		ResultSet rs = gDao.queryGoods(null);
		
		int iPageSize =4, iTotal = 0,iPageCnt =0 , iPage = 1;
		try{
			rs.last();
			iTotal = rs.getRow();
			iPageCnt = iTotal%iPageSize == 0?iTotal/iPageSize:iTotal/iPageSize+1;
			ResultSetMetaData rsData = rs.getMetaData();   //�й� ResultSet ���е����ƺ����͵���Ϣ
			int iNumbOfCol = rsData.getColumnCount();      //��Ԫ�����л������ 
			rs.beforeFirst();							   //�α�ָ���һ����ǰ��	
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
		request.setAttribute("mainpage", "goods/goodsMessage.jsp");
		return "goodsMessage";
	}
	
	
	/**
	 * ͨ��id��ȡ��Ʒ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String goodsFindId() throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		String id=request.getParameter("Id");

		GoodsDao gDao=new GoodsDao();
		ResultSet rs = gDao.queryGid(id);
		try{
			if(rs!=null && rs.next()){
				listid.put("g_id", rs.getString("g_id"));
				listid.put("g_name", rs.getString("g_name"));
				listid.put("g_unit", rs.getString("g_unit"));
				listid.put("sto_id", rs.getString("sto_id"));
				listid.put("g_num", rs.getString("g_num"));
				listid.put("g_price", rs.getString("g_price"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		request.setAttribute("mainpage", "goods/goodsUpdate.jsp");
		return "goodsFindId";
	}
	
	/**
	 * ������Ʒ
	 * @return
	 * @throws Exception
	 */
	public String goodsSearch() throws Exception{
		int iPageSize=4,iPage=1,iPageCnt=0;
		request.setCharacterEncoding("UTF-8");;
		String strKey=request.getParameter("strKey");
//		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		GoodsDao gdao=new GoodsDao();
		ResultSet rs=gdao.queryGoods(strKey);
		try {
			rs.last();
			int rsRow=rs.getRow();
			rs.beforeFirst();
			iPageCnt=rsRow%iPageSize==0?rsRow/iPageSize:rsRow/iPageSize+1;
			int count=0;
			while(rs!=null && rs.next() && count<iPageSize){
			
				LinkedHashMap<String, Object> map=new LinkedHashMap<String, Object>();
				map.put("g_id", rs.getString("g_id"));
				map.put("g_name", rs.getString("g_name"));
				map.put("g_unit", rs.getString("g_unit"));			
				map.put("sto_id", rs.getString("sto_id"));		
				map.put("g_num", rs.getString("g_num"));
				map.put("g_price", rs.getString("g_price"));
						
				list.add(map);
				count++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("iPage", iPage);
		request.setAttribute("iPageCnt", iPageCnt);
		request.setAttribute("mainpage", "goods/goodsMessage.jsp");
		HttpSession hs=request.getSession();
		hs.setAttribute("strKey", strKey);
		return "goodsSearch";
	}
	/**
	 * ��ת��������Ʒ��Ϣ����
	 * @return
	 * @throws Exception
	 */
	public String insertJsp() throws Exception{
		request.setAttribute("mainpage", "goods/goodsInsert.jsp");
		return "insertJsp";
	}
	
	/**
	 * ������Ʒ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String goodsInsert() throws Exception{
		request.setCharacterEncoding("UTF-8");
		//Goods goods = new Goods();
		GoodsDao gDao = new GoodsDao();
		
		/*goods.setG_id(request.getParameter("g_id"));
		goods.setG_name(request.getParameter("g_name"));
		goods.setG_unit(request.getParameter("g_unit"));
		goods.setSto_id(Integer.parseInt(request.getParameter("sto_id")));
		goods.setG_num(Integer.parseInt(request.getParameter("g_num")));
		goods.setG_price(Float.parseFloat(request.getParameter("g_price")));*/
		
		boolean flag = false;
		String sRet = null;
		flag = gDao.insertGoods(goods);
		if(flag) 
			sRet = goods.getG_name()+"���ӳɹ�";
		else
			sRet = goods.getG_name()+"����ʧ��";
		request.setAttribute("sRet", sRet);
		request.setAttribute("mainpage", "goods/submitGoods.jsp");
		return "goodsInsert";
	}
	
	/**
	 * ��ҳ����
	 * @return
	 * @throws Exception
	 */
	public String goodsPage() throws Exception{
		int iPageSize = 4, beforePage = 0, iPageCnt = 0, iPage = 1;
		HttpSession hs = request.getSession();
		
		iPage = Integer.parseInt(request.getParameter("iPage"));
		
		String strKey = request.getParameter("strKey");
		GoodsDao gDao = new GoodsDao();
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
		request.setAttribute("mainpage", "goods/goodsMessage.jsp");
		return "goodsPage";
	}
	
	/**
	 * ������Ʒ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String goodsUpdate() throws Exception{
//		String g_id = request.getParameter("g_id");
//		String g_name = request.getParameter("g_name");
//		String g_unit = request.getParameter("g_unit");
//		int sto_id = Integer.parseInt(request.getParameter("sto_id"));
//		int g_num = Integer.parseInt(request.getParameter("g_num"));
//		float g_price = Float.parseFloat(request.getParameter("g_price"));
//		
//		System.out.println("name:"+g_name);
//		
//		Goods good = new Goods();
//		good.setG_id(g_id);
//		good.setG_name(g_name);
//		good.setG_unit(g_unit);
//		good.setSto_id(sto_id);
//		good.setG_num(g_num);
//		good.setG_price(g_price);
		
		GoodsDao gDao = new GoodsDao();
		boolean flag = false;
		flag=gDao.updateGoods(goods);
		String sRet = null;
		if(flag)
			sRet=goods.getG_id()+"��Ʒ�޸ĳɹ�";
		else
			sRet=goods.getG_id()+"��Ʒ�޸�ʧ��";
		request.setAttribute("sRet", sRet);
		request.setAttribute("mainpage", "goods/submitGoods.jsp");
		return "goodsUpdate";
	}
	
	/**
	 * ɾ����Ʒ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String goodsDelete() throws Exception{
		GoodsDao gDao = new GoodsDao();
		String id = request.getParameter("Id");
		boolean flag =false;
		flag = gDao.deleteGoods(id);
		String sRet = null;
		if(flag)
			sRet = id + "��Ʒɾ���ɹ�";
		else
			sRet = id + "��Ʒɾ��ʧ��";
		request.setAttribute("sRet", sRet);
		request.setAttribute("mainpage", "goods/submitGoods.jsp");
		return "goodsDelete";
	}
	
	/**
	 * ������Ʒid�Ƿ����
	 * @throws Exception
	 */
	public void checkId() throws Exception{
		String id = request.getParameter("g_id");
		GoodsDao dao = new GoodsDao();
		ResultSet rs = dao.queryGid(id);
		rs.next();
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(rs!=null&&rs.getRow()>0){							//rs.getRow>0˵������Ʒ����Ѵ���
			out.println("1");									//1ֻ��˵������Ʒ����Ѵ��ڵı�־
			out.flush();
			out.close();
		}else{
			out.println("0");									//1ֻ��˵������Ʒ��ſ��õı�־
			out.flush();
			out.close();
		}
	}
	
	/**
	 * �����Ʒ�Ƿ��Ѵ���
	 * @throws Exception
	 */
	public void checkGoodsName() throws Exception{
		String name = request.getParameter("g_name");
		GoodsDao dao = new GoodsDao();
		ResultSet rs = dao.checkName(name);
		rs.next();
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(rs!=null && rs.getRow()>0){							//rs.getRow()>0˵������Ʒ�Ѵ���
			out.println("1");									//1ֻ��˵������Ʒ�Ѵ��ڵı�־
			out.flush();
			out.close();
		}else{
			out.println("0");									//0ֻ��˵������Ʒ����ӻ��޸ĵı�־
			out.flush();
			out.close();
		}
	}
	
	/**
	 * ���ֿ��Ƿ����
	 * @throws Exception
	 */
	public void checkStorageId() throws Exception{
		String sto_id = request.getParameter("sto_id");
		StorageDao dao = new StorageDao();
		List<WareHouse> list=new ArrayList<WareHouse>();
		list = dao.StoSelectById(sto_id);
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(list.size()>0){										//list.size()>0˵���òֿ����
			out.println("1");									//1ֻ��˵�òֿ���ڵı�־
			out.flush();
			out.close();
		}else{
			out.println("0");									//0ֻ��˵���òֿⲻ���ڵı�־
			out.flush();
			out.close();
		}
	}
	
	/**
	 * android�˻�ȡ������Ʒ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String telGoodsMessage() throws Exception{
		request.setCharacterEncoding("UTF-8");
		GoodsDao gDao = new GoodsDao();
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
	
	/**
	 * android��������Ʒ��Ϣ
	 * @throws Exception
	 */
	public void telGoodsInsert() throws Exception{
		request.setCharacterEncoding("UTF-8");
		Goods goods = new Goods();
		GoodsDao gDao = new GoodsDao();
		Map<String,String> json=new HashMap<String,String>();
		
		goods.setG_id(request.getParameter("g_id"));
		goods.setG_name(request.getParameter("g_name"));
		goods.setG_unit(request.getParameter("g_unit"));
		goods.setSto_id(Integer.parseInt(request.getParameter("sto_id")));
		goods.setG_num(Integer.parseInt(request.getParameter("g_num")));
		goods.setG_price(Float.parseFloat(request.getParameter("g_price")));
		
		boolean flag = false;
	
		flag = gDao.insertGoods(goods);
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
	
	/**
	 * android��ɾ����Ʒ��Ϣ
	 * @throws Exception
	 */
	public void telGoodsDelete() throws Exception{
		GoodsDao gDao = new GoodsDao();
		String id = request.getParameter("g_id");
		boolean flag =false;
		flag = gDao.deleteGoods(id);
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
	
	/**
	 * android��ͨ��id��ȡ��Ʒ��Ϣ
	 * @throws Exception
	 */
	public void telGoodsFindId() throws Exception{
		request.setCharacterEncoding("UTF-8");
		
		String id=request.getParameter("Id");
		GoodsDao gDao=new GoodsDao();
		ResultSet rs = gDao.queryGid(id);
		try{
			if(rs!=null && rs.next()){
				listid.put("g_id", rs.getString("g_id"));
				listid.put("g_name", rs.getString("g_name"));
				listid.put("g_unit", rs.getString("g_unit"));
				listid.put("sto_id", rs.getString("sto_id"));
				listid.put("g_num", rs.getString("g_num"));
				listid.put("g_price", rs.getString("g_price"));	
				list.add(listid);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String jsonStr=gson.toJson(list);
		response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		
	}
	
	/**
	 * android�˸�����Ʒ��Ϣ
	 * @throws Exception
	 */
	public void telGoodsUpdate() throws Exception{
		String g_id = request.getParameter("g_id");
		String g_name = request.getParameter("g_name");
		String g_unit = request.getParameter("g_unit");
		int sto_id = Integer.parseInt(request.getParameter("sto_id"));
		int g_num = Integer.parseInt(request.getParameter("g_num"));
		float g_price = Float.parseFloat(request.getParameter("g_price"));
		
		Goods goods = new Goods();
		goods.setG_id(g_id);
		goods.setG_name(g_name);
		goods.setG_unit(g_unit);
		goods.setSto_id(sto_id);
		goods.setG_num(g_num);
		goods.setG_price(g_price);
		
		GoodsDao gDao = new GoodsDao();
		boolean flag = false;
		flag=gDao.updateGoods(goods);
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
	
	/**
	 * android��������Ʒ��Ϣ
	 * @throws Exception
	 */
	public void telGoodsSearch() throws Exception{

		request.setCharacterEncoding("UTF-8");;
		String strKey=request.getParameter("strKey");
		System.out.println(strKey);
		GoodsDao gdao=new GoodsDao();
		ResultSet rs=gdao.queryGoods(strKey);
		try {
			rs.last();
			rs.beforeFirst();
			int count = 0;
			while(rs!=null && rs.next()){
				HashMap<String, Object> map=new HashMap<String, Object>();
				map.put("g_id", rs.getString("g_id"));
				map.put("g_name", rs.getString("g_name"));
				map.put("g_unit", rs.getString("g_unit"));
				map.put("sto_id", rs.getString("sto_id"));
				map.put("g_num", rs.getString("g_num"));
				map.put("g_price", rs.getString("g_price"));
				list.add(map);
				count++;
			}
			Gson gson = new Gson();
			String jsonStr=gson.toJson(list);
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * android�˼�����Ʒid�Ƿ����
	 * @throws Exception
	 */
	public void telCheckId() throws Exception{
		String id = request.getParameter("g_id");
		System.out.println(id);
		GoodsDao dao = new GoodsDao();
		ResultSet rs = dao.queryGid(id);
		rs.next();
		Map<String,String> json=new HashMap<String,String>();
		if(rs!=null&&rs.getRow()>0){		
			json.put("message", "����Ʒ����Ѵ���");
		}else{
			json.put("message", "����Ʒ��ſ���");
		}
		byte[] jsonBytes = json.toString().getBytes("utf-8");
		response.setContentLength(jsonBytes.length);
		response.getOutputStream().write(jsonBytes);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	/**
	 * android�˼����Ʒ�Ƿ��Ѵ���
	 * @throws Exception
	 */
	public void telCheckGoodsName() throws Exception{
		String name = request.getParameter("g_name");
		String g_name =  new String(name.getBytes("iso-8859-1"),"utf-8");
		System.out.println(g_name);
		GoodsDao dao = new GoodsDao();
		ResultSet rs = dao.checkName(g_name);
		rs.next();
		Map<String,String> json=new HashMap<String,String>();
		if(rs!=null && rs.getRow()>0){
			json.put("message", "����Ʒ�Ѵ���");
		}else{
			json.put("message", "����Ʒ�����");
		}
		byte[] jsonBytes = json.toString().getBytes("utf-8");
		response.setContentLength(jsonBytes.length);
		response.getOutputStream().write(jsonBytes);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	/**
	 * android�˼��ֿ��Ƿ����
	 * @throws Exception
	 */
	public void telCheckStorageId() throws Exception{
		String sto_id = request.getParameter("sto_id");
		System.out.println(sto_id);
		StorageDao dao = new StorageDao();
		List<WareHouse> list=new ArrayList<WareHouse>();
		list = dao.StoSelectById(sto_id);
		Map<String,String> json=new HashMap<String,String>();
		if(list.size()>0){
			json.put("message", "�òֿ����");
		}else{
			json.put("message", "�òֿⲻ����");
		}
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
