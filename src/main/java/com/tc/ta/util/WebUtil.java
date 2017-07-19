package com.tc.ta.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebUtil {
	public static String getWebContent(String url) {
		StringBuilder sb = new StringBuilder();

		try {
			URL urlObj = new URL(url);
			HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		System.out
				.println(getWebContent("http://api.map.baidu.com/direction/v1?mode=walking&origin=30.3125000000,120.0847220000&destination=30.379000000,120.0847220000&region=%E6%9D%AD%E5%B7%9E&output=xml&ak=9284f3663c9e165c75a7812eab665734"));
	}
}
