package co.com.bancolombia.security.model.catoken.response;


import lombok.Data;

@Data
public class ResponseTokenValidateDTO {

     private String  documentType;
     private String  documentNumber;
     private String  customerName;
     private String  lastEntryDate;
     private String  lastHour;
     private String  tokenType;
     private String  sessionToken;
     private String  scope;
}
