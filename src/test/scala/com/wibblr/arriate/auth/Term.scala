package com.wibblr.arriate.auth;
import java.net.URL;

import org.junit.Test;

class Term {
	val scheme = "http";
	val provider = "term.ie"
	val pathPrefix = "/oauth/example"
	
	def callGetMethod(path: String): Array[Byte] = {
		val url = new URL(scheme + "://" + provider + pathPrefix + path)
		val con = url.openConnection().asInstanceOf[HttpURLConnection]
		con.setRequestMethod("GET")
		
		val oauth: OAuth10 = new OAuth10(provider, new TokenStorage(provider), new NullAuthorizer())
		
		if (!oauth.isAuthorized()) {
			oauth.authorize();
		}
		
		oauth.signRequest(con);
	}
	
	@Test
	def echo() {
		Assert.assertArrayEquals("a=b&c=d".getBytes, callGetMethod("/echo_api.php?a=b&c=d"));
	}
}