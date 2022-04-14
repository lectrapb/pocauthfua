package co.com.bancolombia.security.usecase.validatetoken;

import co.com.bancolombia.security.model.catoken.gateways.CaTokenFaker;
import co.com.bancolombia.security.model.catoken.gateways.CaTokenRepository;
import co.com.bancolombia.security.model.catoken.request.RequestTokenValidateDTO;
import co.com.bancolombia.security.model.exceptions.BusinessException;
import co.com.bancolombia.security.usecase.logs.LogsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class ValidateTokenUseCaseTest {

    private ValidateTokenUseCase useCase;
    private CaTokenRepository tokenAdapter;
    private LogsUseCase logsUseCase;
    private  CaTokenFaker tokenFaker;

    @BeforeEach
    void setUp(){
        tokenAdapter = mock(CaTokenRepository.class);
        logsUseCase = mock(LogsUseCase.class);
        tokenFaker = mock(CaTokenFaker.class);
        useCase = new ValidateTokenUseCase(tokenAdapter, logsUseCase, tokenFaker);
    }


    @Test
    void validate_token_null_test(){

        //Given
        RequestTokenValidateDTO requestDTO = null;
        //Then
        assertThrows(BusinessException.class, () -> {
             //When
             useCase.validateToken(requestDTO);
        });

    }



}