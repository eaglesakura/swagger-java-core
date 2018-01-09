package com.eaglesakura.swagger;

import java.io.IOException;
import java.io.InputStream;

/**
 * データ送信を行うためのインターフェース
 */
public interface DataPayload {

    String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";

    String CONTENT_TYPE_JSON = "application/json";

    String CONTENT_TYPE_OCTET_STREAM = "application/octet-stream";

    /**
     * 送信データを取得するためのInputStreamを開く。
     *
     * InputStream#close()はSwaggerHttpClientの実装が行う必要がある。
     */
    InputStream openStream() throws IOException;

    /**
     * bytes of content
     */
    int getContentLength();

    /**
     * Post Data Payload
     */
    String getContentType();
}
