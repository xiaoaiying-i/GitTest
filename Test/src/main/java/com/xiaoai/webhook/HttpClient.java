package com.xiaoai.webhook;

import java.util.Objects;

/**
 * @Author xiaoaiying
 * @Date 2023-05-28 18:55
 */
public class HttpClient {

    public static Object execute(Api api){
        Objects.requireNonNull(api, "api is null.");
        return api.client.apiImpl(api);
    }
}
