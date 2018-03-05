package cn.magicwindow.pingan.util;


import cn.magicwindow.pingan.common.dto.Result;

public class ResultUtil {

    public static Result success(Object object){
        Result result = new Result();
        result.setCode("200");
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(String code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static Result error(String msg){
        return error("2001",msg);
    }
    
    public static Result login_error(String msg){
    	return error("3001",msg);
    }
}
