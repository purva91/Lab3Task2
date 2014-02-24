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
 * Servlet implementation class DaysServlet
 */

@WebServlet("/DaysServlet")
public class DaysServlet extends HttpServlet 
{

	File file = new File("/Preferences.txt");
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see HttpServlet#HttpServlet()
	 */

	public DaysServlet() 
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
		//value of checked boxes on send Redirect String[] to string??
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
		String[] days = request.getParameterValues("days");
		 
		for (int i = 0; i < days.length; i++) 
		{
			Cookie avail_days=new Cookie("days",days[i]);
			 
			response.addCookie(avail_days);
		}
	}
}