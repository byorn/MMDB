package foss.app.mmdb.client;

import java.util.Collection;

import com.google.gwt.user.client.rpc.AsyncCallback;

import foss.app.mmdb.shared.MyMovieDTO;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface DataServiceAsync {
	void fetchData(int tabId, AsyncCallback<Collection<MyMovieDTO>> callback);
	
	public  void getData(AsyncCallback<String> callback);
}
