package cl.roberto.saucedemoqa.pages;

import org.openqa.selenium.By;
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
        return driver.findElements(productosEnCarrito).size();
    }

    public List<String> obtenerNombresProductos() {
        List<String> nombres = new ArrayList<>();

        for (WebElement elemento : driver.findElements(nombreProducto)) {
            nombres.add(elemento.getText());
        }

        return nombres;
    }

    public List<String> obtenerPreciosProductos() {
        List<String> precios = new ArrayList<>();

        for (WebElement elemento : driver.findElements(precioProducto)) {
            precios.add(elemento.getText());
        }

        return precios;
    }
}