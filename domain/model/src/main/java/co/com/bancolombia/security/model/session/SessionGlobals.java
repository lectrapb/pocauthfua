package co.com.bancolombia.security.model.session;

public class SessionGlobals {

    public static final ThreadLocal<String> messageId = new ThreadLocal<>();
    public static final ThreadLocal<String> appId = new ThreadLocal<>();
}
