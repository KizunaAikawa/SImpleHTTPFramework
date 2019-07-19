package cn.voidnet.SimpleHTTPFramework;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
