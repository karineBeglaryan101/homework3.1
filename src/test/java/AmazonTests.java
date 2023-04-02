import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;


public class AmazonTests {
    public static final String WEBDRIVER = "webdriver.chrome.driver";
    public static final String DRIVER_PATH = "src/Drivers/chromedriver";
    public static final String baseURL = "https://www.amazon.com/";
    public static WebDriver driver;

    @BeforeClass
    public static void initWebDriver(){
        System.setProperty(WEBDRIVER, DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @org.testng.annotations.Test
    public void searchButtonTest(){
        driver.get(baseURL);
        WebElement searchButton = driver.findElement(By.id("twotabsearchtextbox"));
        searchButton.click();
        Assert.assertTrue(searchButton.getText().isEmpty());
    }

    @org.testng.annotations.Test
    public void searchBookTest(){
        driver.get(baseURL);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("A man called ove" + Keys.ENTER);
        String book = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[2]/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span")).getText();
        Assert.assertTrue(book.contains("A Man Called Ove: A Novel"));
    }

    @org.testng.annotations.Test
    public void loginWithWrongEmailTest(){
        driver.get(baseURL);
        driver.findElement(By.cssSelector("#nav-link-accountList")).click();
        driver.findElement(By.id("ap_email")).sendKeys("aaa"+Keys.ENTER);
        String message = driver.findElement(By.id("auth-error-message-box")).getText();
        Assert.assertTrue(message.contains("We cannot find an account with that email address"));
    }


    @org.testng.annotations.Test
    public void checkingTheCartTest(){
        driver.get(baseURL);
        driver.findElement(By.id("nav-cart")).click();
        WebElement element =  driver.findElement(By.id("sc-active-cart"));
        String message = element.getText();
        Assert.assertTrue(message.contains("Your Amazon Cart is empty"));
    }


    @org.testng.annotations.Test
    public void changeLanguageTest(){
        driver.get(baseURL);
        driver.findElement(By.id("icp-nav-flyout")).click();
        WebElement language = driver.findElement(By.xpath("//*[@id=\"icp-language-settings\"]/div[3]/div"));
        language.click();
        WebElement message = driver.findElement(By.id("icp-language-subheading"));
        String text = message.getText();
        Assert.assertTrue(text.contains("Selecciona el idioma que prefieres utilizar para navegar, comprar y comunicarte."));

    }
    


    @AfterTest
    public void tearDown(){
        driver.quit();
    }



}
