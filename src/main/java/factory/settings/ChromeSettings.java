package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChromeSettings implements ISettings {

  @Override
  public AbstractDriverOptions setting() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--start-maximized");
    Map<String, Object> selenoidOptions = new HashMap<>();
    selenoidOptions.put("name", "Test: " + Thread.currentThread().getStackTrace()[2].getMethodName());
    selenoidOptions.put("sessionTimeout", "15m");
    selenoidOptions.put("enableVNC", true);
    selenoidOptions.put("enableVideo", true);
    List<String> env = new ArrayList<>();
    env.add("TZ=UTC");
    selenoidOptions.put("env", env);
    Map<String, Object> labels = new HashMap<>();
    labels.put("manual", "true");
    selenoidOptions.put("labels", labels);
    chromeOptions.setCapability("selenoid:options", selenoidOptions);

    return chromeOptions;
  }
}
