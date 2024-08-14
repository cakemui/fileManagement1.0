package com.system.xysmartassistants.utils;

import lombok.Data;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 爬虫工具类
 */
@Component
@Data
public class ClientSpiderUtil {
    private static String html = "";

    /**
     * 页面爬取
     *
     * @param clientURL 页面地址
     * @throws IOException
     */
    public void clientSpider(String clientURL) throws IOException {
        //1.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = null;

        //2. 创建HttpGet请求，并进行相关设置
        HttpGet httpGet = new HttpGet(clientURL);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Mobile Safari/537.36 Edg/85.0.564.68");
        try {
            //3.发起请求
            response = httpClient.execute(httpGet);

            //4.判断响应状态码并获取响应数据
            if (response.getStatusLine().getStatusCode() == 200) { //200表示响应成功
                System.out.println("获取页面成功");
                html = EntityUtils.toString(response.getEntity(), "UTF-8");
            } else {
                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            httpClient.close();
            response.close();
        }
        jsoupSpider(html);

    }

    /**
     * 页面解析
     * @param html
     */
    //选择器查找元素
    private String clientElements = "title";
    //Selector选择器组合查找参数
    private String clientSelector = "*";
    public void jsoupSpider(String html){
        //6.Jsoup解析html
        Document document = Jsoup.parse(html);
        System.out.println(document.getElementsByTag(clientElements).first());
        System.out.println(document.select(clientSelector));
    }
}
