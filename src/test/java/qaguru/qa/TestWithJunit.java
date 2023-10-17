package qaguru.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class TestWithJunit {


    @DisplayName("В поисковой выдаче google присутствует ссылка https://bigenc.ru/ для запроса 'bigenc'")
    @Test
    void successOpenRegistrationPage(){
        open("https://www.google.com/");
        $x("//div[contains(@class,'RNNXgb')]").click();
        $x("//textarea[contains(@name,'q')]").setValue("bigenc").pressEnter();
        $x("//*[@id='search']").shouldHave(text("https://bigenc.ru/"));
    }

    @DisplayName("В поисковой выдаче bigenc присутствует поле поиска для отправления запроса 'lord' с целью перехода на первый элемент контента в поисковой выдаче")
    @Test
    void successOpenSearchPageAndClickToFirstContent(){
        open("https://www.bigenc.ru/");
        $x("//span[contains(@class,'search')]").click();
        $x("//input[contains(@name,'search')]").setValue("rus").pressEnter();
        $x("//li[contains(@class,'md:tw-mx-4')]").click();
    }
}
