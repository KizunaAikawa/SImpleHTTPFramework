package cn.voidnet.SimpleHTTPFramework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServletRequest {
    private String body;
    private String method;
    private String remoteHost;
    private Map<String, String> headerMap = new HashMap<>();
    private Map<String, String> parameterMap = new HashMap<>();
    private String URI;
    private Socket remote;
    private static Logger logger = LoggerFactory.getLogger(ServletRequest.class);

    public ServletRequest(Socket remote) throws Exception {
        this.remote = remote;
        var reader = new BufferedReader(new InputStreamReader(remote.getInputStream()));
        fromHTTPRequestMessage(reader);
    }

    private void fromHTTPRequestMessage(BufferedReader reader) throws Exception {
        logger.trace("Resolving request");
        logger.trace("Resolving header");
        //TODO:解析HTTP Request并储存至自身
        String line = reader.readLine();//处理例如:'GET / HTTP/1.1'
        logger.info(line);
        var items = line.split(" ");
        if (items.length != 3)
            throw new BadRequestException("Request method invalid");
        method = items[0];//例如 GET
        URI = items[1];//例如 /
        if (!items[2].startsWith("HTTP"))//例如 HTTP/1.1
            throw new BadRequestException("Request method invalid");

        while (true) {//处理例如:User-agent:Mozilla/5.0 (Macintosh; Intel Mac
            line = reader.readLine();
            logger.trace("headerLine = " + line);
            if (line.isEmpty())
                break;
            int separatorIndex = line.indexOf(':');
            if (separatorIndex == -1)
                throw new BadRequestException("Request header invalid");
            headerMap.put
                    (
                            line.substring(0, separatorIndex),
                            line.substring(separatorIndex + 2)
                    );
        }
        logger.trace("Resolved header");


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

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public Map<String, String> getParameterMap() {
        return parameterMap;
    }

    public ServletRequest setParameterMap(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
        return this;
    }

    public String getURI() {
        return URI;
    }

    public Socket getRemote() {
        return remote;
    }
}
