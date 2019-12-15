package com.cx.qt.framework.common.code;

/**
 * Created by IntelliJ IDEA.
 * User: amyyu
 * Date: 2019-08-15
 * Time: 14:57
 */
public enum LogFormatCode {
    HTTP_REQUEST("[HTTP - request]url: {} request {}", "http请求[urq]"),
    HTTP_RESPONSE("[HTTP - response]url: {} response {} ", "http请求返回[urq]"),
    HTTP_ERROR("[HTTP]msg: {} url {}", "http请求[mu]"),
    ;
    private String format;

    private String desc;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    LogFormatCode(String format, String desc) {
        this.format = format;
        this.desc = desc;
    }
}
