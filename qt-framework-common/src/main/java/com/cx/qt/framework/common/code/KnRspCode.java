package com.cx.qt.framework.common.code;


import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: amyyu
 * Date: 2019-08-15
 * Time: 22:03
 */
@Data
public class KnRspCode {
    private String code;

    private String msg;

    public KnRspCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
