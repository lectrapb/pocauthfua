package co.com.bancolombia.security.model.exceptions;

import lombok.Getter;

@Getter
public class BusinessException  extends  RuntimeException{



    public BusinessException(String message) {
        super(message);
    }
}
