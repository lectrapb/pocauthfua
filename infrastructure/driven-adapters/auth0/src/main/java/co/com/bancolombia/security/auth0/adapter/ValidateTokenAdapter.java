package co.com.bancolombia.security.auth0.adapter;

import co.com.bancolombia.security.auth0.util.Certificates;
import co.com.bancolombia.security.model.catoken.gateways.CaTokenRepository;
import co.com.bancolombia.security.model.catoken.response.JsonToken;
import co.com.bancolombia.security.model.util.Constant;
import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

import com.google.gson.*;

public class ValidateTokenAdapter implements CaTokenRepository {

    @Override
    public String validateToken(String token) {

        //Auth0
        //JwkProvider provider = new UrlJwkProvider("https://bancolombia-poc.us.auth0.com/oauth");
        //Cognito
        JwkProvider provider = new UrlJwkProvider("https://cognito-idp.us-east-1.amazonaws.com/us-east-1_rVnPBe3ge");

        String rta = "200";
        try{
            DecodedJWT jwt = JWT.decode(token);
            String keyJWK = jwt.getKeyId();
            Jwk jwk = provider.get(keyJWK);
            PublicKey publicKey = jwk.getPublicKey();
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, null);
            algorithm.verify(jwt);

        } catch (JWTVerificationException e){
            //Invalid signature/claims
            rta = Constant.ERROR_IN_CLAIMS;
        } catch (JwkException e) {
            // invalid JWT token
            rta = Constant.ERROR_VALIDATION_TOKEN;
        }

