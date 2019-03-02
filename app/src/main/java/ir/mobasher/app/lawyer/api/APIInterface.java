package ir.mobasher.app.lawyer.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ir.mobasher.app.lawyer.api.login.LoginRequest;
import ir.mobasher.app.lawyer.api.login.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    //Post User PhoneNumber
    @FormUrlEncoded
    @POST("/api/v1/auth/login")
    Call<LoginResponse> loginUser(@Field("phoneNumber") String phoneNumber);

}
