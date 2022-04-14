package co.com.bancolombia.security.usecase.domain;

import co.com.bancolombia.security.model.catoken.request.RequestTokenValidateDTO;
import co.com.bancolombia.security.usecase.util.ConstantTest;

public class RequestTokenValidateMother {

    public static RequestTokenValidateDTO standard() {

        RequestTokenValidateDTO requestDTO=  new RequestTokenValidateDTO();
        requestDTO.setGrantType(ConstantTest.GRANT_TYPE);
        requestDTO.setClientId(ConstantTest.CLIENT_ID);
        requestDTO.setRedirectUri(ConstantTest.REDIRECT_URL);
        requestDTO.setCode(ConstantTest.CA_BASE);
        requestDTO.setAuthorization(ConstantTest.AUTH);

        return requestDTO;
    }

    public static RequestTokenValidateDTO notFound() {

        RequestTokenValidateDTO requestDTO = standard();
        requestDTO.setCode(ConstantTest.CA_NOT_FOUND);
        return  requestDTO;
    }
}
