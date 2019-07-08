package com.ssm.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.ssl.internal.www.protocol.https.Handler;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/6/4.
 */
public class SystemUtil {

    public static String reloadNgx() {
        try {
            ProcessBuilder pb = new ProcessBuilder("/opt/pro/nginx/sbin/nginx", "-s", "reload");
            int runningStatus = 0;
            try {
                Process p = pb.start();
                try {
                    runningStatus = p.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (runningStatus != 0) {

            }

            final Process p = pb.start();
            InputStream inputStream = p.getInputStream();

            return SystemUtil.inputStreamToStr(inputStream);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String executeCmd(String cmd) {
        Runtime run = Runtime.getRuntime();
        try {
            Process process = run.exec(cmd);
            InputStream in = process.getInputStream();
            return SystemUtil.inputStreamToStr(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根椐网址得到返回的字符串</br>
     *
     * @param url 传入的网址
     */
    public static String responseText(String url, Map<String, String> args) {

        StringBuffer ret = new StringBuffer();

        try {

            InputStream inputStream = getInputStream(url, args);
            if (inputStream != null) {
                BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;

                while ((line = buf.readLine()) != null) {
                    ret.append(line);
                }

            }
            return ret.toString();

        } catch (Exception e) {
            System.err.println(url);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根椐网址得到返回的字符串</br>
     *
     * @param url 传入的网址
     */
    public static String responseText(String url) {
        return responseText(url, null);
    }

    /**
     * 返回网页的bytes,传MAP类型的参数
     *
     * @param uri
     * @param args
     */
    public static InputStream getInputStream(String uri, Map<String, String> args) {
        try {
            String host = PropertiesUtil.get("auth.host");
            HttpURLConnection conn;
            URL url = new URL(null, uri, new Handler());
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setRequestProperty("host", host);

            conn.setUseCaches(false);
            conn.setConnectTimeout(10 * 1000);

            if (args != null) {
                Set<String> key = args.keySet();
                for (String k : key) {
                    conn.setRequestProperty(k, args.get(k));
                }
            }

            InputStream inputStream = conn.getInputStream();
            return inputStream;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回网页的bytes
     */
    public static InputStream getInputStream(String urlStr, String requestMethod) {
        return getInputStream(urlStr, null, requestMethod);
    }

    /**
     * 返回网页的bytes,传MAP类型的参数
     *
     * @param urlStr 清求的地址
     * @data 转入的参数
     */
    public static InputStream getInputStream(String urlStr, String data, String requestMethod) {
        try {
            HttpURLConnection conn = null;
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(requestMethod);

            conn.setUseCaches(false);
            conn.setConnectTimeout(10 * 1000);
            if (data != null) {
                OutputStream os = conn.getOutputStream();
                os.write(data.getBytes("UTF-8"));
            }
            return conn.getInputStream();
        } catch (Exception e) {
            System.err.println(urlStr);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param fileName
     * @return
     */
    public static StringBuffer readFile(String fileName) {
        StringBuffer item = new StringBuffer();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8"); //或GB2312,GB18030
            reader = new BufferedReader(isr);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                item.append(tempString);
            }
            reader.close();
            return item;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {

                }
            }
        }
    }

    public static <E> E fromJson(String json, Class<E> cls) {
        try {

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            return gson.fromJson(json, cls);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     *  @param fileName
    * @return
    */
    public static KeyStore readFile(String fileName, String key) {

        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            File file = new File(fileName);
            FileInputStream instream = new FileInputStream(file);

            keyStore.load(instream, key.toCharArray());
            return keyStore;
        } catch (KeyStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (CertificateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存文件
     *
     * @param content
     * @param path
     * @param fileName
     * @return
     */
    public static String saveFile(String content, String path, String fileName) {
        try {
            File file = new File(path);
            if (!file.exists()) file.mkdirs();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + "/" + fileName), "UTF-8"));
            bw.write(content);
            bw.flush();
            bw.close();
            return path;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 删除文件
     *
     * @param folderPath
     * @return
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            File myFilePath = new File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存图片
     *
     * @param inputStream
     * @param savePath
     * @return
     */
    public static String saveFile(InputStream inputStream, String savePath, String fileName) {
        FileOutputStream out = null;

        try {

            File file = new File(savePath);
            if (!file.exists()) file.mkdirs();
            out = new FileOutputStream(file + "/" + fileName);
            int i = 0;
            while ((i = inputStream.read()) != -1) {
                out.write(i);
            }
            out.flush();
            out.close();
            System.err.println("SystemUtil save 打印 " + savePath);
            return savePath;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 返回两个时间相差的秒数
     *
     * @param begin 较早的时间
     * @param end   较晚的时间
     */
    public static long distanceTime(String begin, String end) {
        try {
            if (begin == null) begin = end;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date bt = sdf.parse(begin);
            Date et = sdf.parse(end);
            long diff = et.getTime() - bt.getTime();
            return diff / 1000;

        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }

    }

    /**
     * 得到系统的当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String currentTime() {
        return currentTime(0);
    }

    /**
     * 得到系统的当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String currentTime(int second) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        int currentSecond = cal.get(Calendar.SECOND);
        cal.set(Calendar.SECOND, currentSecond + second);
        return sdf.format(cal.getTime());
    }


    public static String inputStreamToStr(InputStream inputStream) {
        try {
            BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String line = null;
            StringBuffer ret = new StringBuffer();
            while ((line = buf.readLine()) != null) {
                if (line == " ") {
                }
                ret.append(line);
            }

            return ret.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * List排序
     *
     * @param signParem
     */
    public static String sortList(List<String> signParem) {

        Collections.sort(signParem, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        StringBuffer keyword = new StringBuffer();
        Iterator<String> iter = signParem.iterator();
        while (iter.hasNext()) {
            keyword.append(iter.next());
        }
        return keyword.toString();
    }

    /**
     * SHA1加密
     *
     * @param decript：字符串
     */
    public static String SHA1(String decript) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            if (decript.startsWith("&"))
                decript = decript.substring(1);

            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Map<String, Object> xmlToMap(String xml) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            Document doc = DocumentHelper.parseText(xml);
            org.dom4j.Element root = doc.getRootElement();

            if (root == null)
                return map;

            for (Iterator iterator = root.elementIterator(); iterator.hasNext(); ) {
                org.dom4j.Element e = (org.dom4j.Element) iterator.next();
                List list = e.elements();
                if (list.size() > 0) {
                    map.put(e.getName(), Dom2Map(e));
                } else
                    map.put(e.getName(), e.getText());
            }
            return map;
        } catch (DocumentException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Map Dom2Map(org.dom4j.Element e) {
        Map map = new HashMap();
        List list = e.elements();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                org.dom4j.Element iter = (org.dom4j.Element) list.get(i);
                List mapList = new ArrayList();

                if (iter.elements().size() > 0) {
                    Map m = Dom2Map(iter);
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if (obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), m);
                } else {
                    if (map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if (!obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if (obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    } else
                        map.put(iter.getName(), iter.getText());
                }
            }
        } else
            map.put(e.getName(), e.getText());
        return map;
    }

    public static String readXml(String xml, String path) {
        try {
            Document doc = DocumentHelper.parseText(xml);
            org.dom4j.Element root = doc.getRootElement();
            org.dom4j.Element e = (org.dom4j.Element) root.selectSingleNode(path);
            return e.getText();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    public static String mapToUri(Map<String, Object> map) {
        StringBuffer xmlTemp = new StringBuffer();
        if (map != null) {
            List<String> mapKey = new ArrayList<>(map.keySet());
            Collections.sort(mapKey);

            Iterator<String> iter = mapKey.iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                xmlTemp.append("&" + key + "=" + map.get(key));
            }
        }

        return xmlTemp.substring(1);
    }

    /**
     * map转面XML
     *
     * @param map
     * @return
     */
    public static String createXML(Map<String, Object> map) {
        String xml = "<xml>";
        Set<String> set = map.keySet();
        Iterator<String> i = set.iterator();
        while (i.hasNext()) {
            String str = i.next();
            xml += "<" + str + ">" + "<![CDATA[" + map.get(str) + "]]>" + "</" + str + ">";
        }
        xml += "</xml>";
        return xml;
    }

    public static <T> String toJson(Object obj) {
        return toJson(obj, false);
    }

    public static <T> String toJson(Object obj, boolean disable) {
        GsonBuilder builder = new GsonBuilder();
        if (disable)
            builder.disableHtmlEscaping();
        Gson gson = builder.create();
        return gson.toJson(obj);
    }

    // 判断字符串是否base64编码
    public static boolean isBase64(String str) {
//        String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
//        return Pattern.matches(base64Pattern, str);
            if (str == null || str.trim().length() == 0) {
                return false;
            } else {
                if (str.length() % 4 != 0) {
                    return false;
                }

                char[] strChars = str.toCharArray();
                for (char c:strChars) {
                    if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')
                            || c == '+' || c == '/' || c == '=') {
                        continue;
                    } else {
                        return false;
                    }
                }
                return true;
            }
    }

    /**
     *
     * @param key
     * @param content
     * @return
     */
    public static String searchMqtt(String key, String content) {
        //
        String rex = "\"" + key + "\":\"(.*?)\"";
        // 匹配的模式
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
