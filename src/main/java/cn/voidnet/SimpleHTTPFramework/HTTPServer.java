package cn.voidnet.SimpleHTTPFramework;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServer {//Container
    private int port;
    private Servlet servletInstance;
    private ServerSocket local;

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
            ServletRequest request = new ServletRequest(client.getInputStream());
            ServletResponse response = new ServletResponse(client.getOutputStream());
            servletInstance.service(request, response);
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
