import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

@Getter
public class LoginPage {
    private String baseUrl = "http://localhost:4200";
    private String driverPath = "/home/andibalaj/Downloads/geckodriver-v0.29.1-linux64/geckodriver";
    @FindBy(xpath = "//input[contains(@type,'text')]")
    private WebElement usernameElement;
    @FindBy(xpath = "//input[contains(@type,'password')]")
    private WebElement passwordElement;
    @FindBy(xpath = "//button[@class='btn btn-outline-primary'][contains(.,'Login')]")
    private WebElement loginButtonElement;
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public String generateRandomUsername() {
        Random r = new Random();
        int c = r.nextInt(9999);
        return "User" + c;
    }
    public String generateRandomPassword() {
        Random r = new Random();
        int c = r.nextInt(9999);
        return "Password" + c;
    }
}
