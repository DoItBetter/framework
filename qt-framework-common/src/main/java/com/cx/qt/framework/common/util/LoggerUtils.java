/**
 * 
 */
package com.cx.qt.framework.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

public class LoggerUtils {

  /** 默认的调用id,当上下文未初始化好拿不到invokeId时候返回 */
  private static final String DEFAULT_INVOKEID = "-";

  /**
   * 返回当前线程ID
   * 
   * @return
   */
  private static final String getContextInvokeId(){
    String invokeId = null;
    try {
      invokeId = AppContextManager.getCurInvokeId();
    } catch (Exception e) {
        // do nothing
    }

    if (StringUtils.isBlank(invokeId)) {
      return DEFAULT_INVOKEID;
    }

    return invokeId;
  }

  /**
   * 获取带任务ID的Log格式化字串
   * 
   * @param format
   * @return
   */
  private static final String getLogFormatString(String format){
    StringBuilder log = new StringBuilder();
    // 生成任务Id头
    String invokeId = getContextInvokeId();
    // 格式化log内容
    log.append(invokeId);
    if (StringUtils.isNotBlank(format)) {
      log.append(",").append(format);
    }
    return log.toString();
  }

  /**
   * 格式化log字串，由于带Throwable参数的log接口无法传入format，所以在Utils类中直接格式化。
   * 
   * @param format
   * @param objs
   * @return
   */
  private static final String getFormattedString(String format, Object[] objs){
    String logFormatString = getLogFormatString(format);
    FormattingTuple ft = MessageFormatter.arrayFormat(logFormatString, objs);
    return ft.getMessage();
  }

  /**
   * 生成输出到日志的字符串
   *
   * @param obj 任意个要输出到日志的参数
   * @return
   */
  public static String getLogString(Object... obj){
    StringBuilder log = new StringBuilder();

    for (Object o : obj) {
      log.append(o);
    }
    return log.toString();
  }

  /**
   * @param logger
   * @param format
   * @param obj
   */
  public static void debug(Logger logger, String format, Object... obj){
    if (logger.isDebugEnabled()) {
      logger.debug(getLogFormatString(format), obj);
    }
  }

  /**
   * @param logger
   * @param format
   * @param obj
   */
  public static void info(Logger logger, String format, Object... obj){
    if (logger.isInfoEnabled()) {
      logger.info(getLogFormatString(format), obj);
    }
  }

  /**
   * @param logger
   * @param format
   * @param obj
   */
  public static void warn(Logger logger, String format, Object... obj){
    if (logger.isWarnEnabled()) {
      logger.warn(getLogFormatString(format), obj);
    }
  }

  /**
   * @param logger
   * @param e
   * @param format
   * @param obj
   */
  public static void warn(Logger logger, Throwable e, String format, Object... obj){
    if (logger.isWarnEnabled()) {
      logger.warn(getFormattedString(format, obj), e);
    }
  }

  /**
   * @param logger
   * @param e
   * @param format
   * @param obj
   */
  public static void error(Logger logger, Throwable e, String format, Object... obj){
    if (logger.isErrorEnabled()) {
      logger.error(getFormattedString(format, obj), e);
    }
  }

  /**
   * 生成<font color="brown">错误</font>级别日志<br>
   * 可处理任意多个输入参数，并避免在日志级别不够时字符串拼接带来的资源浪费
   *
   * @param logger
   * @param obj
   */
  public static void error(Logger logger, Throwable e, Object... obj){
    if (logger.isErrorEnabled()) {
      logger.error(getLogFormatString(getLogString(obj)), e);
    }
  }

  public static void error(Logger logger, String format, Object... obj){
    if (logger.isErrorEnabled()) {
      logger.error(getLogFormatString(format), obj);
    }
  }

}
