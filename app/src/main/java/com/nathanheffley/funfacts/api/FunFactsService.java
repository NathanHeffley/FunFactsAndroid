package com.nathanheffley.funfacts.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FunFactsService {
    @GET("v1/fact/random")
    Call<Fact> getRandomFact();
}
