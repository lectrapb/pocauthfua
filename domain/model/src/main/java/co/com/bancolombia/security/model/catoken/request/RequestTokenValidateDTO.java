package co.com.bancolombia.security.model.catoken.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestTokenValidateDTO {

    private String grantType;
    private String clientId;
    private String redirectUri;
    private String code;
    private String authorization;
}
