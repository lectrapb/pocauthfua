package co.com.bancolombia.security.model.util;

import co.com.bancolombia.security.model.response.Error;

import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.util.*;

public class Constant {


    public static final List<String> caList ;
    public static final String DATE_FORMAT = "dd/MM/yyyy hh:mm:ss:ms" ;
    public static LocalDate localDate = LocalDate.now();
    public static final int TOTAL_LOGS = 100;

    //HEADERS NAMES
    public static final String MESSAGE_ID_HEADER = "messageId";
    public static final String CLIENT_ID_HEADER = "clientId";
    public static final String CLIENT_SECRET_HEADER = "clientSecret";
    public static final String CONTENT_TYPE_HEADER = "Content-Type";

    //DATA DEFAULT
    public static final String DOUCUMMET_TYPE_DEFAULT = "CC";
    public static final String DOCUMENT_NUMBER = "1325698754";
    public static final String CUSTOMER_NAME_DAFAULT = "Robin Hood";
    public static final String SCOPE_DEFAULT = "scope/extra";
    public static final String TOKEN_TYPE = "bearer";
    public static final String SESSION_TOKEN = "fdfdfdfdf";

    static {
        caList = new ArrayList<>();
        caList.add("1000000001");
        caList.add("1000000002");
        caList.add("1000000003");
        caList.add("1000000004");
    }

    //Certificate
    public static final String DIGITAL_CERTIFICATE =
            "MIIC+DCCAeCgAwIBAgIGAX+3kYz/MA0GCSqGSIb3DQEBCwUAMD0xCzAJBgNVBAYTAkNPMRQwEgYD" +
            "VQQKEwtCYW5jb2xvbWJpYTEYMBYGA1UEAxMPQmFuY29sb21iaWFUZXN0MB4XDTIyMDMyMzE2MTcy" +
            "M1oXDTIzMDMyMzE2MTcyM1owPTELMAkGA1UEBhMCQ08xFDASBgNVBAoTC0JhbmNvbG9tYmlhMRgw" +
            "FgYDVQQDEw9CYW5jb2xvbWJpYVRlc3QwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCe" +
            "aGXTQOygrGL1E2B4ClCW/1Chs24nsyyY5d2asOnrEmetxA/peH94yCLrj/cem1srHzIoe4OMFyV+" +
            "y85rOAZwvS/s+TyVPc2lZdblM82Yz4iUBk0bp5TJB/Op3gPDpTEib4MfVEaZnu5GbvN3ScipjXUA" +
            "jvBh4J+nQtxcOYbwioKZJ4zAIicOSCiAeZbtapdO0wspZgslTOexuxUnC3FhA359bB9iz0d9a52s" +
            "/F0/GZyjibySX2lMN07IU+7MCo8PB9brgYoU++LvuAVAn5FNAEhlOSLbgQlemZaLlbbAfbVWf/DM" +
            "pb8cZKKhj0QZoZHqLb2dO9tqxj6Refdf01n/AgMBAAEwDQYJKoZIhvcNAQELBQADggEBACZS6U5H" +
            "0j3jOEEjOcVwUKzbUGwo0suPx+E0ogQRQUI5b+0yN9DKWUn3J3FtwiZzaQvtSHmw5fW3uXegI5zj" +
            "HVZUq5fQMrikEc6ZydVNgZ/Q/CCjSTGt/45v5YiOvnJmsmvVqcpo120hvRoC9VHoSuSOvC3gk3/0" +
            "jk3KpCyKNud/u2tUPtMkON2zncuowM7FW2p+5NqZJDjp2C4RMosrYOAs0BjlCazzrF94HgbBs/hh" +
            "0RgsTht5cCNsYWfhex4sfLj17ABTG2AhAbWU0uiVrHjn4IAn47ueSppLnHfqSH/O+bWuOkxQHyhs" +
            "6IJOmOBIwlFCIszBaKO2j8pv2XqZbAs=";



    //INTERNAL ERROR
    public static final String UNHANDLED_ERROR_CODE = "ER500-01";

    //TITLES
    public static final String TITLE_ERROR_OPERATION= "Operacion fallida";

    //TYPE OF ERROR
    public static final String VALIDATION_ERROR_STATUS = "400";

    //CODE ERROR
    public static final String ERROR_MISSING_PARAM_CODE =  "ER-401";
    public static final String ERROR_NOT_FOUND_CA_CODE  =  "ER-402";
    public static final String CLIENT_NOT_AUTHORIZED    =  "ER-403";
    public static final String MISSING_HEADERS_CODE     =  "ER-404";
    public static final String ERROR_IN_CLAIMS          =  "ER-405";
    public static final String ERROR_VALIDATION_TOKEN   =  "ER-406";

    private static final Map<String, Error> errorMessages = new HashMap<>();

    static {
        errorMessages.put(ERROR_MISSING_PARAM_CODE,
                new Error(  UUID.randomUUID().toString(), Constant.VALIDATION_ERROR_STATUS,ERROR_MISSING_PARAM_CODE, TITLE_ERROR_OPERATION,
                        "Parametros obligatorios omititidos" ) );

        errorMessages.put(ERROR_NOT_FOUND_CA_CODE,
                new Error(  UUID.randomUUID().toString(), Constant.VALIDATION_ERROR_STATUS,ERROR_NOT_FOUND_CA_CODE, TITLE_ERROR_OPERATION,
                        "No se eoncotro coidgo CA" ) );

        errorMessages.put(CLIENT_NOT_AUTHORIZED ,
                new Error(  UUID.randomUUID().toString(), Constant.VALIDATION_ERROR_STATUS,CLIENT_NOT_AUTHORIZED , TITLE_ERROR_OPERATION,
                        "Cliente no autorizado" ) );

        errorMessages.put(MISSING_HEADERS_CODE ,
                new Error(  UUID.randomUUID().toString(), Constant.VALIDATION_ERROR_STATUS,MISSING_HEADERS_CODE , TITLE_ERROR_OPERATION,
                        "Faltan cabeceras obligatorias" ) );

        errorMessages.put(ERROR_IN_CLAIMS ,
                new Error(  UUID.randomUUID().toString(), Constant.VALIDATION_ERROR_STATUS,ERROR_IN_CLAIMS , TITLE_ERROR_OPERATION,
                        "Falla en la firma de los claims" ) );

        errorMessages.put(ERROR_VALIDATION_TOKEN ,
                new Error(  UUID.randomUUID().toString(), Constant.VALIDATION_ERROR_STATUS,ERROR_VALIDATION_TOKEN , TITLE_ERROR_OPERATION,
                        "Falla en la validacion del token" ) );
    }

    private Constant(){}

    public static Error getErrorMessage(String errorCode) {

          return  errorMessages.get(errorCode);

    }
}
