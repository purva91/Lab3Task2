import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.Cookies;

/**
 * 
 * Servlet implementation class Lab3Task2
 */

@WebServlet("/Lab3Task2")
public class Lab3Task2 extends HttpServlet {

	File file = new File("/ResultEntries.txt");
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see HttpServlet#HttpServlet()
	 */

	public Lab3Task2() {
		// TODO Auto-generated constructor stub
		super();
	}

	/*
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Map<String,String[]> queryParams = request.getParameterMap();
		boolean set = false;
		ArrayList<String> result = new ArrayList<String>();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Enumeration<String> params = request.getParameterNames();
		BufferedReader read = new BufferedReader(new FileReader(
				file.getAbsoluteFile()));

		String fileEntry = read.readLine();
		String saveEntry = fileEntry;
		while (fileEntry != null) {
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

			while (params.hasMoreElements()) {
				/*
				 * Get the value of each parameter, pass the parameter as the
				 * key in fileHasMap
				 */
				String paramN = (String) params.nextElement();
				String[] paramV = request.getParameterValues(paramN);
				if (paramV.length == 1) {
					String paramValue = paramV[0];
					String checkValue = fileMap.get(paramN);
					if (checkValue.contains(paramValue))
						set = true;
					else {
						set = false;
						break;
					}
				}

				else {
					for (int i = 0; i < paramV.length; i++) {
						String paramValue = paramV[i];
						String checkValue = fileMap.get(paramN);
						if (checkValue.contains(paramValue))
							set = true;
					}
				}

			}
			if (set == true)
				result.add(saveEntry);
			fileEntry = read.readLine();
			saveEntry = fileEntry;
		}

		read.close();

		String user = request.getHeader("User-Agent");
		String accept = request.getHeader("Accept");

		if (!result.isEmpty()) {
			for (int i = 0; i < result.size(); i++) {
				if (user.indexOf("Mobile") != -1) {
					out.println("<html><body background-color:\"pink\"><font size=8>>\""
							+ result.get(i) + "\"<font></body></html>");
					// you're in mobile land
				} else {
					out.println("<html><body background-color:\"pink\"><font size=12>>\""
							+ result.get(i) + "\"<font></body></html>");
				}

			}

		}
	}

	/**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		HashMap<Integer, ArrayList<String>> prfnces = new HashMap<Integer, ArrayList<String>>();
		int cntEntries = 0;
		String cookieFname = "", cookieLname = "";
		String fileEntry = "";
		boolean set = false;
		String languages[]=null, days[]=null;
		ArrayList<String> result = new ArrayList<String>();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Hashtable<String, String> param = new Hashtable<String, String>();
		// get the values of firstname and lastname from parameters
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");

		// collect cookies in array
		Cookie[] cokkies = request.getCookies();

		// check the loop for firstname and get its value
		for (int i = 0; i < cokkies.length; i++)
			if (cokkies[i].getName() == "firstname")
				cookieFname = cokkies[i].getValue();

		// check the loop for lastname and get its value
		for (int i = 0; i < cokkies.length; i++)
			if (cokkies[i].getName() == "lastname")
				cookieLname = cokkies[i].getValue();

		// check if the firstname and lastname match
		if (fname.contentEquals(cookieFname)
				&& lname.contentEquals(cookieLname)) {

			// get the file entry for the firstname and lastname from file:
			BufferedReader read = new BufferedReader(new FileReader(
					file.getAbsoluteFile()));

			fileEntry = read.readLine();
			boolean complete = false;

			while (fileEntry != null && !complete) {
				HashMap<String, String> fileMap = new HashMap<String, String>();
				String[] fileValues = fileEntry.split(",");
				/*
				 * TODO split the file entry and store in the fileHashMap in
				 * corresponding values
				 */
				fileMap.put("firstname", fileValues[0]);
				fileMap.put("lastname", fileValues[1]);
				fileMap.put("dob ", fileValues[2]);
				fileMap.put("languages", fileValues[3]);
				fileMap.put("days", fileValues[4]);

				// search for firstname and lastname in file, if found gather
				// attributes info
				if (fileValues[0] == fname && fileValues[1] == lname) {
					complete = true;
					languages = fileValues[3].split(" ");
					days = fileValues[4].split(" ");
					break;
				}

			}

			read.close();
			// check the file for preferences
			if (complete) {
				BufferedReader reader = new BufferedReader(new FileReader(
						file.getAbsoluteFile()));

				fileEntry = reader.readLine();
				while (fileEntry != null) {
					HashMap<String, String> fileMap = new HashMap<String, String>();
					String[] fileValues = fileEntry.split(",");
					int cnt_matches = 0;
					/*
					 * TODO split the file entry and store in the fileHashMap in
					 * corresponding values
					 */
					fileMap.put("firstname", fileValues[0]);
					fileMap.put("lastname", fileValues[1]);
					fileMap.put("dob ", fileValues[2]);
					fileMap.put("languages", fileValues[3]);
					fileMap.put("days", fileValues[4]);

					// if it is not the same person,note the preferences
					if ((fileValues[0] != fname) && (fileValues[1] != lname)) {
						// check the languages in common
						for (int i = 0; i < languages.length; i++) {
							if (fileValues[3].contains(languages[i]))
								++cnt_matches;
						}
						// check the days of the week in common
						for (int i = 0; i < days.length; i++) {
							if (fileValues[4].contains(days[i]))
								++cnt_matches;
						}

					}// end if

					// enter the results in map
					if (cnt_matches > 0) {

						// if key present, append to its list
						if (prfnces.containsKey(cnt_matches)) {
							ArrayList<String> results = prfnces
									.get(cnt_matches);
							results.add(fileEntry);
							prfnces.put(cnt_matches, results);
						}

						else {
							ArrayList<String> results = new ArrayList<String>();
							results.add(fileEntry);
							prfnces.put(cnt_matches, results);
						}

					}
					fileEntry = read.readLine();

				}// end while
				reader.close();
			}//end if(complete)
		
			
			if(!prfnces.isEmpty()){
			ArrayList<String> result_pref = new ArrayList<String>();
			ArrayList<String> result_pre = new ArrayList<String>();
			TreeSet<Integer> matches = (TreeSet<Integer>) prfnces.keySet();
			Iterator topPref=matches.descendingIterator();
			int cnt=3;
			while(topPref.hasNext()||cnt<3){
				result_pre=prfnces.get(topPref);
				
				if(result_pref.isEmpty()){
				if(result_pre.size()<3)
				{
					if(result_pref.isEmpty())
					result_pref=result_pre;
					cnt=cnt-result_pref.size();
				}
				if(result_pre.size()==3)
				{
					result_pref=result_pre;
					cnt=3;
					break;
				}
				}
				
				else{
					for(int i=0;i<result_pre.size()||i<cnt&&result_pref.size()<4;i++)
						result_pref.add(result_pre.get(i));
				}
				
			}
			for(int i=0;i<result_pref.size();i++)
			{
				out.println("<html>"+result_pref+"</html>");
			}
			}
			
			else
			{
				out.println("<html>No preferences can be found for You</html>");
			}
		}

		else {
			// go to landing page
		}

		
	}
}
