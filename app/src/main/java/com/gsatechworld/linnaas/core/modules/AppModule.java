package com.gsatechworld.linnaas.core.modules;

import android.content.Context;

import com.google.gson.Gson;
import com.gsatechworld.linnaas.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    @Singleton
    @Provides
    Context provideContext(MainApplication application){
        return application;
    }

    @Singleton
    @Provides
    Gson provideGson(){
        return new Gson();
    }

   /* @Singleton
    @Provides
    Utils provideUtils(Context context){
        return new Utils(context);
    }*/

}
