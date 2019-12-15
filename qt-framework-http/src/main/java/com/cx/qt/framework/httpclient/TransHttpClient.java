package com.cx.qt.framework.httpclient;


import com.alibaba.fastjson.JSON;
import com.cx.qt.framework.common.code.LogFormatCode;
import com.cx.qt.framework.common.util.SystemCodeManager;
import com.cx.qt.framework.httpclient.request.BaseTransHttpRequest;
import com.cx.qt.framework.httpclient.response.BaseTransHttpResponse;
import com.cx.qt.framework.httpclient.bean.HttpSerBean;
import com.cx.qt.framework.common.exception.FWBaseException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: amyyu
 * Date: 2019-08-14
 * Time: 18:56
 */
@Slf4j
@SuppressWarnings("unchecked")
public class TransHttpClient {
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    private static final int CONNECTION_TIMEOUT = 5000;// 连接超时时间

    private static final int CONNECTION_REQUEST_TIMEOUT = 5000;// 请求超时时间

    private static final int SOCKET_TIMEOUT = 10000;// 数据读取等待超时

    private static final String DEFAULT_ENCODING = "UTF-8";// 默认编码

    private static final String HEAD_CONTENT_TYPE  = "Content-Type";// 默认编码

    private static final String CONTENT_TYPE_TEXT = "application/json";// 默认编码

    @Resource
    static
    private SystemCodeManager systemCodeManager;
    public static BaseTransHttpResponse post(HttpSerBean httpSerBean) throws FWBaseException {

        BaseTransHttpRequest req=httpSerBean.getRequest();
        String jsonParams = JSON.toJSONString(req);
        log.info(LogFormatCode.HTTP_REQUEST.getFormat(), httpSerBean.getUrl(), jsonParams);
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setRedirectsEnabled(true)
                .build();

        HttpPost httpPost = new HttpPost(httpSerBean.getUrl());
        httpPost.setConfig(requestConfig);
        httpPost.setHeader(HEAD_CONTENT_TYPE,CONTENT_TYPE_TEXT);

        Class responseClass = httpSerBean.getResponseClass();
        if (Objects.isNull(responseClass)) {
            throw new FWBaseException(systemCodeManager.create("ERR_HTTP_RESPONSE_CLASS_NULL"));
        }
        try {
            StringEntity entity= new StringEntity(jsonParams, ContentType.create(CONTENT_TYPE_TEXT, DEFAULT_ENCODING));
            httpPost.setEntity(entity);
            HttpResponse result = httpClient.execute(httpPost);
            String jsonReponse = EntityUtils.toString(result.getEntity());
            BaseTransHttpResponse response = (BaseTransHttpResponse) JSON.parseObject(jsonReponse, responseClass);
            log.info(LogFormatCode.HTTP_RESPONSE.getFormat(), httpSerBean.getUrl(), jsonReponse);
            return response;
        } catch (HttpHostConnectException e) {
            log.error("[HTTP]request fail,msg={},url={},reqeust={}", e.getMessage().toString(), httpSerBean.getUrl(), httpSerBean.getRequest());
            throw new FWBaseException(systemCodeManager.create("ERR_HTTP_CONNECT"),e);

        } catch (Exception e) {
            log.error("[HTTP]request fail,msg={},url={},reqeust={}", e.getMessage().toString(), httpSerBean.getUrl(), httpSerBean.getRequest());
            log.error("[HTTP]reqeust fail,exception", e);
            throw new FWBaseException(systemCodeManager.create("ERR_HTTP_CONNECT"), e.getMessage().toString());
        }
    }
}
