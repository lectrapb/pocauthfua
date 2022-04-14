package co.com.bancolombia.security.model.logs;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class Log {

    private Long id;
    private String messageId;
    private String appId;
    private String action;
    private String status;
    private String data;
    private LocalDateTime create;
}
