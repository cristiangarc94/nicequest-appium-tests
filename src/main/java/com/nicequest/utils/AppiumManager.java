package com.nicequest.utils;

import java.io.IOException;

public class AppiumManager {

    public static void startAppium() throws IOException, InterruptedException {
        Process check = Runtime.getRuntime().exec("lsof -i :4723");
        Thread.sleep(1000);
        if (check.getInputStream().readAllBytes().length > 0) {
            System.out.println("Appium ya corriendo.");
            return;
        }

        System.out.println("Arrancando Appium...");
        Runtime.getRuntime().exec("appium &");
        Thread.sleep(5000);
    }
}
