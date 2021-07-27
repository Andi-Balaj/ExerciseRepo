import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginPageTest {
    String driverPath="/home/andibalaj/Downloads/geckodriver-v0.29.1-linux64/geckodriver";
    String baseUrl="http://localhost:4200/";
    public WebDriver driver;
    private LoginPage loginPage;

    @BeforeTest
    public void initialSetUp() {
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        loginPage=new LoginPage(driver);
    }
    @Test(priority = 3)
    public void verifyLoginWithNoUsernameAndNoPassword(){
        loginPage.getLoginButtonElement().click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(driver.findElement(By.xpath("//button[@class='btn btn-outline-primary'][contains(.,'Login')]")).isDisplayed());
    }
    @Test(priority = 4)
    public void verifyLoginWithUsernameAndNoPassword(){
        driver.get(baseUrl);
        String username=loginPage.generateRandomUsername();
        loginPage.getUsernameElement().sendKeys(username);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loginPage.getLoginButtonElement().click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'" + username + "')]")).isDisplayed());
    }
    @Test(priority = 5)
    public void verifyLoginWithPasswordNoUsername(){
        driver.get(baseUrl);
        String password=loginPage.generateRandomPassword();
        loginPage.getPasswordElement().sendKeys(password);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loginPage.getLoginButtonElement().click();
        Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'" + password + "')]")).isDisplayed());
    }
    @Test(priority = 2)
    public void verifyLoginWithUsernameAndPassword(){
        driver.get(baseUrl);
        String username=loginPage.generateRandomUsername();
        String password=loginPage.generateRandomPassword();
        loginPage.getUsernameElement().sendKeys(username);
        loginPage.getPasswordElement().sendKeys(password);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loginPage.getLoginButtonElement().click();
        try{
            Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'" + username + "')]")).isDisplayed());
        }catch (Exception e){
            Assert.fail();
        }

    }
    @Test(priority = 1)
    public void verifyHomepageTitle() {
        String expectedTitle = "Frontend";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @AfterTest
    public void closeDriver() {
        driver.close();
    }
}
