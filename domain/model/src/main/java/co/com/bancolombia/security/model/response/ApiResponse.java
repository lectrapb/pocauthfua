package co.com.bancolombia.security.model.response;

import co.com.bancolombia.security.model.session.SessionGlobals;
import co.com.bancolombia.security.model.util.Constant;
import co.com.bancolombia.security.model.util.CurrentDate;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@lombok.Data
public class ApiResponse {

    private Meta meta;
    private Object[] data;
    private Error[] errors;


    public static ApiResponse createOnSuccess(){
        return new ApiResponse()
                .setMeta(SessionGlobals.messageId.get(), SessionGlobals.appId.get());
    }
    
    public static ApiResponse createOnError(String errorCode){

        String messageId =  SessionGlobals.messageId.get();
        String appId =  SessionGlobals.appId.get();
        return new ApiResponse()
                .setMeta(SessionGlobals.messageId.get(), SessionGlobals.appId.get())
                .setErrors(errorCode);
    }

    public ApiResponse setData(Object[] data) {
        this.data = data;
        return this;
    }

    private ApiResponse setErrors(String errorCode) {

        Error error = Constant.getErrorMessage(errorCode);
        this.errors = new Error[]{error};
        return  this;
    }

    private ApiResponse setMeta(String messageId, String applicationId ) {

        this.meta = new Meta(messageId, CurrentDate.standard(), applicationId);
        return  this;
    }


}
