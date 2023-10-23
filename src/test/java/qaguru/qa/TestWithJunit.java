package qaguru.qa;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;


public class TestWithJunit extends  TestBase {

    @CsvSource (value = (
        "Пушкин, Пушкин (город)"
        ))
    @ValueSource(strings = {"Пушкин", "Пушкин (город)"})
    @ParameterizedTest(name = "На странице bigenc.ru в футере нажимается кнопка 'поиск', отправляется неполный запрос и на странице результатов поиска проверяется значение")
    @Tags({@Tag("Web"), @Tag("Search"), @Tag("UI")})
    void successOpenSearchPageTest(String searchQuery) {

        $x("//div[contains(@class,'bre-header-nav-item _flex-start')][3]")
                .click();
        $x("//input[contains(@type,'text')]").setValue(searchQuery).pressEnter();
        System.out.println(searchQuery);
        $x("//a[contains(@class,'tw-grow')]").shouldHave(text(searchQuery));
    }

    static Stream<Arguments> buttonsTest(){
        return Stream.of(
                Arguments.of(List.of("bre_quotes","bre_likes","bre-share", "bre-views"))
        );
}
    @MethodSource("buttonsTest")
    @ParameterizedTest(name = "На главной странице bigenc.ru в футере происходит переход на статическую страницу 'О проекте',на которой ищется ссылка на статью, происходит переход на статью и в меню происходят действия")
    @Tags({@Tag("UI"),@Tag("Buttons"),@Tag("Search")})
    @CsvFileSource(emptyValue = "buttons.csv")
    @Test
    void OpenStaticPageAndClickToButtonsTest(){

        $x("//a[contains(@href,'/p/about-project')]").click();
        $x("//a[contains(@href,'/c/sadovnichii-viktor-antonovich-edcd4f')][1]").click();
        $x("//div[contains(@class,'bre-article-page__content bre-article-content')]").click();
    }
}



