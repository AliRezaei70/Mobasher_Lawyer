package ir.mobasher.app.lawyer.api;

import com.google.gson.JsonObject;

import ir.mobasher.app.lawyer.api.login.LoginRequest;
import ir.mobasher.app.lawyer.api.login.LoginResponse;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {


    @POST("/api/v1/auth/login")
    Call<LoginRequest> loginUser(@Body LoginRequest loginRequest, Callback<LoginResponse> response);

}
