package co.com.bancolombia.security.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Meta {

    private String _messageId;
    private String _requestDateTime;
    private String _applicationId;
}
