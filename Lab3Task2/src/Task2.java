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

@WebServlet("/Task2")
public class Task2 extends HttpServlet {

	File file = new File("/Users/Cynosure/Downloads/ResultEntries.txt");
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see HttpServlet#HttpServlet()
	 */

	public Task2() {
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
	
}

	/**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HashMap<Integer, ArrayList<String>> prfnces = new HashMap<Integer, ArrayList<String>>();
		String cookieFname = "", cookieLname = "";
		String fileEntry = "";
		String languages[] = null, days[] = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// Hashtable<String, String> param = new Hashtable<String, String>();
		// get the values of firstname and lastname from parameters
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");

		// collect cookies in array
		 Cookie[] cokkies = request.getCookies();
		 
		 for (int i = 0; i < cokkies.length; i++){
	           
	            if(cokkies[i].getName().equals("firstname"))
	            	cookieFname = cokkies[i].getValue();
	            if(cokkies[i].getName().equals("lastname"))
	            	cookieLname = cokkies[i].getValue();
	            
	         }
	
				

		// check if the firstname and lastname match
		if (fname.equals(cookieFname)
				&& lname.equals(cookieLname)) {

			// get the file entry for the firstname and lastname from file:
			BufferedReader read = new BufferedReader(new FileReader(
					file.getAbsoluteFile()));

			fileEntry = read.readLine();
			boolean complete = false;

			while (fileEntry != null && !complete) {
				HashMap<String, String> fileMap = new HashMap<String, String>();
				String[] fileValues = fileEntry.split(",");
				
				fileMap.put("firstname", fileValues[0]);
				fileMap.put("lastname", fileValues[1]);
				fileMap.put("dob ", fileValues[2]);
				fileMap.put("languages", fileValues[3]);
				fileMap.put("days", fileValues[4]);

				// search for firstname and lastname in file, if found gather
				// attributes info
				if (fileValues[0].equals(fname) && fileValues[1].equals(lname)) {
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
				
					fileMap.put("firstname", fileValues[0]);
					fileMap.put("lastname", fileValues[1]);
					fileMap.put("dob ", fileValues[2]);
					fileMap.put("languages", fileValues[3]);
					fileMap.put("days", fileValues[4]);

					// if it is not the same person,note the preferences
					if ((!fileValues[0].equals(fname)) && (!fileValues[1].equals(lname))) {
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
					fileEntry = reader.readLine();

				}// end while
				reader.close();
			}// end if(complete)

			if (!prfnces.isEmpty()) {
				ArrayList<String> result_pref = new ArrayList<String>();
				Set<Integer> matche = prfnces.keySet();
				TreeSet<Integer> matches = new TreeSet<Integer>();
				for(Integer key: matche){
					matches.add(key);
				}
				Iterator<Integer> topPref = matches.descendingIterator();
				while (topPref.hasNext()) 
					result_pref.addAll(prfnces.get(topPref.next()));
				if(result_pref.size()>3){
				for (int i = 0; i<3; i++) {
					out.println("<html>" + result_pref.get(i) + "<br></html>");
				}
				}
				else
				{
					for (int i = 0; i<result_pref.size(); i++) {
						out.println("<html>" + result_pref.get(i) + "<br></html>");
					}
				}
			}

			else {
				out.println("<html>No preferences can be found for You</html>");
			}
		}

		else {
			out.println("<html>Redirect to WebForm Pages</html>");
		}
	}
	
	
}
