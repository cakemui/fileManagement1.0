package com.system.xysmartassistants.utils;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.*;

@Component
@Data
public class DingtalkRobot {

    /**
     * 用来存储机器人的地址(配置此项可改变发消息的机器人)
     */
    public static String CUSTOM_ROBOT_TOKEN = "fd29d4678ed8f2405611051f3625a918b99193090af316ba6222a0f1d4e7a40f";

    /**
     * 与机器人地址对应的密钥(此数据来自钉钉机器人的设置)
     */
    public static String SECRET = "SEC458f419c540028d1a77c1873d13dc9282d3bf39e724de1e670b2e93087884464";

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(DingtalkRobot.class);

    /**
     * 暂存标志位
     */
    private static int FLAG = 0;

    /**
     * 机器人发送text消息类型(*为必填)
     *
     * @param msg       *要发送的消息内容
     * @param atUserIds 被at的人的钉钉id
     * @param atMobiles 被at的人的钉钉绑定手机号(一般只用这个，且只有在群内的成员才可被@，非群内成员手机号会被脱敏)
     * @param isAtAll   是否at全体成员
     * @return
     */
    public static final String textMsg( String msg, List<String> atUserIds, List<String> atMobiles, Boolean isAtAll) throws InterruptedException {
        //获取链接
        DingTalkClient client = DingtalkRobot.getClientURL(SECRET);
        //创建消息体
        OapiRobotSendRequest req = new OapiRobotSendRequest();
        try {
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();//设置消息内容
            if (msg != null) {
                text.setContent(msg);//文本内容
            }
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();//设置at内容
            if (atUserIds != null) {
                at.setAtUserIds(atUserIds);//被at人uid
            }
            if (atMobiles != null) {
                at.setAtMobiles(atMobiles);//被at人手机号
            }
            if (isAtAll != null) {
                at.setIsAtAll(isAtAll);//是否at全体成员
            }else {
                at.setIsAtAll(false);//默认为否
            }
            //组装消息
            req.setMsgtype("text");//设置类型
            req.setText(text);
            req.setAt(at);
            OapiRobotSendResponse rsp = client.execute(req, CUSTOM_ROBOT_TOKEN);//加密消息体
            logger.info("发送消息:" + rsp.getBody());//发送并打印内容
        }catch (Exception e){
            logger.error("发送机器人消息失败！", e);
            FLAG++;
            if (FLAG < 5) {
                logger.info("正在重新尝试....");
                Thread.sleep(5000);
                DingtalkRobot.textMsg(msg, atUserIds, atMobiles, isAtAll);
            }else {
                logger.info("已超出尝试次数!请重新尝试发送！");
            }
            FLAG = 0;//重置标志位
        }
        return null;
    }

    /**
     * 机器人发送link消息类型(*为必填)
     *
     * @param title         *标题内容
     * @param text          *文本内容
     * @param picUrl        图片url
     * @param messageUrl    *链接地址url
     * @return
     */
    public static final String linkMsg(String title, String text, String picUrl, String messageUrl) throws InterruptedException {
        //获取链接
        DingTalkClient client = DingtalkRobot.getClientURL(SECRET);
        //创建消息体
        OapiRobotSendRequest req = new OapiRobotSendRequest();
        try {
            OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();//设置消息内容
            if (title != null) {
                link.setTitle(title);//设置标题
            }
            if (text != null) {
                link.setText(text);//设置文本内容
            }
            if (picUrl != null) {
                link.setPicUrl(picUrl);//设置图片链接
            }
            if (messageUrl != null) {
                link.setMessageUrl(messageUrl);//设置跳转链接
            }
            //组装消息
            req.setMsgtype("link");//设置类型
            req.setLink(link);
            OapiRobotSendResponse rsp = client.execute(req, CUSTOM_ROBOT_TOKEN);//加密消息体
            logger.info("发送消息:" + rsp.getBody());//发送并打印内容
        }catch (Exception e){
            logger.error("发送机器人消息失败！", e);
            FLAG++;
            if (FLAG < 5) {
                logger.info("正在重新尝试....");
                Thread.sleep(5000);
                DingtalkRobot.linkMsg(title, text, picUrl, messageUrl);
            }else {
                logger.info("已超出尝试次数!请重新尝试发送！");
            }
            FLAG = 0;//重置标志位
        }

        return null;
    }

