package com.cx.qt.framework.common.bean.servicebean;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: ckhero
 * Date: 2019/7/7
 * Time: 7:32 PM
 */
@Data
public class BaseQryReqSerBean extends BaseReqSerBean {
    private Integer offset;

    private Integer limit;
}
