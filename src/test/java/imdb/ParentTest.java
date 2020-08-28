package imdb;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class ParentTest {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	public static final int SHORT_WAIT = 5;
	public static final int MEDIUM_WAIT = 10;
	public static final int LONG_WAIT = 15;

	
	@Before
	public void setUp() {
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(SHORT_WAIT, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, LONG_WAIT);
	}
	
	@After
	public void tearDown() {
		driver.quit();
		
	}
	protected void validateMovieExists(String movieName) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(movieName)));
	}

	protected void searchMovie(String movieName) {

		//encontrar el campo de busqueda name= "q"
		WebElement campoBusqueda = driver.findElement(By.name("q"));
		campoBusqueda.sendKeys(movieName);
		//encontrar el boton de busqueda id="navbar-submit-button
		WebElement botonBusqueda = driver.findElement(By.cssSelector("#suggestion-search-button"));
		botonBusqueda.click();
	}
	//Econtrar el campo busqueda

	protected void validatePage() {
		// TODO Auto-generated method stub
		
	}

	protected void navigateToPage(String url) {
		// TODO Auto-generated method stub
		driver.navigate().to(url);
	}
	
	protected void playTrailer() {
		// .slate_button.prevent-ad-overlay.video-modal
		WebElement playbutton = driver.findElement(By.cssSelector(".slate_button.prevent-ad-overlay.video-modal"));
		playbutton.click();
	}

	protected void validateCorrectMovie(String nombre, String anio) {
		WebElement titleMovie = driver.findElement(By.cssSelector(".title_wrapper h1"));
		if (titleMovie.getText().contains(nombre)){
			System.out.println("El nombre de la pelicula es el correcto");
		}

		WebElement anioMovie = driver.findElement(By.cssSelector("#titleYear a"));
		if (anioMovie.getText().contains(anio)){
			System.out.println("El anio de la pelicula es el correcto");
		}

	}

	protected void selectMovie(String movieName, String movieYear) {
		// encontramos un elemento cuyo link diga 'movieName' y cuyo anio coincida con movieYear
		String xpathResultado = "//td[contains(., '" + movieName + " (" + movieYear+ ")')]/a";
		WebElement peliculaCorrecta = driver.findElement(By.xpath(xpathResultado));
		peliculaCorrecta.click();
		System.out.println("Si accedio a la pelicula correcta");
	}
	
	protected void validateMovieStars() {
		// TODO Auto-generated method stub
		
	}


}
