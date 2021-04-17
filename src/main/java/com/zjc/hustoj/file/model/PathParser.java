package com.zjc.hustoj.file.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author David Hsiang
 * @date 2021/04/17/1:13 上午
 */
@Component
public class PathParser {
    @Value("${fs.root-path}")
    private String baseurl;

    public String parse(String path) {
        return path;
    }
}
