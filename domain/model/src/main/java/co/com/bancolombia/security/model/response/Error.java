package co.com.bancolombia.security.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

    private String id;
    private String status;
    private String code;
    private String title;
    private String detail;
}
