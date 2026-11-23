package cl.roberto.saucedemoqa.listeners;

import cl.roberto.saucedemoqa.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
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
import java.util.Arrays;

public class ListenerPruebas implements ITestListener {

    private static final ExtentReports extent = ExtentManager.getInstance();
    private ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {

        String nombre = result.getMethod().getDescription();

        if (nombre == null || nombre.isEmpty()) {
            nombre = result.getName();
        }

        if (result.getParameters().length > 0) {
            nombre += " " + Arrays.toString(result.getParameters());
        }

        test = extent.createTest(nombre);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Prueba aprobada");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Prueba omitida");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        Object instancia = result.getInstance();

        if (!(instancia instanceof BaseTest)) {
            return;
        }

        WebDriver driver = ((BaseTest) instancia).getDriver();

        if (driver == null) {
            return;
        }

        try {
            TakesScreenshot camara = (TakesScreenshot) driver;

            // 1) Captura como archivo en target/capturas
            File captura = camara.getScreenshotAs(OutputType.FILE);

            String fecha = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

            Path carpeta = Paths.get("target", "capturas");
            Files.createDirectories(carpeta);

            Path destino = carpeta.resolve(result.getName() + "_" + fecha + ".png");
            Files.copy(captura.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Captura guardada en: " + destino.toAbsolutePath());

            // 2) Captura incrustada dentro del reporte HTML
            String base64 = camara.getScreenshotAs(OutputType.BASE64);
            test.addScreenCaptureFromBase64String(base64, "Captura al fallar");

        } catch (IOException e) {
            System.out.println("No se pudo guardar la captura: " + e.getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}