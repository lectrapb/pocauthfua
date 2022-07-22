package co.com.bancolombia.security.api;


import co.com.bancolombia.security.model.catoken.request.RequestTokenValidateDTO;
import co.com.bancolombia.security.model.response.ApiResponse;
import co.com.bancolombia.security.model.response.RequestData;
import co.com.bancolombia.security.model.util.PathConstant;
import co.com.bancolombia.security.usecase.validateca.ValidateCaUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Api(value="CaTokenController", description="Servcio validacion CD X Token")
public class CaTokenController {

    private final ValidateCaUseCase useCase;

    @PostMapping(PathConstant.POST_CA_TOKEN)
    @ApiOperation(value="Valida  valor CA ", response = ApiResponse.class)
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "Ca valido"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Ca invalido")
    })
    public ResponseEntity<ApiResponse> caToken(@RequestBody RequestData<RequestTokenValidateDTO> request) {
        ApiResponse apiResponse = useCase.validateCaToken(request.getData()[0]);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(PathConstant.HEALTH)
    public ResponseEntity<String> health(){

        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

}
