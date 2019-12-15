package com.cx.qt.framework.http;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by IntelliJ IDEA.
 * User: amyyu
 * Date: 2019-09-26
 * Time: 16:39
 * description: Apache Http 实现类
 * */
public class KnHttpRequestImpl implements KnHttpRequest<CloseableHttpClient , HttpHost> {
    @Override
    public CloseableHttpClient getKnHttpRequestClient() {
        return HttpClients.createDefault();
    }

    @Override
    public HttpHost getKnHttpRequestProxy() {
        return null;
    }

}
