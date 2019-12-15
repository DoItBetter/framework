package com.cx.qt.framework.http.apache;

import com.cx.qt.framework.http.BaseGetRequestExecutor;
import com.cx.qt.framework.http.KnHttpRequest;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * @author danol
 * @Classname ApacheBaseGetRequestExecutor
 * @Description TODO
 * @Date 2019/9/18 5:03 PM
 */
public class ApacheBaseGetRequestExecutor extends BaseGetRequestExecutor<CloseableHttpClient, HttpHost> {
    public ApacheBaseGetRequestExecutor(KnHttpRequest knHttpRequest) {
        super(knHttpRequest);
    }

    @Override
    public String execute(String uri, String data) throws IOException {
        if (data != null) {
            if (uri.indexOf('?') == -1) {
                uri += '?';
            }
            uri += uri.endsWith("?") ? data : '&' + data;
        }
        HttpGet httpGet = new HttpGet(uri);

        if (knHttpRequest.getKnHttpRequestProxy() != null) {
            RequestConfig config = RequestConfig.custom().setProxy(knHttpRequest.getKnHttpRequestProxy()).build();
            httpGet.setConfig(config);
        }

        try (CloseableHttpResponse response = knHttpRequest.getKnHttpRequestClient().execute(httpGet)) {
            String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);

            return responseContent;
        } finally {
            httpGet.releaseConnection();
        }
    }
}
