package Sopitas;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ParentSopitas {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @Before
    public void before(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 15);
    }

    @After
    public void after(){
        driver.quit();

    }
    public void navegarPagina(String url) {
    driver.get(url);
    }
    public void verificarPagina(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1 .text-hide.logo-sopitas")));
        System.out.println("Estamos en la pagina de sopitas");
    }
    public boolean elementIsVisible(By descripcion) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(descripcion));
            return true;
        } catch(Exception ex) {
            return false;
        }

    }
    public void verificarHashtag() throws Exception {
        if(elementIsVisible(By.cssSelector("#trending h5"))){
            System.out.println("El elemento hashtag es visible");
        } else throw new Exception("El elemento hashtag no es visible");
    }
    public void imprimitHashTag(){
        List<WebElement> listahashtag = driver.findElements(By.cssSelector("#trending ol li"));
        for(WebElement element:listahashtag ){
            System.out.println(element.getText());
        }


    }

}