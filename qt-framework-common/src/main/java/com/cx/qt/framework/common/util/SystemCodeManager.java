package com.cx.qt.framework.common.util;

import com.cx.qt.framework.common.code.KnRspCode;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author danol
 * @Classname SystemCodeManager
 * @Description TODO
 * @Date 2019/9/19 3:46 PM
 */
/**
 * 框架工具类：SystemCodeManager
 * 用法说明：
 *     注入工具类
 *     @Resource
 *     private SystemCodeManager systemCodeManager;
 *     使用
 *     systemCodeManager.create("配置文件中错误码定义");
 *     eg: systemCodeManager.create("ERR_SYS_ERROR");
 * 工具类用途：用于可配置化错误码
 */
@Component
public class SystemCodeManager {
    @Resource
    Environment environment;

    public KnRspCode create(String codeName) {
        return new KnRspCode(environment.getProperty(codeName).split(",")[0], environment.getProperty(codeName).split(",")[1]);
    }
}
