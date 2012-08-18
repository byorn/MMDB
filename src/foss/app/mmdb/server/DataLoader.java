package foss.app.mmdb.server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Node;

import foss.app.mmdb.server.generated.Movies;







public class DataLoader {
	
	
	public static String getWebPageData(){
		
		StringBuilder sb = new StringBuilder();
		
		try{
			
			URL oracle = new URL("http://10.1.1.3/XMLGenerater/movies/classes/tabledata.php");
	        URLConnection yc = oracle.openConnection();
	        yc.setDoOutput(true);
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                                    yc.getInputStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) {
	        	sb.append(inputLine + "\n");
	        	System.out.println(inputLine);
	        }
	        in.close();
	        
			}catch(Throwable e){
				e.printStackTrace();
				System.out.println(e.getStackTrace());
			}
			
		return sb.toString();
		
	}
	
	

	
	public static List<Movies.Movie> loadData(int tabId) throws JAXBException, IOException{
		
		StringBuilder sb = new StringBuilder();
		
		URL oracle = new URL("http://webservice.byornjohn.com/movies/classes/tabledata.php?range=" + tabId);
        URLConnection yc = oracle.openConnection();
        yc.setDoOutput(true);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    yc.getInputStream()));
        String inputLine;
        
        while ((inputLine = in.readLine()) != null) {
        	
        	if(inputLine.startsWith("<?xml version=\"1.0\"?>")){
        		sb.append(inputLine + "\n");
        	}
        	
        }
        in.close();
		
        ByteArrayInputStream input = new ByteArrayInputStream (sb.toString().getBytes());

		
		JAXBContext jc = JAXBContext.newInstance("foss.app.mmdb.server.generated");
		
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		
		Movies movies = (Movies) unmarshaller.unmarshal(input);
		
		List<Movies.Movie> list  = movies.getMovie();
		
		return list;
		
	
		
		
		
	}
	
}
