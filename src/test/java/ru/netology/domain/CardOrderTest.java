package ru.netology.domain;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardOrderTest {


    @Test
    public void shouldDisplayRequestSentSuccessfully() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79255553322");
        form.$("[data-test-id=agreement]").click();
        form.$("[type='button']").click();
        $("[data-test-id='order-success']").shouldHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void ifAnInvalidNameIsEntered() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='name'] input").setValue("Ivanov Ivan");
        form.$("[data-test-id='phone'] input").setValue("+79255553322");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void ifAnInvalidPhoneIsEntered() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("9255553322");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void ifCheckboxNotSelected() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='name'] input").setValue("Иванов Иван");
        form.$("[data-test-id='phone'] input").setValue("+79255553322");
        form.$("[type='button']").click();
        $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));
    }

    @Test
    void ifOnlyCheckboxIsSelected() {
        open("http://localhost:9999");
        SelenideElement form = $("#root");
        form.$("[data-test-id='name'] input").setValue("");
        form.$("[data-test-id='phone'] input").setValue("");
        form.$("[data-test-id='agreement']").click();
        form.$("[type='button']").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }


}
