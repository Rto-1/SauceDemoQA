package cl.roberto.saucedemoqa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                    new ExtentSparkReporter("target/reportes/reporte.html");

            spark.config().setDocumentTitle("SauceDemoQA - Reporte de pruebas");
            spark.config().setReportName("Pruebas de login");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Aplicación", "https://www.saucedemo.com/");
            extent.setSystemInfo("Navegador", "Chrome");
            extent.setSystemInfo("Java", System.getProperty("java.version"));
        }

        return extent;
    }
}