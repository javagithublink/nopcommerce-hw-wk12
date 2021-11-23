package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseClass {

    public static WebDriver driver;

    public void openBrowser(String baseUrl){

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(baseUrl);  // objectname.get() method is used to open URL in browser.
        driver.manage().window().maximize(); // to open browser in full screen. to maximize
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void closeBrowser() {
        driver.quit();
    }
}

