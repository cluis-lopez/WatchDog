package com.clopez.watchdog;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.com.google.common.io.CharStreams;
import com.pusher.rest.Pusher;


/**
 * Servlet implementation class Auth
 */
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    // Instantiate a pusher connection
	    Pusher pusher = PusherService.getDefaultInstance();
	    // Get current logged in user credentials
	    String query = CharStreams.toString(request.getReader());
	    // socket_id, channel_name parameters are automatically set in the POST body of the request
	    // eg.socket_id=1232.12_name=presence-my-channel
	    Map data = splitQuery(query);
	    String socketId = (String) data.get("socket_id");
	    String channelId = (String) data.get("channel_name");


	    // Inject custom authentication code for your application here to allow/deny current request

	    String auth =
	        pusher.authenticate(socketId, channelId);
	    
	    // if successful, returns authorization in the format
	    //    {
	    //      "auth":"49e26cb8e9dde3dfc009:a8cf1d3deefbb1bdc6a9d1547640d49d94b4b512320e2597c257a740edd1788f",
	    //      "channel_data":"{\"user_id\":\"23423435252\",\"user_info\":{\"displayName\":\"John Doe\"}}"
	    //    }

	    response.getWriter().append(auth);
	  }

	  private static Map splitQuery(String query) throws UnsupportedEncodingException {
	    Map query_pairs = new HashMap<>();
	    String[] pairs = query.split("&");
	    for (String pair : pairs) {
	      int idx = pair.indexOf("=");
	      query_pairs.put(
	          URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
	          URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
	    }
	    return query_pairs;
	  }

}
