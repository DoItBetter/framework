package com.cx.qt.framework.common.exception;

import com.cx.qt.framework.common.code.KnRspCode;
import com.cx.qt.framework.common.response.BaseResponse;
import lombok.Data;

import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * User: ckhero
 * Date: 2019/5/19
 * Time: 10:10 AM
 */
@Data
public class FWBaseRuntimeException extends RuntimeException{
    private KnRspCode knRspCode;

    private String code;

    private String msg;

    public void exceptionToResponse(BaseResponse baseResponse){
        baseResponse.setCode(this.getCode());
        baseResponse.setMsg(this.getMsg());
        baseResponse.setException(this);
    }

    public FWBaseRuntimeException(String pattern, Object... arguments){
        super(MessageFormat.format(pattern, arguments));
    }
}
