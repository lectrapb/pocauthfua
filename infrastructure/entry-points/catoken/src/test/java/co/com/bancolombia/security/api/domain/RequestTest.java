package co.com.bancolombia.security.api.domain;

import co.com.bancolombia.security.model.response.ApiResponse;
import co.com.bancolombia.security.model.response.RequestData;


public class RequestTest<T>{

    private RequestData requestData;
    private T attributes;
    private T[] data;
    private ApiResponse apiResponse;


    public  RequestTest(T attributes) {
       T[] arrayData = (T[]) new Object[]{attributes};
        requestData = new RequestData<T>();
        requestData.setData(arrayData);
    }

    public RequestData  getApiResponse(){
        return requestData;
    }



}
