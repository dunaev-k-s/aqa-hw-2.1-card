package ru.netology.order;


import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class FormSendTest {
    @Test
    void shouldNotSendASCII(){
        open("http://localhost:9999");
        SelenideElement form =$("div[class=App_appContainer__3jRx1]");
        form.$("[type=text]").setValue("John James O'Grady");
        form.$("[type=tel]").setValue("+79999999999");
        form.$("[data-test-id] [class=checkbox__box]").click();
        form.$("[role=button]");
        form.$("span[data-test-id=name] [class=input__sub]")
                .shouldHave(exactText("Имя и Фамилия указаные неверно." +
                        " Допустимы только русские буквы, пробелы и дефисы."));

    }


}
