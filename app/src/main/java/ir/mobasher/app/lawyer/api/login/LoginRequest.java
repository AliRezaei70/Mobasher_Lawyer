package ir.mobasher.app.lawyer.api.login;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

/**
 * Created by ALI_REZAEI on 12/24/2017.
 */

public class LoginRequest {

    private String phoneNumber;

    public LoginRequest(){}

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
