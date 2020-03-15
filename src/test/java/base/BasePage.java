package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class BasePage {
    private static final Logger log = LogManager.getLogger(BasePage.class);

    protected WebDriver driver;
    private ScrollOperation scrollOperation;
    private WaitOperation waitOperation;

    public BasePage(WebDriver driver) {
        this.driver = driver;
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

    public void sendKeys(By by, String value){
        try {
            waitOperation.waitPresence(by);
            waitOperation.waitVisible(by);
            scrollOperation.scrollToElement(by);
            WebElement element = waitOperation.waitClickable(by);
            element.sendKeys(value);
        }
        catch (StaleElementReferenceException staleElementReferenceException ){
            String errorMessage = String.format("'%s elementine '%s' değerini yazma işlemi sırasında sorun oluştu", by, value);
            log.error(errorMessage);

        }
    }

    public void sendKeyWithJs(By by, String value){
        try {
            waitOperation.waitPresence(by);
            waitOperation.waitVisible(by);
            scrollOperation.scrollToElement(by);
            WebElement element = waitOperation.waitClickable(by);
            String injectString = String.format("argument[0].value='%s';", value);
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript(injectString, element);

        }
        catch (StaleElementReferenceException staleElementReferenceException ){
            String errorMessage = String.format("'%s' elementine '%s' değerini yazma işlemi sırasında sorun oluştu", by, value);
            log.error(errorMessage);

        }
    }

    public void clear(By by){
        try {
            waitOperation.waitPresence(by);
            waitOperation.waitVisible(by);
            scrollOperation.scrollToElement(by);
            WebElement element = waitOperation.waitClickable(by);
            element.clear();

        }
        catch (StaleElementReferenceException staleElementReferenceException ){
            String errorMessage = String.format("'%s elementi temizleme işlemi sırasında sorun oluştu", by);
            log.error(errorMessage);
        }
        catch (Exception exception){
            String exceptionMessage = String.format("'%s elementinde text değeri temizlenirken hata oluştu. Hata Kodu: %s", by, exception.getMessage());
            log.error(exceptionMessage);
            Assert.fail(exceptionMessage);
        }
    }
}
