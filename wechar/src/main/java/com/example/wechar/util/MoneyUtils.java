package com.example.wechar.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.security.KeyStore;

public class MoneyUtils {
	
	final static String KEYSTORE_FILE = PropertiesUtil.get("KEYSTORE_FILE");
	final static String KEYSTORE_PASSWORD =  PropertiesUtil.get("MCHID");
	
	public static String doSendMoney(String url, String data) throws Exception {

		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		try {
			keyStore.load(new FileInputStream(KEYSTORE_FILE), KEYSTORE_PASSWORD.toCharArray());
		} finally {

		}

		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, KEYSTORE_PASSWORD.toCharArray()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		try {
			HttpPost httpost = new HttpPost(url); // 设置响应头信息
			httpost.addHeader("Connection", "keep-alive");
			httpost.addHeader("Accept", "*/*");
			httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			httpost.addHeader("Host", "api.mch.weixin.qq.com");
			httpost.addHeader("X-Requested-With", "XMLHttpRequest");
			httpost.addHeader("Cache-Control", "max-age=0");
			httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
			httpost.setEntity(new StringEntity(data, "UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httpost);

			try {
				HttpEntity entity = response.getEntity();
				String jsonStr = toStringInfo(entity, "UTF-8");

				System.err.println(jsonStr);
				//微信返回的报文时GBK，直接使用httpcore解析乱码
				EntityUtils.consume(entity);
				return jsonStr;
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}
	
	private static String toStringInfo(HttpEntity entity, String defaultCharset) throws Exception{
		final InputStream instream = entity.getContent();

	    if (instream == null) {
	        return null;
	    }
	    try {
	        Args.check(entity.getContentLength() <= Integer.MAX_VALUE,
	                "HTTP entity too large to be buffered in memory");
	        int i = (int)entity.getContentLength();
	        if (i < 0) {
	            i = 4096;
	        }
	        Charset charset = null;
	        
	        if (charset == null) {
	            charset = Charset.forName(defaultCharset);
	        }
	        if (charset == null) {
	            charset = HTTP.DEF_CONTENT_CHARSET;
	        }
	        final Reader reader = new InputStreamReader(instream, charset);
	        final CharArrayBuffer buffer = new CharArrayBuffer(i);
	        final char[] tmp = new char[1024];
	        int l;
	        while((l = reader.read(tmp)) != -1) {
	            buffer.append(tmp, 0, l);
	        }
	        return buffer.toString();
	    } finally {
	        instream.close();
	    }
	}
}
