package com.nicequest.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import java.net.URL;

public class DriverManager {

    private static AndroidDriver androidDriver;
    private static IOSDriver iosDriver;

    public static Object getDriver(String platform) throws Exception {
        String serverUrl = ConfigReader.get("appium.server");

        if(platform.equalsIgnoreCase("Android")) {
            if(androidDriver == null) {
                // ===============================================
                // OPCIÓN: Arranque automático (comentado)
                // EmulatorManager.startEmulator("Medium_Phone_API_35");
                // AppiumManager.startAppium();
                // ===============================================

                UiAutomator2Options options = new UiAutomator2Options()
                        .setDeviceName(ConfigReader.get("android.deviceName"))
                        .setPlatformName(ConfigReader.get("android.platformName"))
                        .setAppPackage(ConfigReader.get("android.appPackage"))
                        .setAppActivity(ConfigReader.get("android.appActivity"))
                        .setNoReset(Boolean.parseBoolean(ConfigReader.get("android.noReset")));

                androidDriver = new AndroidDriver(new URL(serverUrl), options);
            }
            return androidDriver;

        } else if(platform.equalsIgnoreCase("iOS")) {
            if(iosDriver == null) {
                XCUITestOptions options = new XCUITestOptions()
                        .setDeviceName(ConfigReader.get("ios.deviceName"))
                        .setPlatformName(ConfigReader.get("ios.platformName"))
                        .setBundleId(ConfigReader.get("ios.bundleId"))
                        .setNoReset(Boolean.parseBoolean(ConfigReader.get("ios.noReset")));

                iosDriver = new IOSDriver(new URL(serverUrl), options);
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

    public static AndroidDriver getDriver() throws Exception {
        return (AndroidDriver) getDriver("Android");
    }
}
