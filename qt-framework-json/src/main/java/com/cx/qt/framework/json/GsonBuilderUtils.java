package com.cx.qt.framework.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;


/**
 * Created by IntelliJ IDEA.
 * User: amyyu
 * Date: 2019-09-26
 * Time: 14:26
 */
public class GsonBuilderUtils {

    private static final GsonBuilder INSTANCE = new GsonBuilder();

    public static void GsonBuilder(Type type, Object obj){
        INSTANCE.registerTypeAdapter(type,obj);
    }

    /**
     * create instance
     * @return
     */
    public static Gson create() {
        return INSTANCE.create();
    }
}
