package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScrollOperation {
    private WebDriver driver;
    private WaitOperation waitOperation;

    public ScrollOperation() {
        driver = BaseTest.getDriver();
        this.driver = driver;
        waitOperation = new WaitOperation();
    }

    public void scrollToElement(By by){

        WebElement element = waitOperation.waitPresence(by);
        //((JavascriptExecutor) driver).executeScript("argument[0].scrollIntoViewIfNeeded();", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
