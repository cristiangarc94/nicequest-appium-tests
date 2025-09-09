package com.nicequest.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import java.net.URL;

public class DriverManager {

    private static AndroidDriver androidDriver;
    private static IOSDriver iosDriver;

    // Devuelve driver según plataforma
    public static Object getDriver(String platform) throws Exception {
        if(platform.equalsIgnoreCase("Android")) {
            if(androidDriver == null) {
                // ===============================================
                // OPCIÓN: Arranque automático (comentado)
                // EmulatorManager.startEmulator("Medium_Phone_API_35");
                // AppiumManager.startAppium();
                // ===============================================

                UiAutomator2Options options = new UiAutomator2Options()
                        .setDeviceName("Android Emulator")
                        .setPlatformName("Android")
                        .setAppPackage("com.netquest.pokey")
                        .setAppActivity("com.netquest.pokey.MainActivity")
                        .setNoReset(true);

                androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
            }
            return androidDriver;

        } else if(platform.equalsIgnoreCase("iOS")) {
            if(iosDriver == null) {
                XCUITestOptions options = new XCUITestOptions()
                        .setDeviceName("iPhone Simulator")
                        .setPlatformName("iOS")
                        .setBundleId("com.netquest.pokey")
                        .setNoReset(true);

                iosDriver = new IOSDriver(new URL("http://127.0.0.1:4723/"), options);
            }
            return iosDriver;
        }

        throw new Exception("Plataforma no soportada: " + platform);
    }

    public static void quitDriver(String platform) {
        try {
            if(platform.equalsIgnoreCase("Android") && androidDriver != null) {
                androidDriver.quit();
                androidDriver = null;
            } else if(platform.equalsIgnoreCase("iOS") && iosDriver != null) {
                iosDriver.quit();
                iosDriver = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Sobrecarga para Android por defecto
    public static AndroidDriver getDriver() throws Exception {
        return (AndroidDriver) getDriver("Android");
    }
}
