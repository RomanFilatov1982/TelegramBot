package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Feature("Practice Form")
public class RegistrationTests extends TestBase {


    @Test
    @Tag("web")
    @Story("Успешное заполнение формы регистрации")
    @Owner("filatovri")
    @Link(value = "Practice Form", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("Student Registration Form")
    void successfulRegistrationTest() {
        step("Открываем главную страницу", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
        });
        step("В поле \"Firstname\" ввести \"Roman\"", () -> {
            $("#firstName").setValue("Roman");
        });
        step("В поле \"LastName\" ввести \"Filatov\"", () -> {
            $("#lastName").setValue("Filatov");
        });
        step("В поле \"Email\" ввести \"romanf@gmail.com\"", () -> {
            $("#userEmail").setValue("romanf@gmail.com");
        });
        step("Выбрать пол \"Male\"", () -> {
            $("#genterWrapper").$(byText("Male")).click();
        });

        step("В поле \"Mobile Number\" ввести номер телефона \"9085693730\"", () -> {
            $("#userNumber").setValue("9085693730");
        });
        step("В поле \"Date of Birth\" ввести дату рождения \"18 January 1982\"", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("January");
            $("select.react-datepicker__year-select").selectOption("1982");
            $(".react-datepicker__day--018:not(.react-datepicker__day--outside-month)").click();
        });
        step("В поле \"Subjects\" ввести номер телефона \"Physics\"", () -> {
            $("#subjectsInput").setValue("Physics").pressEnter().scrollTo();
        });
        step("Выбрать хобби \"Sports\"", () -> {
            $("#hobbiesWrapper").$(byText("Sports")).click();
        });
        step("Загрузить файл \"main-2.jpg\"", () -> {
            $("#uploadPicture").uploadFromClasspath("img/img.png");
        });
        step("В поле \"Current Address\" ввести адрес \"Proxladnaya street 28\"", () -> {
            $("#currentAddress").setValue("Proxladnaya street 28");
        });
        step("Выбрать из выпадающего списка \"State\" \"Haryana\"", () -> {
            $("#state").click();
            $("#stateCity-wrapper").$(byText("Haryana")).click();
        });
        step("Выбрать из выпадающего списка \"City\" \"Karnal\"", () -> {
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Karnal")).click();
        });
        step("Кликнуть на кнопку \"Submit\"", () -> {
            $("#submit").click();
        });

        step("Проверка результатов формы", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text("Roman Filatov"), text("romanf@gmail.com"),
                    text("Male"), text("9085693730"), text("18 January,1982"), text("Physics"), text("Sports"),
                    text("img.png"), text("Proxladnaya street 28"), text("Haryana Karnal"));
        });
    }
}