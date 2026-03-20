import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ScreenShooterExtension.class)
public class BaseTest {

  @BeforeEach
  void setUp() {
    Configuration.reportsFolder = "target/allure-results";
    SelenideLogger.addListener("allure", new AllureSelenide()
            .screenshots(true)
            .savePageSource(false));
  }
}
