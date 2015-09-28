package project.dal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetQuotes 
{
	public static ArrayList<String> main(String[] stocks, String URLData) throws Exception
	{
		//while(true)
		//{
			StringBuilder url = new StringBuilder("http://finance.yahoo.com/d/quotes.csv?s=");
	        for (String s : stocks)
	        {
	            url.append(s + "+");
	        }
	        
	        //url.deleteCharAt(url.length()-1);
	        // Properties is for bid and ask
	        //url.append("&f=a0sa5sb0sb6&e=.csv");
	        
	        url.append("&f=" + URLData + "&e=.csv");
	        
	        String theUrl = url.toString();
	        URL obj = new URL(theUrl);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        
	        // This is a GET request
	        con.setRequestMethod("GET");
	        con.setRequestProperty("User-Agent", "Mozilla/5.0");
	        int responseCode = con.getResponseCode();
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        
	        ArrayList<String> inputLines = new ArrayList<>();
	        while ((inputLine = in.readLine()) != null)
	        {
	        	inputLines.add(inputLine);
	        	//String[] fields = inputLine.split(",");
	        	//for(int j = 0; j<fields.length;j++)
	        	//{
	        		//System.out.println("Array Element [" + j + "] " + fields[j]);
	        	//}
	            //int n = inputLine.indexOf("\"");
	            //String sub = inputLine.substring(n+1);
	            //int m = sub.indexOf("\"");
	            //String s = sub.substring(0, m);
	            //String[] fields = inputLine.split(",\"" + s + "\",", -1);
	            //System.out.println(s + ": " + fields[0] + "," + fields[1] + "," +
	            	//fields[2] + "," + fields[3]);
	        }
	        
		//}
	        return inputLines;
	}
}