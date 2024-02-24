package co.com.reliquias.software.definitions;


import co.com.reliquias.software.steps.ExitoStep;
import co.com.reliquias.software.steps.LoginSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.Map;

public class ExitoStepDefinition {
    @Steps

    private ExitoStep exitoStep;


    @Dado("Un usuario navega a la pagina de inicio de sesion")
    public void UsuarioNavegaPaginaInicioDeSesion() {
        exitoStep.openLoginPage();
         // exitoStep.queElUsuarioNavegaALaPaginaDeInicioDeSesion();
    }

    @Cuando("elijo una {string} y {string}")
    public void elijoUnaY(String cat, String subCat) {
        exitoStep.elijoUnaY(cat, subCat);
    }

    @Y("selecciono cinco productos aleatorios")
    public void seleccionoCincoProductosAleatorios(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps();
        List<Map<String, String>> listU = dataTable.asMaps();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).get("sProductos"));//nombre del producto Celular...
            System.out.println(listU.get(i).get("sUnidades"));//numero de veces o unidades
            exitoStep.seleccionoCincoProductosAleatorios(list.get(i), listU.get(i));
        }
        System.out.println("Termine de seleccionar todos mis productos");
    }

    @Y("nos dirigimos al carrito de compras")
    public void nosDirigimosAlCarritoDeCompras() {
        exitoStep.nosDirigimosAlCarritoDeCompras();
    }


    @Entonces("Valido que los nombres de los productos agregados sean igual que en el carro")
    public void validoQueLosNombresDeLosProductosAgregadosSeanIgualQueEnElCarro() {
            exitoStep.validarNombresProductos();
        }


    @Y("Valido que el total de los precios agregados sean igual que en el carrito")
    public void validoQueElTotalDeLosPreciosAgregadosSeanIgualQueEnElCarrito() {
            exitoStep.validarPreciosProductos();

    }

    @Y("Valido que las cantidades de los productos agregados sean igual que en el carrito")
    public void validoQueLasCantidadesDeLosProductosAgregadosSeanIgualQueEnElCarrito() {
        exitoStep.validoCantidadesProductosCarrito();
    }

    @Y("Valido que el numero de productos agregados debe ser igual que en el carrito")
    public void validoQueElNumeroDeProductosAgregadosDebeSerIgualQueEnElCarrito() {
        exitoStep.validoNumeroProductosAgregadosIgualCarrito();
    }
}

