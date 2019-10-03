import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MavenHomeWorkRevised extends AllUtils {
    LoadProps props = new LoadProps();

    @Before
    public void setup() {
        //open the browser
        launchCromeDriver();
        // to maximise the browser window screen
        fullScreen();
        // set implicitly wait driver object
        implicityWait(20, TimeUnit.SECONDS);
        // to open the website
        openWebsite(props.getProperty("url"));

    }

    @Test
    public void userRegistrationSuccessfully() {
        // to take screenshot
        takeScreenshot(driver);
        //click on register button
        clickElements(By.xpath("//a[@class='ico-register']"));
        //enter the first name
        enterText(By.id("FirstName"), props.getProperty("FirstName"));
        //enter the last name
        enterText(By.xpath("//input[@name='LastName']"), props.getProperty("LastName"));
        // to select date from dropdown
        dropDownSelectionByVisibleText(By.xpath("//select[@name='DateOfBirthDay']"), props.getProperty("Date"));
        // to select month from dropdown
        dropDownSelectionByVisibleText(By.xpath("//select[@name='DateOfBirthMonth']"), props.getProperty("Month"));
        // to select year from dropdown
        dropDownSelectionByVisibleText(By.xpath("//select[@name='DateOfBirthYear']"), props.getProperty("Year"));
        // to enter the email
        enterText(By.name("Email"), props.getProperty("Email1") + randomdate() + props.getProperty("Email2"));
        // to enter the password
        enterText(By.name("Password"), props.getProperty("Password"));
        // to enter confirm password
        enterText(By.id("ConfirmPassword"), props.getProperty("ConfirmPassword"));
        // to click on register to finish the registration
        clickElements(By.name("register-button"));
        String actualmsg = getTextFromElement(By.xpath("//div[@class='result']"));
        Assert.assertEquals(props.getProperty("expectedmsg"), actualmsg);
    }

    @Test
    public void referProductToFriend() {
        // reusing user registration method to get registration successfully
        userRegistrationSuccessfully();
        // to click on nopp commerce logo
        clickElements(By.xpath("//img[@alt='nopCommerce demo store']"));
        // to select the Apple macbook image
        clickElements(By.xpath("//img[@title=\"Show details for Apple MacBook Pro 13-inch\"]"));
        // to select email a friend
        clickElements(By.xpath("//input[@value=\"Email a friend\"]"));
        // to enter friend's name
        enterText(By.id("FriendEmail"), props.getProperty("FriendEmail"));
        // to enter personal message to friend
        enterText(By.id("PersonalMessage"), props.getProperty("PersonalMessage"));
        // to click on SEND EMAIL button
        clickElements(By.xpath("//input[@value='Send email']"));
        String actualrefermsg = getTextFromElement(By.xpath("//*[@class='result' and contains(text(),'Your message has been sent.')]"));
        Assert.assertEquals(props.getProperty("expectedreferalmsg"), actualrefermsg);
    }

    @Test
    public void userNavigatetoCameraPhoto() {
        // to click on electronics category
        clickElements(By.xpath("//h2/a[@title='Show products in category Electronics']"));
        // to click on camera & photo
        clickElements(By.xpath("//h2/a[@title='Show products in category Camera & photo']"));
        String actualtitle = getTextFromElement(By.xpath("//div[@class='page-title']"));
        Assert.assertEquals(props.getProperty("expectedtitle"), actualtitle);
    }

    @Test
    public void userFilterJewelryByPrice() {
        // to click on jewelry category
        clickElements(By.linkText("Jewelry"));
        //to select price range
        clickElements(By.xpath("//a[@href='//demo.nopcommerce.com/jewelry?price=700-3000']"));
        String actualPriceRange = getTextFromElement(By.xpath("//span[@class='item']"));
        Assert.assertEquals(props.getProperty("expectedPriceRange"), actualPriceRange);
        // to store actual product price in String
        String actualPrice = getTextFromElement(By.xpath("//span[@class='price actual-price']"));
        // converting string into float
        float a = Float.parseFloat(actualPrice.replaceAll(",", "").substring(1));
        System.out.println(a);
        // to store start price in string
        String startPrice = getTextFromElement(By.xpath("//span[@class='PriceRange' and text()='$700.00']"));
        // converting string into float
        float s = Float.parseFloat(startPrice.substring(1));
        System.out.println(s);
        // to store the end price
        String endPrice = getTextFromElement(By.xpath("//span[@class='PriceRange' and text()='$3,000.00']"));
        // converting string into float
        float e = Float.parseFloat(endPrice.replaceAll(",", "").substring(1));
        System.out.println(e);
        // to check whether price is between start price and end price
        Assert.assertTrue(a >= s && a <= e);
        // to print the statement
        System.out.println("The product price " + actualPrice + " is between " + startPrice + " and " + endPrice);
    }

    @Test
    public void userAbleToAddItemsIntoBasket() {
        // to open the book category page
        clickElements(By.linkText("Books"));
        // to select first book "First prize pies"
        clickElements(By.linkText("First Prize Pies"));
        // to add the first book in basket
        clickElements(By.xpath("//input[@id='add-to-cart-button-38']"));
        // to make webdriver to wait until item adds into the shopping cart
        waitForElementVisible(By.xpath("//span[@class='cart-qty' and text()='(1)']"), 5);
        // to select second book
        clickElements(By.linkText("Fahrenheit 451 by Ray Bradbury"));
        // to add second book in shopping cart
        clickElements(By.xpath("//input[@id='add-to-cart-button-37']"));
        // to make webdriver to wait until item adds into the shopping cart
        waitForElementVisible(By.xpath("//span[@class='cart-qty' and text()='(2)']"), 5);
        String actual = getTextFromElement(By.className("cart-qty"));
        String actualQnt = actual.replace('(', ' ').replace(')', ' ').trim();
        // to check whether actual quantity matches expected quantity
        Assert.assertEquals(actualQnt, props.getProperty("expectedQnt"));
        // to click on shopping cart
        clickElements(By.xpath("//a[@href=\"/cart\" and text()='shopping cart']"));
        // This code check whether first book is on shopping cart page
        String product1 = getTextFromElement(By.xpath("//a[@class='product-name' and text()='First Prize Pies']"));
        Assert.assertEquals("First Prize Pies", product1);
        // This code check whether second book is on shopping cart page
        String product2 = getTextFromElement(By.xpath("//a[@class='product-name' and text()='Fahrenheit 451 by Ray Bradbury']"));
        Assert.assertEquals("Fahrenheit 451 by Ray Bradbury", product2);
    }

    @Test
    public void userAbleToCompareProducts() {
        // to click on apple mcbook on homepage
        clickElements(By.xpath("//img[@alt='Picture of Apple MacBook Pro 13-inch']"));
        // to click on add to compare list
        clickElements(By.xpath("//input[@onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/4\"),!1']"));
        // to wait until confirmation message appears on top
        waitForElementVisible(By.xpath("//p[@class='content'] "), 5);
        // to get the confirmation message as a string from bar notification
        String actualmsg1 = getTextFromElement(By.xpath("//p[@class='content'] "));
        String expectedconfirmmsg1 = "The product has been added to your product comparison";
        // to assert that the confirmation message is displayed matches the expected message
        Assert.assertEquals(expectedconfirmmsg1, actualmsg1);
        // to close the bar notification
        clickElements(By.xpath("//span[@class='close'] "));
        // to click on second product
        clickElements(By.xpath("//img[@alt='Picture of Asus N551JK-XO076H Laptop'] "));
        // to click on add to compare list
        clickElements(By.xpath("//input[@onclick='return AjaxCart.addproducttocomparelist(\"/compareproducts/add/5\"),!1']"));
        // to wait until confirmation message appears on top
        waitForElementVisible(By.xpath("//p[@class='content'] "), 5);
        // to get the confirmation message as a string from bar notification
        String actualmsg2 = getTextFromElement(By.xpath("//p[@class='content'] "));
        String expectedconfirmmsg2 = "The product has been added to your product comparison";
        // to assert that the confirmation message is displayed matches the expected message
        Assert.assertEquals(expectedconfirmmsg2, actualmsg2);
        // to close the bar notification
        clickElements(By.xpath("//span[@class='close'] "));
        // to click on product compare list
        clickElements(By.linkText("Compare products list"));
        // to get all webelements as a list
        List<WebElement> itemlist = driver.findElements(By.xpath("//div[@class='page compare-products-page']"));
        // declaring a string array to store all the texts from webElements
        List<String> textList = new ArrayList<String>();
        for (WebElement e : itemlist) {
            textList.add(e.getText());
        }
        System.out.println(textList.toString());
        // assert method to check the name of products are in the list.
        Assert.assertTrue(textList.toString().contains("Asus N551JK-XO076H Laptop Apple MacBook Pro 13-inch"));
        // to clear the comparision list
        clickElements(By.className("clear-list"));
        // to get the text from comparision page
        String actualClearmsg = getTextFromElement(By.className("no-data"));
        String expectedClearmsg = "You have no items to compare.";
        // Assert method to compare actual and expected messages
        Assert.assertEquals(expectedClearmsg, actualClearmsg);
    }

    @Test
    public void userShouldBeAbbleToAddComments() {
        // to click on DETAILS
        clickElements(By.xpath("//a[@href=\"/new-online-store-is-open\" and text()='details']"));
        // to get text from message
        String actualCommentmsg = getTextFromElement(By.xpath("//h1[text()='New online store is open!']"));
        String expectedCommentmsg = "New online store is open!";
        // assert to compare messages
        Assert.assertEquals(expectedCommentmsg, actualCommentmsg);
        // to enter comment title
        enterText(By.className("enter-comment-title"), "Hello");
        // to enter comment in comment box
        enterText(By.className("enter-comment-text"), "This is an excellent site");
        // to submit the comment
        clickElements(By.xpath("//input[@name='add-comment']"));
        // to get message as a String
        String actualNewsComment = getTextFromElement(By.xpath("//*[contains(text(),'News comment is successfully added.')]"));
        String expectedNewsComment = "News comment is successfully added.";
        Assert.assertEquals(expectedNewsComment, actualNewsComment);

        List<WebElement> commentslist = driver.findElements(By.xpath("//div[@class='comments']"));
        String comments = null;
        for (WebElement e : commentslist) {
            comments = e.getText();
        }
        Assert.assertTrue(comments.contains("This is an excellent site"));
    }

    @Test
    public void userAbleToSearchProductByName(){
        // to search product by entering text'nike'.
        enterText(By.xpath("//input[@class='search-box-text ui-autocomplete-input']"),"Nike");
        // to click on search
        clickElements(By.xpath("//input[@class='button-1 search-box-button']"));
        // to get all webelements as a list
        List<WebElement> itemlist = driver.findElements(By.xpath("//div[@class='search-results']"));
        List<String> itemtextList = new ArrayList<String>();
        for (WebElement e : itemlist) {
            itemtextList.add(e.getText());
           }
        System.out.println(itemtextList.toString());
        // assert method to check the name of products are in the list.
        Assert.assertTrue(itemtextList.toString().contains("Nike Floral Roshe Customized Running Shoes"));
        Assert.assertTrue(itemtextList.toString().contains("Nike SB Zoom Stefan Janoski \"Medium Mint\""));
        Assert.assertTrue(itemtextList.toString().contains("Nike Tailwind Loose Short-Sleeve Running Shirt"));
        // to search product by entering text'manoj'.
        enterText(By.xpath("//input[@class='search-box-text ui-autocomplete-input']"),"Manoj");
        // to click on search
        clickElements(By.xpath("//input[@class='button-1 search-box-button']"));
        // get text from failure message
        String actualFailmsg = getTextFromElement(By.xpath("//*[text()='No products were found that matched your criteria.']"));
        String expectedFailmsg = "No products were found that matched your criteria.";
        Assert.assertEquals(expectedFailmsg,actualFailmsg);

    }
    @After
    public void teardown(){
    //to close the browser
    quiteDriver();
    }
}

