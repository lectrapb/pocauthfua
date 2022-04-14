package co.com.bancolombia.security.api.filter;

import co.com.bancolombia.security.api.util.ConstantTest;
import co.com.bancolombia.security.model.util.AppParam;
import co.com.bancolombia.security.model.util.Constant;
import co.com.bancolombia.security.model.util.PathConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationInterceptorTest {

    private AuthenticationInterceptor interceptor;

    @Test
    void preHandleTest(){

        AppParam params  = AppParam.builder()
                .apiConnectClientId(ConstantTest.API_CONNECT_CLIENT_ID)
                .apiConnectClientSecret(ConstantTest.API_CONNECT_CLIENT_SECRET)
                .build();
        interceptor = new AuthenticationInterceptor(params);
        MockHttpServletRequest req = new MockHttpServletRequest();

        req.setRequestURI(PathConstant.POST_CA_TOKEN);
        req.addHeader(Constant.CONTENT_TYPE_HEADER, MediaType.APPLICATION_JSON);
        req.addHeader(Constant.CLIENT_ID_HEADER, ConstantTest.API_CONNECT_CLIENT_ID);
        req.addHeader(Constant.CLIENT_SECRET_HEADER,  ConstantTest.API_CONNECT_CLIENT_SECRET);
        req.addHeader(Constant.MESSAGE_ID_HEADER, ConstantTest.MESSAGE_ID);

        MockHttpServletResponse res = new MockHttpServletResponse();
        boolean response = interceptor.preHandle(req, res, null);
        assertTrue(response);
    }

    @Test
    void preHandleOnHealth(){
        AppParam params  = AppParam.builder()
                .apiConnectClientId(ConstantTest.API_CONNECT_CLIENT_ID)
                .apiConnectClientSecret(ConstantTest.API_CONNECT_CLIENT_SECRET)
                .build();
        interceptor = new AuthenticationInterceptor(params);
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setRequestURI(PathConstant.HEALTH);
        MockHttpServletResponse res = new MockHttpServletResponse();
        boolean response = interceptor.preHandle(req, res, null);
        assertTrue(response);
    }

}