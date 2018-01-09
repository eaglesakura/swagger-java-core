package com.eaglesakura.swagger;

import com.eaglesakura.json.JSON;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class SwaggerUtil {
    private SwaggerUtil() {
    }

    /**
     * URI Encoding
     */
    public static String escapeString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            try {
                return URLEncoder.encode(obj.toString(), "UTF-8");
            } catch (Exception e) {
                return obj.toString();
            }
        }
    }

    /**
     * byte配列から送信用Payloadを生成する
     *
     * @param contentType Content-Type header
     * @param buffer      Data Buffer
     */
    public static DataPayload newByteBufferPayload(String contentType, byte[] buffer) {
        return new ByteArrayPayload(contentType, buffer);
    }

    /**
     * オブジェクトをJsonシリアライズし、送信用Payloadを生成する.
     *
     * シリアライズには Google Gson が使用される.
     *
     * @param obj Jsonシリアライズ対象
     */
    public static DataPayload newJsonPayload(Object obj) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream(1024)) {
            JSON.encode(os, obj);
            return newByteBufferPayload(DataPayload.CONTENT_TYPE_JSON, os.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Formデータ送信用Payloadを生成する
     *
     * @param form Formデータとして送信するKey-Value
     */
    public static DataPayload newFormPayload(Map<String, String> form) {
        StringBuilder buffer = new StringBuilder();

        for (Map.Entry<String, String> entry : form.entrySet()) {
            buffer.append(escapeString(entry.getKey()));
            buffer.append("=");
            buffer.append(escapeString(entry.getValue()));
            buffer.append("&");
        }

        return newByteBufferPayload(DataPayload.CONTENT_TYPE_FORM, buffer.toString().getBytes());
    }

    /**
     * URLのパスを合成する
     *
     * base: "http://example.com"  local: "example"  -> http://example.com/example
     * base: "http://example.com/" local: "example"  -> http://example.com/example
     * base: "http://example.com/" local: "/example" -> http://example.com/example
     * base: "http://example.com"  local: "/example" -> http://example.com/example
     */
    public static String addPath(String base, String local) {
        if (base.endsWith("/")) {
            base = base.substring(0, base.length() - 1);
        }
        if (local.startsWith("/")) {
            local = local.substring(1);
        }

        return base + "/" + local;
    }

    public static <T> Object fetch(SwaggerHttpClient client, String type, Class<T> decodeClass, Class<T[]> arrayClass) throws IOException {
        if ("array".equals(type) || "list".equals(type)) {
            T[] array = client.fetch(arrayClass);
            if (array == null) {
                return null;
            }

            List<T> list = new ArrayList<>(array.length);
            for (T obj : array) {
                list.add(obj);
            }
            return list;
        } else {
            return client.fetch(decodeClass);
        }
    }

    public static String parameterToString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return obj.toString();
        }
    }
}
