package cn.voidnet.SimpleHTTPFramework;

public class Main {
    public static void main(String[] args) throws Exception {
        var server = new HTTPServer(8080, new SimpleServletImpl());
    }
}
