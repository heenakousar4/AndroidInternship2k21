package com.example.demo;

public interface MyInterface {
    @GET("dayone/country/{input}")
    Call<String> getValue(@Path("input") String i);
}

