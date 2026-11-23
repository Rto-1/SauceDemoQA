package cl.roberto.saucedemoqa;

import cl.roberto.saucedemoqa.pages.CartPage;
import cl.roberto.saucedemoqa.pages.LoginPage;
import cl.roberto.saucedemoqa.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private ProductsPage productsPage;

    @BeforeMethod
    public void iniciarSesion() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.ingresarUsuario("standard_user");
        loginPage.ingresarPassword("secret_sauce");
        loginPage.hacerClickLogin();

        productsPage = new ProductsPage(driver);
    }

    @Test(description = "Validar que el carrito muestra el producto agregado con su nombre")
    public void carritoMuestraProductoAgregado() {

        productsPage.agregarProducto("Sauce Labs Backpack");
        CartPage cartPage = productsPage.irAlCarrito();

        Assert.assertEquals(
                cartPage.obtenerTitulo(),
                "Your Cart",
                "No se llegó a la página del carrito"
        );

        Assert.assertTrue(
                cartPage.obtenerNombresProductos().contains("Sauce Labs Backpack"),
                "El carrito no contiene Sauce Labs Backpack"
        );
    }

    @Test(description = "Validar el precio del producto dentro del carrito")
    public void carritoMuestraPrecioCorrecto() {

        productsPage.agregarProducto("Sauce Labs Backpack");
        CartPage cartPage = productsPage.irAlCarrito();

        Assert.assertEquals(
                cartPage.obtenerPreciosProductos().get(0),
                "$29.99",
                "El precio del producto en el carrito no es el esperado"
        );
    }

    @Test(description = "Validar que el carrito contiene todos los productos agregados")
    public void carritoMuestraVariosProductos() {

        productsPage.agregarProducto("Sauce Labs Backpack");
        productsPage.agregarProducto("Sauce Labs Bike Light");
        CartPage cartPage = productsPage.irAlCarrito();

        Assert.assertEquals(
                cartPage.obtenerCantidadProductos(),
                2,
                "La cantidad de productos en el carrito no es la esperada"
        );
    }
}