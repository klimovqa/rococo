package guru.qa.rococo.page;

import com.codeborne.selenide.CollectionCondition;

import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class MuseumPage extends BasePage{
    public MuseumPage checkingThatListExist() {
        step("Checking that the list exists", () ->
                $$(".abstract-table tbody tr").
                        shouldBe(CollectionCondition.sizeGreaterThan(0)));
        return this;
    }
}
