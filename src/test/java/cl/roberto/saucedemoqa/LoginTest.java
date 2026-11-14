
package cl.roberto.saucedemoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void iniciarNavegador() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void cerrarNavegador() {
        driver.quit();
    }

    @Test
    public void loginExitoso() {

        driver.findElement(By.id("user-name"))
                .sendKeys("standard_user");

        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        driver.findElement(By.id("login-button"))
                .click();

        String titulo = driver.findElement(
                By.className("title")
        ).getText();

        Assert.assertEquals(titulo, "Products");
    }

    @Test
    public void loginUsuarioBloqueado() {

        driver.findElement(By.id("user-name"))
                .sendKeys("locked_out_user");

        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        driver.findElement(By.id("login-button"))
                .click();

        String mensaje = driver.findElement(
                By.cssSelector("[data-test='error']")
        ).getText();

        Assert.assertTrue(
                mensaje.contains("Sorry, this user has been locked out")
        );
    }
}
