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
 * Servlet implementation class Lab3Task2
 */

@WebServlet("/Lab3Task2")
public class Lab3Task2 extends HttpServlet 
{

	File file = new File("/ResultEntries.txt");
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see HttpServlet#HttpServlet()
	 */

	public Lab3Task2() 
	{
		// TODO Auto-generated constructor stub
		super();
	}

	/*
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		// Map<String,String[]> queryParams = request.getParameterMap();
		boolean set = false;
		ArrayList<String> result = new ArrayList<String>();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Enumeration<String> params = request.getParameterNames();
		BufferedReader read = new BufferedReader(new FileReader(file.getAbsoluteFile()));

		String fileEntry = read.readLine();
		String saveEntry = fileEntry;
		while (fileEntry != null) 
		{
			HashMap<String, String> fileMap = new HashMap<String, String>();
			String[] fileValues = fileEntry.split(",");
			/*
			 * TODO split the file entry and store in the fileHashMap in
			 * corresponding values
			 */
			fileMap.put("firstname", fileValues[0]);
			fileMap.put("lastname", fileValues[1]);
			fileMap.put("dob", fileValues[2]);
			fileMap.put("languages", fileValues[3]);
			fileMap.put("days", fileValues[4]);

			/*
			 * TODO: pass the keys of query parameter map to file HashMap to
			 * check if the value of fileHashMap contains the same value or
			 * substring of value .
			 */

			while (params.hasMoreElements()) 
			{
				/*
				 * Get the value of each parameter, pass the parameter as the
				 * key in fileHasMap
				 */
				String paramN = (String) params.nextElement();
				String[] paramV = request.getParameterValues(paramN);
				if (paramV.length == 1) 
				{
					String paramValue = paramV[0];
					String checkValue = fileMap.get(paramN);
					if (checkValue.contains(paramValue))
						set = true;
					else 
					{
						set = false;
						break;
					}
				}

				else 
				{
					for (int i = 0; i < paramV.length; i++) 
					{
						String paramValue = paramV[i];
						String checkValue = fileMap.get(paramN);
						if (checkValue.contains(paramValue))
							set = true;
					}
				}

			}
			if (set==true)
				result.add(saveEntry);
			fileEntry = read.readLine();
			saveEntry = fileEntry;
		}

		read.close();

		String user = request.getHeader("User-Agent");
		String accept = request.getHeader("Accept");


		if (!result.isEmpty()) 
		{
			for (int i = 0; i < result.size(); i++) 
			{
				if(user.indexOf("Mobile") != -1) 
				{
					out.println("<html><body background-color:\"pink\"><font size=8>>\"" +result.get(i)+"\"<font></body></html>");
				    //you're in mobile land
				} 
				else 
				{
					  out.println("<html><body background-color:\"pink\"><font size=12>>\"" +result.get(i)+"\"<font></body></html>");
				}
				
			}

		}
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

		Enumeration<String> params = request.getParameterNames();
		/*
		Cookie[] cookies = request.getCookies();
		if (cookies != null) 
		{
			for (int i = 0; i < cookies.length; i++) 
			{
				if (cookies[i].getName().equals("firstname"))
				{
					if(cookies[i].getValue().equals(paramV[0])
				}
			}
		}
		*/
		while (params.hasMoreElements()) 
		{
			String paramN = (String) params.nextElement();
			String[] paramV = request.getParameterValues(paramN);
			
			Cookie[] cookies = request.getCookies();
			if (cookies != null) 
			{
				for (int i = 0; i < cookies.length; i++) 
				{
					if ((cookies[i].getName().equals("firstname"))||cookies[i].getName().equals("lastname"))
					{
						if((cookies[i].getValue().equals(paramV[0]))|| (cookies[i].getValue().equals(paramV[1])))
						{
							//prioritize the file
						}
						
						else
						{
							//go to next page
						}
					}
				}
			}
			
			else
			{
				//go to next page
			}
		}
	}
}
			
			
			
					
							
			