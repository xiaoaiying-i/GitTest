package com.xiaoai.webhook.wh;

import lombok.Data;

import java.util.List;

/**
 * @Author xiaoaiying
 * @Date 2023-05-28 18:02
 */
@Data
public class WebHook {
    private List<Event> eventList;
    private Api callBackApi;

    public WebHook(List<Event> eventList, Api callBackApi) {
        this.eventList = eventList;
        this.callBackApi = callBackApi;
    }
}
