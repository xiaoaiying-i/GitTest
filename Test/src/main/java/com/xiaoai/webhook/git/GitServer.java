package com.xiaoai.webhook.git;

import com.xiaoai.webhook.wh.Api;
import com.xiaoai.webhook.HttpClient;
import com.xiaoai.webhook.wh.Event;
import com.xiaoai.webhook.wh.WebHook;
import com.xiaoai.webhook.wh.WebHookManage;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xiaoaiying
 * @Date 2023-05-28 18:15
 */
public class GitServer {
    private WebHookManage webHookManage = new WebHookManage();

    public Object eventHandler(String repositoryName, Object data, Event event){

        Map<String, Object> eventData = new HashMap<>();
        eventData.put("repository", repositoryName);
        eventData.put("data", data);
        Object handler = event.handler(eventData);

        // webhook处理
        webHookHandler(repositoryName, data, event);

        return handler;
    }

    private void webHookHandler(String repositoryName, Object data, Event event){
        WebHook webHook = webHookManage.getWebHook(repositoryName);
        if (webHook != null){
            for (Event e : webHook.getEventList()) {
                // todo: 验证是否回调，简单模拟， 具体逻辑根据需要实现
                if (e.name().equals(event.name())){
                    Api callBackApi = webHook.getCallBackApi();
                    callBackApi.setBody("callback-response-" + data);
                    Object apiResp = HttpClient.execute(callBackApi);
                    // todo:异常重试机制
                }
            }
        }
    }

    public void registerWebHook(String repositoryName, WebHook webHook){
        webHookManage.regist(repositoryName, webHook);
    }
}
