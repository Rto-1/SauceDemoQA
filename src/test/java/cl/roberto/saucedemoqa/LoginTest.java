
package cl.roberto.saucedemoqa;

import cl.roberto.saucedemoqa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

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

    @Test
    public void loginConCredencialesIncorrectas() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.ingresarUsuario("usuario_incorrecto");
        loginPage.ingresarPassword("clave_incorrecta");
        loginPage.hacerClickLogin();

        Assert.assertTrue(
                loginPage.obtenerMensajeError().contains(
                        "Username and password do not match"
                )
        );
    }
}
