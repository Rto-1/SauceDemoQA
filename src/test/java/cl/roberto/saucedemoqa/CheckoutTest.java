package cl.roberto.saucedemoqa;

import cl.roberto.saucedemoqa.pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseAutenticadoTest {

    private CheckoutPage irAlFormularioCheckout() {
        productsPage.agregarProducto("Sauce Labs Backpack");
        return productsPage.irAlCarrito().irACheckout();
    }

    @Test(description = "Validar el error al continuar el checkout sin ingresar datos")
    public void checkoutSinDatos() {

        CheckoutPage checkoutPage = irAlFormularioCheckout();

        checkoutPage.hacerClickContinuar();

        Assert.assertTrue(
                checkoutPage.obtenerMensajeError().contains("First Name is required"),
                "No apareció el error 'First Name is required'"
        );
    }

    @Test(description = "Validar el error al continuar el checkout sin apellido")
    public void checkoutSinApellido() {

        CheckoutPage checkoutPage = irAlFormularioCheckout();

        checkoutPage.ingresarNombre("Roberto");
        checkoutPage.hacerClickContinuar();

        Assert.assertTrue(
                checkoutPage.obtenerMensajeError().contains("Last Name is required"),
                "No apareció el error 'Last Name is required'"
        );
    }

    @Test(description = "Validar el error al continuar el checkout sin código postal")
    public void checkoutSinCodigoPostal() {

        CheckoutPage checkoutPage = irAlFormularioCheckout();

        checkoutPage.ingresarNombre("Roberto");
        checkoutPage.ingresarApellido("Pérez");
        checkoutPage.hacerClickContinuar();

        Assert.assertTrue(
                checkoutPage.obtenerMensajeError().contains("Postal Code is required"),
                "No apareció el error 'Postal Code is required'"
        );
    }

    @Test(description = "Validar que con datos completos se avanza al resumen del pedido")
    public void checkoutConDatosValidos() {

        CheckoutPage checkoutPage = irAlFormularioCheckout();

        checkoutPage.completarFormulario("Roberto", "Pérez", "7550000");
        checkoutPage.hacerClickContinuar();

        Assert.assertEquals(
                checkoutPage.obtenerTitulo(),
                "Checkout: Overview",
                "No se llegó al resumen del pedido"
        );
    }
}