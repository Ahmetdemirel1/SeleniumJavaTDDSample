package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;



public class ClickOperation {
    private static final Logger log = LogManager.getLogger(ClickOperation.class);

    protected WebDriver driver;
    private ScrollOperation scrollOperation;
    private WaitOperation waitOperation;

    public ClickOperation() {
        this.driver = BaseTest.getDriver();
        waitOperation = new WaitOperation();
        scrollOperation = new ScrollOperation();
    }

    public void click(By by){
        try {
            waitOperation.waitPresence(by);
            waitOperation.waitVisible(by);
            scrollOperation.scrollToElement(by);
            WebElement element = waitOperation.waitClickable(by);
            element.click();
        }
        catch (StaleElementReferenceException staleElementReferenceException ){
            String errorMessage = String.format("'%s elementine tıklarken sorun oluştu", by);
            log.error(errorMessage);
            click(by);
        }
    }

    public void clickWithJS(By by){
        try {
            waitOperation.waitPresence(by);
            waitOperation.waitVisible(by);
            scrollOperation.scrollToElement(by);
            WebElement element = waitOperation.waitClickable(by);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("argument[0].click();", element);
        }
        catch (StaleElementReferenceException staleElementReferenceException ){
            String errorMessage = String.format("'%s elementine tıklarken sorun oluştu", by);
            log.error(errorMessage);
            clickWithJS(by);
        }
    }

    public void hoverElement(By by){
        Actions actions = new Actions(driver);
        WebElement element = waitOperation.waitPresence(by);
        actions.moveToElement(element).build().perform();
    }
}
