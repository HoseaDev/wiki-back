package com.hosea.wiki.resp;

public class JsonResponse<T> {
    private String msg;
    private int code;
    private T data;


    public JsonResponse(String msg, int code) {
        this.msg = msg;
        this.code = code;
        this.data = null;
    }

    public JsonResponse(T data) {
        this.data = data;
        this.code = 0;
        this.msg = "成功";
    }

    public static JsonResponse<String> success() {
        return new JsonResponse<>(null);
    }

    public static JsonResponse<String> success(String data) {
        return new JsonResponse<>(data);
    }

    public static JsonResponse<String> fail(int code, String message) {
        return new JsonResponse<>(message, code);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
