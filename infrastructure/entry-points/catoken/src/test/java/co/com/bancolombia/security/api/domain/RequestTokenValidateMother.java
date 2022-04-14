package co.com.bancolombia.security.api.domain;

import co.com.bancolombia.security.api.util.ConstantTest;
import co.com.bancolombia.security.model.catoken.request.RequestTokenValidateDTO;

public class RequestTokenValidateMother {

     public static RequestTokenValidateDTO standard(){
         RequestTokenValidateDTO requestDTO =  new RequestTokenValidateDTO();
         requestDTO.setGrantType(ConstantTest.GRANT_TYPE);
         requestDTO.setClientId(ConstantTest.CLIENT_ID);
         requestDTO.setRedirectUri(ConstantTest.REDIRECT_URL);
         requestDTO.setCode(ConstantTest.AUTH_CODE);
         requestDTO.setAuthorization(ConstantTest.AUTH);
         return  requestDTO;
     }
}
