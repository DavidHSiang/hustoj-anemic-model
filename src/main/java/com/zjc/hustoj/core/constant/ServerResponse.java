package com.zjc.hustoj.core.constant;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * @author David Hsiang
 * @email ls5129469@163.com
 */
public class ServerResponse{

    public static ResponseEntity file(File file) {
        return
            init()
                .header("Cache-Control", "no-cache, no-store, must-revalidate")
                .header("Content-Disposition", "attachment; filename=" + file.getName())
                .header("Pragma", "no-cache")
                .header("Expires", "0")
                .header("Last-Modified", new Date().toString())
                .header("ETag", String.valueOf(System.currentTimeMillis()))
            .file(file);
    }

    public static ResponseEntity file(MemoryFileOutputStream file) {
        return
                init()
                        .header("Cache-Control", "no-cache, no-store, must-revalidate")
                        .header("Content-Disposition", "attachment; filename=" + file.getFileName())
                        .header("Pragma", "no-cache")
                        .header("Expires", "0")
                        .header("Last-Modified", new Date().toString())
                        .header("ETag", String.valueOf(System.currentTimeMillis()))
                        .file(file);
    }

    @Data
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private static class ResponseBody implements Serializable {
        private int status;
        private String msg;
        private Object data;
    }

    public static class Builder{

        private ResponseEntity.BodyBuilder bodyBuilder;

        private ResponseBody responseBody;

        public Builder() {
            this.bodyBuilder = ResponseEntity.ok();
            this.responseBody = new ResponseBody();
        }

        public ResponseEntity ok() {
            return ok(null);
        }

        public ResponseEntity ok(Object data) {
            return status(ResponseCode.SUCCESS.getCode(), data);
        }

        public ResponseEntity ok(String msg, Object data){
            return this.msg(msg).ok(data);
        }

        public ResponseEntity error() {
            return error(null);
        }

        public ResponseEntity error(Object data) {
            return status(ResponseCode.ERROR.getCode(), data);
        }

        public ResponseEntity error(String msg, Object data){
            return this.msg(msg).error(data);
        }

        public Builder msg(String msg){
            this.responseBody.msg = msg;
            return this;
        }

        public Builder header(String key, String... val) {
            this.bodyBuilder.header(key, val);
            return this;
        }

        public ResponseEntity status(int status, Object data) {
            this.responseBody.status = status;
            this.responseBody.data = data;
            return bodyBuilder.body(responseBody);
        }

        public ResponseEntity file(File file) {
            FileSystemResource body = new FileSystemResource(file);
            return bodyBuilder.body(body);
        }

        public ResponseEntity file(OutputStream outputStream) {
            return bodyBuilder.body(outputStream);
        }

        public ResponseEntity status(int status) {
            return status(status,null);
        }

    }


    private static Builder init(){
        return new Builder();
    }

    public static Builder header(String key, String... val) {
        return init().header(key, val);
    }

    public static ResponseEntity ok(){
        return init().ok();
    }

    public static ResponseEntity ok(Object data){
        return init().ok(data);
    }

    public static ResponseEntity ok(String msg, Object data){
        return init().ok(msg, data);
    }

    public static ResponseEntity error(){
        return init().error();
    }

    public static ResponseEntity error(Object data){
        return init().error(data);
    }

    public static ResponseEntity error(String msg, Object data){
        return init().error(msg, data);
    }

    public static ResponseEntity okMsg(String msg){
        return init().msg(msg).ok();
    }

    public static ResponseEntity errorMsg(String msg){
        return init().msg(msg).error();
    }


}
