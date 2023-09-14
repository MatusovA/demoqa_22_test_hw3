import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    void fillFormTest() {
        open("https://demoqa.com/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Aleksandr");
        $("#lastName").setValue("Matusov");
        $("#userEmail").setValue("autotest@mail.com");
        $(byText("Male")).click();
        $("#userNumber").setValue("8902394129");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-dropdown-container").$(byText("March")).click();
        $(".react-datepicker__year-select").selectOption("1993");
        $(".react-datepicker__day--007").click();
        $("#subjectsInput").setValue("English").pressEnter();
        $(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("Sashka.jpg");
        $("#currentAddress").setValue("Secret Address");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Noida")).click();
        $("#submit").click();

        $(".modal-dialog").shouldHave(text("Thanks for submitting the form"));
        $(".modal-content").shouldHave(
                text("Aleksandr Matusov"),
                text("autotest@mail.com"),
                text("Male"),
                text("8902394129"),
                text("07 March,1993"),
                text("English"),
                text("Sports"),
                text("Sashka.jpg"),
                text("Secret Address"),
                text("NCR Noida")
        );
    }
}