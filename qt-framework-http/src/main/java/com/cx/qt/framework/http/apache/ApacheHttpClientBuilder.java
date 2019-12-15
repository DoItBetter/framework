package com.cx.qt.framework.http.apache;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author danol
 * @Classname ApacheHttpClientBuilder
 * @Description TODO
 * @Date 2019/9/18 5:24 PM
 */
public interface ApacheHttpClientBuilder {
    /**
     * create httpclient instance.
     *
     * @return new instance of CloseableHttpClient
     */
    CloseableHttpClient build();

    /**
     * proxy server address
     *
     * @param httpProxyHost
     * @return
     */
    ApacheHttpClientBuilder httpProxyHost(String httpProxyHost);

    /**
     * proxy server port
     *
     * @param httpProxyPort
     * @return
     */
    ApacheHttpClientBuilder httpProxyPort(int httpProxyPort);

    /**
     * proxy server name
     *
     * @param httpProxyUsername
     * @return
     */
    ApacheHttpClientBuilder httpProxyUsername(String httpProxyUsername);

    /**
     * proxy server password
     *
     * @param httpProxyPassword
     * @return
     */
    ApacheHttpClientBuilder httpProxyPassword(String httpProxyPassword);

    /**
     * ssl connnection socket factory
     *
     * @param sslConnectionSocketFactory
     * @return
     */
    ApacheHttpClientBuilder sslConnectionSocketFactory(SSLConnectionSocketFactory sslConnectionSocketFactory);
}
