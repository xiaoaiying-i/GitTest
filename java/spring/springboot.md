### 资源绑定

```
ResourceBundle resourceBundle = ResourceBundle.getBundle("test");
this.ssoTargetUrl = resourceBundle.getString("test.url");
```

- 添加配置文件：test.properties

```
test.url=http://localhost.huawei.com:9090
```
