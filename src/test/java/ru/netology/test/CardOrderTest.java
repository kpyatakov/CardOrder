package ru.netology.test;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @Test
    void submittingAForm() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Романов Дмитрий");
        $("[data-test-id=phone] input").setValue("+78910000000");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void nameNotFilledIn() {
        open("http://localhost:9999/");
        $("[data-test-id=phone] input").setValue("+78910000000");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void anIncompletePhoneNumber() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Романов Дмитрий");
//        $("[data-test-id=phone] input").setValue("+78910000000");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void theСheckBoxButtonIsNotPressed() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Романов Дмитрий");
        $("[data-test-id=phone] input").setValue("+78910000000");
//        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=agreement].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void wrongName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Dmitri Romanov");
        $("[data-test-id=phone] input").setValue("+78910000000");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
}