    /**
     * 机器人发送markdown消息类型(*为必填)
     *
     * @param title     *首屏会话透出的展示标题内容
     * @param text      *markdown格式的消息
     * @param atUserIds 被@人的用户userId(在text中添加@人的userId)
     * @param atMobiles 被@人的手机号(在text中添加@人的手机号，只有在群内的成员才可被@，非群内成员手机号会被脱敏)
     * @param isAtAll   是否@全体成员
     * @return
     */
    public static final String markdownMsg(String title, String text, List<String> atUserIds, List<String> atMobiles, Boolean isAtAll) throws InterruptedException {
        //获取链接
        DingTalkClient client = DingtalkRobot.getClientURL(SECRET);
        //创建消息体
        OapiRobotSendRequest req = new OapiRobotSendRequest();
        try {
            OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();//设置markdown内容
            if (title != null) {
                markdown.setTitle(title);//标题内容
            }
            if (text != null) {
                markdown.setText(text);//正文内容
            }
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();//设置at内容
            if (atUserIds != null) {
                at.setAtUserIds(atUserIds);//被at人uid
            }
            if (atMobiles != null) {
                at.setAtMobiles(atMobiles);//被at人手机号
            }
            if (isAtAll != null) {
                at.setIsAtAll(isAtAll);//是否at全体成员
            } else {
                at.setIsAtAll(false);//默认为否
            }
            //组装消息
            req.setMsgtype("markdown");//设置类型
            req.setMarkdown(markdown);
            req.setAt(at);
            OapiRobotSendResponse rsp = client.execute(req, CUSTOM_ROBOT_TOKEN);//加密消息体
            logger.info("发送消息:" + rsp.getBody());//发送并打印内容
        } catch (Exception e) {
            logger.error("发送机器人消息失败！", e);
            FLAG++;
            if (FLAG < 5) {
                logger.info("正在重新尝试....");
                Thread.sleep(5000);
                DingtalkRobot.markdownMsg(title, text, atUserIds, atMobiles, isAtAll);
                ;
            } else {
                logger.info("已超出尝试次数!请重新尝试发送！");
            }
            FLAG = 0;//重置标志位
        }
        return null;
    }
//    markdown语法
//标题:
//# 一级标题
//## 二级标题
//### 三级标题
//#### 四级标题
//##### 五级标题
//###### 六级标题
//
//引用:
//> A man who stands for nothing will fall for anything.
//
//文字加粗、斜体:
//**bold**
//*italic*
//
//链接
//[this is a link](http://name.com)
//
//图片（建议不要超过20张）
//![](http://name.com/pic.jpg)

