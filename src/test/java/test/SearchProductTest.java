package test;

import base.BaseTest;
import org.junit.Before;
import org.junit.Test;
import page.MainPage;

public class SearchProductTest extends BaseTest {
    MainPage mainPage;

    @Before
    public void before() {
        mainPage = new MainPage(getDriver());
    }


    @Test
    public void urunArama(){
        mainPage.searchProduct();
    }
}
