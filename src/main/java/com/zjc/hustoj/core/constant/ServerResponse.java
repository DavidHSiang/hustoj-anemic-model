package com.zjc.hustoj.core.constant;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import java.io.File;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * @author David Hsiang
 * @email ls5129469@163.com
 */
public class ServerResponse{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class ResponseBody implements Serializable {
        private int status;
        private String msg;
        private Object data;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

    public static class DefaultBuilder implements FileBuilder, ResponseBodyBuilder {

        private ResponseEntity.BodyBuilder bodyBuilder;

        private ResponseBody responseBody;

        public DefaultBuilder() {
            this.bodyBuilder = ResponseEntity.ok();
            this.responseBody = new ResponseBody();
        }

        @Override
        public ResponseEntity ok() {
            return ok(null);
        }

        @Override
        public ResponseEntity ok(Object data) {
            return status(ResponseCode.SUCCESS.getCode(), data);
        }

        @Override
        public ResponseEntity ok(String msg, Object data){
            return this.msg(msg).ok(data);
        }

        @Override
        public ResponseEntity error() {
            return error(null);
        }

        @Override
        public ResponseEntity error(Object data) {
            return status(ResponseCode.ERROR.getCode(), data);
        }

        @Override
        public ResponseEntity error(String msg, Object data){
            return this.msg(msg).error(data);
        }

        @Override
        public ResponseEntity status(int status, Object data) {
            this.responseBody.status = status;
            this.responseBody.data = data;
            return bodyBuilder.body(responseBody);
        }

        @Override
        public ResponseEntity file(File file) {
            FileSystemResource body = new FileSystemResource(file);
            return bodyBuilder.body(body);
        }

        @Override
        public ResponseEntity file(OutputStream outputStream) {
            return bodyBuilder.body(outputStream);
        }

        @Override
        public ResponseEntity status(int status) {
            return status(status,null);
        }

        @Override
        public DefaultBuilder header(String key, String... val) {
            this.bodyBuilder.header(key, val);
            return this;
        }

        @Override
        public ResponseBodyBuilder msg(String msg){
            this.responseBody.msg = msg;
            return this;
        }

        @Override
        public FileBuilder filename(String filename) {
            return this
                    .header("Cache-Control", "no-cache, no-store, must-revalidate")
                    .header("Content-Disposition", "attachment; filename=" + filename)
                    .header("Pragma", "no-cache")
                    .header("Expires", "0")
                    .header("Last-Modified", new Date().toString())
                    .header("ETag", String.valueOf(System.currentTimeMillis()));
        }

    }

    public interface ResponseBodyBuilder extends ResponseBuilder{

        public ResponseEntity ok() ;

        public ResponseEntity ok(Object data) ;

        public ResponseEntity ok(String msg, Object data);

        public ResponseEntity error() ;

        public ResponseEntity error(Object data) ;

        public ResponseEntity error(String msg, Object data);

        public ResponseEntity status(int status, Object data) ;

        public ResponseEntity status(int status) ;

        public ResponseBuilder msg(String msg);

        public ResponseBuilder header(String key, String... val) ;

    }

    public interface FileBuilder extends ResponseBuilder{

        public ResponseEntity file(File file) ;

        public ResponseEntity file(OutputStream outputStream) ;

        public ResponseBuilder header(String key, String... val) ;

        public ResponseBuilder filename(String filename);

    }

    public interface ResponseBuilder{

    }

    private static DefaultBuilder init(){
        return new DefaultBuilder();
    }

    public static DefaultBuilder header(String key, String... val) {
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

    public static ResponseEntity file(File file) {
        return init()
                .filename(file.getName())
                .file(file);
    }

    public static ResponseEntity file(MemoryFileOutputStream file) {
        return init()
                .filename(file.getFilename())
                .file(file);
    }
}
