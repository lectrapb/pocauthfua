package co.com.bancolombia.security.model.catoken.gateways;

import co.com.bancolombia.security.model.catoken.response.JsonToken;

public interface CaTokenRepository {

    String  validateToken(String token);

    String validateTokenPCD(String token);

    JsonToken  decodeToken (String token);

}
