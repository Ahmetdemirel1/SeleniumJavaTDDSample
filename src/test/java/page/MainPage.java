package page;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.ConstantsMainPage.*;

public class MainPage extends BasePage {

    private String searchValue = "Bilgisayar";


    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage searchProduct() {

        click(SEARCH);
        sendKeys(SEARCH, searchValue);
        click(SEARCH_BUTTON);

        return new MainPage(driver);
    }
}
