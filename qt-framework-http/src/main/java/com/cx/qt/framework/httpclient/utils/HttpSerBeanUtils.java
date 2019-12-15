package com.cx.qt.framework.httpclient.utils;


import com.cx.qt.framework.httpclient.bean.HttpSerBean;
import com.cx.qt.framework.httpclient.request.BaseTransHttpRequest;

/**
 * Created by IntelliJ IDEA.
 * User: ckhero
 * Date: 2019/6/2
 * Time: 11:10 AM
 */
public class HttpSerBeanUtils {

    public static HttpSerBean buildHttpSerBean(BaseTransHttpRequest request, Class response, String domain, String uri) {
        HttpSerBean httpSerBean = new HttpSerBean();
        httpSerBean.setRequest(request);
        httpSerBean.setResponseClass(response);
        String url = domain + uri;
        httpSerBean.setUrl(url);
        httpSerBean.setDomain(domain);
        httpSerBean.setUri(uri);
        return  httpSerBean;
    }
}
