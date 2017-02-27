package com.eaglesakura.swagger;

import java.io.File;

/**
 * File
 */
public class RawFileContent implements FileContent {
    private final File mFile;

    private final String mContentType;

    public RawFileContent(File file) {
        mFile = file;
        mContentType = DataPayload.CONTENT_TYPE_OCTET_STREAM;
    }

    public RawFileContent(String contentType, File file) {
        mContentType = contentType;
        mFile = file;
    }

    public File getFile() {
        return mFile;
    }

    @Override
    public DataPayload toPayload() {
        return new FilePayload(mFile, mContentType);
    }
}
