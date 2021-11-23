package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class TopMenuTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";


    public void selectMenu(String menu) throws InterruptedException {

        // clickOnElement(menu);

        List<WebElement> names = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']//li"));

        for (WebElement name : names) {
            //Thread.sleep(2000);
            if (name.getText().equalsIgnoreCase(menu)) {
                Thread.sleep(2000);
                name.click();
                break;
            }
        }

    }


    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserCanNavigateToComputerMenu() throws InterruptedException {

        selectMenu("Computers");
        String expectedResult = "Computers";
        String actualResult = useGetTextFromElement(By.xpath("//h1[normalize-space()='Computers']"));

        Assert.assertEquals("Result doesn't match", expectedResult, actualResult);

    }

    @Test
    public void verifyUserCanNavigateToElectronicsMenu() throws InterruptedException {


        selectMenu("Electronics");
        String expectedResult = "Electronics";
        String actualResult = useGetTextFromElement(By.xpath("//h1[normalize-space()='Electronics']"));

        Assert.assertEquals("Result doesn't match", expectedResult, actualResult);

    }

    @Test
    public void verifyUserCanNavigateToApparelMenu() throws InterruptedException {

        selectMenu("Apparel");
        String expectedResult = "Apparel";
        String actualResult = useGetTextFromElement(By.xpath("//h1[normalize-space()='Apparel']"));

        Assert.assertEquals("Result doesn't match", expectedResult, actualResult);

    }

    @Test
    public void verifyUserCanNavigateToBooksMenu() throws InterruptedException {

        selectMenu("Books");
        String expectedResult = "Books";
        String actualResult = useGetTextFromElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Books']"));

        Assert.assertEquals("Result doesn't match", expectedResult, actualResult);

    }

    @Test
    public void verifyUserCanNavigateToJewelryMenu() throws InterruptedException {

        selectMenu("Jewelry");
        String expectedResult = "Jewelry";
        String actualResult = useGetTextFromElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Jewelry']"));

        Assert.assertEquals("Result doesn't match", expectedResult, actualResult);

    }

    @Test
    public void verifyUserCanNavigateToGiftCardsMenu() throws InterruptedException {

        selectMenu("Gift Cards");
        String expectedResult = "Gift Cards";
        String actualResult = useGetTextFromElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Gift Cards']"));

        Assert.assertEquals("Result doesn't match", expectedResult, actualResult);

    }

    @Test
    public void verifyUserCanNavigateToDigitalDownloadsMenu() throws InterruptedException {

        selectMenu("Digital downloads");
        String expectedResult = "Digital downloads";
        String actualResult = useGetTextFromElement(By.xpath("//h1[normalize-space()='Digital downloads']"));

        Assert.assertEquals("Result doesn't match", expectedResult, actualResult);

    }


    @After
    public void tearDown() {
        closeBrowser();
    }

}
