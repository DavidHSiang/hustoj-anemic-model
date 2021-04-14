package com.zjc.hustoj.file.model;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileExplorer {
    public File get(String uri){
//        return new File("/Users/david/Documents");
        return new File("/Users/david/Documents/ABC.xml");
    }
}
