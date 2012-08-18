package foss.app.mmdb.shared;


import java.io.Serializable;

import com.google.gwt.view.client.ProvidesKey;

public class MyMovieDTO implements  Comparable<MyMovieDTO>, Serializable{

	public static final ProvidesKey<MyMovieDTO> KEY_PROVIDER = new ProvidesKey<MyMovieDTO>() {
	      public Object getKey(MyMovieDTO item) {
	        return item == null ? null : item.getId();
	      }
	    };
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int  id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getiHaveWatchedIt() {
		return iHaveWatchedIt;
	}
	public void setiHaveWatchedIt(String iHaveWatchedIt) {
		this.iHaveWatchedIt = iHaveWatchedIt;
	}
	public String getiHaveACopy() {
		return iHaveACopy;
	}
	public void setiHaveACopy(String iHaveACopy) {
		this.iHaveACopy = iHaveACopy;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	
	private int id;
	private String name;
	private String year;
	private String imdbRating;
	private String genre;
	private String notes;
	private String iHaveWatchedIt;
	private String iHaveACopy;
	private String rank;

	
	public static final String yes = "y";
	public static final String no = "n";



	@Override
	public int compareTo(MyMovieDTO o) {
		 return (o == null || o.name == null) ? -1
		          : -o.name.compareTo(name);
	}
			

	
	
}
