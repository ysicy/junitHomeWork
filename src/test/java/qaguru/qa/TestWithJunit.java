package qaguru.qa;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class TestWithJunit extends  TestBase {
    @BeforeEach
    void setUp(){
        open("https://www.bigenc.ru/");
    }

    @ValueSource(strings = {"Пушкин","Пушкин (город)"})
    @ParameterizedTest(name = "На странице bigenc.ru в футере нажимается кнопка 'поиск', отправляется неполный запрос и на странице результатов поиска проверяется значение")
    @Tags({@Tag("Web"),@Tag("Search"),@Tag("UI")})
    void successOpenSearchPage(String searchQuery){

        $x("//div[contains(@class,'bre-header-nav-item _flex-start')][3]")
                .click();
        $x("//input[contains(@type,'text')]").setValue(searchQuery).pressEnter();
        System.out.println(searchQuery);
        $x("//a[contains(@class,'tw-grow')]").shouldHave(text(searchQuery));
    }


    @DisplayName("На главной странице bigenc.ru в футере происходит переход на статическую страницу 'О проекте',на которой ищется ссылка на статью, происходит переход на статью и ставится лайк")
    @Tags({@Tag("UI"),@Tag("Search")})
    @CsvSource(value = {
            "https://bigenc.ru/, bre-likes"
    })
    @Test
    void successOpenStaticPageAndClickToButtons(){

        $x("//li[contains(@class,'_button bre-inline-menu__item')][1]").click();
        $x("//a[contains(@href,'/c/sadovnichii-viktor-antonovich-edcd4f')][1]").click();
        $x("//div[contains(@data-bre,'bre-likes')]").click();
    }
}



