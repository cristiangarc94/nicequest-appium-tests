package com.nicequest.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {
    public static void takeScreenshot(AndroidDriver driver, String name) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String path = "target/screenshots/" + name + "_" + timestamp + ".png";
            Files.createDirectories(Paths.get("target/screenshots"));
            Files.copy(srcFile.toPath(), Paths.get(path));
            System.out.println("Screenshot guardado: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
