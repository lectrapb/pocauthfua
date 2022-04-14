package co.com.bancolombia.security.config;

import co.com.bancolombia.security.auth0.adapter.CaTokenFakerAdapter;
import co.com.bancolombia.security.auth0.adapter.LogAdapter;
import co.com.bancolombia.security.auth0.adapter.ValidateTokenAdapter;
import co.com.bancolombia.security.model.util.AppParam;
import co.com.bancolombia.security.usecase.logs.LogsUseCase;
import co.com.bancolombia.security.usecase.validateca.ValidateCaUseCase;
import co.com.bancolombia.security.usecase.validatetoken.ValidateTokenUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "co.com.bancolombia.security.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {


        @Bean
        public AppParam appParam(@Value("${aws.appSecretName}") String appSecretName){
                return AppParam.builder()
                        .apiConnectClientId("1")
                        .apiConnectClientSecret("1")
                        .build();
        }

        //USE CASE
        @Bean
        public ValidateCaUseCase validateCaUseCase(LogsUseCase logsUseCase){
                return new ValidateCaUseCase(logsUseCase, new CaTokenFakerAdapter());
        }


        @Bean
        public ValidateTokenUseCase validateTokenUseCase(LogsUseCase logsUseCase){
                return  new ValidateTokenUseCase(new ValidateTokenAdapter(),logsUseCase, new CaTokenFakerAdapter() );
        }

        @Bean
        public LogsUseCase logsUseCase(LogAdapter adapter){
                return new LogsUseCase(adapter);
        }


}
