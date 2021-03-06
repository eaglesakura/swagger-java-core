package com.eaglesakura.swagger;

import java.io.IOException;

/**
 * swagger.yamlで定義されたAPIへのリクエストを組み立てるインターフェース.
 */
public interface SwaggerHttpClient {

    /**
     * "${Api.basePath}/path/to/local/api"
     */
    void setApiPath(/* @NonNull */ String path);

    /**
     * Query param
     *
     * @param key   query key(not url encoded!)
     * @param value query value
     */
    void addQueryParam(/* @NonNull */ String key, /* @NonNull */ String value);

    /**
     * GET, POST, PUT...
     */
    void setMethod(/* @NonNull */ String method);

    /**
     * http header
     */
    void addHeader(/* @NonNull */ String key, /* @NonNull */ String value);

    /**
     * upload data
     */
    void setPayload(/* @NonNull */ DataPayload payload);

    /**
     * start api request
     *
     * if no return api(status 201) => parseClass is null.
     *
     * @param parseClass deserialize class
     * @param <T>        result data
     */
    /* @Nullable */
    <T> T fetch(/* @NonNull */ Class<T> parseClass) throws IOException;
}
