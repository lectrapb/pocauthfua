package co.com.bancolombia.security.usecase.validateca;

import co.com.bancolombia.security.model.catoken.gateways.CaTokenFaker;
import co.com.bancolombia.security.model.exceptions.BusinessException;
import co.com.bancolombia.security.model.response.ApiResponse;
import co.com.bancolombia.security.model.catoken.request.RequestTokenValidateDTO;
import co.com.bancolombia.security.model.util.Constant;
import co.com.bancolombia.security.usecase.domain.RequestTokenValidateMother;
import co.com.bancolombia.security.usecase.logs.LogsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ValidateCaUseCaseTest {

    private ValidateCaUseCase useCase;
    private LogsUseCase logsUseCase;
    private CaTokenFaker caTokenFaker;
    @BeforeEach
    void setUp() {
        logsUseCase = mock(LogsUseCase.class);

        useCase = new ValidateCaUseCase(logsUseCase, caTokenFaker);
    }


    @Test
    void  validate_catoken_null_test(){
        //Given
        RequestTokenValidateDTO requestDTO = null;

        //Then
        Exception exception = assertThrows(BusinessException.class, () ->{
             //WHen
            useCase.validateCaToken(requestDTO);
        } );
        assertEquals(Constant.ERROR_MISSING_PARAM_CODE, exception.getLocalizedMessage());
    }


    @Test
    @Disabled
    void  validate_catoken_ca_ok_test(){
        //Given
        RequestTokenValidateDTO  requestDTO = RequestTokenValidateMother.standard();
        //WHen
        ApiResponse response = useCase.validateCaToken(requestDTO);
        //Then
        assertNotNull(response);
    }


    @Test
    void validate_catoken_ca_notfound_test(){

        //Given
        RequestTokenValidateDTO  requestDTO = RequestTokenValidateMother.notFound();
        //Then
        Exception exception =  assertThrows(BusinessException.class, ()->{
            //When
            useCase.validateCaToken(requestDTO);
        });
        assertEquals(Constant.ERROR_NOT_FOUND_CA_CODE, exception.getLocalizedMessage());

    }
}