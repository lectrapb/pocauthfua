package co.com.bancolombia.security.exceptions;

import co.com.bancolombia.security.model.exceptions.BusinessException;
import co.com.bancolombia.security.model.response.ApiResponse;
import co.com.bancolombia.security.model.session.SessionGlobals;
import co.com.bancolombia.security.model.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionHandler.class);



    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<ApiResponse> handleConflict(RuntimeException ex, WebRequest request) {
        LOG.error("Internal Server Error", ex);

        ApiResponse apiResponse =
                ApiResponse.createOnError(Constant.UNHANDLED_ERROR_CODE);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(Constant.MESSAGE_ID_HEADER, SessionGlobals.messageId.get())
                .body(apiResponse);
    }


    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<ApiResponse> handleBusinessConflict(BusinessException ex, WebRequest request) {
        LOG.error("Business Error {} - {}: {}", SessionGlobals.messageId.get(),
                SessionGlobals.messageId.get(), ex.getMessage());


        ApiResponse apiResponse =
                ApiResponse.createOnError(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .header(Constant.MESSAGE_ID_HEADER, SessionGlobals.messageId.get())
                .body(apiResponse);
    }


}
