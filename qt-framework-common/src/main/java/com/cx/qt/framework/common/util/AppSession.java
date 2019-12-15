package com.cx.qt.framework.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: amyyu
 * Date: 2019-08-15
 * Time: 22:19
 */

public class AppSession extends HashMap<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(AppSession.class);

    public AppSession(String invokeNo){
        setInvokeNo(invokeNo);
    }

    public void setInvokeNo(String invokeNo){
        this.put(AppContextConstant.INVOKE_KEY, invokeNo);
        this.put(AppContextConstant.INVOKE_TYPE, AppContextConstant.INVOKE_TYPE_COMMON);
    }

    public String getInvokeNo(){
        return this.get(AppContextConstant.INVOKE_KEY);
    }

    public String getInvokeType(){
        return this.get(AppContextConstant.INVOKE_TYPE);
    }

    /*
     * @see java.lang.Object#clone()
     */
    @Override
    public AppSession clone(){
        AppSession newSession = null;
        newSession = (AppSession) super.clone();

        for (Entry<String, String> e : this.entrySet()) {
            newSession.put(e.getKey(), e.getValue());
        }

        return newSession;
    }
}
