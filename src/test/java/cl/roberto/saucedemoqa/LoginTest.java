package cl.roberto.saucedemoqa;

import cl.roberto.saucedemoqa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LoginTest extends BaseTest {

    @Test(description = "Validar inicio de sesión exitoso con credenciales válidas")
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

    @Test(description = "Validar que un usuario bloqueado no pueda iniciar sesión")
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

    @Test(description = "Validar inicio de sesión sin ingresar nombre de usuario")
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

    @Test(description = "Validar inicio de sesión sin ingresar contraseña")
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


    private Object[][] leerCSV() throws IOException {

        List<Object[]> datos = new ArrayList<>();

        InputStream archivo = getClass()
                .getClassLoader()
                .getResourceAsStream("credenciales.csv");

        if (archivo == null) {
            throw new IOException("No se encontró credenciales.csv en resources");
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(archivo, StandardCharsets.UTF_8))) {

            String linea;

            // Saltamos los encabezados
            br.readLine();

            while ((linea = br.readLine()) != null) {

                // Ignoramos líneas vacías
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] columnas = linea.split(",", 3);

                // Verificamos que existan las 3 columnas
                if (columnas.length != 3) {
                    throw new IOException("Fila CSV incorrecta: " + linea);
                }

                datos.add(new Object[]{
                        columnas[0].trim(),
                        columnas[1].trim(),
                        columnas[2].trim()
                });
            }
        }

        return datos.toArray(new Object[0][]);
    }


    @DataProvider(name = "credencialesIncorrectas")
    public Object[][] obtenerCredenciales() throws IOException {
        return leerCSV();
    }
}