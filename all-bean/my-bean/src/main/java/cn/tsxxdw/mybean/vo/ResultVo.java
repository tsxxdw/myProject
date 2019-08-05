package cn.tsxxdw.mybean.vo;

/**
 * @Author created by dsj
 * @Date 2019/4/22 17:16
 * @Description 返回Vo
 */
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 返回值：
 * code = -2 代表系统原因错误，一般是不会出现这种情况的(例如，数据库连接错误)，请一定要查看message
 * code = -1 代表服务器错误(失败情况下一般会返回：-1)
 * code = 0  代表默认状态,也代表失败，但是正常情况下是不会出现的,如果出现，那么就是逻辑可能有问题
 * code = 1  代表成功
 * 最外层的getCode()，getMessage()方法不能设置为 private,否则在任何转json字符串的地方会导致属性输出错误
 *
 * @param <T> 泛型
 */
@ToString
@Getter
public class ResultVo<T> implements Serializable {
    @Getter
    @AllArgsConstructor
    enum MyEnum {
        DEFAULT(0, "Now you's code is 0,this is not normal"),
        SUCCESS(1, "Already success"),
        FAIL(-1, "Sorry,You fail"),
        FAILMESSAGE_SYSTEM_ERROR(-2, "I'm sorry,System error");
        private int code;
        private String message;
    }

    private int code = MyEnum.DEFAULT.getCode(); //返回码
    private String message = MyEnum.DEFAULT.getMessage();
    private T data = null;
    private String customMessage = "";//自定义返回信息,一般情况下用不到这个属性


    public ResultVo<T> setSuccess(T data) {
        return this.setCode(MyEnum.SUCCESS.code).setMessage(MyEnum.SUCCESS.getMessage()).setData(data);
    }

    public ResultVo<T> setSuccess(String message, T data) {
        return setSuccess(data).setMessage(message);
    }

    public ResultVo<T> setFail(String message) {
        return this.setCode(MyEnum.FAIL.code).setMessage(message);
    }

    public ResultVo<T> setFail(String message, T data) {
        return setFail(message).setData(data);
    }


    public static ResultVo createSimpleSuccessResult() {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(MyEnum.SUCCESS.getCode());
        resultVo.setMessage(MyEnum.SUCCESS.getMessage());
        return resultVo;
    }

    /**
     * 创建一个简单失败的结果
     * @return
     */
    public static ResultVo createSimpleFailResult() {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(MyEnum.FAIL.getCode());
        resultVo.setMessage(MyEnum.FAIL.getMessage());
        return resultVo;
    }

    /**
     * 创建一个系统错误的结果（该结果正常情况下是不会出现的，一般是写代码有问题）
     * @return
     */
    public static ResultVo createServerErrorResult() {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(MyEnum.FAIL.getCode());
        resultVo.setMessage(MyEnum.FAILMESSAGE_SYSTEM_ERROR.getMessage());
        return resultVo;
    }




    private ResultVo setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    private ResultVo setData(T data) {
        this.data = data;
        return this;
    }



    private ResultVo setCode(int code) {
        this.code = code;
        return this;
    }


    /**
     * 设置默认状态
     */
    private void setDefault() {
        this.setCode(MyEnum.DEFAULT.getCode());
        this.setMessage(MyEnum.DEFAULT.getMessage());
        this.setData(data);
    }


    /**
     * @param code    返回码
     * @param data  返回的数据
     * @param message 信息
     * @author tsxxdw
     * 2018
     */
    public ResultVo(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ResultVo() {
        setDefault();
    }

    public boolean isSuccess() {
        if (this.code == MyEnum.SUCCESS.getCode()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isFail() {
        return !isSuccess();
    }

}