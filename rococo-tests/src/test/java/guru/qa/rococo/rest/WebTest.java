package guru.qa.rococo.rest;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import guru.qa.rococo.jupiter.annotation.ApiLogin;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

@Epic("[WEB]")
@DisplayName("[WEB]")
@Tag("WEB")
public class WebTest extends BaseWebTest {

    @DisplayName("WEB: 1")
//    @ApiLogin(
//            username = "dima7",
//            password = "12345"
//    )
    void testWeb(){
        System.out.println("##### start web test");
        Selenide.open(CFG.baseUrl());
        System.out.println("##### after open url");
        System.out.println("dima7");
        Selenide.sleep(2000);
        if (WebDriverRunner.hasWebDriverStarted()) {
            Allure.addAttachment("Dima", new ByteArrayInputStream(
                    ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES)
            ));
        }
        System.out.println("##### end web test");
    }
    @DisplayName("WEB: 2")
//    @ApiLogin(
//            username = "vova7",
//            password = "12345"
//    )
    void testWeb2(){
        System.out.println("vova7");
        Selenide.open(CFG.baseUrl());
        Selenide.sleep(2000);
        if (WebDriverRunner.hasWebDriverStarted()) {
            Allure.addAttachment("vova", new ByteArrayInputStream(
                    ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES)
            ));
        }
    }

    @DisplayName("WEB: 3")
//    @ApiLogin(
//            username = "valentin7",
//            password = "12345"
//    )
//    @Test
    void testWeb3(){
        System.out.println("valentin7");
        Selenide.open(CFG.baseUrl());
        Selenide.sleep(2000);
        if (WebDriverRunner.hasWebDriverStarted()) {
            Allure.addAttachment("valentin", new ByteArrayInputStream(
                    ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES)
            ));
        }

        System.out.println("end");
    }

//    @Test
    void testWeb4(){
        Selenide.open(CFG.baseUrl());
    }
}
