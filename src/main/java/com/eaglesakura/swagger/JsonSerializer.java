package com.eaglesakura.swagger;

import java.io.IOException;
import java.io.OutputStream;

public interface JsonSerializer {

    /**
     * Object serialize to byte-array.
     *
     * @param os  output
     * @param obj source data-transfer-object.
     * @return json
     */
    void serialize(OutputStream os, Object obj) throws IOException;
}
