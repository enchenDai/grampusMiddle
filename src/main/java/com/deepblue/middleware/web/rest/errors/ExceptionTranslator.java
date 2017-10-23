package com.deepblue.middleware.web.rest.errors;



import com.deepblue.middleware.config.Constants;
import com.deepblue.middleware.exception.ObjectNotFoundException;
import com.deepblue.middleware.exception.SystemException;
import com.deepblue.middleware.web.rest.vm.ResultCode;
import com.deepblue.middleware.web.rest.vm.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 */
@ControllerAdvice
public class ExceptionTranslator {

    private final Logger log = LoggerFactory.getLogger(ExceptionTranslator.class);

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseBody
    public WebResult processNotFoundError(ObjectNotFoundException e) {
        log.error(e.getMessage());
        String message = messageSource.getMessage(ResultCode.NOT_FOUND + "", new String[]{}, ResultCode.UNKNOW_SOURCE, e.getLocale());
        return new WebResult(ResultCode.NOT_FOUND, message + ((Class) e.getObject()).getSimpleName(), null);
    }


//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseBody
//    public WebResult processAccessDeniedException(AccessDeniedException e) {
//        log.error(e.getMessage());
//        String message = messageSource.getMessage(ResultCode.FORBIDDEN + "", new String[]{}, ResultCode.UNKNOW_SOURCE, Constants.DEFAULT_LOCALE);
//        return new WebResult(ResultCode.FORBIDDEN, message, null);
//    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public WebResult processMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        exception.printStackTrace();
        String message = messageSource.getMessage(ResultCode.NOT_ALLOWED + "", new String[]{}, ResultCode.UNKNOW_SOURCE, Constants.DEFAULT_LOCALE);
        return new WebResult(ResultCode.NOT_ALLOWED, message, null);
    }

    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public WebResult processSystemException(SystemException e) {
        log.error(e.getMessage());
        String message = messageSource.getMessage(e.getCode() + "", new String[]{}, ResultCode.UNKNOW_SOURCE, e.getLocale());
        if (ResultCode.UNKNOW_SOURCE.equals(message)) {
            message = e.getMessage();
        }
        return new WebResult(e.getCode(), message, null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WebResult processException(Exception ex) {
        ex.printStackTrace();
        String errorMessage = messageSource.getMessage(ResultCode.SYSTEM_ERROR + "", new String[]{}, ResultCode.UNKNOW_SOURCE, Constants.DEFAULT_LOCALE);
        return new WebResult(ResultCode.SYSTEM_ERROR, errorMessage, ex.getMessage());
    }
}
