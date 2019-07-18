package cn.voidnet.SimpleHTTPFramework;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ServletRequest {
    private String body;
    private String method;
    private String remoteHost;
    private HeaderMap headerMap;
    private ParameterMap parameterMap;
    private String URI;
    private InputStream in;

    public ServletRequest(InputStream in) throws Exception {
        this.in = in;
        var reader = new BufferedReader(new InputStreamReader(in));
        String data = reader.readLine();
        fromHTTPRequestMessage(data);
    }

    private void fromHTTPRequestMessage(String message) {
        //TODO:解析HTTP Request并储存至自身

    }

    public String getBody() {
        return body;
    }

    public String getMethod() {
        return method;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public HeaderMap getHeaderMap() {
        return headerMap;
    }

    public ParameterMap getParameterMap() {
        return parameterMap;
    }

    public ServletRequest setParameterMap(ParameterMap parameterMap) {
        this.parameterMap = parameterMap;
        return this;
    }

    public String getURI() {
        return URI;
    }


    public InputStream getInputStream() {
        return in;
    }

}
