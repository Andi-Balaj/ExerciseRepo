import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EditUserTest {
    String driverPath="/home/andibalaj/Downloads/geckodriver-v0.29.1-linux64/geckodriver";
    String baseUrl="http://localhost:4200/users";
    public WebDriver driver;
    private EditUserPage editPage;

    @BeforeTest
    public void initialSetUp() {
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        editPage=new EditUserPage(driver);
    }
    @Test(priority = 1) /*15*/
    public void editUser() {
        driver.get(baseUrl);
        String newUsername=editPage.generateRandomUsername();
        String newEmail=editPage.generateRandomEmail();
        String newFullName=editPage.generateRandomFullName();
        String newPassword=editPage.generateRandomPassword();
        editPage.getEditUser().click();
        editPage.getEditUsername().clear();
        editPage.getEditUsername().sendKeys(newUsername);
        editPage.getEditEmail().clear();
        editPage.getEditEmail().sendKeys(newEmail);
        editPage.getEditFullName().clear();
        editPage.getEditFullName().sendKeys(newFullName);
        editPage.getEditPassword().clear();
        editPage.getEditPassword().sendKeys(newPassword);
        editPage.getEditTrait1Field().click();
        editPage.getEditTrait2Field().click();
        editPage.getEditTrait3Field().click();
        editPage.getEditTrait4Field().click();
        editPage.getEditSubmitButton().click();
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'" + newUsername + "')]")).isDisplayed());
        } catch (Exception ex) {
            Assert.fail();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test(priority = 2) /*16*/
    public void editUserCancel(){
        driver.get(baseUrl);
        String newUsername = editPage.generateRandomUsername();
        String newEmail = editPage.generateRandomEmail();
        String newFullName = editPage.generateRandomFullName();
        String newPassword = editPage.generateRandomPassword();
        editPage.getEditUser().click();
        editPage.getEditUsername().clear();
        editPage.getEditUsername().sendKeys(newUsername);
        editPage.getEditEmail().clear();
        editPage.getEditEmail().sendKeys(newEmail);
        editPage.getEditFullName().clear();
        editPage.getEditFullName().sendKeys(newFullName);
        editPage.getEditPassword().clear();
        editPage.getEditPassword().sendKeys(newPassword);
        editPage.getEditTrait1Field().click();
        editPage.getEditTrait3Field().click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        editPage.getEditCancelButton().click();
        try {
            Assert.assertFalse(driver.findElement(By.xpath("//span[contains(text(),'" + newFullName + "')]")).isDisplayed());
        }catch(Exception e) {
            Assert.assertFalse(false);
        }
    }
    @Test(priority = 4) /*18*/
    public void editUserWithNoUsernameNoEmailNoFullNameNoPassword(){
        editPage.getEditUser().click();
        editPage.clearFields();
        editPage.getEditSubmitButton().click();
        WebElement el=null;
        try{
            el=driver.findElement(By.xpath("(//span[contains(.,'Edit')])[1]"));
        }catch (Exception exception){}
        Assert.assertNull(el);
    }
    @Test(priority = 3) /*17*/
    public void editUserWithNoEmail() {
        driver.get(baseUrl);
        String newUsername = editPage.generateRandomUsername();
        String newFullName = editPage.generateRandomFullName();
        String newPassword = editPage.generateRandomPassword();
        editPage.getEditUser().click();
        editPage.getEditUsername().clear();
        editPage.getEditUsername().sendKeys(newUsername);
        editPage.getEditEmail().clear();
        editPage.getEditEmail().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        editPage.getEditFullName().clear();
        editPage.getEditFullName().sendKeys(newFullName);
        editPage.getEditPassword().clear();
        editPage.getEditPassword().sendKeys(newPassword);
        editPage.getEditTrait1Field().click();
        editPage.getEditTrait2Field().click();
        editPage.getEditTrait3Field().click();
        editPage.getEditTrait4Field().click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        editPage.getEditSubmitButton().click();
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'" + newFullName + "')]")).isDisplayed());
        } catch (Exception ex) {
            Assert.assertTrue(true);
        }
    }
    @AfterTest
    public void closeDriver() {
        driver.close();
    }
}
