package ir.mobasher.app.lawyer.api.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private String message;
    @SerializedName("userId")
    private String userId;
    @SerializedName("active")
    private Boolean active;

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }

    public Boolean getActive() {
        return active;
    }

    public Integer getStatus() {
        return status;
    }
}

