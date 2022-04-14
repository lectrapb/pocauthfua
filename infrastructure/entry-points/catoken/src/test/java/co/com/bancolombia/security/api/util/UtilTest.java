package co.com.bancolombia.security.api.util;

import co.com.bancolombia.security.model.response.ApiResponse;
import co.com.bancolombia.security.model.response.Data;
import co.com.bancolombia.security.model.response.RequestData;
import co.com.bancolombia.security.model.session.SessionGlobals;
import co.com.bancolombia.security.model.util.Constant;
import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class UtilTest {

    public static final Gson GSON = new Gson();
    public static RequestBuilder requestBuilder(String path, RequestData<?> request ){


        return  MockMvcRequestBuilders
                .post(path)
                .content(GSON.toJson(request))
                .header(Constant.CONTENT_TYPE_HEADER , MediaType.APPLICATION_JSON)
                .header(Constant.MESSAGE_ID_HEADER, SessionGlobals.messageId.get())
                .header(Constant.CLIENT_ID_HEADER , ConstantTest.CLIENT_ID)
                .header(Constant.CLIENT_SECRET_HEADER, ConstantTest.CLIENT_SECCRET);

    }


    public static   ApiResponse getApiResponseForTest(Object object) {

//        Data<?>[] arrayData = new Data[1];
//        arrayData[0] = new Data<>(object);
//
//        return ApiResponse.createOnSuccess()
//                   .setData(arrayData);
        Object[] arrayObject = {object};
        return ApiResponse.createOnSuccess()
                .setData( arrayObject);
    }
}
