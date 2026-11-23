package cl.roberto.saucedemoqa;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTest extends BaseAutenticadoTest {

    @Test(description = "Validar que el catálogo muestra los 6 productos")
    public void catalogoMuestraSeisProductos() {

        Assert.assertEquals(
                productsPage.obtenerCantidadProductos(),
                6,
                "La cantidad de productos del catálogo no es la esperada"
        );
    }

    @Test(description = "Validar que al agregar un producto el carrito muestra 1")
    public void agregarProductoAlCarrito() {

        productsPage.agregarProducto("Sauce Labs Backpack");

        Assert.assertEquals(
                productsPage.obtenerCantidadEnCarrito(),
                1,
                "El carrito debería tener 1 producto"
        );
    }

    @Test(description = "Validar que al eliminar un producto el carrito queda vacío")
    public void eliminarProductoDelCarrito() {

        productsPage.agregarProducto("Sauce Labs Backpack");
        productsPage.eliminarProducto("Sauce Labs Backpack");

        Assert.assertEquals(
                productsPage.obtenerCantidadEnCarrito(),
                0,
                "El carrito debería estar vacío"
        );
    }
}