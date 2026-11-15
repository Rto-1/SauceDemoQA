
package cl.roberto.saucedemoqa;

import cl.roberto.saucedemoqa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class LoginTest extends BaseTest {

    @Test
    public void loginExitoso() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.ingresarUsuario("standard_user");
        loginPage.ingresarPassword("secret_sauce");
        loginPage.hacerClickLogin();

        Assert.assertEquals(
                loginPage.obtenerTituloPagina(),
                "Products"
        );
    }

    @Test
    public void loginUsuarioBloqueado() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.ingresarUsuario("locked_out_user");
        loginPage.ingresarPassword("secret_sauce");
        loginPage.hacerClickLogin();

        Assert.assertTrue(
                loginPage.obtenerMensajeError().contains(
                        "Sorry, this user has been locked out"
                )
        );
    }

    @Test
    public void loginSinUsuario() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.ingresarPassword("secret_sauce");
        loginPage.hacerClickLogin();

        Assert.assertTrue(
                loginPage.obtenerMensajeError().contains(
                        "Username is required"
                )
        );
    }

    @Test
    public void loginSinPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.ingresarUsuario("standard_user");
        loginPage.hacerClickLogin();

        Assert.assertTrue(
                loginPage.obtenerMensajeError().contains(
                        "Password is required"
                )
        );
    }

    @Test(dataProvider = "credencialesIncorrectas")
    public void loginConCredencialesIncorrectas(
            String usuario,
            String password,
            String mensajeEsperado) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.ingresarUsuario(usuario);
        loginPage.ingresarPassword(password);
        loginPage.hacerClickLogin();

        Assert.assertTrue(
                loginPage.obtenerMensajeError().contains(mensajeEsperado)
        );
    }

    @DataProvider(name = "credencialesIncorrectas")
    public Object[][] obtenerCredenciales() {
        return new Object[][]{
                {
                        "usuario_incorrecto",
                        "clave_incorrecta",
                        "Username and password do not match"
                },
                {
                        "standard_user",
                        "clave_incorrecta",
                        "Username and password do not match"
                },
                {
                        "usuario_incorrecto",
                        "secret_sauce",
                        "Username and password do not match"
                }
        };
    }
}
