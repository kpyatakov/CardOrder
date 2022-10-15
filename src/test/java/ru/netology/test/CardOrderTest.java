package ru.netology.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {
	
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void submittingAForm() {
        $("[data-test-id=name] input").setValue("Романов Дмитрий");
        $("[data-test-id=phone] input").setValue("+78910000000");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void nameNotFilledIn() {
        $("[data-test-id=phone] input").setValue("+78910000000");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void anIncompletePhoneNumber() {
        $("[data-test-id=name] input").setValue("Романов Дмитрий");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void theСheckBoxButtonIsNotPressed() {
        $("[data-test-id=name] input").setValue("Романов Дмитрий");
        $("[data-test-id=phone] input").setValue("+78910000000");
        $("button[type=button]").click();
        $("[data-test-id=agreement].input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void wrongName() {
        $("[data-test-id=name] input").setValue("Dmitri Romanov");
        $("[data-test-id=phone] input").setValue("+78910000000");
        $("[data-test-id=agreement]").click();
        $("button[type=button]").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
	
	   @Test
    void shouldIMobilePhoneField() {
        $x("//*[@name = \"name\"]").setValue("Иванов Андрей");
        $x("//*[@name = \"phone\"]").setValue("9214567845");
        $x("//*[@data-test-id = \"agreement\"]").click();
        $x("//*[@class =\"button__text\" ]").click();
        $x("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }
}