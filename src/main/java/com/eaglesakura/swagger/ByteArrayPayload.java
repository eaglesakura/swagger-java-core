package com.eaglesakura.swagger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteArrayPayload implements DataPayload {
    private final byte[] mPostBuffer;

    private final String mContentType;

    public ByteArrayPayload(String contentType, byte[] postBuffer) {
        mPostBuffer = postBuffer;
        mContentType = contentType;
    }

    @Override
    public int getContentLength() {
        return mPostBuffer.length;
    }

    @Override
    public InputStream openStream() throws IOException {
        return new ByteArrayInputStream(mPostBuffer);
    }

    @Override
    public String getContentType() {
        return mContentType;
    }
}
