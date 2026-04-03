package com.example.smolgo;

import com.yandex.mapkit.MapKitFactory;

// Подключение Yandex API
public class App extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MapKitFactory.setApiKey("YOUR API KEY");
        MapKitFactory.initialize(this);
    }
}