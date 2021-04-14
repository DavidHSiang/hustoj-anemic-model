package com.zjc.hustoj.core.constant;


import java.io.ByteArrayOutputStream;

/**
 * @author David Hsiang
 * @date 2021/04/14/11:44 下午
 */
public class MemoryFileOutputStream extends ByteArrayOutputStream {
    private String fileName;

    public MemoryFileOutputStream(String fileName) {
        this.fileName = fileName;
    }

    public MemoryFileOutputStream(int size, String fileName) {
        super(size);
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
