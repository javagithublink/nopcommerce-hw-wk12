package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        useMouseHoverAction(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
        useMouseHoverAndClickAction(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));
        List<WebElement> before = driver.findElements(By.xpath("//div[@class='products-container']"));
        useSelectByValueFromDropDown(By.xpath("//select[@id='products-orderby']"), "6");
        List<WebElement> after = driver.findElements(By.xpath("//div[@class='products-container']"));

        Assert.assertEquals(before, after);



    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        useMouseHoverAction(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']"));
        useMouseHoverAndClickAction(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));
        useSelectByValueFromDropDown(By.xpath("//select[@id='products-orderby']"), "5");
        Thread.sleep(2000);
        useMouseHoverAndClickAction(By.xpath("//div[@class='picture']//img[@title='Show details for Build your own computer']"));

        useVerifyResult(By.xpath("//h1[normalize-space()='Build your own computer']"), "Build your own computer", "User can't select product correctly");


        Thread.sleep(1000);
        useSelectByValueFromDropDown(By.xpath("//select[@id='product_attribute_1']"), "1");
        Thread.sleep(1000);
        useSelectByValueFromDropDown(By.xpath("//select[@id='product_attribute_2']"), "5");
        useClickOnElement(By.xpath("//label[text()='400 GB [+$100.00]']"));
        useClickOnElement(By.xpath("//label[text()='Vista Premium [+$60.00]']"));
        useClickOnElement(By.xpath("//label[text()='Total Commander [+$5.00]']"));

        Thread.sleep(1000);
        useClickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));

        useVerifyResult(By.xpath("//p[text()='The product has been added to your ']"), "The product has been added to your shopping cart", "Product has been not added");


        Thread.sleep(1000);
        useClickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@value='1']")).clear();

        useSendTextToElement(By.xpath("//input[@value='1']"), "2");
        useClickOnElement(By.xpath("//button[normalize-space()='Update shopping cart']"));

        useGetTextFromElement(By.xpath("//button[@value='checkout']")); // verify

        useClickOnElement(By.xpath("//div[@class='terms-of-service']//input[@type='checkbox']"));
        useClickOnElement(By.xpath("//button[@value='checkout']"));

        useVerifyResult(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"), "Welcome, Please Sign In!", "Message do not match");

        useClickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));

        useSendTextToElement(By.xpath("//input[@data-val-required='First name is required.']"), "JJJ");
        useSendTextToElement(By.xpath("//input[@data-val-required='Last name is required.']"), "PPP");
        useSendTextToElement(By.xpath("//input[@data-val-email='Wrong email']"), "abc123@gmail.com");
        useSelectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "Iceland");
        useSendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "abcd");
        useSendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "1 north ave");
        useSendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "12345");
        useSendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "1234567891");
        useClickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        useClickOnElement(By.xpath("//input[@id='shippingoption_1']"));
        useClickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        useClickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        useClickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));
        useSelectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Master card");
        useSendTextToElement(By.xpath("//input[@id='CardholderName']"), "Abc Def");
        useSendTextToElement(By.xpath("//input[@id='CardNumber']"), "5512345678912345");
        useSelectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2023");
        useSendTextToElement(By.xpath("//input[@id='CardCode']"), "123");
        useClickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        useVerifyResult(By.xpath("//span[normalize-space()='Credit Card']"), "Credit Card", "Payment method doesn't match");


        useVerifyResult(By.xpath("//span[normalize-space()='Next Day Air']"), "Next Day Air", "Shipping mode doesn't match");


        useVerifyResult(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$2,950.00')]"), "$2,950.00", "Amount Error");


        useClickOnElement(By.xpath("//button[normalize-space()='Confirm']"));

        useVerifyResult(By.xpath("//h1[normalize-space()='Thank you']"), "Thank you", "Message doesn't match");


        useVerifyResult(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']"), "Your order has been successfully processed!", "Order processing error");


        useClickOnElement(By.xpath("//button[normalize-space()='Continue']"));

        useVerifyResult(By.xpath("//h2[normalize-space()='Welcome to our store']"), "Welcome to our store", "User not able to come back on Homepage");


    }



    @After
    public void tearDown() {
        closeBrowser();
    }

}
