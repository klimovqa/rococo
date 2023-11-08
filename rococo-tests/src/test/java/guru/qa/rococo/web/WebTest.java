package guru.qa.rococo.web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import guru.qa.rococo.api.model.ArtistJson;
import guru.qa.rococo.db.dao.ArtistDAO;
import guru.qa.rococo.db.dao.impl.ArtistDAOHibernate;
import guru.qa.rococo.db.entity.artist.ArtistEntity;
import guru.qa.rococo.jupiter.annotation.ApiLogin;
import guru.qa.rococo.util.CsvUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.util.List;

@Epic("[WEB]")
@DisplayName("[WEB]")
@Tag("WEB")
public class WebTest extends BaseWebTest {

    @DisplayName("WEB: 1")
    @ApiLogin(
            username = "dima7",
            password = "12345"
    )
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
    @Test
    void testWeb2(){
        System.out.println();
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
