package com.xiaoai.webhook;

import java.util.Map;

/**
 * @Author xiaoaiying
 * @Date 2023-05-28 18:17
 */
public interface Client {
    String name();

    Object apiImpl(Api api);
}
