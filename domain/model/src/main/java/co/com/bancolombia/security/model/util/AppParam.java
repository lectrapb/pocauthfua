package co.com.bancolombia.security.model.util;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppParam {

    private String apiConnectClientId;
    private String apiConnectClientSecret;
}
