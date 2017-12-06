package com.ut.business.common;

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
        BackResult<String> br = new BackResult<>("服务器发生异常");
        br.setMsg(ex.getMessage());
        br.setCode(2);
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
