package co.com.reliquias.software.steps;

import co.com.reliquias.software.pages.ExitoPage;

import co.com.reliquias.software.pages.interacciones.MainPageInteraction;
import net.thucydides.core.annotations.Step;
import org.fluentlenium.core.annotation.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ExitoStep {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSteps.class);

    @Page
    private ExitoPage exitoPage;


    @Step("Elijo productos")
    public void elijoUnaY(String cat, String subCat) {
        exitoPage.elijoUnaY(cat, subCat);
    }

    @Step("Selecciono productos")
    public void seleccionoCincoProductosAleatorios(Map<String, String> produc, Map<String, String> cant) {
        String productos = produc.get("sProductos");
        String cantidad = cant.get("sUnidades");
        exitoPage.seleccionoCincoProductosAleatorios(productos, cantidad);
    }

    @Step("Nos dirigimos al carrito")
    public void nosDirigimosAlCarritoDeCompras() {
        exitoPage.nosDirigimosAlCarritoDeCompras();

    }

    @Step("Abrir la pagina")
    public void queElUsuarioNavegaALaPaginaDeInicioDeSesion() {
        // loginSteps.openLoginPage();
//        exitoPage.queElUsuarioNavegaALaPaginaDeInicioDeSesion();


    }

    @Step("user open login page")
    public void openLoginPage() {
        exitoPage.open();
        LOGGER.info("user open login page");
    }

    @Step("validar precio del producto")
    public void validarPrecioProducto(Map<String, String> produc) {
        String producto = produc.get("sProductos");
        exitoPage.validarPrecioProducto(producto);
    }

    @Step("validar nombre del producto")
    public void validarNombreProducto(Map<String, String> produc) {
        String producto = produc.get("sProductos");
        exitoPage.validarNombreProducto(producto);

    }

    @Step("validar cantidad del producto")
    public void validoCantidadesProductosCarrito() {
        exitoPage.validoCantidadesProductosCarrito();
    }

    @Step("validar los nombres de los productos")
    public void validarNombresProductos() {
        exitoPage.validarNombresProductos();
    }

    @Step("validar cantidades de los productos")
    public void validarPreciosProductos() {
        exitoPage.validarPreciosProductos();
    }
    @Step("validar numero de productos totales")
    public void validoNumeroProductosAgregadosIgualCarrito() {
        exitoPage.validoNumeroProductosAgregadosIgualCarrito();
    }
}
