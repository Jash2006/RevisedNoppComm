import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AllUtils extends BasePage{

        //(0) this method is to launch ChromeWebdriver
        public void launchCromeDriver(){
            // to set the path for driver object
            System.setProperty("webdriver.chrome.driver","src\\main\\BrowserDrivers\\chromedriver.exe");
            //open the browser
            driver = new ChromeDriver();
        }
        // (1)this method is to click on element
        public  void clickElements(By by){
            driver.findElement(by).click();
        }
        // (2)this method is to enter the text at element
        public void enterText(By by,String text){
            driver.findElement(by).sendKeys(text);
        }
        // (3)this method is to get text as a return from element
        public String  getTextFromElement(By by){
            return driver.findElement(by).getText();
        }
        // (4)this method is for webdriver to wait until the clickable element is present explicit wait
        public static void waitForElementClickable(By by,long time) {
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.elementToBeClickable(by));
        }
        // (5)this method is for webdriver to wait until the  element is visible
        public static void waitForElementVisible(By by,long time){
            WebDriverWait wait= new WebDriverWait(driver,time);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        // (6)This code will check whether the alert is present or not by explicit wait.
        public static void waitForAlertPresent(long time){
            WebDriverWait wait= new WebDriverWait(driver,time);
            wait.until(ExpectedConditions.alertIsPresent());
        }
        // (7)This code will generate long random date.
        public static String randomdate(){
            SimpleDateFormat format = new SimpleDateFormat("ddMMyyhhmmss");
            return format.format(new Date());
        }
        // (8) to clear text from input box or area
        public  void clearText(By by){
            driver.findElement(by).clear();
        }
        // (9)Clear and enter text in input field
        public  void ClearAndEnterTextInInputField(By by,String text){
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(text);
        }
        //(10)Checking WebElemnt present in DOM
        public void CheckingWebElemntIsPresentInDOM(By by){
            if(driver.findElement(by)!= null){
                System.out.println("Element is Present");
            }
            else{
                System.out.println("Element is Absent");
            }
        }
        //(11)Checking WebElement displayed or not
        public void CheckingWebElementDisplayedOrNot(By by){
            if( driver.findElement(by).isDisplayed()){
                System.out.println("Element is Displayed");
            }else{
                System.out.println("Element is not Displayed");
            }
        }
        //(12)Wait for fixed time given in seconds
        public void WaitForFixedTimeGivenInSeconds() throws InterruptedException {
            driver.wait(20);
        }
        //(13)Try to click element three times if not available in first go
        public void TryClickElementThreeTimesIfNotAvailableFirst(By by){
        }

        //(14)date stamp short
        public static String randomdateShort(){
            SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
            return format.format(new Date());
        }
        //(15)Wait for element for given time in second
        public void pageLoadWait(long num, TimeUnit value){
            driver.manage().timeouts().pageLoadTimeout(num,value);
        }
        //(16)explicit wait for element to be invisible
        public void waitForElementToInvisible(By by,long time){
            WebDriverWait wait= new WebDriverWait(driver,time);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        }
        //(17)To select from dropdown by visible text
        public void dropDownSelectionByVisibleText(By by,String text) {
            Select drpDate = new Select(driver.findElement(by));
            drpDate.selectByVisibleText(text);
        }
        //(18)To select from dropdown by visible value
        public void dropDownSelectionByValue(By by,String value) {
            Select drpDate = new Select(driver.findElement(by));
            drpDate.selectByValue(value);
        }
        //(19)To select from dropdown by index number
        public void dropDownSelectionByIndex(By by,int index) {
            Select drpDate = new Select(driver.findElement(by));
            drpDate.deselectByIndex(index);
        }
        //(20) this code is to wait for element With Text To Invisible
        public void waitForElementWithTextToInvisible(By by,long time,String text){
            WebDriverWait wait= new WebDriverWait(driver,time);
            wait.until(ExpectedConditions.invisibilityOfElementWithText(by,text));
        }
        //(21)Scroll to view element
        public void scrollToViewElement(By by){
            Actions actions= new Actions(driver);
            actions.moveToElement((WebElement) by).perform();
        }
        //(22)Scroll to element and click
        public void scrollToElementAndClick(By by){
            Actions actions= new Actions(driver);
            actions.moveToElement((WebElement) by).click();
        }
        //(23) to maximise the browser window screen
        public void fullScreen(){
            driver.manage().window().fullscreen();
        }
        //(24)set implicitly wait for driver object
        public void implicityWait(long imp,TimeUnit time){
            driver.manage().timeouts().implicitlyWait(imp, time);
        }
        //(25) to open website
        public void openWebsite(String website){
            driver.get(website);
        }
        //(26) Checking WebElement is present or not by explicit wait
        public void waitUntilElementVisible(By by){
            WebDriverWait wait= new WebDriverWait(driver,5);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }
        //(27)Checking WebElement is clickable or not by explicit wait
        public void checkingWebElementclickable(By by) {
            WebDriverWait wait= new WebDriverWait(driver,5);
            wait.until(ExpectedConditions.elementToBeClickable(by));
        }
        //(28) to quite the driver
        public void quiteDriver(){
            driver.quit();
        }
        //(29) to close the driver
        public void closeDriver(){
            driver.close();
        }
    }