    /**
     * 机器人发送整体跳转的actionCard消息类型(*为必填)
     *
     * @param title             *首屏会话透出的展示内容
     * @param text              *markdown格式的消息(如果需要实现 @ 功能 ，在 text 内容中添加 @ 用户的 userId)
     * @param singleTitle       *单个按钮的标题
     * @param singleURL         *点击消息跳转的URL
     * @return
     */
    public final static String actionCardMsg(String title, String text,String singleTitle, String singleURL) throws InterruptedException {
        //获取链接
        DingTalkClient client = DingtalkRobot.getClientURL(SECRET);
        //创建消息体
        OapiRobotSendRequest req = new OapiRobotSendRequest();
        try {
            OapiRobotSendRequest.Actioncard actionCard = new OapiRobotSendRequest.Actioncard();//设置actionCard内容
            if (title != null){
                actionCard.setTitle(title);
            }
            if (text != null){
                actionCard.setText(text);
            }
            if (singleTitle != null){
                actionCard.setSingleTitle(singleTitle);
            }
            if (singleURL != null){
                actionCard.setSingleURL(singleURL);
            }
            //组装消息
            req.setMsgtype("actionCard");//设置类型
            req.setActionCard(actionCard);
            OapiRobotSendResponse rsp = client.execute(req, CUSTOM_ROBOT_TOKEN);//加密消息体
            logger.info("发送消息:" + rsp.getBody());//发送并打印内容
        }catch (Exception e){
            logger.error("发送机器人消息失败！", e);
            FLAG++;
            if (FLAG < 5) {
                logger.info("正在重新尝试....");
                Thread.sleep(5000);
                DingtalkRobot.actionCardMsg(title, text, singleTitle, singleURL);
            } else {
                logger.info("已超出尝试次数!请重新尝试发送！");
            }
            FLAG = 0;//重置标志位
        }
        return null;
    }

    /**
     * 机器人发送独立跳转的actionCard消息类型(*为必填)
     *
     * @param title             *首屏会话透出的展示内容
     * @param text              *markdown格式的消息(如果需要实现 @ 功能 ，在 text 内容中添加 @ 用户的 userId)
     * @param btnList              *按钮消息体，其中参数为 {[
     *          title           *单个按钮的标题
     *          actionURL       *点击按钮触发的URL
     *        ]}
     * @param btnOrientation    按钮排列方式(0：按钮竖直排列,1：按钮横向排列)
     * @return
     * @throws InterruptedException
     */
    public final static String actionCardMsg(String title, String text, List<Map<String, String>> btnList, String btnOrientation) throws InterruptedException {
        //获取链接
        DingTalkClient client = DingtalkRobot.getClientURL(SECRET);
        //创建消息体
        OapiRobotSendRequest req = new OapiRobotSendRequest();
        try {
            OapiRobotSendRequest.Actioncard actionCard = new OapiRobotSendRequest.Actioncard();//设置actionCard内容
            if (title != null){
                actionCard.setTitle(title);
            }
            if (text != null){
                actionCard.setText(text);
            }
            if (btnOrientation != null){
                actionCard.setBtnOrientation(btnOrientation);
            }
            if (btnList != null){
                List<OapiRobotSendRequest.Btns> btnS = new ArrayList<>();//设置btnS内容
                for (Map<String, String> map : btnList
                     ) {
                    OapiRobotSendRequest.Btns btn = new OapiRobotSendRequest.Btns();
                    if (map.size() == 2){
                        if (map.get("title") != null){
                            btn.setTitle(map.get("title"));
                        }
                        if (map.get("actionURL") != null){
                            btn.setActionURL(map.get("actionURL"));
                        }
                    }else {
                        logger.error("btnList对象缺少参数,错误对象为" + map);
                        throw new ArrayStoreException();
                    }
                    btnS.add(btn);
                }
                actionCard.setBtns(btnS);
            }
            //组装消息
            req.setMsgtype("actionCard");//设置类型
            req.setActionCard(actionCard);
            OapiRobotSendResponse rsp = client.execute(req, CUSTOM_ROBOT_TOKEN);//加密消息体
            logger.info("发送消息:" + rsp.getBody());//发送并打印内容
        }catch (Exception e){
            logger.error("发送机器人消息失败！", e);
            FLAG++;
            if (FLAG < 5) {
                logger.info("正在重新尝试....");
                Thread.sleep(5000);
                DingtalkRobot.actionCardMsg(title, text, btnList, btnOrientation);
            } else {
                logger.info("已超出尝试次数!请重新尝试发送！");
            }
            FLAG = 0;//重置标志位
        }
        return null;
    }

