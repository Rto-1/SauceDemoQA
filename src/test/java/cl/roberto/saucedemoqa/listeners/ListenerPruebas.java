package cl.roberto.saucedemoqa.listeners;

import cl.roberto.saucedemoqa.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ListenerPruebas implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object instancia = result.getInstance();

        if (!(instancia instanceof BaseTest)) {
            return;
        }

        WebDriver driver = ((BaseTest) instancia).getDriver();

        if (driver == null) {
            return;
        }

        try {
            File captura = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String fecha = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

            Path carpeta = Paths.get("target", "capturas");
            Files.createDirectories(carpeta);

            Path destino = carpeta.resolve(result.getName() + "_" + fecha + ".png");
            Files.copy(captura.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Captura guardada en: " + destino.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("No se pudo guardar la captura: " + e.getMessage());
        }
    }
}