package cn.voidnet.SimpleHTTPFramework;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;

public class ServletResponse {
    private String body;
    private Map<String, String> headerMap;
    private int statusCode;
    private Socket remote;

    public ServletResponse(Socket remote) {
        this.remote = remote;
    }

    public void writeResponse() throws Exception {
        var writer = new BufferedWriter(new OutputStreamWriter(remote.getOutputStream()));
        writer.write(toReqeustMessage());
        writer.write('\n');
        writer.flush();
    }

    private String toReqeustMessage() {//输出自身对应的HTTP Response
        //TODO:写入正确的HTTP请求内容
        return "";

    }

    public String getBody() {
        return body;
    }

    public ServletResponse setBody(String body) {
        this.body = body;
        return this;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ServletResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public Socket getRemote() {
        return remote;
    }
}
