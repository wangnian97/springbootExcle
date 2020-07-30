package com.zslin.model;

/**
 * http错误
 * @author wangyanqing16
 * @date 2019-06-14 11:29 2019-06-24 18:56
 */
public class HttpClientException extends RuntimeException {

    public HttpClientException() {
        super();
    }

    public HttpClientException(Throwable cause) {
        super(cause);
    }

    public HttpClientException(String message) {
        super(message);
    }
}
