package foss.app.mmdb.client;

import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import foss.app.mmdb.shared.MyMovieDTO;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("fetchdata")
public interface DataService extends RemoteService {
	Collection<MyMovieDTO> fetchData(int tabId);

	String getData();
	
	
}
