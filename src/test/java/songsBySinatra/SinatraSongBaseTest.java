package songsBySinatra;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

public class SinatraSongBaseTest {
	
	protected WebDriver driver;
	//Que significa final en esta linea
	public static final String WINDOWS_DRIVER_PATH = "C:\\Windows\\";
	public static final String UNIX_DRIVER_PATH = "/usr/local/bin/";

	public void setUp(String browser, String url) {
		//Como funciona el starBrowser
		driver = startBrowser(System.getProperty("os.name"), browser);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	private WebDriver startBrowser(String osName, String browserName) {
		String basePath = "";
		String fileExt = "";
		String execName = "";
		if(osName.startsWith("Windows")) {
			basePath = WINDOWS_DRIVER_PATH;
			fileExt = ".exe";
		}
		else {
			basePath = UNIX_DRIVER_PATH;
		}

		if (BrowserType.FIREFOX.equals(browserName)) {
			execName = "geckodriver";
			//--------que hace el set property?
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_XPI_PROPERTY, basePath + execName + fileExt);
			return new FirefoxDriver();
		} else if (BrowserType.SAFARI.equals(browserName)) {
			execName = "safaridriver";
			System.setProperty("webdriver.safari.driver", basePath + execName + fileExt);
			return new SafariDriver();
		} else if (BrowserType.EDGE.equals(browserName)) {
			execName = "msedgedriver";
			System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, basePath + execName + fileExt);
			return new EdgeDriver();
		} else {
			execName = "chromedriver";
			System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, basePath + execName + fileExt);
			return new ChromeDriver();
		}
	}

	public void verificarPaginaHome() {
		//localizar el elemento de login
		WebElement linkLogin = driver.findElement(By.cssSelector("[href='/login']"));
		//preguntar si esta visible
		if (linkLogin.isDisplayed()) {
			System.out.println("Si es visible el login");
		} else {
			System.out.println("No es visible el link de login");
			System.exit(-1);
		}

	}

	public void clickLogin() {
		WebElement linkLogin = driver.findElement(By.cssSelector("[href='/login']"));
		linkLogin.click();
	}

	public void verificarPaginaLogin(){
		//verificar que existen los campos de username, password, boton login
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.xpath("//input[@value = 'Log In']"));
//localizar los tres elementos
		//preguntar si estan visibles
		if (usernameField.isDisplayed() && passwordField.isDisplayed() && loginButton.isDisplayed())
		{
			System.out.println("Si son visibles los elementos username, password y boton login");
		}else{
			System.out.println("No se muestran los elementos username, password ni login");
			driver.quit();
		}
	}

	public void logIntoSinatra(String user, String password) {
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.xpath("//input[@value = 'Log In']"));
		//ingresar los datos en campos de username y password
		usernameField.sendKeys("frank");
		passwordField.sendKeys("sinatra");
		//click a login
		loginButton.click();
		//verificar que aparece la pagina de 'Songs'
	}

//	public void validateUserLogged(String user) {
//	}
	public void validateUserLogged() {
		//encontar el header
		WebElement tituloSongs = driver.findElement(By.linkText("Songs"));
		System.out.println("Verificado"+tituloSongs);
		//encontrar la liga de create a new song
		WebElement linkCrearCancion = driver.findElement(By.cssSelector("[href='/songs/new']"));
		System.out.println("Verificado"+linkCrearCancion);
		//verificar el mensaje de bienvenida
		WebElement bienvenida = driver.findElement(By.cssSelector("[class='flash notice']"));
		System.out.println("Valor de bienvenida" + bienvenida);

		//preguntar si estan visibles
		if (tituloSongs.isDisplayed() && linkCrearCancion.isDisplayed() && bienvenida.isDisplayed()){
			System.out.println("Si estan visibles el titulo,el link, y la bienvenida");
		}
	}


	@After
	public void tearDown() {
		driver.quit();
	}


}