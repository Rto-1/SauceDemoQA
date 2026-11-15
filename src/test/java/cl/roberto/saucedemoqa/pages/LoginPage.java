package cl.roberto.saucedemoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    private By campoUsuario = By.id("user-name");
    private By campoPassword = By.id("password");
    private By botonLogin = By.id("login-button");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Acciones de la página
    public void ingresarUsuario(String usuario) {
        esperarElementoVisible(campoUsuario).sendKeys(usuario);
    }

    public void ingresarPassword(String password) {
        esperarElementoVisible(campoPassword).sendKeys(password);
    }

    public void hacerClickLogin() {
        esperarElementoClickeable(botonLogin).click();
    }

    public String obtenerTituloPagina() {

        return esperarElementoVisible(By.className("title")).getText();
    }

    public String obtenerMensajeError() {

        By mensajeError = By.cssSelector("[data-test='error']");

        return esperarElementoVisible(mensajeError).getText();
    }

    private WebElement esperarElementoVisible(By elemento) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(elemento));
    }

    private WebElement esperarElementoClickeable(By elemento) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(ExpectedConditions.elementToBeClickable(elemento));
    }
}


