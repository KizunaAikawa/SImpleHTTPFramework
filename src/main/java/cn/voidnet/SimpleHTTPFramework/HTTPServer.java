package cn.voidnet.SimpleHTTPFramework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServer {//Container
    private int port;
    private Servlet servletInstance;
    private ServerSocket local;
    private static Logger logger = LoggerFactory.getLogger(HTTPServer.class);
    

    public HTTPServer(int port, Servlet servletInstance) {
        this.port = port;
        this.servletInstance = servletInstance;
    }


    public void start() throws Exception {
        local = new ServerSocket(port);
        servletInstance.init();
        ExecutorService workerThreadPool = Executors.newCachedThreadPool();
        while (true) {
            Socket client = local.accept();
            workerThreadPool.submit(() -> handleRequest(client));
        }

    }

    private void handleRequest(Socket client) {
        try {
            logger.info("Request from " + client.getRemoteSocketAddress());
            ServletRequest request = new ServletRequest(client);
            ServletResponse response = new ServletResponse(client);
            servletInstance.service(request, response);
            logger.info("Request done");
            response.writeResponse();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
