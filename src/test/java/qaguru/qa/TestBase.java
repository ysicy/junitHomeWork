package qaguru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.open;


public class TestBase {

    @BeforeAll
    static void beforeAll() {
        open("https://www.bigenc.ru/");
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
    }
}
