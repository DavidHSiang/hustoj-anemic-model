package com.zjc.hustoj.file.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;

/**
 * @author david
 */
@Component
public class DefaultFileExplorer {

    @Resource
    private PathParser pathParser;

    public File get(String path){
        String parsedPath = pathParser.parse(path);
        return new File(parsedPath);
    }

    public boolean delete(String path){
        String parsedPath = pathParser.parse(path);
        File file = new File(parsedPath);
        return file.delete();
    }

    public boolean update(){
        return true;
    }

    public boolean add(){
        return false;
    }

    public File trash(){
        return null;
    }

    public File uploadList(){
        return null;
    }
}
