package com.wibblr.arriate.osm.api06;
import java.net.URL;
class Api06(provider: String, oauth: OAuth10) {
	val pathPrefix = "/api/0.6"
	
	def callGetMethod(path: String): InputStream = {
		val url = new URL(scheme + "://" + provider + pathPrefix + path)
		val con = url.openConnection().asInstanceOf[HttpURLConnection]
		con.setRequestMethod("GET")
		
		if (!oauth.isAuthorized()) {
			oauth.authorize()
		}
		
		oauth.signRequest(con);
	}
	
		UserDetails.deserialize(callGetMethod("/user/details"));  
	}

}