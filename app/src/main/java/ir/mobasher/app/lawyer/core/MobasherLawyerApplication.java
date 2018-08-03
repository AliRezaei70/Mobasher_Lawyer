package ir.mobasher.app.lawyer.core;

import android.app.Application;
import android.content.Context;


/**
 * Created by ALI_REZAEI on 12/27/2017.
 */

public class MobasherLawyerApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MobasherLawyerApplication.context = getApplicationContext();

        //FontUtil.getInstance().init(getApplicationContext().getAssets(), getApplicationContext().getResources());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
