package com.chou.common_module.exception;

import com.chou.common_module.context.ResponseData;
import com.chou.common_module.context.ResponseDataBuilder;
import com.chou.common_module.context.enums.CommonEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 处理自定义的业务异常
	 * @param req
	 * @param e
	 * @return
	 */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
	public ResponseData bizExceptionHandler(HttpServletRequest req, BizException e){
    	log.error("发生业务异常！原因是：{}",e.getMsg());
    	return ResponseDataBuilder.buildException(e);
    }

	/**
	 * 处理空指针的异常
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value =NullPointerException.class)
	@ResponseBody
	public ResponseData exceptionHandler(HttpServletRequest req, NullPointerException e){
		log.error("发生空指针异常！原因是:",e);
		return ResponseDataBuilder.buildFailData(CommonEnum.BODY_NOT_MATCH);
	}


    /**
        * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
	@ResponseBody
	public ResponseData exceptionHandler(HttpServletRequest req, Exception e){
    	log.error("未知异常！原因是:",e);
       	return ResponseDataBuilder.buildException(CommonEnum.INTERNAL_SERVER_ERROR);
    }
}
