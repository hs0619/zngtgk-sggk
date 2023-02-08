package com.baosight.sggk.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpServiceUtil {

	public static String insureResponsePost(String url, String param) {
		PrintWriter out = null;
		InputStream is = null;
		BufferedReader br = null;
		String result = "";
		HttpURLConnection conn = null;
		StringBuffer strBuffer = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(20000);
			conn.setReadTimeout(300000);
			conn.setRequestProperty("Charset", "UTF-8");
			//conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Content-Encoding", "utf-8");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);

			// flush输出流的缓冲
			out.flush();
			is = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null) {

				strBuffer.append(line);
			}
			result = strBuffer.toString();
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (br != null) {
					br.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	public static String insurePost(String postURL, String code ,String  begin,String end) {
		try {
		    String url;
		    PostMethod postMethod = null;
		    postMethod = new PostMethod(postURL) ;
		    postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8") ;
		    //参数设置，需要注意的就是里边不能传NULL，要传空字符串
		    NameValuePair[] data = {
		            new NameValuePair("proc_code",code),
		            new NameValuePair("begin_date",begin),
		            new NameValuePair("end_date",end)
		    };

		    postMethod.setRequestBody(data);

		    org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
		    int response = httpClient.executeMethod(postMethod); // 执行POST方法
		    String result = postMethod.getResponseBodyAsString() ;

		    return result;
		} catch (Exception e) {
		   
		    throw new RuntimeException(e.getMessage());
		}
	}
	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("MAT_ID", "H41Q235B-73");
		Object jsonObjectMat = JSONObject.toJSON(map);
		String matBuffer = HttpServiceUtil.insureResponsePost("http://127.0.0.1:8080/mhrzhb/service/S_DU_31",
				jsonObjectMat.toString());
		// 获取数据
		JSONObject obj1 = JSONObject.parseObject(matBuffer);



	}

}
