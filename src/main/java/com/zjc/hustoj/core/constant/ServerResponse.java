package com.zjc.hustoj.core.constant;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.*;
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

        private String filename;

        private final String DEFAULT_FILENAME = "未命名文件";

        public DefaultBuilder() {
            this(HttpStatus.OK);
        }

        public DefaultBuilder(HttpStatus httpStatus){
            this.bodyBuilder = ResponseEntity.status(httpStatus);
            this.responseBody = new ResponseBody();
            this.filename = DEFAULT_FILENAME;
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
            FileSystemResource resource = new FileSystemResource(file);
            return this.file(resource);
        }

        @Override
        public ResponseEntity file(InputStream inputStream) {
            final Resource resource = new InputStreamResource(inputStream);
            return this.file(resource);
        }

        @Override
        public ResponseEntity file(byte[] bytes) {
            ByteArrayResource resource = new ByteArrayResource(bytes);
            return this.file(resource);
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
            this.filename = filename;
            return this;
        }

        @Override
        public ResponseEntity file(Resource resource) {
            return this
                    .header("Cache-Control", "no-cache, no-store, must-revalidate")
                    .header("Content-Disposition", "attachment; filename=" + filename)
                    .header("Pragma", "no-cache")
                    .header("Expires", "0")
                    .header("Last-Modified", new Date().toString())
                    .header("ETag", String.valueOf(System.currentTimeMillis()))
                    .body(resource);
        }

        @Override
        public ResponseEntity body(Object body){
            return bodyBuilder.body(body);
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

    }

    public interface FileBuilder extends ResponseBuilder{

        public ResponseEntity file(File file) ;

        public ResponseEntity file(InputStream inputStream) ;

        public ResponseEntity file(byte[] bytes) ;

        public ResponseEntity file(Resource resource);

        public ResponseBuilder filename(String filename);

    }

    public interface ResponseBuilder{
        public ResponseBuilder header(String key, String... val) ;

        public ResponseEntity body(Object body);
    }

    private static DefaultBuilder init(){
        return new DefaultBuilder();
    }

    private static DefaultBuilder httpStatus(HttpStatus httpStatus){
        return new DefaultBuilder(httpStatus);
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

    public static ResponseEntity file(InputStream inputStream) {
        return init().file(inputStream);
    }

    public static ResponseEntity file(String filename, InputStream inputStream) {
        return init().filename(filename).file(inputStream);
    }

    public static ResponseEntity file(ExportInputStream inputStream){
        return init().filename(inputStream.getFilename()).file(inputStream);
    }

    public static ResponseEntity file(byte[] bytes) {
        return init().file(bytes);
    }

    public static ResponseEntity file(String filename, byte[] bytes) {
        return init().filename(filename).file(bytes);
    }

    public static class ExportInputStream extends ByteArrayInputStream {
        private String filename;

        public ExportInputStream(String filename, byte[] buf) {
            super(buf);
            this.filename = filename;
        }

        public ExportInputStream(String filename, byte[] buf, int offset, int length) {
            super(buf, offset, length);
            this.filename = filename;
        }

        public String getFilename() {
            return filename;
        }
    }

}
