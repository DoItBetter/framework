package com.cx.qt.framework.http.apache;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * copy from {@link org.apache.http.impl.client.BasicResponseHandler}
 *
 * @author danol
 * @Classname Utf8ResponseHandler
 * @Description TODO
 * @Date 2019/9/18 4:44 PM
 */
public class Utf8ResponseHandler implements ResponseHandler<String> {

    public static final ResponseHandler<String> INSTANCE = new Utf8ResponseHandler();

    @Override
    public String handleResponse(final HttpResponse response) throws IOException {
        final StatusLine statusLine = response.getStatusLine();
        final HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= 300) {
            EntityUtils.consume(entity);
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        }
        return entity == null ? null : EntityUtils.toString(entity, Consts.UTF_8);
    }
}