package com.ut.business.common;

import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * controller 增强器
 *
 * @author sam
 * @since 2017/7/17
 */
@ControllerAdvice
public class MyControllerAdvice {


    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BackResult errorHandler(Exception ex) {
        String msg ="";
        System.out.println(ex.getLocalizedMessage());
        if (ex.getLocalizedMessage() != null && ex.getLocalizedMessage().indexOf("result returns more than one elements") != -1) {
            msg = "查询返回实体为多个";
        } else if (ex.getLocalizedMessage() != null && ex.getLocalizedMessage().indexOf("could not execute statement") != -1) {
            msg = "实体于数内容不匹配或关联不匹配";
        } else if (ex.getMessage() == null) {
            msg = "查询返回内容为空";
        }
        BackResult<String> br = new BackResult<>(ex.getMessage() + "::::" + msg);
        br.setMsg("服务器发生异常");
        br.setCode(2);
        ex.printStackTrace();
        return br;
    }


    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    /*@ResponseBody
    @ExceptionHandler(value = MyException.class)
    public Map myErrorHandler(MyException ex) {
        Map map = new HashMap();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        return map;
    }*/

}
