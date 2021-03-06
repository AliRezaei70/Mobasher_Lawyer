package ir.mobasher.app.lawyer.ativity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import ir.mobasher.app.lawyer.R;
import ir.mobasher.app.lawyer.api.APIInterface;
import ir.mobasher.app.lawyer.api.login.LoginErrorResponse;
import ir.mobasher.app.lawyer.api.login.LoginSuccessResponse;
import ir.mobasher.app.lawyer.app.AppTags;
import ir.mobasher.app.lawyer.app.Config;
import ir.mobasher.app.lawyer.network.RetrofitClientInstance;
import ir.mobasher.app.lawyer.utils.NotificationUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private TextView txtRegId, txtMessage;

    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    txtMessage.setText(message);
                }
            }
        };

        displayFirebaseRegId();
        test();
    }

    public void test(){

        APIInterface service = RetrofitClientInstance.getRetrofitInstance().create(APIInterface.class);
        Call<LoginSuccessResponse> responseCall = service.loginUser("09158556521");
        responseCall.enqueue(new Callback<LoginSuccessResponse>() {
            @Override
            public void onResponse(Call<LoginSuccessResponse> call, Response<LoginSuccessResponse> response) {
                if (response.isSuccessful()){
                    LoginSuccessResponse loginResponse = response.body();
                    Log.i(AppTags.POST_USER_NUMBER_RESPONSE, loginResponse.getMessage());
                    Toast.makeText(getBaseContext(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    Gson gson = new GsonBuilder().create();
                    LoginErrorResponse errorResponse = new LoginErrorResponse();
                    try {
                        errorResponse = gson.fromJson(response.errorBody().string(), LoginErrorResponse.class);
                        Log.i(AppTags.POST_USER_NUMBER_RESPONSE, errorResponse.getMessage());
                        Toast.makeText(getBaseContext(), errorResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.i(AppTags.POST_USER_NUMBER_RESPONSE, AppTags.UNKNOWN_ERROR);
                    }


                }

            }

            @Override
            public void onFailure(Call<LoginSuccessResponse> call, Throwable t) {
                Log.e(AppTags.POST_USER_NUMBER_RESPONSE, t.getMessage());
            }
        });


    }

    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId))
            Toast.makeText(getApplicationContext(),"Firebase Reg Id: " + regId,Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"Firebase Reg Id is not received yet!",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

}
