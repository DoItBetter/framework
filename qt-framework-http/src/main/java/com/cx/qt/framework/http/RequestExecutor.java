package com.cx.qt.framework.http;

import java.io.IOException;

/**
 * http request executor
 *
 * @param <T> return type
 * @param <E> request param type
 * @author danol
 * @Classname RequestExecutor
 * @Description TODO
 * @Date 2019/9/18 4:22 PM
 */
public interface RequestExecutor<T, E> {
    /**
     * execute http request.
     *
     * @param uri  uri
     * @param data
     * @return response result
     * @throws IOException      io异常
     */
    T execute(String uri, E data) throws IOException;


    /**
     * execute http request.
     *
     * @param uri     uri
     * @param data
     * @param handler http response handler
     * @throws IOException      io exception
     */
    void execute(String uri, E data, ResponseHandler<T> handler) throws IOException;
}
