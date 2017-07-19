package com.tc.ta.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UrlUtil {

	/**
	 * 构建完整的请求地址，没有加请求协议的默认为http://
	 * 
	 * @author zy.wu
	 * @param url
	 * @return
	 */
	public static String buildFullUrl(String url) {
		if (StringUtil.isEmpty(url)) {
			return "";
		}
		String regex = "^(\\w+:{1}\\/\\/).*$";
		if (url.matches(regex)) {
			return url;
		} else {
			return "http://" + url;
		}
	}

	/**
	 * 通过API方式查询快递信息
	 * 
	 * @param id
	 * @param com
	 * @param no
	 * @return
	 */
	public static String getLogisticsInfo(String id, String com, String no) {
		try {
			URL url = new URL("http://api.kuaidi100.com/api?id=" + id + "&com=" + com + "&nu=" + no + "&show=0&muti=1&order=desc");
			URLConnection con = url.openConnection();
			con.setAllowUserInteraction(false);
			InputStream urlStream = url.openStream();
			String type = con.guessContentTypeFromStream(urlStream);
			String charSet = null;
			if (type == null)
				type = con.getContentType();

			if (type == null || type.trim().length() == 0 || type.trim().indexOf("text/html") < 0)
				return "";

			if (type.indexOf("charset=") > 0)
				charSet = type.substring(type.indexOf("charset=") + 8);

			byte b[] = new byte[10000];
			int numRead = urlStream.read(b);
			// String content = new String(b, 0, numRead);
			String content = new String(b, 0, numRead, "UTF-8");
			while (numRead != -1) {
				numRead = urlStream.read(b);
				if (numRead != -1) {
					// String newContent = new String(b, 0, numRead);
					String newContent = new String(b, 0, numRead, charSet);
					content += newContent;
				}
			}
			System.out.println("content:" + content);
			urlStream.close();
			return content;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
