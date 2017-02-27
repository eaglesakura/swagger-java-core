package com.eaglesakura.swagger;

import java.io.IOException;
import java.io.InputStream;

/**
 * upload data payload
 */
public interface DataPayload {

    String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";

    String CONTENT_TYPE_JSON = "application/json";

    String CONTENT_TYPE_OCTET_STREAM = "application/octet-stream";

    /**
     * close in ApiInvoker impl.
     */
    InputStream openStream() throws IOException;

    /**
     * Post Data Payload
     */
    String getContentType();
}
