package com.nicequest.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;

public class DriverManager {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() throws Exception {
        if (driver == null) {

            // ===============================================
            // OPCIÓN 1: Arranque automático
            // ===============================================
            /*
            EmulatorManager.startEmulator("Medium_Phone_API_35");
            AppiumManager.startAppium();
            */

            // ===============================================
            // OPCIÓN 2: Conectar a emulador y Appium ya corriendo (rápido)
            // ===============================================
            UiAutomator2Options options = new UiAutomator2Options()
                    .setDeviceName("Android Emulator")
                    .setPlatformName("Android")
                    .setAppPackage("com.netquest.pokey")
                    .setAppActivity("com.netquest.pokey.MainActivity")
                    .setNoReset(true);


            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
