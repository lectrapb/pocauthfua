package co.com.bancolombia.security.model.catoken.response;

import lombok.Data;

@Data
public class JsonToken {

    private String requestIp;
    private User user;
    private String iss;
    private String sub;
    private String aud;
    private String iat;
    private String exp;
    private String azp;
    private String scope;
    private String gty;

    @Data
    public class  User{

        private String customerName;
        private String documentNumber;
        private String documentType;
        private String lastEntryDate;
        private String lastHour;
        private String scopeExtra;
        private String sessionToken;
        private String tokenType;
    }
}
