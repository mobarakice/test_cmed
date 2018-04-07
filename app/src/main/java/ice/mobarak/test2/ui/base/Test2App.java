package ice.mobarak.test2.ui.base;

import android.app.Application;
import android.content.Context;


public class Test2App extends Application {

    private static Test2App mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;


    }

    public static Test2App getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

}