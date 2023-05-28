package com.xiaoai.webhook;

import com.xiaoai.webhook.git.GitClient;
import com.xiaoai.webhook.wh.Api;

import java.util.Objects;

/**
 * @Author xiaoaiying
 * @Date 2023-05-28 18:55
 */
public class HttpClient {
    //模拟回调api接口实现使用
    public static GitClient client = new GitClient("test-repository");

    public static Object execute(Api api){
        Objects.requireNonNull(api, "api is null.");
        return client.apiImpl(api);
    }
}
