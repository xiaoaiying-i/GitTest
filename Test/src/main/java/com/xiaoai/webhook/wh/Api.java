package com.xiaoai.webhook.wh;

import com.xiaoai.webhook.git.GitClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author xiaoaiying
 * @Date 2023-05-28 18:46
 */
public class Api {
    private String url;
    private Map<String, String> headers;
    private Map<String, Object> params;
    private Object body;

    public Api(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addHeaders(String key, String val) {
        if (headers == null){
            headers = new HashMap<>();
        }
        headers.put(key, val);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void addParam(String key, Object val) {
        if (params == null){
            params = new HashMap<>();
        }
        params.put(key, val);
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
