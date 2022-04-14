package co.com.bancolombia.security.auth0.util;

import co.com.bancolombia.security.model.util.Constant;
import org.jose4j.keys.X509Util;
import org.jose4j.lang.JoseException;

import java.security.PublicKey;
import java.security.cert.X509Certificate;

public class Certificates {

    public static final X509Util x509Util = new X509Util();
    public static X509Certificate certificate;

    static {
        try {
            certificate = x509Util.fromBase64Der(Constant.DIGITAL_CERTIFICATE);
        } catch (JoseException e) {
            e.printStackTrace();
        }
    };
    public static final PublicKey publicKey = certificate.getPublicKey();

}
