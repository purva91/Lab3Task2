import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Servlet implementation class DobLanguagesServlet
 */

@WebServlet("/DobLanguagesServlet")
public class DobLanguagesServlet extends HttpServlet 
{

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see HttpServlet#HttpServlet()
	 */

	public DobLanguagesServlet() 
	{
		// TODO Auto-generated constructor stub
		super();
	}

	/**ss
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{		
		String dob=null;
		String lang=null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) 
		{
			for (int i = 0; i < cookies.length; i++) 
			{
				if (cookies[i].getName().equals("dob"))
				{
					dob = cookies[i].getValue();
				}
				else if(cookies[i].getName().equals("Languages"))
				{
					lang  = cookies[i].getValue();
				}
						
			}
		}
				
		response.sendRedirect("doblang.html.html?dob=dob&languages=lang");					
	}
				
	

	/**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		// doGet(request, response);
				
		String dob=request.getParameter("dob");    
	    String langs=request.getParameter("Languages");
	    
	    if((dob == null))
	    	dob = "No Value";
	    
	    if((langs == null))
	    	langs  = "No Value";
	    
	    
	    Cookie d=new Cookie("dob",dob);
	    Cookie lang=new Cookie("langs",langs);
	    
	    response.addCookie(d);
	    response.addCookie(lang);
	  }
}