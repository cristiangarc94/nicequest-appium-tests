package com.nicequest.utils;

import java.io.IOException;

public class EmulatorManager {

    public static void startEmulator(String avdName) throws IOException, InterruptedException {
        Process check = Runtime.getRuntime().exec("adb devices");
        Thread.sleep(1000);
        String output = new String(check.getInputStream().readAllBytes());
        if (output.contains("emulator-5554")) {
            System.out.println("Emulador ya corriendo.");
            return;
        }

        System.out.println("Arrancando emulador " + avdName + "...");
        Runtime.getRuntime().exec(System.getenv("ANDROID_HOME") + "/emulator/emulator -avd " + avdName + " -netdelay none -netspeed full &");

        Process wait = Runtime.getRuntime().exec("adb wait-for-device");
        wait.waitFor();
        Thread.sleep(10000);
    }
}
