package co.com.reliquias.software.pages;

import co.com.reliquias.software.models.ProductModel;
import co.com.reliquias.software.pages.interacciones.MainPageInteraction;
import co.com.reliquias.software.pages.mapeos.MainPage;
import co.com.reliquias.software.steps.LoginSteps;
import groovyjarjarpicocli.CommandLine;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.actions.Scroll;
import net.thucydides.core.annotations.DefaultUrl;
import org.fluentlenium.core.annotation.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.SchemaOutputResolver;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@DefaultUrl("https://www.exito.com")
public class ExitoPage extends PageObject {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSteps.class);

    @Page
    private MainPageInteraction mainPageInteraction;
    @Page
    private MainPage mainPage;

    @Page
    private ProductModel productModel;

    @FindBy(xpath = "//button[@aria-label='Collapse menu']/div")
    public WebElement btnMenu;

    @FindBy(xpath = "//button[@data-testid='cart-toggle']")
    public WebElement btnCarritoCompras;


    //LISTAS
    @FindBy(xpath = "//section//li")
    public List<WebElement> lstCategoria;

    @FindBy(xpath = "//ul//li[@data-link='true']")
    public List<WebElement> lstSubCategoria;

    @FindBy(xpath = "//section//h3//a")
    public List<WebElement> lstProductos;

    @FindBy(xpath = "//section//h3//a")
    public WebElement producto;
    @FindBy(xpath = "//div/button[@class='DefaultStyle_default__vCozi ']")
    public List<WebElement> lstBotonesAgregarCarrito;
    public List<ProductModel> lstproductModel = new ArrayList<ProductModel>();
    public Integer precioxProducto;
    public String nombrexProducto;
    public Integer unidadxProducto;
    public Integer idProd = 0;

    public List<Integer> lstprecioxProducto = new ArrayList<>();
    public List<String> lstnombrexProducto = new ArrayList<>();
    public List<Integer> lstunidadxProducto = new ArrayList<>();


    public void moveScrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void desplazarPagina(String numero) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollBy(0," + numero + ")");
    }

    public void elijoUnaY(String cat, String subCat) {
        btnMenu.click();
        //ELEGIR CATEGORIA
        for (WebElement element : lstCategoria) {
            if (element.getText().trim().toLowerCase(Locale.ROOT).contains(cat.trim().toLowerCase(Locale.ROOT))) {
                element.click();
                break;
            }
        }

        //ELEGIR SUB CATEGORIA
        for (WebElement element : lstSubCategoria) {
            if (element.getText().trim().toLowerCase(Locale.ROOT).contains(subCat.trim().toLowerCase(Locale.ROOT))) {
                element.click();
                break;
            }
        }

    }

    public void seleccionoCincoProductosAleatorios(String productos, String cantidad) {
        Esperar(1500);
        int cant = Integer.parseInt(cantidad);
        //EN ESTE FOR SE BUSCAN LOS PRODUCTOS
        for (WebElement element : lstProductos) {
            moveScrollToElement(element);
            Esperar(2000);
            WebDriverWait webDriverWait = new WebDriverWait(getDriver(), Duration.ofSeconds(50));
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(element));

            //EN ESTE IF SE SELECCIONA EL PRODUCTO DE LA LISTA
            if (productos.trim().replace(" ", "").contains(element.getText().replace(" ", "").trim())) {
                System.out.println("PRODUCTO:" + productos.trim());
                System.out.println("compre prouducto:" + element.getText());
                desplazarPagina("-300");
                Esperar(5000);

                //DARLE CLICK AL BOTON AGREGAR CANASTA POR PRIMERA VEZ
                WebElement btnProductoX = getDriver().findElement(By.xpath("//a[contains(text(),'" + productos + "')]//..//..//..//..//div/button"));
                btnProductoX.click();

                // EN ESTA LINEA SE GUARDA EN UNA CLASE PRODUCTO LOS ATRIBUTOS DE ESTE

                lstproductModel.add(new ProductModel(idProd, productos, Integer.parseInt(obtenerPrecio(productos)), cant));


                //HACER CLICK EL NUMERO DE VECES IGUALES A LA CANTIDAD DE PRODUCTOS, RECORDAR QUE YA LE DIMOS CLICK UNA VEZ , ESTE METODO ES PARA COMPLETAR LAS UNIDADES DEL PRODUCTO
                for (int j = 0; j < cant - 1; j++) {
                    Esperar(4000);
                    WebElement plus = getDriver().findElement(By.xpath("//a[contains(text(),'" + productos + "')]//..//..//..//..//div[@class='QuantitySelectorDefault_defaultStyles__b4Srq  ']/button[2]"));
                    System.out.println("veces: " + cant);
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(plus)).click();
                }
                idProd++;
                break;
            }

        }

    }

    public String obtenerPrecio(String producto) {
        WebElement precio = getDriver().findElement(By.xpath("//a[contains(text(),'" + producto + "')]//..//..//..//..//div[@class='ProductPrice_container__CkWjL']//p[@class='ProductPrice_container__price__LS1Td']"));
        String precioFormateado = FormatearPrecio(precio.getText());
        return precioFormateado;

    }

    public void nosDirigimosAlCarritoDeCompras() {
        Esperar(5000);
        moveScrollToElement(btnCarritoCompras);
        desplazarPagina("-500");
        WebDriverWait webDriverWait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(btnCarritoCompras)).click();

    }


    public void Esperar(Integer tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String FormatearPrecio(String precio) {
        String obteniendoPrecio = precio.replace("$", " ").replace(".", "").trim();
        System.out.println("el precio se obtuvo: " + obteniendoPrecio);
        return obteniendoPrecio;
    }

    public Boolean validarPrecioProducto(String producto) {
        Boolean validar = false;
        int precioFinal = (precioxProducto * unidadxProducto);
        WebElement ValidarPrecioProducto = getDriver().findElement(By.xpath("//div[@data-molecule-product-detail-price-best-price='true']//span"));
        Integer precioFinalObtenido = Integer.parseInt(FormatearPrecio(ValidarPrecioProducto.getText()));
        if (precioFinalObtenido == precioFinal) {
            LOGGER.info("EL precio DEL PRODUCTO ES EL MISMO");
            validar = true;
        }
        return validar;
    }

    public Boolean validarPreciosProductos() {
        Esperar(5000);
        Boolean validar = false;
        for (ProductModel productModelElementos : lstproductModel) {
            WebElement validarPrecios = getDriver().findElement(By.xpath("//div[@data-molecule-product-detail-name='true']//span[contains(text(),'"+productModelElementos.getNombreProducto()+"')]//..//..//div[@data-molecule-product-detail-price-best-price='true']"));
            System.out.println("ESTOS SON LOS PRECIOS POR PRODUCTO QUE SE VALIDA POO:" + productModelElementos.getPrecioUnitario());
            //for (WebElement element : validarPrecios) {
                Esperar(1500);
                moveScrollToElement(validarPrecios);
                System.out.println("ESTOS SON LOS PRECIOS QUE DEBO VALIDAR DE LA PAGINA : " + validarPrecios.getText().trim());
                Integer precioFinalObtenido = Integer.parseInt(FormatearPrecio(validarPrecios.getText().trim()));
                if (precioFinalObtenido == (productModelElementos.getPrecioUnitario() * productModelElementos.getCantidad())) {
                    LOGGER.info("EL precio DEL PRODUCTO ES EL MISMO");
                    System.out.println("EL PRECIO DEL PRODUCTO ES EL MISMO SOUT");
                    validar = true;
                }else{
                    validar=false;
                    break;
                }
            }
        return validar;
    }

    public Boolean validarNombreProducto(String producto) {
        Boolean validar = false;

        WebElement ValidarNombreProducto = getDriver().findElement(By.xpath("//div[@data-molecule-product-detail-name='true']//span[contains(text(),'" + producto + "')]"));

        if (ValidarNombreProducto.getText().trim().replace(" ", "").contains
                (producto.trim().replace(" ", ""))) {
            LOGGER.info("EL NOMBRE DEL PRODUCTO ES EL MISMO");
            validar = true;
        }
        return validar;
    }

    public Boolean validarNombresProductos() {
        Esperar(4000);
        Boolean validar = false;
        List<WebElement> ValidarNombreProducto = getDriver().findElements(By.xpath("//div[@data-molecule-product-detail-name='true']//span"));
        for (ProductModel productModelElementos : lstproductModel) {
            System.out.println("PRODUCTO GUARDADO EN LA LISTA POO:" + productModelElementos.getNombreProducto());
            for (WebElement element : ValidarNombreProducto) {
                Esperar(2500);
                System.out.println("ELEMENTO A ENCONTRAR PARA VALIDAR:" + element.getText());
                moveScrollToElement(element);
                if (element.getText().replace(" ", "").contains(productModelElementos.getNombreProducto().replace(" ", ""))) {
                    LOGGER.info("EL NOMBRE DEL PRODUCTO ES EL MISMO");
                    System.out.println("el nombre del producto es el mismo");
                    validar = true;
                    break;
                }
            }

        }
        return validar;
    }

    public Boolean validoCantidadesProductosCarrito() {
        Boolean validar = false;
         for (ProductModel productModelElementos : lstproductModel) {
             WebElement validarCantidadesProductos = getDriver().findElement(By.xpath("//div[@data-molecule-product-detail-name='true']//span[contains(text(),'"+productModelElementos.getNombreProducto()+"')]//..//..//div[@data-molecule-quantity-und='true']//span"));
             System.out.println("ESTAS SON LAS CANTIDADES QUE VALIDAR DESDE POO:" + productModelElementos.getCantidad());
                Esperar(1500);
                moveScrollToElement(validarCantidadesProductos);
                System.out.println("CANTIDAD PARA VALIDAR: " + validarCantidadesProductos.getText().trim());
                Integer validarCantidad = Integer.parseInt(validarCantidadesProductos.getText().trim());
                lstunidadxProducto.add(validarCantidad);
                if (validarCantidad == productModelElementos.getCantidad()) {
                    System.out.println("la cantidad es la misma");
                    LOGGER.info("LA CANTIDAD ES LA MISMA");
                    validar = true;
                }else {
                    validar=false;
                    break;
                }
            }
        return validar;
    }

    public Boolean validoNumeroProductosAgregadosIgualCarrito() {
        Boolean validar =false;
        Integer nmrProducto=0;
        Integer numrProdcutoValidar=0;
        for(ProductModel productModel1 : lstproductModel){
            nmrProducto = nmrProducto + productModel1.getCantidad();
        }
        System.out.println("NUMERO TOTAL DE PRODUCTOS: "+nmrProducto);
        for(int cantidad : lstunidadxProducto){
            numrProdcutoValidar= numrProdcutoValidar +cantidad;

        }
        System.out.println("NUMERO TOTAL DE PRODUCTOS PARA VALIDAR EN LA PAGINA: "+numrProdcutoValidar);
        if (nmrProducto==numrProdcutoValidar){
            System.out.println("SE VALIDO LA MISMO NUMERO DE PRODUCTOS QUE EN LA PAGINA DEL CARRO");
            validar=true;
        }
        return validar;
    }
}
