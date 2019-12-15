package com.cx.qt.framework.httpclient.bean;

import com.cx.qt.framework.httpclient.request.BaseTransHttpRequest;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: amyyu
 * Date: 2019-08-14
 * Time: 18:57
 */
@Data
public class HttpSerBean {
    private BaseTransHttpRequest request;

    private Class responseClass;

    private String url;

    private String domain;

    private String uri;
}