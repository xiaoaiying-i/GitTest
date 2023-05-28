package com.xiaoai.webhook;

import com.xiaoai.webhook.git.GitClient;
import com.xiaoai.webhook.git.event.DelEvent;
import com.xiaoai.webhook.git.event.PushEvent;
import com.xiaoai.webhook.wh.Api;
import com.xiaoai.webhook.wh.Event;
import com.xiaoai.webhook.wh.WebHook;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaoaiying
 * @Date 2023-05-28 18:04
 */
public class TestMain {
    public static void main(String[] args){
        // 操作的仓库
        GitClient client = GitClient.connect("test-repository");

        // 创建webhook并注册
        // 绑定触发事件
        List<Event> eventTriggerList = new ArrayList<>();
        eventTriggerList.add(new PushEvent());
        eventTriggerList.add(new DelEvent());

        // 绑定回调api
        Api api = new Api("http://test.url.com");
        api.addHeaders("Content-Type", "application/json");
        WebHook webHook = new WebHook(eventTriggerList, api);
        client.registerWebHook(webHook);

        // 调用事件
        client.push("push-data");
        client.pull("pull-data");
        client.del("del-data");
    }
}
