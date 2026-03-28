package PuntosDePractica;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PuntoDosPractica
{
    private SelfHealingDriver _driver;

    @BeforeMethod
    public void setup()
    {
        // inicializamos los drivers (atributos de la clase)
        // haciendo el wrap con el driver self-healing de selenium
        // esto permite la integración con Healenium
        WebDriver delegate =  new ChromeDriver();
        _driver = SelfHealingDriver.create(delegate);
    }

    @Test
    public void PrimerTest() throws InterruptedException
    {
        // Creamos el driver para el sitio web
        _driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        /* Creamos los objetos WebElement para la siguiente informacion del sitio web:
           - Text input field
           - Password field
           - Text area
        */
        WebElement textInput = _driver.findElement(By.id("my-text-id"));
        WebElement passwordField = _driver.findElement(By.id("my-password"));
        WebElement textArea = _driver.findElement(By.id("my-textarea"));
        // Ingresamos valores para cada campo
        textInput.sendKeys("Mi usuario");
        passwordField.sendKeys("Mi contraseña");
        textArea.sendKeys("Texto descriptivo");
        /*
            Creamos los objetos para la interacción con el campo dropdown, checkbox, radio button
           y date picker.
         */
        WebElement dropdown = _driver.findElement(By.id("my-select"));
        WebElement checkbox = _driver.findElement(By.id("my-check-1"));
        WebElement radioButton = _driver.findElement(By.id("my-radio-2"));
        WebElement datePicker = _driver.findElement(By.id("my-date"));

        // Interaccion con el dropdown
        dropdown.click();
        // Navegamos entre las opciones por medio de simular las teclas de flechas.
        dropdown.sendKeys(Keys.ARROW_DOWN); // Navegamos a la primera opcion
        // y la elegimos
        dropdown.click();
        // Interactuamos con el checkbox, des-seleccionándolo
        checkbox.click();
        // Interactuamos con el radio button, seleccionando el segundo. (Etiquetado como "Default")
        radioButton.click();
        // Interactuamos con el date picker, ingresando una fecha
        datePicker.click();
        datePicker.sendKeys("2026/01/01");

        // Por ultimo, subimos el formulario
        // Al no poder encontrar el boton de submit por su id, name o class, lo buscamos por su xpath
        // Pero añadimos un try catch, para que arroje la excepcion especifica en caso de no encontrarl el objeto.
        try {
            WebElement submitButton = _driver.findElement(By.xpath("/html/body/main/div/form/div/div[2]/button"));
            System.out.println("El texto del boton es: " + submitButton.getText()); // confirmacion de localizacion
            submitButton.click();
        } catch (Exception e) {
            throw new RuntimeException("No se pudo encontrar el boton de submit por su xpath", e);
        }
    }

    @AfterMethod
    public void teardown()
    {
        if (_driver != null)
            _driver.quit();
    }
}
