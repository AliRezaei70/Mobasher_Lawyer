package ir.mobasher.app.lawyer.api;

import ir.mobasher.app.lawyer.api.login.LoginSuccessResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    //Post User PhoneNumber
    @FormUrlEncoded
    @POST("/api/v1/auth/login")
    Call<LoginSuccessResponse> loginUser(@Field("phoneNumber") String phoneNumber);

}