    /**
     * 机器人发送feedCard消息类型(*为必填)
     *
     * @param linksBody     *消息体,其中参数为 {[
     *      title         *单条信息文本
     *      messageURL    *点击单条信息到跳转链接，详情参考消息链接说明
     *      picURL        *单条信息后面图片的URL
     *      ]}
     * @return
     */
    public final static String feedCardMsg(List<Map<String, String>> linksBody) throws InterruptedException {
        //获取链接
        DingTalkClient client = DingtalkRobot.getClientURL(SECRET);
        //创建消息体
        OapiRobotSendRequest req = new OapiRobotSendRequest();
        try {
            OapiRobotSendRequest.Feedcard feedCard = new OapiRobotSendRequest.Feedcard();//设置feedCard内容
            if (linksBody != null) {
                List<OapiRobotSendRequest.Links> linksList = new ArrayList<>();
                for (Map<String, String> map : linksBody
                ) {
                    OapiRobotSendRequest.Links links = new OapiRobotSendRequest.Links();
                    if (map.size() == 3) {
                        if (map.get("title") != null) {
                            links.setTitle(map.get("title"));
                        }
                        if (map.get("messageURL") != null) {
                            links.setMessageURL(map.get("messageURL"));
                        }
                        if (map.get("picURL") != null) {
                            links.setPicURL(map.get("picURL"));
                        }
                    } else {
                        logger.error("linksBody对象缺少参数,错误对象为" + map);
                        throw new ArrayStoreException();
                    }
                    linksList.add(links);
                }
                feedCard.setLinks(linksList);
            }
            //组装消息
            req.setMsgtype("feedCard");//设置类型
            req.setFeedCard(feedCard);
            OapiRobotSendResponse rsp = client.execute(req, CUSTOM_ROBOT_TOKEN);//加密消息体
            logger.info("发送消息:" + rsp.getBody());//发送并打印内容
        }catch (Exception e){
            logger.error("发送机器人消息失败！", e);
            FLAG++;
            if (FLAG < 5) {
                logger.info("正在重新尝试....");
                Thread.sleep(5000);
                DingtalkRobot.feedCardMsg(linksBody);
            } else {
                logger.info("已超出尝试次数!请重新尝试发送！");
            }
            FLAG = 0;//重置标志位
        }
        return null;
    }


    /**
     * 获取clientURL
     *
     * @param secret
     * @return
     */
    private static DingTalkClient getClientURL(String secret){
        try {
            //获取时间
            Long timestamp = System.currentTimeMillis();
            logger.info(timestamp.toString());
            //拼接机器人信息
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
            System.out.println(sign);

            //创建链接（sign字段和timestamp字段必须拼接到请求URL上，否则会出现 310000 的错误信息）
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?sign=" + sign + "&timestamp=" + timestamp);
            return client;
        }catch (Exception e){
            logger.error("访问机器人出错",e);
        }
        return null;
    }

    /**
     * 设置机器人信息
     *
     * @param secret            用来存储机器人的地址(配置此项可改变发消息的机器人)
     * @param customRobotToken  与机器人地址对应的密钥(此数据来自钉钉机器人的设置)
     */
    public void setRobotMsg(String secret, String customRobotToken){
        this.SECRET = secret;
        this.CUSTOM_ROBOT_TOKEN = customRobotToken;
    }

//    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        List<String> strings = new ArrayList<>();
//        strings.add("15667082818");
//        List<Map<String, String>> mapList = new ArrayList<>();
//        Map<String, String> map = new HashMap<>();
//        map.put("title", "1");
//        map.put("messageURL", "https://open.dingtalk.com/document/orgapp/robot-overview#16dcd2cb0d5vq");
//        map.put("picURL", "https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png");
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("title", "2");
//        map.put("messageURL", "https://open.dingtalk.com/document/orgapp/robot-overview#16dcd2cb0d5vq");
//        map.put("picURL", "https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png");
//        mapList.add(map);
//        try {
//            feedCardMsg(mapList);
//        }catch (Exception e){
//
//        }
//
//    }

}
