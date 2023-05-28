package com.xiaoai.webhook.wh;

/**
 * @Author xiaoaiying
 * @Date 2023-05-28 18:04
 */
public interface Event {
    String name();
    Object handler(Object data);
}
