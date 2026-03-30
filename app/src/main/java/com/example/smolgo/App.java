package com.example.smolgo;

import com.yandex.mapkit.MapKitFactory;

public class App extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey("26271c3e-c6ed-4240-be69-26387ce0c7e5");
        MapKitFactory.initialize(this);
    }
}