package cl.roberto.saucedemoqa;

import cl.roberto.saucedemoqa.listeners.ListenerPruebas;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(ListenerPruebas.class)
public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void iniciarNavegador() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void cerrarNavegador() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}