        return rta;
    }

    @Override
    public String validateTokenPCD(String token) {

        String rta = "200";
        try{
            DecodedJWT jwt = JWT.decode(token);
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) Certificates.publicKey, null);
            algorithm.verify(jwt);

        } catch (JWTVerificationException e){
            //Invalid signature/claims
            rta = Constant.ERROR_IN_CLAIMS;
        }

        return rta;
    }

    @Override
    public  JsonToken   decodeToken (String token){

        Base64.Decoder decoder = Base64.getDecoder();

        String[] chunks = token.split("\\.");
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        //Read the JSON file
        JsonElement root = new JsonParser().parse(payload);

        //Get the content of the first map
        //JsonObject object = root.getAsJsonObject().get("user").getAsJsonObject();
        Gson gson = new Gson();
        JsonToken jsonToken = new JsonToken();
        String dataClear ="\"";
        //jsonToken.setRequestIp(root.getAsJsonObject().get("request-ip").toString());
        //jsonToken.setUser(gson.fromJson(object.toString(),JsonToken.User.class));
        //String data = root.getAsJsonObject().get("iss").toString();
        jsonToken.setCustomerName(root.getAsJsonObject().get("customerName").toString().replace(dataClear, ""));
        jsonToken.setDocumentNumber(root.getAsJsonObject().get("documentNumber").toString().replace(dataClear, ""));
        jsonToken.setDocumentType(root.getAsJsonObject().get("documentType").toString().replace(dataClear, ""));
        jsonToken.setLastEntryDate(root.getAsJsonObject().get("lastEntryDate").toString().replace(dataClear, ""));
        jsonToken.setLastHour(root.getAsJsonObject().get("lastHour").toString().replace(dataClear, ""));
        jsonToken.setScopeExtra(root.getAsJsonObject().get("scopeExtra").toString().replace(dataClear, ""));
        //jsonToken.setSessionToken(root.getAsJsonObject().get("sessionToken").toString());
        jsonToken.setTokenType(root.getAsJsonObject().get("tokenType").toString().replace(dataClear, ""));
        jsonToken.setScope(root.getAsJsonObject().get("scopeExtra").toString().replace(dataClear, ""));


        jsonToken.setIss(root.getAsJsonObject().get("iss").toString().replace(dataClear, ""));
        //jsonToken.setSub(root.getAsJsonObject().get("sub").toString().replace(dataClear, ""));
        jsonToken.setAud(root.getAsJsonObject().get("aud").toString().replace(dataClear, ""));
        jsonToken.setIat(root.getAsJsonObject().get("iat").toString().replace(dataClear, ""));
        jsonToken.setExp(root.getAsJsonObject().get("exp").toString().replace(dataClear, ""));
        //jsonToken.setAzp(root.getAsJsonObject().get("azp").toString().replace(dataClear, ""));
        //jsonToken.setScope(root.getAsJsonObject().get("scope").toString().replace(dataClear, ""));
        //jsonToken.setGty(root.getAsJsonObject().get("gty").toString().replace(dataClear, ""));
        return  jsonToken;
    }


    public  JsonToken   decodeToken2 (String token){

        Base64.Decoder decoder = Base64.getDecoder();

        String[] chunks = token.split("\\.");
        String header = new String(decoder.decode(chunks[0]));
        String payloadInit = new String(decoder.decode(chunks[1]));

        String dataClear = "https://bancolombia.com/request-ip";
        String payload = payloadInit.replace(dataClear, "request-ip");
        dataClear = "https://bancolombia.com/user";
        payload = payload.replace(dataClear, "user");

        //Read the JSON file
        JsonElement root = new JsonParser().parse(payload);

        //Get the content of the first map
        JsonObject object = root.getAsJsonObject().get("user").getAsJsonObject();
        Gson gson = new Gson();
        JsonToken jsonToken = new JsonToken();
        //jsonToken.setRequestIp(root.getAsJsonObject().get("request-ip").toString());
        //jsonToken.setUser(gson.fromJson(object.toString(),JsonToken.User.class));
        //String data = root.getAsJsonObject().get("iss").toString();
        dataClear ="\"";
        jsonToken.setIss(root.getAsJsonObject().get("iss").toString().replace(dataClear, ""));
        //jsonToken.setSub(root.getAsJsonObject().get("sub").toString().replace(dataClear, ""));
        jsonToken.setAud(root.getAsJsonObject().get("aud").toString().replace(dataClear, ""));
        //jsonToken.setIat(root.getAsJsonObject().get("iat").toString().replace(dataClear, ""));
        //jsonToken.setExp(root.getAsJsonObject().get("exp").toString().replace(dataClear, ""));
        //jsonToken.setAzp(root.getAsJsonObject().get("azp").toString().replace(dataClear, ""));
        jsonToken.setScope(root.getAsJsonObject().get("scope").toString().replace(dataClear, ""));
        jsonToken.setGty(root.getAsJsonObject().get("gty").toString().replace(dataClear, ""));
        return  jsonToken;
    }


    public  JsonToken   decodeTokenAuth (String token){

        Base64.Decoder decoder = Base64.getDecoder();

        String[] chunks = token.split("\\.");
        String header = new String(decoder.decode(chunks[0]));
        String payloadInit = new String(decoder.decode(chunks[1]));

        String dataClear = "https://bancolombia.com/request-ip";
        String payload = payloadInit.replace(dataClear, "request-ip");
        dataClear = "https://bancolombia.com/user";
        payload = payload.replace(dataClear, "user");

        //Read the JSON file
        JsonElement root = new JsonParser().parse(payload);

        //Get the content of the first map
        JsonObject object = root.getAsJsonObject().get("user").getAsJsonObject();
        Gson gson = new Gson();
        JsonToken jsonToken = new JsonToken();
        //jsonToken.setRequestIp(root.getAsJsonObject().get("request-ip").toString());
        //jsonToken.setUser(gson.fromJson(object.toString(),JsonToken.User.class));
        //String data = root.getAsJsonObject().get("iss").toString();
        dataClear ="\"";
        jsonToken.setIss(root.getAsJsonObject().get("iss").toString().replace(dataClear, ""));
        //jsonToken.setSub(root.getAsJsonObject().get("sub").toString().replace(dataClear, ""));
        jsonToken.setAud(root.getAsJsonObject().get("aud").toString().replace(dataClear, ""));
        //jsonToken.setIat(root.getAsJsonObject().get("iat").toString().replace(dataClear, ""));
        //jsonToken.setExp(root.getAsJsonObject().get("exp").toString().replace(dataClear, ""));
        //jsonToken.setAzp(root.getAsJsonObject().get("azp").toString().replace(dataClear, ""));
        jsonToken.setScope(root.getAsJsonObject().get("scope").toString().replace(dataClear, ""));
        jsonToken.setGty(root.getAsJsonObject().get("gty").toString().replace(dataClear, ""));
        return  jsonToken;
    }
}
