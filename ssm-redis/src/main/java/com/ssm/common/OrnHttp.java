package com.ssm.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 项目名:      parent
 * 包名:        com.ssm.common
 * 创建时间:    2019/3/27 08:55
 *  原生http请求
 * @author: Bobo_Yu
 * 描述:
 */
public class OrnHttp {

    public static void main(String[] args) throws Exception {
        String urlPath  = "http://v.juhe.cn/dream/category";
        String param = "key=" + URLEncoder.encode("059a9308bf857974b0fa79fe5fa171a4", "UTF-8");

        //建立连接
        URL url=new URL(urlPath);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

        //设置参数
        httpConn.setDoOutput(true);     //需要输出
        httpConn.setDoInput(true);      //需要输入
        httpConn.setUseCaches(false);   //不允许缓存
//        httpConn.setRequestMethod("POST");      //设置POST方式连接
        httpConn.setRequestMethod("GET");      //设置POST方式连接

        //设置请求属性
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
        httpConn.setRequestProperty("Charset", "UTF-8");

        //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
        httpConn.connect();
        //建立输入流，向指向的URL传入参数
        DataOutputStream dos=new DataOutputStream(httpConn.getOutputStream());
        dos.writeBytes(param);
        dos.flush();
        dos.close();

        //获得响应状态
        int resultCode=httpConn.getResponseCode();
        if(HttpURLConnection.HTTP_OK==resultCode){
            StringBuffer sb=new StringBuffer();
            String readLine=new String();
            BufferedReader responseReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
            while((readLine=responseReader.readLine())!=null){
                sb.append(readLine).append("\n");
            }
            responseReader.close();
            System.out.println(sb.toString());
        }
    }


}
