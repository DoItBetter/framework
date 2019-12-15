package com.cx.qt.framework.http.apache;

import com.alibaba.fastjson.JSON;
import com.cx.qt.framework.common.util.LoggerUtils;
import com.cx.qt.framework.common.util.TimeUtils;
import com.cx.qt.framework.http.BasePostRequestExecutor;
import com.cx.qt.framework.http.KnHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author danol
 * @Classname ApacheBasePostRequestExecutor
 * @Description TODO
 * @Date 2019/9/18 4:41 PM
 */
@Slf4j
public class ApacheBasePostRequestExecutor extends BasePostRequestExecutor<CloseableHttpClient, HttpHost> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApacheBasePostRequestExecutor.class);

    private static final int CONNECTION_TIMEOUT = 5000;// 连接超时时间

    private static final int CONNECTION_REQUEST_TIMEOUT = 5000;// 请求超时时间

    private static final int SOCKET_TIMEOUT = 10000;// 数据读取等待超时

    private static final String HEAD_CONTENT_TYPE  = "Content-Type";// 默认编码

    private static final String CONTENT_TYPE_TEXT = "application/json";// 默认编码

    public ApacheBasePostRequestExecutor(KnHttpRequest knHttpRequest) {
        super(knHttpRequest);
    }

    @Override
    public String execute(String uri, String postEntity) throws IOException {
        HttpPost httpPost = new HttpPost(uri);

        if (knHttpRequest.getKnHttpRequestProxy() != null) {
            RequestConfig config = RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT)
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .setRedirectsEnabled(true)
                    .setProxy(knHttpRequest.getKnHttpRequestProxy())
                    .build();
            httpPost.setConfig(config);
        }

        if (postEntity != null) {
            StringEntity entity = new StringEntity(postEntity, Consts.UTF_8);
            httpPost.setEntity(entity);
        }

        httpPost.setHeader(HEAD_CONTENT_TYPE,CONTENT_TYPE_TEXT);

        LoggerUtils.info(LOGGER, "{} request URL is {} HTTP request data is {}", TimeUtils.nowMsTime() ,httpPost.getURI(),postEntity);
        try (CloseableHttpResponse response = knHttpRequest.getKnHttpRequestClient().execute(httpPost)) {
            String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);

            LoggerUtils.info(LOGGER, "{} HTTP response data is {} ", TimeUtils.nowMsTime(), JSON.toJSONString(responseContent));
            if (responseContent.isEmpty()) {
                throw new IOException("response content is empty");
            }
            if (responseContent.startsWith("<xml>")) {
                return responseContent;
            }

            return responseContent;

        } catch (HttpHostConnectException e) {
            throw new IOException("http connect error");

        } catch (HttpResponseException e) {
            throw new IOException("http response error");

        } finally {
            httpPost.releaseConnection();

        }
    }

}
