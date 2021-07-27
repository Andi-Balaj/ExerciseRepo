import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeleteUserTest {
    String driverPath="/home/andibalaj/Downloads/geckodriver-v0.29.1-linux64/geckodriver";
    String baseUrl="http://localhost:4200/users";
    public WebDriver driver;
    private DeleteUserPage deletePage;

    @BeforeTest
    public void initialSetUp() {
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        deletePage=new DeleteUserPage(driver);
    }
    @Test(priority = 1) /*19*/
    public void deleteUser(){
        driver.get(baseUrl);
        deletePage.getDeleteUser().click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String string= driver.findElement(By.xpath("(//span)[8]")).getText();
        deletePage.getYes().click();
        try {
            Assert.assertFalse(driver.findElement(By.xpath("//span[contains(text(),'" + string + "')]")).isDisplayed());
        } catch (Exception ex) {
            Assert.assertTrue(true);
        }
    }
    @Test(priority = 2) /*20*/
    public void cancelButton(){
        driver.get(baseUrl);
        deletePage.getDeleteUser().click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deletePage.getNo().click();
        Assert.assertTrue(deletePage.getUsername().isDisplayed());
    }
    @AfterTest
    public void closeDriver() {
        driver.close();
    }
}
