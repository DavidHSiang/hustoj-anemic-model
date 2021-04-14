package com.zjc.hustoj.file.model;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;

/**
 * @author David Hsiang
 * @date 2021/04/14/12:38 上午
 */
public class FileUtils {

    private final String ROOT_PATH;



    public FileUtils(
            @Value("${fs.root-path}") String path) {
        this.ROOT_PATH = path;
    }

    public File[] listRoots() {
        try {
            SecurityManager security = System.getSecurityManager();
            if (security != null) {
                security.checkRead(ROOT_PATH);
            }
            return new File[] { new File(ROOT_PATH) };
        } catch (SecurityException x) {
            return new File[0];
        }
    }
}
