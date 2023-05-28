package com.xiaoai.webhook.git.event;

import com.xiaoai.webhook.wh.Event;

import java.util.Map;

/**
 * @Author xiaoaiying
 * @Date 2023-05-28 18:10
 */
public class PushEvent implements Event {
    @Override
    public String name() {
        return "push";
    }

    @Override
    public Object handler(Object data) {
        Map<String, Object> dataMap = (Map<String, Object>) data;
        Object repository = dataMap.get("repository");
        Object realData = dataMap.get("data");
        System.out.println("\n[" +repository + "] event-execute-push, data: " + realData);
        return "push";
    }
}
