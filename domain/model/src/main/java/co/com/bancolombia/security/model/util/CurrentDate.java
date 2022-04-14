package co.com.bancolombia.security.model.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class CurrentDate {


    public static String standard(){
        LocalDateTime date = LocalDateTime.now(ZoneOffset.of("-05:00"));
        return date.format(DateTimeFormatter.ofPattern(Constant.DATE_FORMAT));
    }
}
