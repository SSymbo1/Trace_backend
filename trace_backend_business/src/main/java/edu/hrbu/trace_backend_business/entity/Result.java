package edu.hrbu.trace_backend_business.entity;

import edu.hrbu.trace_backend_business.entity.enums.Statue;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class Result {
    private Integer code;
    private boolean success;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public static Result ok(String message) {
        Result result = new Result();
        result.setCode(Statue.SUCCESS.getValue());
        result.setSuccess(true);
        result.setMessage(message);
        return result;
    }

    public static Result fail(String message) {
        Result result = new Result();
        result.setCode(Statue.FAIL.getValue());
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    public static Result custom(String message, Integer code, boolean success) {
        Result result = new Result();
        result.setCode(code);
        result.setSuccess(success);
        result.setMessage(message);
        return result;
    }
}
