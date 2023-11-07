package guru.qa.rococo.rest;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("[WEB]")
@Tag("WEB")
public class WebTest2 {

    protected static final guru.qa.rococo.config.Config CFG = guru.qa.rococo.config.Config.getInstance();

    @Test
    @DisplayName("[WEB] 888")
    void testWeb4(){
        System.out.println("##### Start web4 test");
        Selenide.open(CFG.baseUrl());
        System.out.println("##### Stop web4 test");
    }
}
