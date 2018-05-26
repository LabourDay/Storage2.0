package com.storage.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionLoinFilter
 */
@WebFilter("/SessionLoinFilter")
public class SessionLoinFilter implements Filter {
	String ex[];
	String path=null;

    /**
     * Default constructor. 
     */
    public SessionLoinFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	} 

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest requestt, ServletResponse responset, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		 HttpServletRequest request=(HttpServletRequest)requestt;
		 HttpServletResponse response=(HttpServletResponse)responset;
		 
		  HttpSession session=((HttpServletRequest)request).getSession();		
	       path=request.getRequestURI();
		   
	      
	      //  if((path.contains(ex))||(path.contains("login"))){  
	       if(isnotlogin(ex)){
	            //这里表示如果当前页面是登陆页面，跳转到登陆页面  
	        	//System.out.println("AAA");
	        	chain.doFilter(request, response);  	      
	            return;  	           
	        }else{            	            
	            //在不为登陆页面时，再进行判断，如果不是登陆页面也没有session则跳转到登录页面，  
	            if(session == null || session.getAttribute("username") == null){  
	            //	System.out.println("rr");
	            //	((HttpServletResponse)response).sendRedirect(path);  
	            	((HttpServletRequest)request).getRequestDispatcher("login.jsp").forward(request, response);
	                return;  
	            }else{  
	                //这里表示正确，会去寻找下一个链，如果不存在，则进行正常的页面跳转  
	            	chain.doFilter(request, response);  
	            //	System.out.println("ff");
	                return;  
	            }  
	        } 
	        
	    //  chain.doFilter(request, response);  
	     
			
	}
				
				
		
 public boolean isnotlogin(String p[]){
	 boolean f=false;
	 for(int i=0;i<ex.length;i++){
		 if(path.contains(p[i])){
			 f=true;
			 break;
		 }
	 }
	 
	 
	 return f;
	 
 }
		
	
	
	
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	    if(fConfig.getInitParameter("logo")!=null){
		ex=fConfig.getInitParameter("logo").split(",");
	    }
		
	}

}
