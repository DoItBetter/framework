package com.cx.qt.framework.http;

/**
 * @author danol
 * @Classname KnHttpRequest
 * @Description TODO
 * @Date 2019-08-30 11:57
 */
public interface KnHttpRequest<H, P> {
    /**
     * @return
     */
    H getKnHttpRequestClient();

    /**
     * 返回httpProxy.
     *
     * @return 返回httpProxy
     */
    P getKnHttpRequestProxy();

}
