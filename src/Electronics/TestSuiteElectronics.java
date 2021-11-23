package Electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TestSuiteElectronics extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductAddedToCellPhonesPageSuccessFully() {
        useMouseHoverAction(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));
        useMouseHoverAndClickAction(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        useVerifyResult(By.xpath("//h1[normalize-space()='Cell phones']"), "Cell phones", "Incorrect page");
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        //From homepage to Electronics/cell phone section
        useMouseHoverAction(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));
        useMouseHoverAndClickAction(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        useVerifyResult(By.xpath("//h1[normalize-space()='Cell phones']"), "Cell phones", "Incorrect page");

        //Cell phone section - selecting phone and adding it to the shopping cart
        useClickOnElement(By.xpath("//a[normalize-space()='List']"));
        Thread.sleep(2000);
        useClickOnElement(By.xpath("//a[normalize-space()='Nokia Lumia 1020']"));
        Thread.sleep(1000);
        useVerifyResult(By.xpath("//h1[normalize-space()='Nokia Lumia 1020']"), "Nokia Lumia 1020", "Wrong product");
        Thread.sleep(1000);
        useVerifyResult(By.xpath("//span[@id='price-value-20']"), "$349.00", "Wrong price");
        useClickOnElement(By.xpath("//input[@id='product_enteredQuantity_20']"));
        driver.findElement(By.xpath("//input[@id='product_enteredQuantity_20']")).clear();
        useSendTextToElement(By.xpath("//input[@id='product_enteredQuantity_20']"),"2");
        useClickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));
        useVerifyResult(By.xpath("//p[text()='The product has been added to your ']"), "The product has been added to your shopping cart", "Product has been not added");
        Thread.sleep(1000);

        //user is on shopping cart
        useClickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        Thread.sleep(2000);
        useVerifyResult(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$698.00')]"),"$698.00","Wrong total amount");
        Thread.sleep(1000);
        useClickOnElement(By.xpath("//div[@class='terms-of-service']//input[@type='checkbox']"));
        useClickOnElement(By.xpath("//button[@value='checkout']"));
        useVerifyResult(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"), "Welcome, Please Sign In!", "Message do not match");

        //On Register page
        useClickOnElement(By.xpath("//button[normalize-space()='Register']"));
        useVerifyResult(By.xpath("//h1[normalize-space()='Register']"),"Register","User is not on Register Page");

        //Completing registration details
        useClickOnElement(By.xpath("//input[@id='gender-male']"));
        useSendTextToElement(By.xpath("//input[@id='FirstName']"),"abcd");
        useSendTextToElement(By.xpath("//input[@id='LastName']"),"abcd");
        useSendTextToElement(By.xpath("//select[@name='DateOfBirthDay']"),"12");
        useSendTextToElement(By.xpath("//select[@name='DateOfBirthMonth']"),"August");
        useSendTextToElement(By.xpath("//select[@name='DateOfBirthYear']"),"1972");
        useSendTextToElement(By.xpath("//input[@id='Email']"),useGetUniqueEmailAddress());
        useSendTextToElement(By.xpath("//input[@id='Password']"),"123456");
        useSendTextToElement(By.xpath("//input[@id='ConfirmPassword']"),"123456");
        useClickOnElement(By.xpath("//button[@id='register-button']"));

        useClickOnElement(By.xpath("//a[normalize-space()='Continue']"));

        //Back on Shopping cart
        useVerifyResult(By.xpath("//h1[normalize-space()='Shopping cart']"),"Shopping cart","User is not on shopping cart");
        useClickOnElement(By.xpath("//div[@class='terms-of-service']//input[@type='checkbox']"));
        useClickOnElement(By.xpath("//button[@id='checkout']"));

        //Billing details
        useSelectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "Iceland");
        useSendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "abcd");
        useSendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "1 north ave");
        useSendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "12345");
        useSendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "1234567891");
        useClickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        // Shipping method
        useClickOnElement(By.xpath("//label[normalize-space()='2nd Day Air ($0.00)']"));
        useClickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        //Payment method
        useClickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        useClickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));

        //Credit card details
        useSendTextToElement(By.xpath("//input[@id='CardholderName']"),"JJJ PPP");
        useSendTextToElement(By.xpath("//input[@id='CardNumber']"),"4716013610479941");
        useSelectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireYear']"),"2023");
        useSendTextToElement(By.xpath("//input[@id='CardCode']"),"203");
        useClickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        //Confirm order page
        useVerifyResult(By.xpath("//span[normalize-space()='Credit Card']"),"Credit Card","different payment method than selected");
        useVerifyResult(By.xpath("//span[normalize-space()='2nd Day Air']"),"2nd Day Air","different shipping method than selected");
        useVerifyResult(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$698.00')]"),"$698.00","Total is not correct");
        useClickOnElement(By.xpath("//button[normalize-space()='Confirm']"));

        //Thank you message page
        useVerifyResult(By.xpath("//h1[normalize-space()='Thank you']"), "Thank you", "Message doesn't match");
        useVerifyResult(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']"), "Your order has been successfully processed!", "Order processing error");
        useClickOnElement(By.xpath("//button[normalize-space()='Continue']"));

        //Back on homepage
        useVerifyResult(By.xpath("//h2[normalize-space()='Welcome to our store']"), "Welcome to our store", "User not able to come back on Homepage");
        useClickOnElement(By.xpath("//a[normalize-space()='Log out']"));

        //Verify url
        Assert.assertEquals(driver.getCurrentUrl(),"https://demo.nopcommerce.com/");





    }



    @After
    public void tearDown() {
        closeBrowser();
    }

}
