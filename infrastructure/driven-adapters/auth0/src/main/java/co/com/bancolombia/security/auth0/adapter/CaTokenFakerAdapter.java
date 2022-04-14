package co.com.bancolombia.security.auth0.adapter;

import co.com.bancolombia.security.model.catoken.gateways.CaTokenFaker;
import com.github.javafaker.Faker;

public class CaTokenFakerAdapter implements CaTokenFaker {

    @Override
    public String customerNameFaker() {
        Faker faker = new Faker();
        return faker.funnyName().name();
    }
}
