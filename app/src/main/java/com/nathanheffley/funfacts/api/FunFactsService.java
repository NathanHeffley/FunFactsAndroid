package com.nathanheffley.funfacts.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FunFactsService {
    @GET("api/v1.0/fact/random")
    Call<Fact> getRandomFact();
}
