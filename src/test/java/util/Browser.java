package util;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class Browser {
    protected final Logger logger = LogManager.getLogger(Browser.class);
    private ChromeOptions options;
    private DesiredCapabilities capabilities;


    private void chromeOptions(){
        options = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();

        options.addArguments("test-type");
        options.addArguments("disable-popup-blocking");
        options.addArguments("ignore-certificate-errors");
        options.addArguments("disable-translate");
        options.addArguments("start-maximized");

        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setBrowserName(options.getBrowserName().toUpperCase());
        capabilities.setVersion(System.getProperty("os.version"));
        capabilities.setPlatform(Platform.getCurrent());
        capabilities.setCapability("acceptSslCerts", "true");
    }


    private void commonMethod(){
        BaseTest.getDriver().manage().timeouts().implicitlyWait(16, TimeUnit.SECONDS);
        BaseTest.getDriver().manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);

        if (capabilities.getPlatform().toString().equals("MAC")){
            BaseTest.getDriver().manage().window().setSize(new Dimension(1280, 900));
        }else {
            BaseTest.getDriver().manage().window().maximize();
        }

        logger.info("Installation Complete");
        logger.info("********* BROWSER:" + capabilities.getBrowserName() + ", " + "VERSION:" + capabilities.getVersion()
                + ", " + "PLATFORM:" + capabilities.getPlatform());
    }

    public void createDriver(){
        chromeOptions();
        selectPath(capabilities.getPlatform());
        BaseTest.setDriver(new ChromeDriver());
        commonMethod();
    }

    public void selectPath(Platform platform){
        String browser;
        if ("CHROME".equalsIgnoreCase(capabilities.getBrowserName())) {
            browser = "webdriver.chrome.driver";
            switch (platform) {
                case MAC:
                    System.setProperty(browser, "properties/driver/chromedrivermac");
                    break;
                case WIN10:
                case WIN8:
                case WIN8_1:
                case WINDOWS:
                case VISTA:
                    System.setProperty(browser, "properties/driver/chromedrivermac.exe");
                    break;
                case LINUX:
                    System.setProperty(browser, "properties/driver/chromedriverlinux64.exe");
                    break;
                default:
                    logger.info("PLATFORM DOES NOT EXISTS");
                    break;
            }
        }
    }
}
