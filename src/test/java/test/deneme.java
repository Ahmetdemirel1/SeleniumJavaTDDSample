package test;

import base.BaseTest;
import org.junit.Before;
import org.junit.Test;
import page.LoginPage;

public class deneme extends BaseTest {


    LoginPage loginPage;

    @Before
    public void before(){
        loginPage = new LoginPage(getDriver());
    }

    @Test
    public void deneme(){

    loginPage.login();
    }
}
