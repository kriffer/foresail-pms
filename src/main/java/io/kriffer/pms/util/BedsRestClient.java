/*
 * MIT License
 *
 * Copyright (c) 2021 Anton Kravets (kriffer.io)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.kriffer.pms.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BedsRestClient {

	String API_URL = "https://api.beds24.com/json/";
	String apiKey;
	String propKey;

	public BedsRestClient(String apiKey, String propKey) {
		this.apiKey = apiKey;
		this.propKey = propKey;
	}

	public Map<String, Map<String, String>> auth() {
		Map<String, Map<String, String>> authRequest = new HashMap<>();
		Map<String, String> auth = new HashMap();
		auth.put("apiKey", apiKey);
		auth.put("propKey", propKey);
		authRequest.put("authentication", auth);
		return authRequest;
	}


	public String postRequest(String url, String request) throws IOException {
		HttpPost post = new HttpPost(url);

		String result = "";
		post.setEntity(new StringEntity(request.toString()));
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
		     CloseableHttpResponse res = httpClient.execute(post)) {

			result = EntityUtils.toString(res.getEntity());

		}
		return result;
	}

	public List<Map<String, String>> getBookings() throws IOException {
		String request = "";
		String result;
		List<Map<String, String>> response;

		ObjectMapper mapper = new ObjectMapper();

		request = auth().toString();

		result = postRequest(API_URL + "getBookings", mapper.writeValueAsString(request));

		ObjectMapper mapper2 = new ObjectMapper();
		response = mapper2.readValue(result, List.class);
		return response;
	}

	public void setBooking() {

	}

//	public static void main(String[] args) throws IOException {
//		System.out.println(new BedsRestClient("3010122030101220", "3010122030101220").getBookings());
//	}
}
