package page;

import base.BasePage;
import base.ClickOperation;
import base.WaitOperation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    WaitOperation waitOperation;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage login() {

        click(By.xpath("//*[@id='tsf']/div[2]/div[1]/div[3]/center/input[2]"));
        waitOperation.waitSecond(4);
        return new LoginPage(driver);
    }
}
