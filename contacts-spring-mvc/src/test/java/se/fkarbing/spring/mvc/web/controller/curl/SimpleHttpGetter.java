package se.fkarbing.spring.mvc.web.controller.curl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class SimpleHttpGetter {

	// Spring mvc
	public final String contacts_spring_mvc_contextPath = "http://localhost:7778/contacts-spring-mvc";

	// Riak mvc
	public final String contacts_riak_contextPath = "http://localhost:8098/riak";


	@Test
	public void doAppGetForLorcations() throws Exception {
		String data = getResource(contacts_spring_mvc_contextPath, "locations");
		System.out.println(data);
	}


	@Test
	public void doRiakGetBuckets() throws Exception {
		String data = getResource(contacts_riak_contextPath, "?buckets=true");
		System.out.println(data);
	}


	public static String getResource(String contextPath, String resourcePath) throws Exception {

		URL serverAddress;
		HttpURLConnection openConnection = null;

		try {
			serverAddress = new URL(String.format("%s/%s", contextPath, resourcePath));
			openConnection = connect(serverAddress);
			return readData(openConnection).toString();
		} finally {
			// close the connection, set all objects to null
			if (openConnection != null) {
				openConnection.disconnect();
				openConnection = null;
			}
		}
	}


	private static StringBuilder readData(HttpURLConnection openConnection) throws Exception {
		BufferedReader rd = null;
		StringBuilder sb = null;
		try {
			rd = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
			sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line + '\n');
			}
		} finally {
			rd.close();
			rd = null;
		}
		return sb;

	}


	private static HttpURLConnection connect(URL serverAddress) throws Exception {
		// / Set up the initial connection
		HttpURLConnection connection = (HttpURLConnection) serverAddress.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		connection.setReadTimeout(10000);
		connection.connect();
		return connection;
	}

}
