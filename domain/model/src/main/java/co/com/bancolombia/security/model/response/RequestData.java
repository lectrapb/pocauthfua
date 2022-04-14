package co.com.bancolombia.security.model.response;

import lombok.Data;

@Data
public class RequestData <T>{

    private T[] data;
}
