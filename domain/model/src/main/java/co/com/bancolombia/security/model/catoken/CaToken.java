package co.com.bancolombia.security.model.catoken;

import co.com.bancolombia.security.model.util.Constant;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Data
public class CaToken {


    private String documentType = Constant.DOUCUMMET_TYPE_DEFAULT;
    private String documentNumber = Constant.DOCUMENT_NUMBER;
    private String customerName = Constant.CUSTOMER_NAME_DAFAULT;
    private String lastEntryDate;
    private String lastHour;
    private String tokenType = Constant.TOKEN_TYPE;
    private String sessionToken = Constant.SESSION_TOKEN;
    private String scopeExtra = Constant.SCOPE_DEFAULT;

    public static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");

    public CaToken() {
        Random random = new Random();
        this.lastEntryDate = LocalDateTime.now().format(formatterDate).toString();
        this.lastHour = LocalDateTime.now().format(formatterTime).toString();
        this.scopeExtra = this.getScopeExtra()+"_"+ random.nextInt(50);
    }

}
