package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitOperation {

    private WebDriver driver;

    private int defaultTimeOutInSecond = 30;
    private int defaultIntervalMilliSecond = 1000;

    private static final Logger log = LogManager.getLogger(WaitOperation.class);


    public WaitOperation() {
        driver = BaseTest.getDriver();
        this.driver = BaseTest.getDriver();
    }

    public WebElement waitPresence(By by){
        return waitPresence(by, defaultTimeOutInSecond);
    }

    public WebElement waitPresence(By by , int sec){
        WebElement webElement = null;
        try{
            waitUntilReadyForDocumentObjectModel();
            WebDriverWait webDriverWait = new WebDriverWait(driver,sec, defaultIntervalMilliSecond);
            webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        catch (Exception e){
            String errorMessage = String.format("'%s elementini sayfa üzerinde var olması beklenirken hata oluştu! Hata kodu: %s", by,e.getMessage());
            log.error(errorMessage);
            Assert.fail(errorMessage);
        }
        return  webElement;
    }

    public WebElement waitVisible(By by){
        return waitVisible(by, defaultTimeOutInSecond);
    }

    public WebElement waitVisible(By by, int sec){
        WebElement webElement = null;
        try{
            waitUntilReadyForDocumentObjectModel();
            WebDriverWait webDriverWait = new WebDriverWait(driver,sec, defaultIntervalMilliSecond);
            webElement = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        catch (Exception e){
            String errorMessage = String.format("'%s elementini sayfa üzerinde görünür olması beklenirken hata oluştu! Hata kodu: %s", by,e.getMessage());
            log.error(errorMessage);
            Assert.fail(errorMessage);
        }
        return  webElement;
    }

    public boolean waitInvisible(By by){
        return waitInvisible(by, defaultTimeOutInSecond);
    }

    public boolean waitInvisible(By by , int sec){
        boolean isInvisible = false;
        try{
            waitUntilReadyForDocumentObjectModel();

            isInvisible = new WebDriverWait(driver,sec, defaultIntervalMilliSecond)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.invisibilityOfElementLocated(by));
        }
        catch (Exception e){
            String errorMessage = String.format("'%s elementini sayfa üzerinde kaybolması beklenirken hata oluştu! Hata kodu: %s", by,e.getMessage());
            log.error(errorMessage);
            Assert.fail(errorMessage);
        }
        return isInvisible;
    }

    public WebElement waitClickable(By by){
        return waitClickable(by, defaultTimeOutInSecond);
    }

    public WebElement waitClickable(By by, int sec){
        WebElement webElement = null;
        try{
            waitUntilReadyForDocumentObjectModel();
            WebDriverWait webDriverWait = new WebDriverWait(driver,sec, defaultIntervalMilliSecond);
            webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        }
        catch (Exception e){
            String errorMessage = String.format("'%s elementini tıklanabilir olması beklenirken hata oluştu! Hata kodu: %s", by,e.getMessage());
            log.error(errorMessage);
            Assert.fail(errorMessage);
        }
        return  webElement;
    }

    public boolean isPresence(By by, int sec){
        WebElement webElement = null;
        try{
            waitUntilReadyForDocumentObjectModel();
            WebDriverWait webDriverWait = new WebDriverWait(driver,sec, defaultIntervalMilliSecond);
            webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        }
        catch (Exception e){
            String errorMessage = String.format("'%s elementi '%s' saniye boyunca DOM'a yüklenmesi beklendi fakat yüklenemdi", by,sec);
            log.error(errorMessage);
            Assert.fail(errorMessage);
        }
        return  webElement != null;
    }

    public void waitUntilReadyForDocumentObjectModel(){
        new WebDriverWait(driver, defaultTimeOutInSecond).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public static void waitSecond(int sec){
        try {
            TimeUnit.SECONDS.sleep(sec);
            String logMessage = String.format("'%s' saniye Statik bekleme yapıldı", sec);
            log.info(logMessage);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }



}
