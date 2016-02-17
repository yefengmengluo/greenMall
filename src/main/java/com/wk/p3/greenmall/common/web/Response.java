package com.wk.p3.greenmall.common.web;

import com.wk.p3.greenmall.common.mapper.JsonMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 发送到前端的封装类
 * 用法:
 * <p>
 * response.Success()
 * response.Success(Object data)
 * <p>
 * response.NextError()
 * response.NextError(String message)
 * <p>
 * response.isError()
 * response.isSuccess()
 * <p>
 * Created by cc on 15-12-28.
 */

public abstract class Response implements Serializable {

    abstract public int getStatus();
    abstract public Object getData();


    //success

    public static Success success() {
        return new Success();
    }

    public static Success success(Object data) {
        return new Success(200, data);
    }

    //error

    public static Error nextError(Error lastError,String message) {
        return new Error(lastError.getStatus()-1, message);
    }

    public static Error error(String message) {
        return new Error(-1,message);
    }

    public static Error error(int status,String message) {
        return new Error(status,message);
    }

    public static Error error(int status,String message,Object data) {
        return new Error(status,message,data);
    }

    public static class Success extends Response {
        private int status = 200;
        private Object data = "ok";

        public Success() {
        }

        public Success(int status, Object data) {
            this.data = data;
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

    public static class Error extends Response {
        private int status = 0;
        private String message = "error";
        private Object data;

        public Error() {
        };

        public Error(int status, String message) {
            this.message = message;
            this.status = status;
        }

        public Error(int status, String message,Object data) {
            this.message = message;
            this.status = status;
            this.data = data;
        }

        public int getStatus() {
            return status;
        }

        @Override
        public Object getData() {
            return data;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


    public boolean isError() {
        if (this.getStatus() < 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSuccess() {
        if (this.getStatus() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void render(HttpServletResponse response, Object object) {
        try {
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(JsonMapper.toJsonString(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



