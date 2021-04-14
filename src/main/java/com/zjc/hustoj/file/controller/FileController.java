package com.zjc.hustoj.file.controller;

import com.sun.security.auth.module.UnixSystem;
import com.zjc.hustoj.core.constant.ServerResponse;
import com.zjc.hustoj.core.utils.xml.JAXBUtils;
import com.zjc.hustoj.file.model.FileExplorer;
import com.zjc.hustoj.problem.vo.ProblemDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.Set;


/**
 * @author David Hsiang
 * @date 2021/04/12/7:09 下午
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private FileExplorer fileExplorer;


    @GetMapping(value = "/{path}")
    public ResponseEntity find(@PathVariable("path") String filePath) {
        try {
            // 1. input-path 解析 -> 解析是下载还是查看
            // 2. 将 base-path 与 input-path 拼接
            // 3. 解析是否越权,如: /../../
            // 4. 得到文件
            // 5. 根据 input-path 解析结果 和 文件类型, 响应前端
            return ServerResponse.ok("查询成功", filePath);
        } catch (Exception e) {
            log.error("查询失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    /**
     * Get test case list by problem id
     */
    @GetMapping("/get/{problem-id}")
    public ResponseEntity get(@PathVariable("problem-id") String problemId){
        File file =fileExplorer.get("problemId");
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));

            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return ServerResponse.ok(sbf.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return ServerResponse.ok(sbf.toString());
    }


    /**
     * Get test case by problem id
     */
    @GetMapping("/get/{problem-id}/{test-case-name}")
    public ResponseEntity get(
            @PathVariable("problem-id") String problemId,
            @PathVariable("test-case-name") String testCaseName){
        return null;
    }

    /**
     * download all test case by problem id
     */
    @GetMapping("/download/{problem-id}")
    public ResponseEntity download(@PathVariable("problem-id") String problemId){
        return null;
    }

    public ResponseEntity download(){
        return null;
    }

    public static void main2(String[] args) {

        Path path = Paths.get("/Users/david/Documents/ABC.xml");

        FileOwnerAttributeView foav = Files.getFileAttributeView(path,
                FileOwnerAttributeView.class);
        try {
            UserPrincipal owner = foav.getOwner();

            System.out.println(owner.getName());

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main0(String[] args) {
        try {
            String userName = System.getProperty("user.name");
            String command = "id -u "+userName;
            Process child = Runtime.getRuntime().exec(command);

            // Get the input stream and read from it
            InputStream in = child.getInputStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char)c);
            }
            in.close();
        } catch (IOException e) {
        }
    }

    public static void main_1(String[] args) {
        final long uid = new UnixSystem().getUid();
        final long gid = new UnixSystem().getGid();
        System.out.println(uid);
        System.out.println(gid);
    }

    public static void main(String[] args) {
        Path path = Paths.get("/Users/david/Documents/ABC.xml");
        PosixFileAttributeView posixView = Files.getFileAttributeView(path,
                PosixFileAttributeView.class);
        try{
            PosixFileAttributes attribs = posixView.readAttributes();
            Set<PosixFilePermission> permissions = attribs.permissions();
            // Convert the file permissions into the rwxrwxrwx string form
            String rwxFormPermissions = PosixFilePermissions.toString(permissions);
            // Print the permissions
            System.out.println(rwxFormPermissions);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
