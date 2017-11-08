package com.clopez.watchdog;

import com.pusher.rest.Pusher;

public abstract class PusherService {
	public static final String APP_KEY = System.getenv("PUSHER_APP_KEY");
	  public static final String CLUSTER = System.getenv("PUSHER_CLUSTER");

	  private static final String APP_ID = System.getenv("PUSHER_APP_ID");
	  private static final String APP_SECRET = System.getenv("PUSHER_APP_SECRET");

	  private static Pusher instance;

	  static Pusher getDefaultInstance() {
	    if (instance != null) {
	      return instance;
	    } // Instantiate a pusher
	    Pusher pusher = new Pusher(APP_ID, APP_KEY, APP_SECRET);
	    pusher.setCluster(CLUSTER); // required, if not default mt1 (us-east-1)
	    pusher.setEncrypted(true); // optional, ensure subscriber also matches these settings
	    instance = pusher;
	    return pusher;
	  }
}
