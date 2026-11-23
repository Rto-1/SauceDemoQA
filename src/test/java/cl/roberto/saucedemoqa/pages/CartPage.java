package cl.roberto.saucedemoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By titulo = By.className("title");
    private By productosEnCarrito = By.className("cart_item");
    private By nombreProducto = By.className("inventory_item_name");
    private By precioProducto = By.className("inventory_item_price");
    private By botonCheckout = By.id("checkout");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Acciones de la página
    public String obtenerTitulo() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(titulo)).getText();
    }

    public int obtenerCantidadProductos() {
        return obtenerTextos(productosEnCarrito).size();
    }

    public List<String> obtenerNombresProductos() {
        return obtenerTextos(nombreProducto);
    }

    public List<String> obtenerPreciosProductos() {
        return obtenerTextos(precioProducto);
    }

    public CheckoutPage irACheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(botonCheckout)).click();
        return new CheckoutPage(driver);
    }

    // Método privado de apoyo: lee el texto de todos los elementos que coinciden
    // y reintenta si la página se redibuja mientras los leemos
    private List<String> obtenerTextos(By localizador) {

        return wait.ignoring(StaleElementReferenceException.class).until(d -> {

            List<String> textos = new ArrayList<>();

            for (WebElement elemento : d.findElements(localizador)) {
                textos.add(elemento.getText());
            }

            // Si todavía no hay elementos, devolvemos null y la espera lo vuelve a intentar
            return textos.isEmpty() ? null : textos;
        });
    }
}