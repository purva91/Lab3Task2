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

	File file = new File("/Preferences.txt");
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
		Cookie[] cookies = request.getCookies();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String actionURL = "/servlet/coreservlets.RegistrationServlet";
		
		String fName=null;
		String lName=null;
		if (cookies != null) 
		{
			for (int i = 0; i < cookies.length; i++) 
			{
				if(cookies[i].getName().equals("firstname"))
				{
					fName  = cookies[i].getValue();
				}
				
				else if(cookies[i].getName().equals("lastname"))
				{
					lName = cookies[i].getValue();
				}
			}
		}
		
		out.println("<html>\n" + "<head><title>" + "</title></head>\n" + "<form actiona=\"" + actionURL + "\">\n" +
		 "First Name:\n" + " <input type=\"text\" name=\"firstname\" " + "value=\"" + fName + "\"><br>\n" +
		 "Last Name:\n" + " <input type=\"text\" name=\"lastname\" " + "value=\"" + lName + "\"><br>\n" +
		 "<input type=\"button\" value=\"Next\">\n" + "<input type=\"button\" value=\"Back\">\n" +
		  "</form></body></html>");
					
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

	    	    
		/*
	    Enumeration<String> params = request.getParameterNames();

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

		if (!file.exists()) 
		{
			file.createNewFile();
			writeToFile = new PrintWriter(file);
			writeToFile.append(",");
			writeToFile.write(fileEntry);			
		}

		else 
		{
			writeToFile = new PrintWriter(new FileWriter(file, true));
			writeToFile.append(",");
			writeToFile.append(fileEntry);
		}

		writeToFile.close();
		fileEntry = "";
	}
	*/
	}
}