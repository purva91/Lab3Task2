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

	File file = new File("/Preferences.txt");
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
		//get the cookie information and display it in the field
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
	    
	    Cookie fn=new Cookie("firstname",fname);
	    Cookie ln=new Cookie("lastname",lname);

	    response.addCookie(fn);
	    response.addCookie(ln);

	    /*
		while (params.hasMoreElements()) 
		{
			String paramN = (String) params.nextElement();
			String[] paramV = request.getParameterValues(paramN);
			if (paramV.length == 1) 
			{
				String paramValue = paramV[0];
				if (paramValue.length() == 0)
					fileEntry = fileEntry + "no value" + ",";
				else
					fileEntry = fileEntry + paramValue + ",";
			}

			else 
			{
				for (int i = 0; i < paramV.length; i++) 
				{

					if (paramV[i].length() == 0)
						fileEntry = fileEntry + "no value" + " ";
					else
						fileEntry = fileEntry + paramV[i] + " ";
				}
			}

		}
		*/

	}
}