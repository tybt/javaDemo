package utils;


import com.example.demo.response.ResultEnum;
import lombok.Data;

@Data
public class ResultCode  {
    private int code;
    private String msg;
    public static ResultCode error(ResultEnum resultEnum){
        ResultCode resultCode=new ResultCode();
        resultCode.setCode(resultEnum.getCode());
        resultCode.setMsg(resultEnum.getMsg());
        return resultCode;
    }

    public static ResultCode error(int code,String msg){
        ResultCode resultCode=new ResultCode();
        resultCode.setCode(code);
        resultCode.setMsg(msg);
        return resultCode;
    }

}
