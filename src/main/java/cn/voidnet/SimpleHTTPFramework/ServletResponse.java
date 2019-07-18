package cn.voidnet.SimpleHTTPFramework;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ServletResponse {
    private String body;
    private HeaderMap headerMap;
    private int statusCode;
    private OutputStream out;

    public ServletResponse(OutputStream out) {
        this.out = out;
    }

    public void writeResponse() throws Exception {
        var writer = new BufferedWriter(new OutputStreamWriter(out));
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

    public HeaderMap getHeaderMap() {
        return headerMap;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public ServletResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public OutputStream getOutputStream() {
        return out;
    }
}
