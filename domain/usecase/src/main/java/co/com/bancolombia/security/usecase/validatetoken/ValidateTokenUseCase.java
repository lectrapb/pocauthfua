package co.com.bancolombia.security.usecase.validatetoken;

import co.com.bancolombia.security.model.catoken.CaTokenVerify;
import co.com.bancolombia.security.model.catoken.gateways.CaTokenFaker;
import co.com.bancolombia.security.model.catoken.gateways.CaTokenRepository;
import co.com.bancolombia.security.model.catoken.request.RequestTokenValidate;
import co.com.bancolombia.security.model.catoken.request.RequestTokenValidateDTO;
import co.com.bancolombia.security.model.exceptions.BusinessException;
import co.com.bancolombia.security.model.logs.Log;
import co.com.bancolombia.security.model.response.ApiResponse;
import co.com.bancolombia.security.model.session.SessionGlobals;
import co.com.bancolombia.security.model.util.PathConstant;
import co.com.bancolombia.security.usecase.logs.LogsUseCase;
import co.com.bancolombia.security.usecase.util.ValidateTokenRequest;
import lombok.AllArgsConstructor;

import java.net.HttpURLConnection;

@AllArgsConstructor
public class ValidateTokenUseCase {

    private  CaTokenRepository tokenAdapter;
    private  LogsUseCase logsUseCase;
    private  CaTokenFaker tokenFaker;


    public ApiResponse validateToken(RequestTokenValidateDTO requestDTO){

        RequestTokenValidate request = ValidateTokenRequest.standard(requestDTO);
        String responseToken = tokenAdapter.validateToken(request.getAuthorization());
        //String responseToken = tokenAdapter.validateTokenPCD(request.getAuthorization());
        SessionGlobals.appId.set(request.getClientId());

        if(!responseToken.equals(String.valueOf(HttpURLConnection.HTTP_OK))){
            persistLog(request, responseToken);
            throw new BusinessException(responseToken);
        }
        CaTokenVerify caToken = new CaTokenVerify();
        caToken.setCustomerName(tokenFaker.customerNameFaker());
        caToken.setSessionToken(tokenAdapter.decodeToken(request.getAuthorization()));
        persistLog(request, String.valueOf(HttpURLConnection.HTTP_OK));

        return  createResponse(caToken);
    }

    private void  persistLog(Object request, String status){
        String data =  logsUseCase.castToString(request);
        Log log = Log.builder()
                .action(PathConstant.POST_VALIDATE_TOKEN)
                .status(status)
                .data(data)
                .build();
        logsUseCase.savelog(log);

    }

    private ApiResponse createResponse(Object token){
        Object[] arrayObject = {token};
        return ApiResponse.createOnSuccess()
                .setData( arrayObject);
    }



}
