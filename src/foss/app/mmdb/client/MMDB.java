package foss.app.mmdb.client;

import java.util.Collection;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import foss.app.mmdb.shared.MyMovieDTO;

public class MMDB implements EntryPoint {

	@UiField
	HTMLPanel html70sPanel;

	@UiField
	HTMLPanel html80sPanel;

	@UiField
	HTMLPanel html90sPanel;

	@UiField
	HTMLPanel html2000Panel;


	@UiField
	HTMLPanel html2010Panel;

	Label seenMovies;
	Label totalMovies;
	Label seenPercentage;
	
	RootLayoutPanel root;

	
	int totalWatched = 0;
	int totalMoviesInSystem=0;
	float percentage = 0;
	
	private final DataServiceAsync dataService = GWT
			.create(DataService.class);
	

	/**
	 * Gets the singleton application instance.
	 */
	

	interface MMDBUiBinder extends UiBinder<VerticalPanel, MMDB> {
	}

	private static final MMDBUiBinder binder = GWT
			.create(MMDBUiBinder.class);

	public void onModuleLoad() {
	
		seenMovies = new Label(); totalMovies = new Label();  seenPercentage = new  Label();
		
	
		VerticalPanel outer = binder.createAndBindUi(this);
//

 

		RootPanel rp = RootPanel.get("wh");
		 
	    clear(rp.getElement());
		
	    
	    
	 // Create a basic popup widget
	    final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
	    simplePopup.ensureDebugId("cwBasicPopup-simplePopup");
	    simplePopup.setWidth("500px");
	    simplePopup.setWidget(
        new HTML("Hi there, this project is still under development, and its purpose is to demonstrate the power of GWT. The sample code can be gotten from git-hub for learning purposes. Please visit www.byornjohn.com. Also take a break and find how many movies you have watched, by simply clicking on it. You will be able to find out how many great movies you have missed out on. Enjoy! watch some of these great movies.! .. Cheers"));
	    simplePopup.setAnimationEnabled(true);
	    simplePopup.setPopupPosition(700, 210);
	    simplePopup.show();
	    
	    
		
		RootPanel.get("appgoeshere").add(outer);
		
		
		 
		
		loadMovies(1,html70sPanel);
		loadMovies(2,html80sPanel);
		loadMovies(3,html90sPanel);
		loadMovies(4,html2000Panel);
		loadMovies(5,html2010Panel);
		//load80sMovies();
		//load90sMovies();
		//load2000Movies();
		//load2010Movies();
		
				
		
		
	}
	
	public void clear(Element parent)
	{
	        Element firstChild;
	        while((firstChild = DOM.getFirstChild(parent)) != null)
	        {
	                DOM.removeChild(parent, firstChild);
	        }
	}
	
	
	private void displayTotalMovies(){
		
		totalMovies.setText(String.valueOf(totalMoviesInSystem));
		RootPanel.get("totalMovies").clear();
		RootPanel.get("totalMovies").add(totalMovies);
		
		seenMovies.setText(String.valueOf(totalWatched));
		RootPanel.get("seenMovies").clear();
		RootPanel.get("seenMovies").add(seenMovies);
		
		percentage = ((float)totalWatched/totalMoviesInSystem)*100;
//		System.out.println(totalWatched);
//		System.out.println(totalMoviesInSystem);
//		System.out.println((float)totalWatched/totalMoviesInSystem*100);
//		System.out.println(percentage);
        seenPercentage.setText(String.valueOf(percentage)+"%");
		RootPanel.get("seenPercentage").clear();
		RootPanel.get("seenPercentage").add(seenPercentage);
		
		
	}
	
	
	private void processResults(Grid moviesGrid, MyMovieDTO movieDTO){
		Image image = (Image) moviesGrid.getWidget(movieDTO.getId(), 3);
	 	
		if(image.getUrl().contains("wrong")){
			image.setUrl("right.png");
			addToTotalWatched();
		}else{
			image.setUrl("wrong.png");
			removeFromTotalWatched();
		}
	 	
	}
	
	private void addToTotalWatched(){
		
		totalWatched++;
				
		displayTotalMovies();
		
	}
	
	
	private void removeFromTotalWatched(){
		
		totalWatched--;
		displayTotalMovies();
	}
	
		
	public void loadMovies(final int tabId,final HTMLPanel panelToAdd){
		
		dataService.fetchData(tabId,
				new AsyncCallback<Collection<MyMovieDTO>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Collection<MyMovieDTO> result) {
						// TODO Auto-generated method stub
						int rows = result.size();
						totalMoviesInSystem = totalMoviesInSystem + rows;
						displayTotalMovies();
						
						int cols = 4;
						
						int rowNum = 0;
						
						final Grid moviesGrid  = new Grid(++rows, cols);
						
						moviesGrid.setWidget(rowNum, 0, new Label("Rank"));
						moviesGrid.setWidget(rowNum, 1, new Label("Title"));
						moviesGrid.setWidget(rowNum, 2, new Label("Imdb Rating"));
						moviesGrid.setWidget(rowNum, 3, new Label("Seen It ?"));
				    
						
						rowNum = 1;
						for(final MyMovieDTO movieDTO : result){
					    
						
							
							final Label title = new Label();
							title.setText(movieDTO.getName());
							title.addMouseOverHandler(new MouseOverHandler() {
								
								@Override
								public void onMouseOver(MouseOverEvent event) {
									title.setStyleName("mystyle");
								
									
								}
							});
							
							title.addMouseOutHandler(new MouseOutHandler() {
								
								@Override
								public void onMouseOut(MouseOutEvent event) {
									title.removeStyleName("mystyle");	
								
									
								}
							});
							
							title.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									
									processResults(moviesGrid,movieDTO);
								
								}
							});
							
							final Image image = new Image("wrong.png");
							
							image.setPixelSize(15, 15);
							
							
							image.addClickHandler(new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									
									processResults(moviesGrid,movieDTO);
								
								}
							});
							
							
							
							movieDTO.setId(rowNum);
							moviesGrid.setWidget(rowNum, 0, new Label(movieDTO.getRank()));
							moviesGrid.setWidget(rowNum, 1, title);
							moviesGrid.setWidget(rowNum, 2, new Label(movieDTO.getImdbRating()));
							
							moviesGrid.setWidget(rowNum, 3,image);
							
							
							rowNum++;
						}
						panelToAdd.clear();
						panelToAdd.add(moviesGrid);
					}

				});

	}
	
	
	
	

}
