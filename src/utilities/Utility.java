package utilities;

import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseClass{

    /**
     * This method will click on element
     * @param by
     */

    public void useClickOnElement(By by){
        WebElement logIn = driver.findElement(by);
        logIn.click();
    }

    /**
     * This method will get text from element
     * @param by
     * @return
     */
    public String useGetTextFromElement(By by){
        return driver.findElement(by).getText();

       /* WebElement text = driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        String actualMessage = text.getText();
        return actualMessage;*/
    }

    /**
     * This method will write/send text on element
     * @param by
     * @param text
     */

    public void useSendTextToElement(By by, String text){
        driver.findElement(by).sendKeys(text);
    }

    /**
     * This method will switch to and accept alert
     */

    public void useAcceptAlert(){
        driver.switchTo().alert().accept();
    }

    /**
     * This method will switch to and dismiss/cancel alert
     */

    public void useDismissAlert(){
        driver.switchTo().alert().dismiss();
    }

    /**
     * This method will get and return text from alert
     * @return
     */

    public String useGetTextAlert(){
        return driver.switchTo().alert().getText();
    }

    /**
     * This method will select value from dropdown using selectByVisibleText() method
     * @param by
     * @param text
     */

    public void useSelectByVisibleTextFromDropDown(By by, String text){
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);

    }

    /**
     * This method will select value from dropdown using selectByValue() method
     * @param by
     * @param text
     */

    public void useSelectByValueFromDropDown(By by, String text){
        WebElement dropdown = driver.findElement(by);
        Select select = new Select(dropdown);
        select.selectByValue(text);
    }

    /**
     * This method will select index value for dropdown text using selectByIndex() method
     * @param by
     * @param index
     */

    public void useSelectByIndexFromDropDown(By by, int index){
        WebElement dropdown = driver.findElement(by);
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    /**
     * This method will perform mouse drag and drop action
     * @param drag
     * @param drop
     */

    public void useDragAndDrop(By drag, By drop){
        Actions actions = new Actions(driver);

        WebElement draggable = driver.findElement(drag);
        WebElement droppable = driver.findElement(drop);

        actions.dragAndDrop(draggable,droppable).build().perform();

    }

    /**
     * This method will perform mouse slider action based on x and y value
     * @param by
     * @param x
     * @param y
     */
    public void useSliderAction(By by, int x, int y){
        Actions actions = new Actions(driver);
        WebElement slider = driver.findElement(by);
        actions.dragAndDropBy(slider,x,y).build().perform();
    }

    /**
     * This method will perform mouse right click action
     * @param by
     */
    public void useMouseRightClickAction(By by){
        Actions actions = new Actions(driver);
        WebElement button = driver.findElement(by);
        actions.contextClick().build().perform();
    }

    /**
     * This method will perform mouse hover action
     * @param by
     */

    public void useMouseHoverAction(By by){
        Actions actions = new Actions(driver);
        WebElement hover = driver.findElement(by);
        actions.moveToElement(hover).build().perform();
    }

    /**
     * This method will perform mouse hover and click action
     * @param by
     */

    public void useMouseHoverAndClickAction(By by){
        Actions actions = new Actions(driver);
        WebElement hover = driver.findElement(by);
        actions.moveToElement(hover).click().build().perform();
    }

    /**
     * This method will verify expected and actual result using Assert class methods.
     * @param by
     * @param expected
     * @param errorMessage
     */
    public void useVerifyResult(By by, String expected, String errorMessage){
        String actual = useGetTextFromElement(by);
        Assert.assertEquals(errorMessage,expected,actual);
    }


    /**
     * This method will generate unique email address everytime
     * @return
     */

    public String useGetUniqueEmailAddress(){

        RandomString ranstr = new RandomString(10);
        String randomString = ranstr.nextString();
        return randomString+"@gmail.com";
    }

}
