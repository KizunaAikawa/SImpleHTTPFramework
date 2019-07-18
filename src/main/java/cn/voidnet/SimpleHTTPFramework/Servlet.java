package cn.voidnet.SimpleHTTPFramework;

public interface Servlet {
    void init();

    void service(ServletRequest request, ServletResponse response);

    void destory();

}
