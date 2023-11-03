package guru.qa.rococo.web;

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

@Epic("[WEB][rococo-frontend]: Художники")
@DisplayName("[WEB][rococo-frontend]: Художники")
@Tag("WEB")
public class SameTest extends BaseWebTest{

    @DisplayName("WEB: Пользователь должен видеть список Художников")
    @ApiLogin(
            username = "dima7",
            password = "12345"
    )
    void testWeb(){
        Selenide.open(CFG.baseUrl());
        Selenide.sleep(2000);
        if (WebDriverRunner.hasWebDriverStarted()) {
            Allure.addAttachment("Dima", new ByteArrayInputStream(
                    ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES)
            ));
        }
    }
    @DisplayName("WEB: Пользователь должен видеть список Художников 2")
    @ApiLogin(
            username = "vova7",
            password = "12345"
    )
    void testWeb2(){
        Selenide.open(CFG.baseUrl());
        Selenide.sleep(2000);
        if (WebDriverRunner.hasWebDriverStarted()) {
            Allure.addAttachment("vova", new ByteArrayInputStream(
                    ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES)
            ));
        }
    }

    @DisplayName("WEB: Пользователь должен видеть список Художников 3")
    @ApiLogin(
            username = "valentin7",
            password = "12345"
    )
    void testWeb3(){
        Selenide.open(CFG.baseUrl());
        Selenide.sleep(2000);
        if (WebDriverRunner.hasWebDriverStarted()) {
            Allure.addAttachment("valentin", new ByteArrayInputStream(
                    ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES)
            ));
        }
    }
}
