
package cl.roberto.saucedemoqa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

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
