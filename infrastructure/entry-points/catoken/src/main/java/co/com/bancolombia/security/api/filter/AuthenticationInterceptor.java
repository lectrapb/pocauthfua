package co.com.bancolombia.security.api.filter;


import co.com.bancolombia.security.model.exceptions.BusinessException;
import co.com.bancolombia.security.model.session.SessionGlobals;
import co.com.bancolombia.security.model.util.AppParam;
import co.com.bancolombia.security.model.util.Constant;
import co.com.bancolombia.security.model.util.PathConstant;
import co.com.bancolombia.security.model.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("validateGlobalInterceptor")
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final AppParam appParam;

    @Autowired
    public AuthenticationInterceptor(AppParam appParams) {
        this.appParam = appParams;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {


        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        if(path.equals(PathConstant.HEALTH )||
           path.equals(PathConstant.POST_REPORT_LOGS)){
            return true;
        }

        //Obtengo los 3 headers obligatorios para toda peticion
        String clientId = request.getHeader(Constant.CLIENT_ID_HEADER);
        String clientSecret = request.getHeader(Constant.CLIENT_SECRET_HEADER);
        String messageId = request.getHeader(Constant.MESSAGE_ID_HEADER);

        //Valido obligatoriedad y formato de los header message-id y clientSecret
        SessionGlobals.messageId.set(messageId);
        validateHeaders(messageId, clientSecret);



        if(( Validation.isEmpty(clientId) || Validation.isEmpty(clientSecret)) ||
                !clientId.equalsIgnoreCase(appParam.getApiConnectClientId())  ||
                !clientSecret.equalsIgnoreCase(appParam.getApiConnectClientSecret())) {

            throw new BusinessException(Constant.CLIENT_NOT_AUTHORIZED);
        }

        return true;
    }


    private static void validateHeaders(String messageId, String clientSecret){

        if (Validation.isEmpty(messageId) ||
                Validation.isEmpty(clientSecret) ) {
            throw new BusinessException(Constant.MISSING_HEADERS_CODE);
        }

    }


}
