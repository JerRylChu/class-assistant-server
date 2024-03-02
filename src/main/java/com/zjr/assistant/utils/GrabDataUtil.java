package com.zjr.assistant.utils;


import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GrabDataUtil {
    public static void main(String[] args) {
//        try {
//            System.out.println(getURLInfo("https://cas.hyit.edu.cn/cas/login","1171325110","ZJR@1999&cnz"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(Md5Util.getString("123456"));
//        Boolean a = new Boolean(true);
        int length = "http://localhost:8081/dev-api/file/avatarDownload/1621407597054.jpg".split("/").length;
        System.out.println(length);
//        Boolean b = new Boolean(true);
//        System.out.println(a==b);
    }
    public static String getURLInfo(String URL, String username, String password) throws Exception {
        HttpClient httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
        HttpPost httpPost = new HttpPost(URL);
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("username",username));
        nameValuePairs.add(new BasicNameValuePair("password",password));
        nameValuePairs.add(new BasicNameValuePair("errors","0"));
        nameValuePairs.add(new BasicNameValuePair("imageCodeName",null));
        nameValuePairs.add(new BasicNameValuePair("lt","_c7B32794F-ED74-5212-B646-B73C37778C48_kE95D3F4F-36D9-B754-4E3F-D802444D6F93"));

        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8));
        HttpResponse execute;
        try {
            execute = httpClient.execute(httpPost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return "错误";
        } catch (IOException e) {
            e.printStackTrace();
            return "错误";
        }
        return "成功";
    }


}
