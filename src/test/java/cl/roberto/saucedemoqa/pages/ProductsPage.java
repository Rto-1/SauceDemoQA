package cl.roberto.saucedemoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By titulo = By.className("title");
    private By productos = By.className("inventory_item");
    private By contadorCarrito = By.className("shopping_cart_badge");
    private By enlaceCarrito = By.className("shopping_cart_link");

    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Acciones de la página
    public String obtenerTitulo() {
        return esperarElementoVisible(titulo).getText();
    }

    public int obtenerCantidadProductos() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productos));
        return driver.findElements(productos).size();
    }

    public void agregarProducto(String nombreProducto) {
        By botonAgregar = By.id("add-to-cart-" + convertirAId(nombreProducto));
        esperarElementoClickeable(botonAgregar).click();
    }

    public void eliminarProducto(String nombreProducto) {
        By botonEliminar = By.id("remove-" + convertirAId(nombreProducto));
        esperarElementoClickeable(botonEliminar).click();
    }

    public int obtenerCantidadEnCarrito() {
        List<WebElement> contador = driver.findElements(contadorCarrito);

        if (contador.isEmpty()) {
            return 0;
        }

        return Integer.parseInt(contador.get(0).getText());
    }

    public CartPage irAlCarrito() {
        esperarElementoClickeable(enlaceCarrito).click();
        return new CartPage(driver);
    }

    // Métodos privados de apoyo
    private String convertirAId(String nombreProducto) {
        return nombreProducto.toLowerCase().replace(" ", "-");
    }

    private WebElement esperarElementoVisible(By elemento) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elemento));
    }

    private WebElement esperarElementoClickeable(By elemento) {
        return wait.until(ExpectedConditions.elementToBeClickable(elemento));
    }
}