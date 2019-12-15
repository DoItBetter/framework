package com.cx.qt.framework.common.util;

import com.cx.qt.framework.common.util.seq.SysSeqNoManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by IntelliJ IDEA.
 * User: amyyu
 * Date: 2019-08-15
 * Time: 22:21
 */

@Lazy(false)
@Component
public class AppContextManager {

    private static final Logger logger = LoggerFactory.getLogger(AppContextManager.class);

    private static AppContextManager appContext;

    @PostConstruct
    public void init(){
        setAppContextManager(this);
    }

    public static void setAppContextManager(AppContextManager appContext){
        AppContextManager.appContext = appContext;
        AppContextManager.appContext.sysSeqNoManager = appContext.sysSeqNoManager;
    }

    @Autowired
    private SysSeqNoManager sysSeqNoManager;

    private static ThreadLocal<AppSession> appThread = new ThreadLocal<AppSession>();

    public static AppSession getCurAppSession(){
        AppSession as = getAppSession(false);
        return as;
    }

    public static void bindNewInvokeNo(){
        getAppSession(true);
    }

    public static String getCurInvokeId(){
        AppSession as = getCurAppSession();
        if (null == as) {
            as = getAppSession(true);
        }

        if (null != as) {
            return as.getInvokeNo();
        }
        return null;
    }

    private static AppSession getAppSession(boolean bindingNew){
        AppSession as = appThread.get();
        if ((null == as) || (bindingNew)) {
            as = createAppSession();
        }
        return as;
    }

    private static AppSession createAppSession(){
        if (null != appContext && null != appContext.sysSeqNoManager) {
            String invokeNo = appContext.sysSeqNoManager.getInvokeNo();
            AppSession as = new AppSession(invokeNo);
            appThread.set(as);
            return as;
        }

        return null;
    }
}
