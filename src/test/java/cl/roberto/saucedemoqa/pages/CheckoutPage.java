package cl.roberto.saucedemoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By titulo = By.className("title");
    private By campoNombre = By.id("first-name");
    private By campoApellido = By.id("last-name");
    private By campoCodigoPostal = By.id("postal-code");
    private By botonContinuar = By.id("continue");
    private By mensajeError = By.cssSelector("[data-test='error']");

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Acciones de la página
    public String obtenerTitulo() {
        return esperarElementoVisible(titulo).getText();
    }

    public void ingresarNombre(String nombre) {
        esperarElementoVisible(campoNombre).sendKeys(nombre);
    }

    public void ingresarApellido(String apellido) {
        esperarElementoVisible(campoApellido).sendKeys(apellido);
    }

    public void ingresarCodigoPostal(String codigoPostal) {
        esperarElementoVisible(campoCodigoPostal).sendKeys(codigoPostal);
    }

    public void hacerClickContinuar() {
        esperarElementoClickeable(botonContinuar).click();
    }

    public String obtenerMensajeError() {
        return esperarElementoVisible(mensajeError).getText();
    }

    // Método que combina varios pasos
    public void completarFormulario(String nombre, String apellido, String codigoPostal) {
        ingresarNombre(nombre);
        ingresarApellido(apellido);
        ingresarCodigoPostal(codigoPostal);
    }

    // Métodos privados de apoyo
    private WebElement esperarElementoVisible(By elemento) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elemento));
    }

    private WebElement esperarElementoClickeable(By elemento) {
        return wait.until(ExpectedConditions.elementToBeClickable(elemento));
    }
}