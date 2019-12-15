package com.cx.qt.framework.common.util;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: ckhero
 * Date: 2019/6/4
 * Time: 4:50 PM
 */
public class CalculateUtils {

    public static BigDecimal sumBigDecimal(BigDecimal... objs) {
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal obj: objs) {
            obj = null == obj ? BigDecimal.ZERO : obj;
            result = result.add(obj);
        }
        return  result;
    }

    public static BigDecimal sumBigDecimalWithScale(BigDecimal... objs) {
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal obj: objs) {
            obj = null == obj ? BigDecimal.ZERO : obj;
            result = result.add(obj);
        }
        return setScaleQt(result);
    }

    public static BigDecimal setScaleQt(BigDecimal  numerical) {
        if (null == numerical) {
            return BigDecimal.ZERO;
        }
        return numerical.setScale(CommonConstant.SCALE_DEFAULT, CommonConstant.ROUND_DEFAULT);
    }

    public static BigDecimal divide(BigDecimal dividend, Integer divisor) {

        BigDecimal divisorBigDecimal = null == divisor ? BigDecimal.ZERO: new BigDecimal(divisor);
        //除数和被除数为0 直接返回
        if (null == dividend || divisorBigDecimal.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        return dividend.divide(divisorBigDecimal, CommonConstant.SCALE_DEFAULT, CommonConstant.ROUND_DEFAULT);
    }

    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {

        //除数和被除数为0 直接返回
        if (null == dividend || null == divisor || divisor.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        return dividend.divide(divisor, CommonConstant.SCALE_DEFAULT, CommonConstant.ROUND_DEFAULT);
    }

    public static BigDecimal multiply(BigDecimal  multiplier, BigDecimal multiplicand) {
        if (null == multiplier || null == multiplicand) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = multiplier.multiply(multiplicand);
        return setScaleQt(result);
    }

    public static BigDecimal multiply(BigDecimal... objs) {
        BigDecimal res = BigDecimal.ONE;
        for (BigDecimal obj : objs) {
            if (null == obj) {
                return BigDecimal.ZERO;
            }
            res = res.multiply(obj);
        }
        return setScaleQt(res);
    }

    public static BigDecimal multiply(BigDecimal  multiplier, Integer multiplicand) {
        BigDecimal multiplicandBig = null == multiplicand ? BigDecimal.ZERO : new BigDecimal(multiplicand);
        if (null == multiplier || multiplicandBig.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = multiplier.multiply(multiplicandBig);
        return setScaleQt(result);
    }

    public static Integer subtract(Integer subtractor, Integer... objs) {
        subtractor =  null == subtractor? CommonConstant.INTEGER_ZERO : subtractor;
        for (Integer obj: objs) {
            obj = null == obj? CommonConstant.INTEGER_ZERO : obj;
            subtractor -= obj;
        }
        return subtractor;
    }

    public static BigDecimal subtract(BigDecimal subtractor, BigDecimal... objs) {
        subtractor =  null == subtractor? BigDecimal.ZERO : subtractor;
        for (BigDecimal obj: objs) {
            obj = null == obj? BigDecimal.ZERO : obj;
            subtractor = subtractor.subtract(obj);
        }
        return subtractor;
    }

    /**
     *
     * @param divisor 除数
     * @param dividend 被除数
     * @return
     */
    public static BigDecimal calcReturns(BigDecimal divisor, BigDecimal dividend) {
        if (null == dividend || null == divisor || BigDecimal.ZERO.equals(dividend)) {
            return BigDecimal.ZERO;
        }
        BigDecimal diff = divisor.subtract(dividend);
        return diff.divide(dividend, CommonConstant.ROUND_DEFAULT);
    }
}
