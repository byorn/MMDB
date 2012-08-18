package foss.app.mmdb.server;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import foss.app.mmdb.client.DataService;
import foss.app.mmdb.server.generated.Movies;
import foss.app.mmdb.shared.MyMovieDTO;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DataServiceImpl extends RemoteServiceServlet implements
		DataService {
	
	
	public List<MyMovieDTO> fetchData(int tabId){
		
		List<MyMovieDTO> websiteMovies = new ArrayList<MyMovieDTO>();
		try {
			
			
		
			List<Movies.Movie> movies = DataLoader.loadData(tabId);
			
			for(Movies.Movie movie:movies){
				MyMovieDTO websiteMovie = new MyMovieDTO();
				websiteMovie.setId(movie.getId());
				websiteMovie.setRank(String.valueOf(movie.getRank()));
				websiteMovie.setName(movie.getTitle());
				websiteMovie.setImdbRating(String.valueOf(movie.getRating()));
				websiteMovie.setYear(movie.getYear());
				
				websiteMovies.add(websiteMovie);
			}
			
			
			
			
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Throwable e){
			e.printStackTrace();
		}
		
		System.out.println("service called");
		
		return websiteMovies;
	}


	public String getData() {

			return DataLoader.getWebPageData();
	
	}


}
