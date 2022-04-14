package co.com.bancolombia.security.usecase.util;

import java.util.UUID;

public class ConstantTest {
    public static final String CA_BASE = "10001";
    public static final String CA_NOT_FOUND = "10009";

    public static final String CLIENT_ID = UUID.randomUUID().toString();
    public static final String AUTH = UUID.randomUUID().toString() ;
    public static final String GRANT_TYPE = "AUTH0" ;
    public static final String REDIRECT_URL = "www.google.com";
}
