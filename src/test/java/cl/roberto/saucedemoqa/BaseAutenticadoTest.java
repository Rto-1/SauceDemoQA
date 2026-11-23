package cl.roberto.saucedemoqa;

import cl.roberto.saucedemoqa.pages.LoginPage;
import cl.roberto.saucedemoqa.pages.ProductsPage;
import org.testng.annotations.BeforeMethod;

public class BaseAutenticadoTest extends BaseTest {

    protected ProductsPage productsPage;

    @BeforeMethod
    public void iniciarSesion() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.ingresarUsuario("standard_user");
        loginPage.ingresarPassword("secret_sauce");
        loginPage.hacerClickLogin();

        productsPage = new ProductsPage(driver);
    }
}