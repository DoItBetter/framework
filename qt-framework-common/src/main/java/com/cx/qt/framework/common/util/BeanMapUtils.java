package com.cx.qt.framework.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: amyyu
 * Date: 2019-08-15
 * Time: 22:12
 */
/**
 * 框架工具类：BeanMapUtils
 * 用法说明：直接调用
 工具类用途：两个bean之间的相互转换，可调用方法map、mapAsList
 */
public class BeanMapUtils {

    public static void map(Object src, Object tar){
        BeanUtils.copyProperties(src, tar);
    }

    public static <S, D> List<D> mapAsList(List<S> srcList, Class<D> tar)
            throws InstantiationException, IllegalAccessException{
        List<D> tarList = new ArrayList<D>();
        if (CollectionUtils.isEmpty(srcList)) {
            return tarList;
        }
        for (S s : srcList) {
            D d = tar.newInstance();
            BeanUtils.copyProperties(s, d);
            tarList.add(d);
        }
        return tarList;
    }

}
