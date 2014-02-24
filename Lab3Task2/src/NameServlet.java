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
 * Servlet implementation class NameServlet
 */

@WebServlet("/NameServlet")
public class NameServlet extends HttpServlet
{

	//File file = new File("/ResultEntries.txt");
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see HttpServlet#HttpServlet()
	 */

	public NameServlet() 
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
		String fn;
		String ln;
		String id = null;
		doPost(request, response);
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) 
		{
			for (int i = 0; i < cookies.length; i++) 
			{
				if(cookies[i].getName().equals("user_id"))
				{
					if(cookies[i].getValue().equals(id))
					{
						if (cookies[i].getName().equals("firstname"))
						{
							fn = cookies[i].getValue();
						}
						else if(cookies[i].getName().equals("lastname"))
						{
							ln  = cookies[i].getValue();
						}
						
					}
				}
				
			}
		}
		//get the cookie information and display it in the field
		response.sendRedirect("Name.html?firstname=fn&lastname=ln&userId=id");
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
		int cntEntries = 0;
		String fileEntry = "";
		response.setContentType("text/html");
		PrintWriter writeToFile;
		PrintWriter out = response.getWriter();
		String id = null;
		
		Enumeration<String> params = request.getParameterNames();
		
		/*creating and inserting cookies
		 */
		String fname=request.getParameter("firstname");    
	    String lname=request.getParameter("lastname");
	    
	    String time  = String.valueOf(System.currentTimeMillis());
	    
	    id = fname + lname + time;
	    
	    if((fname == null))
	    	fname  = "No Value";
	    if((lname == null))
	    	lname  = "No Value";
	    
	    Cookie fn = new Cookie("firstname",fname);
	    Cookie ln = new Cookie("lastname",lname);
	    Cookie user_id = new Cookie("user_id", id);

	    response.addCookie(fn);
	    response.addCookie(ln);
	    /*
	    UserInfo info = null;
	    info.user_id = id;
	    info.firstName = fname;
	    info.lastName = fname;
	    */

	    
	}
}