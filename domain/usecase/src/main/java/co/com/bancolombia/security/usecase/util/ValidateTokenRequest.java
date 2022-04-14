package co.com.bancolombia.security.usecase.util;

import co.com.bancolombia.security.model.catoken.request.RequestTokenValidate;
import co.com.bancolombia.security.model.catoken.request.RequestTokenValidateDTO;
import co.com.bancolombia.security.model.exceptions.BusinessException;
import co.com.bancolombia.security.model.util.Constant;
import co.com.bancolombia.security.model.util.Validation;

public class ValidateTokenRequest {

    public static  RequestTokenValidate standard(RequestTokenValidateDTO requestDTO) {

        if (Validation.isNull(requestDTO)) {
            throw new BusinessException(Constant.ERROR_MISSING_PARAM_CODE);
        }

        return new RequestTokenValidate(requestDTO.getGrantType(),
                requestDTO.getClientId(),
                requestDTO.getRedirectUri(),
                requestDTO.getCode(),
                requestDTO.getAuthorization());
    }
}
