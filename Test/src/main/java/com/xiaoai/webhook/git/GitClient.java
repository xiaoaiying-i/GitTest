package com.xiaoai.webhook.git;

import com.xiaoai.webhook.wh.Api;
import com.xiaoai.webhook.git.event.PullEvent;
import com.xiaoai.webhook.wh.WebHook;
import com.xiaoai.webhook.git.event.DelEvent;
import com.xiaoai.webhook.git.event.PushEvent;

/**
 * @Author xiaoaiying
 * @Date 2023-05-28 18:03
 */
public class GitClient {
    private String repositoryName;
    private GitServer server;

    public GitClient(String repositoryName) {
        this.repositoryName = repositoryName;
        this.server = new GitServer();
    }

    public static GitClient connect(String repositoryName){
        return new GitClient(repositoryName);
    }

    public void registerWebHook(WebHook webHook){
        server.registerWebHook(this.repositoryName, webHook);
    }

    public void pull(Object data){
        server.eventHandler(repositoryName, data, new PullEvent());
    }

    public void push(Object data){
        server.eventHandler(repositoryName, data, new PushEvent());
    }

    public void del(Object data){
        server.eventHandler(repositoryName, data, new DelEvent());
    }

    public String name() {
        return repositoryName;
    }

    public Object apiImpl(Api api) {
        System.out.println("client api callback: sucess");
        System.out.println("url:" + api.getUrl() + "  body: " + api.getBody());
        return "api-impl";
    }
}
