package co.com.bancolombia.security.model.catoken.request;

import co.com.bancolombia.security.model.exceptions.BusinessException;
import co.com.bancolombia.security.model.util.Validation;
import co.com.bancolombia.security.model.util.Constant;
import lombok.Getter;


@Getter
public class RequestTokenValidate {


    private String grantType;
    private String clientId;
    private String redirectUri;
    private String code;
    private String authorization;

    public RequestTokenValidate(String grantType, String clientId, String redirectUri, String code, String authorization) {

        if(Validation.isNull(grantType) || Validation.isNull(clientId )||
                Validation.isNull(redirectUri) || Validation.isNull(code) || Validation.isNull(authorization)){
             throw new BusinessException(Constant.ERROR_MISSING_PARAM_CODE);
        }
        this.grantType = grantType;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.code = code;
        this.authorization = authorization;
    }
}
