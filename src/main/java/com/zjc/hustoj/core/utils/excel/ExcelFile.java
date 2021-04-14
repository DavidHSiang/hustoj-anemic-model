package com.zjc.hustoj.core.utils.excel;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author David Hsiang
 * @date 2021/04/12/1:34 下午
 */
public class ExcelFile implements MultipartFile {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public boolean isExcel2003() {
        return this.getOriginalFilename().matches("^.+\\.(?i)(xls)$");
    }

    public boolean isExcel2007() {
        return this.getOriginalFilename().matches("^.+\\.(?i)(xlsx)$");
    }

    @Override
    public String getName() {
        return this.file.getName();
    }

    @Override
    public String getOriginalFilename() {
        return this.file.getOriginalFilename();
    }

    @Override
    public String getContentType() {
        return this.file.getContentType();
    }

    @Override
    public boolean isEmpty() {
        return this.file.isEmpty();
    }

    @Override
    public long getSize() {
        return this.file.getSize();
    }

    @Override
    public byte[] getBytes() throws IOException {
        return this.file.getBytes();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.file.getInputStream();
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {
        this.file.transferTo(file);
    }

}
