package co.com.bancolombia.security.api;

import co.com.bancolombia.security.api.domain.RequestTest;
import co.com.bancolombia.security.api.domain.RequestTokenValidateMother;
import co.com.bancolombia.security.api.util.ConstantTest;
import co.com.bancolombia.security.api.util.UtilTest;
import co.com.bancolombia.security.model.catoken.CaToken;
import co.com.bancolombia.security.model.session.SessionGlobals;
import co.com.bancolombia.security.model.catoken.request.RequestTokenValidateDTO;
import co.com.bancolombia.security.model.util.PathConstant;
import co.com.bancolombia.security.usecase.validateca.ValidateCaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureWebMvc
@AutoConfigureMockMvc
@SpringBootTest(classes = CaTokenController.class)
class CaTokenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ValidateCaUseCase useCase;

    @BeforeEach
    void setUp() {
        SessionGlobals.messageId.set(ConstantTest.MESSAGE_ID);
    }


    @Test
    void ca_token_ok_test() throws Exception {
        //GIVEN
        RequestTest<RequestTokenValidateDTO> request = new RequestTest<>(RequestTokenValidateMother.standard());
        when(useCase.validateCaToken(any(RequestTokenValidateDTO.class))).thenReturn(UtilTest.getApiResponseForTest(new CaToken()));
        RequestBuilder requestBuilder = UtilTest.requestBuilder(PathConstant.POST_CA_TOKEN, request.getApiResponse());
        //WHEN
        mockMvc.perform(requestBuilder)
        //THEN
        .andExpect(status().isOk());
    }



}