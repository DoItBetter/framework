package com.cx.qt.framework.http;


import com.cx.qt.framework.http.apache.ApacheBasePostRequestExecutor;

import java.io.IOException;

/**
 * @author danol
 * @Classname BasePostRequestExecutor
 * @Description
 * 1.继承framework里KnHttpRequestImpl实现类
 * 2.代码中调用方法：BasePostRequestExecutor.create(this).execute(String A, String B)
 *  参数A：请求的URL
 *  参数B：请求报文内容（注意这里类型为String）
 * @Date 2019/9/18 4:37 PM
 */
public abstract class BasePostRequestExecutor <H, P> implements RequestExecutor<String, String> {

    protected KnHttpRequest<H, P> knHttpRequest;

    public BasePostRequestExecutor(KnHttpRequest knHttpRequest) {
        this.knHttpRequest = knHttpRequest;
    }

    @Override
    public void execute(String uri, String data, ResponseHandler<String> handler) throws IOException {
        handler.handle(this.execute(uri, data));
    }

    public static RequestExecutor<String, String> create(KnHttpRequest knHttpRequest) {
                return new ApacheBasePostRequestExecutor(knHttpRequest);
    }
}