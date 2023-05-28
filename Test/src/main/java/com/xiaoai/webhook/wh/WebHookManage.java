package com.xiaoai.webhook.wh;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xiaoaiying
 * @Date 2023-05-28 17:58
 */
public class WebHookManage {
    private Map<String, WebHook> webHookRepo = new HashMap();

    public void regist(String name, WebHook webHook){
        webHookRepo.put(name, webHook);
    }

    public WebHook getWebHook(String name) {
        return webHookRepo.get(name);
    }

    public Map<String, WebHook> getWebHookRepo() {
        return webHookRepo;
    }
}
