package com.zjc.hustoj.file.model;

import java.io.File;

/**
 * @author David Hsiang
 * @date 2021/04/17/1:22 上午
 */
public interface FileExplorer {
    /**
     * 根据路径得到文件
     */
    public File get(String path);

    /**
     * 删除文件
     */
    public boolean delete(String path);

    public boolean update();

    public boolean add();

    public File trash();
}
