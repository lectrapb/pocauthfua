package co.com.bancolombia.security.usecase.validateca;

import co.com.bancolombia.security.model.catoken.CaToken;
import co.com.bancolombia.security.model.catoken.gateways.CaTokenFaker;
import co.com.bancolombia.security.model.catoken.request.RequestTokenValidate;
import co.com.bancolombia.security.model.catoken.request.RequestTokenValidateDTO;
import co.com.bancolombia.security.model.exceptions.BusinessException;
import co.com.bancolombia.security.model.logs.Log;
import co.com.bancolombia.security.model.response.ApiResponse;
import co.com.bancolombia.security.model.session.SessionGlobals;
import co.com.bancolombia.security.model.util.Constant;
import co.com.bancolombia.security.model.util.PathConstant;
import co.com.bancolombia.security.usecase.logs.LogsUseCase;
import co.com.bancolombia.security.usecase.util.ValidateTokenRequest;
import lombok.AllArgsConstructor;

import java.net.HttpURLConnection;
import java.util.concurrent.atomic.AtomicBoolean;


@AllArgsConstructor
public class ValidateCaUseCase {


    private LogsUseCase logsUseCase;
    private CaTokenFaker tokenFaker;

    public ApiResponse validateCaToken(RequestTokenValidateDTO requestDTO) {

        RequestTokenValidate request = ValidateTokenRequest.standard(requestDTO);
        AtomicBoolean foundCode = new AtomicBoolean(false);
        SessionGlobals.appId.set(request.getClientId());

        Constant.caList.forEach( (item)->{
            if (item.equals(request.getCode())){
                foundCode.set(true);
            }
        });

        if(!foundCode.get()){
            persistLog(request, Constant.ERROR_NOT_FOUND_CA_CODE);
            throw new BusinessException(Constant.ERROR_NOT_FOUND_CA_CODE);
        }
        persistLog(request, String.valueOf(HttpURLConnection.HTTP_OK));
        CaToken caToken = new CaToken();
        caToken.setCustomerName(tokenFaker.customerNameFaker());
        return createResponse(caToken);
    }

    private void  persistLog(Object request, String status ){
        String data =  logsUseCase.castToString(request);
        Log log = Log.builder()
                 .action(PathConstant.POST_CA_TOKEN)
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
