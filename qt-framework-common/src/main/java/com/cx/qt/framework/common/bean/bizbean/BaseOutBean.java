package com.cx.qt.framework.common.bean.bizbean;

import com.cx.qt.framework.common.code.KnRspCode;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: ckhero
 * Date: 2019/7/2
 * Time: 2:52 PM
 */
@Data
public class BaseOutBean {
    private String code;

    private String msg;

    private Throwable exception;

    public void code(KnRspCode knRspCode){
        this.code = knRspCode.getCode();
        this.msg = knRspCode.getMsg();
    }

    @Override
    public String toString(){
        return "OrderOutBean [code=" + code + ", msg=" + msg + ", exception=" + exception.getMessage() + "]";
    }
}
