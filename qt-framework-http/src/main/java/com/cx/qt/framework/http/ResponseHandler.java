package com.cx.qt.framework.http;

/**
 * http response callback interface
 *
 * @param <T> return type
 * @author danol
 * @Classname ResponseHandler
 * @Description TODO
 * @Date 2019/9/18 4:33 PM
 */
public interface ResponseHandler<T> {
    /**
     * response result handler
     *
     * @param t
     */
    void handle(T t);
}
