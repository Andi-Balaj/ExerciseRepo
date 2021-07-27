import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class AddUserPageTest {
    String driverPath="/home/andibalaj/Downloads/geckodriver-v0.29.1-linux64/geckodriver";
    String baseUrl="http://localhost:4200/users";
    public WebDriver driver;
    private AddUserPage addPage;

    @BeforeTest
    public void initialSetUp() {
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        addPage=new AddUserPage(driver);
    }
    @Test(priority = 2) /*11*/
    public void cancelButton(){
        driver.get(baseUrl);
        String newUsername=addPage.generateRandomUsername();
        String newEmail=addPage.generateRandomEmail();
        String newFullName=addPage.generateRandomFullName();
        String newPassword=addPage.generateRandomPassword();
        addPage.getAddUser().click();
        addPage.getAddUsername().sendKeys(newUsername);
        addPage.getAddEmail().sendKeys(newEmail);
        addPage.getAddFullName().sendKeys(newFullName);
        addPage.getAddPassword().sendKeys(newPassword);
        addPage.getAddTrait1Field().click();
        addPage.getAddTrait2Field().click();
        addPage.getAddTrait3Field().click();
        addPage.getAddTrait4Field().click();
        addPage.getAddMaleGenderButton().click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addPage.getCancelButton().click();
        try {
            Assert.assertFalse(driver.findElement(By.xpath("//span[contains(.,'"+newEmail+"')]")).isDisplayed());
        }catch(Exception e) {
            Assert.assertTrue(true);
        }
    }
    @Test(priority = 3) /*12*/
    public void addUserWithExistingEmail(){
        driver.get(baseUrl);
        String newUsername=addPage.generateRandomUsername();
        String newFullName=addPage.generateRandomFullName();
        String newPassword=addPage.generateRandomPassword();
        String email =driver.findElement(By.xpath("(//span)[9]")).getText();
        addPage.getAddUser().click();
        addPage.getAddUsername().sendKeys(newUsername);

        addPage.getAddEmail().sendKeys(email);
        addPage.getAddFullName().sendKeys(newFullName);
        addPage.getAddPassword().sendKeys(newPassword);
        addPage.getAddTrait1Field().click();
        addPage.getAddTrait2Field().click();
        addPage.getAddTrait3Field().click();
        addPage.getAddTrait4Field().click();
        addPage.getAddMaleGenderButton().click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addPage.getSubmitButton().click();
        try {
            Assert.assertFalse(driver.findElement(By.xpath("//span[contains(text(),'" + newUsername + "')]")).isDisplayed());
        } catch (Exception ex) {
            Assert.assertTrue(true);
        }
    }

    @Test(priority = 1) /*10*/
    public void addUserWithUsernameEmailFullNamePasswordTraitGender(){
        driver.get(baseUrl);
        String newUsername=addPage.generateRandomUsername();
        String newEmail=addPage.generateRandomEmail();
        String newFullName=addPage.generateRandomFullName();
        String newPassword=addPage.generateRandomPassword();
        addPage.getAddUser().click();
        addPage.getAddUsername().sendKeys(newUsername);
        addPage.getAddEmail().sendKeys(newEmail);
        addPage.getAddFullName().sendKeys(newFullName);
        addPage.getAddPassword().sendKeys(newPassword);
        addPage.getAddTrait1Field().click();
        addPage.getAddTrait2Field().click();
        addPage.getAddTrait3Field().click();
        addPage.getAddTrait4Field().click();
        addPage.getAddMaleGenderButton().click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addPage.getSubmitButton().click();
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'" + newEmail + "')]")).isDisplayed());
        } catch (Exception ex) {
            Assert.fail();
        }
    }
    @Test(priority = 5) /*14*/
    public void addUserWithUsernameEmailFullNamePassword() {
        driver.get(baseUrl);
        String newUsername = addPage.generateRandomUsername();
        String newEmail = addPage.generateRandomEmail();
        String newFullName = addPage.generateRandomFullName();
        String newPassword = addPage.generateRandomPassword();
        addPage.getAddUser().click();
        addPage.getAddUsername().sendKeys(newUsername);
        addPage.getAddEmail().sendKeys(newEmail);
        addPage.getAddFullName().sendKeys(newFullName);
        addPage.getAddPassword().sendKeys(newPassword);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addPage.getSubmitButton().click();
        try {
            Assert.assertFalse(driver.findElement(By.xpath("//span[contains(.,'" + newFullName + "')]")).isDisplayed());
        } catch (Exception ex) {
            Assert.assertTrue(true);
        }
    }
    @Test(priority = 4) /*13*/
    public void addUserInvalidPassword(){
        driver.get(baseUrl);
        addPage.getAddUser().click();
        String newUsername=addPage.generateRandomUsername();
        String newEmail=addPage.generateRandomEmail();
        String newFullName=addPage.generateRandomFullName();
        addPage.getAddUsername().sendKeys(newUsername);
        addPage.getAddEmail().sendKeys(newEmail);
        addPage.getAddFullName().sendKeys(newFullName);
        addPage.getAddPassword().sendKeys("abc");
        addPage.getAddTrait1Field().click();
        addPage.getAddTrait2Field().click();
        addPage.getAddTrait3Field().click();
        addPage.getAddTrait4Field().click();
        addPage.getAddMaleGenderButton().click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addPage.getSubmitButton().click();
        try{
            driver.switchTo().alert().dismiss();
            Assert.assertFalse(driver.findElement(By.xpath("//span[contains(.,'abc')]")).isDisplayed());
        }
        catch(Exception Ex){
            Assert.assertTrue(true);
        }
    }
    @AfterTest
    public void closeDriver() {
        driver.close();
    }
}
