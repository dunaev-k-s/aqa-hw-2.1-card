package ru.netology.order;


import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.cssClass;
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
        form.$("[role=button]").click();
        form.$("span[data-test-id=name] [class=input__sub]")
                .shouldHave(exactText("Имя и Фамилия указаные неверно." +
                        " Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldSendRightData(){
        open("http://localhost:9999");
        SelenideElement form =$("div[class=App_appContainer__3jRx1]");
        form.$("[type=text]").setValue("Наина Иосифовна Ельцина");
        form.$("[type=tel]").setValue("+79999999999");
        form.$("[data-test-id] [class=checkbox__box]").click();
        form.$("[role=button]").click();
        $("[data-test-id=order-success]")
                .shouldHave(exactText("Ваша заявка успешно отправлена! " +
                        "Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldNotSendWrongNumber(){
        open("http://localhost:9999");
        SelenideElement form =$("div[class=App_appContainer__3jRx1]");
        form.$("[type=text]").setValue("Борис Николаевич Ельцин");
        form.$("[type=tel]").setValue("88888888888");
        form.$("[data-test-id] [class=checkbox__box]").click();
        form.$("[role=button]").click();
        $("[data-test-id=phone] [class=input__sub]")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldNotSendWithUncheckedBox(){
        open("http://localhost:9999");
        SelenideElement form =$("div[class=App_appContainer__3jRx1]");
        form.$("[type=text]").setValue("Борис Николаевич Ельцин");
        form.$("[type=tel]").setValue("+79999999999");
        form.$("[role=button]").click();
        $("[data-test-id=agreement]")
                .shouldHave(cssClass("input_invalid"));

    }


}
