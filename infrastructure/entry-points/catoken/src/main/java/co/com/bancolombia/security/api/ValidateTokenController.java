package co.com.bancolombia.security.api;



import co.com.bancolombia.security.model.catoken.request.RequestTokenValidateDTO;
import co.com.bancolombia.security.model.response.ApiResponse;
import co.com.bancolombia.security.model.response.RequestData;
import co.com.bancolombia.security.model.util.PathConstant;
import co.com.bancolombia.security.usecase.validatetoken.ValidateTokenUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Api(value="ValidateTokenController", description="Servcio validacion  Token")
public class ValidateTokenController {

     private  final ValidateTokenUseCase useCase;

     @PostMapping(PathConstant.POST_VALIDATE_TOKEN)
     @ApiOperation(value="Valida  valor token ", response = ApiResponse.class)
     @ApiResponses({
             @io.swagger.annotations.ApiResponse(code = 200, message = "Token valido"),
             @io.swagger.annotations.ApiResponse(code = 400, message = "Token invalido")
     })
     private ApiResponse validateToken(@RequestBody RequestData<RequestTokenValidateDTO> request){

         return useCase.validateToken(request.getData()[0]);
     }
}
