package com.cx.qt.framework.httpclient.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: amyyu
 * Date: 2019-08-14
 * Time: 19:00
 */
@Data
public class BaseHttpResponse implements Serializable {
    private static final long serialVersionUID = -8796135407367296842L;

    private String code;

    private String msg;
}
