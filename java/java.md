## 请求中获取参数请求头

```java
private String uriHandle(String uri, String rwOrigin, String rwTarget) {
    if (uri == null || rwTarget == null || "".equals(rwTarget)){
        return uri;
    }
    String urlPrefixOg = rwOrigin;
    if ("".equals(urlPrefixOg)){
        // replacing the level 1 path
        String[] uris = uri.split("/");
        if (uris.length > 1){
            urlPrefixOg = rwTarget.startsWith("/") ? "/" + uris[1] : uris[1];
        }
        if ("/".equals(rwTarget)){
            urlPrefixOg = rwOrigin + "/";
        }
    }
    return uri.replaceFirst(urlPrefixOg, rwTarget);
}

private Map<String, String> getBaseHeaders() {
    HashMap<String, String> headers = new HashMap<>();
    headers.put("Accept", MediaType.APPLICATION_JSON.toString());
    headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    return headers;
}

private Map<String, String> getReqHeaders(HttpServletRequest request) {
    Map<String, String> headers = new HashMap<>();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()){
        String headerName = headerNames.nextElement();
        if ("content-length".equals(headerName)){
            continue;
        }
        headers.put(headerName, request.getHeader(headerName));
    }
    return headers;
}

private Map<String, String> getReqParams(HttpServletRequest request){
    HashMap<String, String> params = new HashMap<>();
    Enumeration<String> parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()) {
        String paramName = parameterNames.nextElement();
        params.put(paramName, request.getParameter(paramName));
    }
    return params;
}

// private String getReqBody(ServletRequest request) {
//     BufferedReader bfReader = null;
//     StringBuilder strBuilder;
//     String reqBody;
//     try {
//         bfReader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
//         String line;
//         strBuilder = new StringBuilder();
//         while ((line = JsonSanitizer.sanitize(bfReader.readLine())) != null && !line.equals("null")) {
//             strBuilder.append(line);
//         }
//
//         try {
//             reqBody = URLDecoder.decode(strBuilder.toString(), StandardCharsets.UTF_8.name());
//         } catch (Exception ex) {
//             reqBody = strBuilder.toString();
//         }
//         return reqBody;
//     } catch (IOException ex) {
//         logger.error(ex.toString());
//         return "";
//     } finally {
//         if(bfReader != null){
//             try {
//                 bfReader.close();
//             } catch (IOException ex) {
//                 logger.error(ExceptionUtils.getStackTrace(ex));
//             }
//         }
//     }
// }

// private String getReqBody(ServletRequest request) {
//     StringBuilder strBuilder = new StringBuilder();
//     try (BufferedReader bfReader = new BufferedReader(
//         new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8))) {
//         String line;
//         while ((line = JsonSanitizer.sanitize(bfReader.readLine())) != null && !line.equals("null")) {
//             strBuilder.append(line);
//         }
//         try {
//             return URLDecoder.decode(strBuilder.toString(), StandardCharsets.UTF_8.name());
//         } catch (Exception ex) {
//             return strBuilder.toString();
//         }
//     } catch (IOException ex) {
//         logger.error(ex.toString());
//         return "";
//     }
// }

private String getReqBody(ServletRequest request) {
    StringBuilder strBuilder = new StringBuilder();
    try (ServletInputStream inputStream = request.getInputStream()) {
        byte[] buf = new byte[4096];
        int read = inputStream.read(buf);
        strBuilder.append(new String(buf));
        while (read != -1) {
            read = inputStream.read(buf);
            strBuilder.append(new String(buf));
        }
        try {
            return URLDecoder.decode(strBuilder.toString(), StandardCharsets.UTF_8.name());
        } catch (Exception ex) {
            return strBuilder.toString();
        }
    } catch (IOException ex) {
        logger.error(ex.toString());
        return "";
    }
}

```
