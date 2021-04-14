package com.zjc.hustoj.core.constant;


import java.io.ByteArrayOutputStream;

/**
 * @author David Hsiang
 * @date 2021/04/14/11:44 下午
 */
public class MemoryFileOutputStream extends ByteArrayOutputStream {
    private String filename;

    public MemoryFileOutputStream(String filename) {
        this.filename = filename;
    }

    public MemoryFileOutputStream(int size, String filename) {
        super(size);
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
