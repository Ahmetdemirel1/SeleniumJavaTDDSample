package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import util.Browser;


public class BaseTest {
    private static RemoteWebDriver driver;
    protected final Logger log = LogManager.getLogger(BaseTest.class);
    protected static Browser browser = new Browser();



    @Before
    public void setUp(){

        log.info("************* Test Başlıyor **************");
        browser.createDriver();
        getDriver().navigate().to("https://www.hepsiburada.com/");
    }

    @After
    public void tearDown(){
        getDriver().quit();
    }


    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(RemoteWebDriver driver) {
        BaseTest.driver = driver;
    }
}
