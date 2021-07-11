package org.dean;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.GzipCompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

public class LocalHttpRequest {

/*
    public static void main(String[] args) throws Exception {
        String localUrl = "http://127.0.0.1:8808/";
        String responseString = LocalHttpRequest.request(localUrl);
        System.out.println("url: " + localUrl + ";\n response: " + responseString);
        String localTestUrl = "http://127.0.0.1:8808/test";
        String testResponse = LocalHttpRequest.request(localTestUrl);
        System.out.println("url: " + localTestUrl + ";\n response: " + testResponse);
    }
*/
    public static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static String request(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } finally {
            if (null != response) {
                response.close();
            }
        }
    }

}

