package com.cx.qt.framework.common.exception;

import com.cx.qt.framework.common.code.KnRspCode;
import com.cx.qt.framework.common.response.BaseResponse;
import com.cx.qt.framework.common.util.CommonConstant;
import com.cx.qt.framework.common.util.SystemCodeManager;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.text.MessageFormat;

/**
 * Created by IntelliJ IDEA.
 * User: amyyu
 * Date: 2019-08-15
 * Time: 14:14
 */
@Data
public class FWBaseException extends Exception{
    private KnRspCode knRspCode;

    private String code;

    private String msg;

    @Resource
    SystemCodeManager systemCodeManager;

    public FWBaseException(KnRspCode KnRspCode){
        this.knRspCode = KnRspCode;
        this.code = KnRspCode.getCode();
        this.msg = KnRspCode.getMsg();
    }

    public FWBaseException(KnRspCode KnRspCode, Throwable t){
        super(t);
        this.knRspCode = KnRspCode;
        this.code = KnRspCode.getCode();
        this.msg = KnRspCode.getMsg();
    }

    public FWBaseException(KnRspCode KnRspCode, String msg, Throwable t){
        super(t);
        this.knRspCode = KnRspCode;
        this.code = KnRspCode.getCode();
        this.msg = KnRspCode.getMsg();
        if (StringUtils.isNotBlank(msg)) {
            this.msg += CommonConstant.COMMA + msg;
        }
    }

    public FWBaseException(KnRspCode KnRspCode, String msg){
        this.knRspCode = KnRspCode;
        this.code = KnRspCode.getCode();
        this.msg = KnRspCode.getMsg();
        if (StringUtils.isNotBlank(msg)) {
            this.msg += CommonConstant.COMMA + msg;
        }
    }

    public FWBaseException(String msg){
        this.knRspCode = systemCodeManager.create("ERR_SYS_ERROR");
        this.code = knRspCode.getCode();
        this.msg = msg;
    }

    public void exceptionToResponse(BaseResponse baseResponse){
        baseResponse.setCode(this.getCode());
        baseResponse.setMsg(this.getMsg());
        baseResponse.setException(this);
    }

    public FWBaseException(String pattern, Object... arguments){
        super(MessageFormat.format(pattern, arguments));
    }
}
