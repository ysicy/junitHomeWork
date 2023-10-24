package qaguru.qa;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;


public class TestWithJunit extends  TestBase {


    @ValueSource(strings = {"Пушкин", "Пушкин (город)"})
    @ParameterizedTest(name = "Переход на странциу результатов поиска,с целью проверки текста в контенте")
    @Tags({@Tag("Web"), @Tag("Search"), @Tag("UI")})
    void successOpenSearchPageTest(String searchQuery) {

        $x("//div[contains(@class,'bre-header-nav-item _flex-start')][3]").click();
        $x("//input[contains(@type,'text')]").setValue(searchQuery).pressEnter();
        $x("//a[contains(@class,'tw-grow')]").shouldHave(text(searchQuery));
    }

    static Stream<Arguments> searchQueryProvider(){
        return Stream.of(
                Arguments.of("Области знаний"),
                Arguments.of("Категории"),
                Arguments.of("Теги"),
                Arguments.of("Авторы"));
    }

    @MethodSource("searchQueryProvider")
    @ParameterizedTest(name = "Переход на странциу результатов поиска,с целью проверки текста в фильтрах")
    @Tags({@Tag("Web"), @Tag("Search"), @Tag("UI")})
    void checkingTextOnButtonsTest(String searchQuery) {


            $x("//div[contains(@class,'bre-header-nav-item _flex-start')][3]").click();
            $x("//input[contains(@type,'text')]").setValue(searchQuery).pressEnter();
            $x("//ul[contains(@class,'search-extended__filters')]").shouldHave(text(searchQuery));
    }

    @ParameterizedTest(name = "Переход на странциу контента,с целью проверки кнопок под заголовком")
    @Tags({@Tag("UI"),@Tag("Buttons"),@Tag("Search")})
    @CsvSource({"bre-quotes" , "bre-likes", "bre-share"})
    void openStaticPageAndClickToButtonsTest(String buttonsQuery){

        String type = String.format("//div[@data-bre = '%s']/span", buttonsQuery);
        SelenideElement types = $x(type);
        $x("//a[contains(@href,'/p/about-project')]").click();
        $x("//a[contains(@href,'/c/sadovnichii-viktor-antonovich-edcd4f')][1]").click();
        $x("//div[contains(@class,'bre-article-page__content bre-article-content')]").shouldBe(visible);
        String attr = types.getCssValue("margin-top");
        Assertions.assertEquals("0px", attr);
    }
}



