package com.cx.qt.framework.common.response;

import com.cx.qt.framework.common.code.KnRspCode;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: amyyu
 * Date: 2019-08-15
 * Time: 22:01
 */
@Data
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -7916964510630016103L;

    private String msg;

    private String code;

    private Throwable exception;

    public void setErrorCodeAndException(KnRspCode knRspCode, Throwable e) {

    }
}
