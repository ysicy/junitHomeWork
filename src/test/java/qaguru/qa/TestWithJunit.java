package qaguru.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class TestWithJunit extends  TestBase {

@BeforeEach
void setUp(){

}


    @DisplayName("На странице TEST-DATA[0] в футере нажимается кнопка 'стать автором', на которой проверяется наличие заголовка TEST-DATA[1]")
    @Test
    @Tag("BLOCKER")
    void successOpenStaticPageBecomeAuthor(){
        open("https://www.bigenc.ru/");
        $x("//div[contains(@class,'bre-header-nav-item _button _flex-start -show-on-tablet')]")
                .click();
        $x("//h1[contains(@class,'bre-static-page-title')]")
                .shouldHave(visible);
    }


    @DisplayName("На главной странице TEST-DATA[2] в футере происходит переход на статическую страницу 'О проекте',на которой ищется ссылка на статью, происходит переход на статью и проверяется наличие кнопки TEST-DATA[3]")
    @Test
    @Tag("BLOCKER")
    void successOpenStaticPageAndClickToButtons(){
        open("https://bigenc.ru/p/about-project");
        $x("//a[contains(@href,'/c/sadovnichii-viktor-antonovich-edcd4f')]")
                .click();
        $x("//div[contains(@data-bre,'bre-quotes')]")
                .shouldHave(visible);
    }
}
