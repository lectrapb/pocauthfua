package co.com.bancolombia.security.model.util;

public class Validation {

    public static  boolean isNull(String data){
        boolean rta = false;
        if(data == null){
            rta = true;
        }
        return rta;
    }

    public static  boolean isNull(Object data){

        return   data == null ;
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}
