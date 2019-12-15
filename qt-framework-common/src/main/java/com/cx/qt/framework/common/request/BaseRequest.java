package com.cx.qt.framework.common.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ckhero
 * Date: 2019/7/2
 * Time: 2:37 PM
 */
@Data
public class BaseRequest implements Serializable {
    private static final long serialVersionUID = 7330880180230716960L;

    private String sysId;
}
