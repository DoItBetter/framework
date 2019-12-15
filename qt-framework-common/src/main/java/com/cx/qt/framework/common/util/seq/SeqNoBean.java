/**
 * 
 */
package com.cx.qt.framework.common.util.seq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public class SeqNoBean {

  private final static Logger logger = LoggerFactory.getLogger(SeqNoBean.class);

  /** 调用标志号 */
  private String invokeId;

  /** 调用入口方法 */
  private String invokeMethod;

  /** 调用类型 */
  private int invokeType;

  /** 业务标志号 */
  private String seqNo;

  /** 业务标志类型 */
  private int seqNoType;

  /** 系统间调用唯一标志 */
  private String traceId;

  /**
   * @return the invokeId
   */
  public String getInvokeId(){
    return invokeId;
  }

  /**
   * @param invokeId the invokeId to set
   */
  public void setInvokeId(String invokeId){
    this.invokeId = invokeId;
  }

  /**
   * @return the invokeMethod
   */
  public String getInvokeMethod(){
    return invokeMethod;
  }

  /**
   * @param invokeMethod the invokeMethod to set
   */
  public void setInvokeMethod(String invokeMethod){
    this.invokeMethod = invokeMethod;
  }

  /**
   * @return the invokeType
   */
  public int getInvokeType(){
    return invokeType;
  }

  /**
   * @param invokeType the invokeType to set
   */
  public void setInvokeType(int invokeType){
    this.invokeType = invokeType;
  }

  /**
   * @return the seqNo
   */
  public String getSeqNo(){
    return seqNo;
  }

  /**
   * @param seqNo the seqNo to set
   */
  public void setSeqNo(String seqNo){
    this.seqNo = seqNo;
  }

  /**
   * @return the seqNoType
   */
  public int getSeqNoType(){
    return seqNoType;
  }

  /**
   * @param seqNoType the seqNoType to set
   */
  public void setSeqNoType(int seqNoType){
    this.seqNoType = seqNoType;
  }

  /**
   * @return the traceId
   */
  public String getTraceId(){
    return traceId;
  }

  /**
   * @param traceId the traceId to set
   */
  public void setTraceId(String traceId){
    this.traceId = traceId;
  }

  /**
   * @return the logger
   */
  public static Logger getLogger(){
    return logger;
  }

  /*
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString(){
    return "SeqNoBean [" + (invokeId != null ? "invokeId=" + invokeId + ", " : "")
        + (invokeMethod != null ? "invokeMethod=" + invokeMethod + ", " : "") + "invokeType=" + invokeType + ", "
        + (seqNo != null ? "seqNo=" + seqNo + ", " : "") + "seqNoType=" + seqNoType + ", "
        + (traceId != null ? "traceId=" + traceId : "") + "]";
  }

}
