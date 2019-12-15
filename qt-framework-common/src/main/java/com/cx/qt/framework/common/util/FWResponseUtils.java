package com.cx.qt.framework.common.util;

import com.cx.qt.framework.common.exception.FWBaseRuntimeException;
import com.cx.qt.framework.common.response.BaseResponse;
import com.cx.qt.framework.common.exception.FWBaseException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeoutException;

/**
 * Created by IntelliJ IDEA.
 * User: amyyu
 * Date: 2019-08-19
 * Time: 21:56
 */
/**
 * 框架工具类：FWResponseUtils
 * 工具类用途：用于统一的应答码处理
 * 用法说明：  先进行子系统特殊化异常处理，再调用FWResponseUtils的FWBaseException抛异常；如果子系统不需要特殊化异常处理，直接实现preHandle方法
 * ***调用FWResponseUtils工具类示例***
 *
 * ***需要先本地化创建一个工具类***
 * @Component
 * public class TResponseUtils extends FWResponseUtils{
 *     @Resource
 *     private SystemCodeManager systemCodeManager;
 *     @Override
 *     public int preHandle(BaseResponse resp, Throwable e){
 *         if (e instanceof BizException) {
 *             BizException se = (BizException) e;
 *             if (se.getCode() != null) {
 *                 se.exceptionToResponse(resp);
 *             } else {
 *                 resp.setErrorCodeAndException(systemCodeManager.create("SYS_ERROR"), e);
 *             }
 *             return 1;
 *         }
 *         return 0;
 *     }
 * }
 * 或者：
 * @Component
 * public class SDResponseUtils extends FWResponseUtils {
 *     @Override
 *     public int preHandle(BaseResponse resp, Throwable e) {
 *         return 0;
 *     }
 * }
 * ***方法里处理的异常示例***
 * public class Demo2Service {
 *       @Resource
 *       private SystemCodeManager systemCodeManager;
 *
 *       public String demo(String str) throws FWBaseException{
 *           try{
 *               XXX
 *           }catch(Exception e){
 *               throw new BizException(systemCodeManager.create("ERR_BUSI_ERROR"),e);
 *           }
 *       }
 *   }
 * ***调用FWResponseUtils工具类示例***
 *   @PropertySource(value = {"本地配置文件名称"},encoding = "UTF-8") ——注解说明：用来加载指定配置文件
 *   @ComponentScan(value = {"com.kuainiu.qt","com.kuainiu.sd"})  ——注解说明：扫包范围，防止服务启动是找不到bean情况
 *   public class DemoController {
 *       @Autowired
 *       Demo2Service demo2Service;
 *       @Autowired
 *       TResponseUtils responseUtils;
 *       try {
 *       BaseResponse response = new BaseResponse();
 *           response = demoService.demo(str);
 *           responseUtils.success(response);
 *       } catch (BizException e) {
 *           responseUtils.sysError(response, e);
 *       }
 *       return response;
 *      }
 */
@Component
@Slf4j
public abstract class FWResponseUtils {

    @Resource
    private SystemCodeManager systemCodeManager;

    public abstract int preHandle(BaseResponse resp, Throwable e);

    public  void sysError(BaseResponse resp, Throwable e){
        this.preHandle(resp,e);

        if (e instanceof IllegalArgumentException) {
            resp.setCode(systemCodeManager.create("ERR_PARAM_ERROR").getCode());
            resp.setMsg(e.getMessage());
        } else if (e instanceof TimeoutException) {
            resp.setCode(systemCodeManager.create("SYS_TIMEOUT").getCode());
            resp.setMsg(systemCodeManager.create("SYS_TIMEOUT").getMsg());
        } else if (e instanceof FWBaseException) {
            FWBaseException se = (FWBaseException) e;
            if (se.getCode() != null) {
                se.exceptionToResponse(resp);
            } else {
                resp.setErrorCodeAndException(systemCodeManager.create("SYS_ERROR"), e);
            }
        } else if (e instanceof FWBaseRuntimeException) {
            FWBaseRuntimeException se = (FWBaseRuntimeException) e;
            if (se.getCode() != null) {
                se.exceptionToResponse(resp);
            } else {
                resp.setErrorCodeAndException(systemCodeManager.create("SYS_ERROR"), e);
            }
        } else if(1 != this.preHandle(resp,e)){
            resp.setCode(systemCodeManager.create("SYS_ERROR").getCode());
            resp.setMsg(systemCodeManager.create("SYS_ERROR").getMsg());
        }
        LoggerUtils.error(log, e, "系统调用返回异常！code：{},msg:{}", resp.getCode(),
                resp.getMsg());
    }

    public void success(BaseResponse resp){
        resp.setCode(systemCodeManager.create("SUCCESS").getCode());
        resp.setMsg(systemCodeManager.create("SUCCESS").getMsg());
    }
}
