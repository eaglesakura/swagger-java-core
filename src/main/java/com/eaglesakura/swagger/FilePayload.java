package com.eaglesakura.swagger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FilePayload implements DataPayload {
    private final File mFile;

    private final String mContentType;

    public FilePayload(File file, String contentType) {
        mFile = file;
        mContentType = contentType;
    }

    @Override
    public int getContentLength() {
        return (int) mFile.length();
    }

    @Override
    public InputStream openStream() throws IOException {
        return new FileInputStream(mFile);
    }

    @Override
    public String getContentType() {
        return mContentType;
    }
}
