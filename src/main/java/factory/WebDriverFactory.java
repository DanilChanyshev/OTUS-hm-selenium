package factory;

import factory.settings.ChromeSettings;
import factory.settings.ISettings;
import listeners.MouseListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class WebDriverFactory {

  private final String browser = System.getProperty("browser.name").toLowerCase(Locale.ROOT);
  private final String gridUrl = System.getProperty("selenide.ui.server").toLowerCase(Locale.ROOT);

  public WebDriver create() {
    ISettings settings = switch (browser) {
      case "chrome" -> new ChromeSettings();
      default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
    };

    ChromeOptions options = (ChromeOptions) settings.setting();
    if (gridUrl != null && !gridUrl.isEmpty()) {
      try {
        return new RemoteWebDriver(new URL(gridUrl), options);
      } catch (MalformedURLException e) {
        throw new RuntimeException("Invalid grid URL: " + gridUrl, e);
      }
    } else {
      ChromeDriver originDriver = new ChromeDriver((ChromeOptions) new ChromeSettings().setting());
      MouseListeners listeners = new MouseListeners(originDriver);
      return new EventFiringDecorator<>(listeners).decorate(originDriver);
    }
  }
}
