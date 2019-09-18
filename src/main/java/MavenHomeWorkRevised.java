import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;

public class MavenHomeWorkRevised extends AllUtils {
    LoadProps props= new LoadProps();
    @Before
    public void setup(){
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
    public void userRegistrationSuccessfully(){
        //click on register button
        clickElements(By.xpath("//a[@class='ico-register']"));
        //enter the first name
        enterText(By.id("FirstName"), props.getProperty("FirstName"));
        //enter the last name
        enterText(By.xpath("//input[@name='LastName']"),props.getProperty("LastName"));
        // to select date from dropdown
        dropDownSelectionByVisibleText(By.xpath("//select[@name='DateOfBirthDay']"),props.getProperty("Date"));
        // to select month from dropdown
        dropDownSelectionByVisibleText(By.xpath("//select[@name='DateOfBirthMonth']"),props.getProperty("Month"));
        // to select year from dropdown
        dropDownSelectionByVisibleText(By.xpath("//select[@name='DateOfBirthYear']"),props.getProperty("Year"));
        // to enter the email
        enterText(By.name("Email"),props.getProperty("Email1")+randomdate()+props.getProperty("Email2"));
        // to enter the password
        enterText(By.name("Password"),props.getProperty("Password"));
        // to enter confirm password
        enterText(By.id("ConfirmPassword"),props.getProperty("ConfirmPassword"));
        // to click on register to finish the registration
        clickElements(By.name("register-button"));
        String actualmsg= getTextFromElement(By.xpath("//div[@class='result']"));
        Assert.assertEquals(props.getProperty("expectedmsg"),actualmsg);
    }
    @Test
    public void referProductToFriend(){
        // reusing user registration method to get registration successfully
        userRegistrationSuccessfully();
        // to click on nopp commerce logo
        clickElements(By.xpath("//img[@alt='nopCommerce demo store']"));
        // to select the Apple macbook image
        clickElements(By.xpath("//img[@title=\"Show details for Apple MacBook Pro 13-inch\"]"));
        // to select email a friend
        clickElements(By.xpath("//input[@value=\"Email a friend\"]"));
        // to enter friend's name
        enterText(By.id("FriendEmail"),props.getProperty("FriendEmail"));
        // to enter personal message to friend
        enterText(By.id("PersonalMessage"),props.getProperty("PersonalMessage"));
        // to click on SEND EMAIL button
        clickElements(By.xpath("//input[@value='Send email']"));
        String actualrefermsg = getTextFromElement(By.xpath("//*[@class='result' and contains(text(),'Your message has been sent.')]"));
        Assert.assertEquals(props.getProperty("expectedreferalmsg"),actualrefermsg);
    }
    @Test
    public void userNavigatetoCameraPhoto(){
        // to click on electronics category
        clickElements(By.xpath("//h2/a[@title='Show products in category Electronics']"));
        // to click on camera & photo
        clickElements(By.xpath("//h2/a[@title='Show products in category Camera & photo']"));
        String actualtitle=getTextFromElement(By.xpath("//div[@class='page-title']"));
        Assert.assertEquals(props.getProperty("expectedtitle"),actualtitle);
    }
    @Test
    public void userFilterJewelryByPrice(){
        // to click on jewelry category
        clickElements(By.linkText("Jewelry"));
        //to select price range
        clickElements(By.xpath("//a[@href='//demo.nopcommerce.com/jewelry?price=700-3000']"));
        String actualPriceRange= getTextFromElement(By.xpath("//span[@class='item']"));
        Assert.assertEquals(props.getProperty("expectedPriceRange"),actualPriceRange);
        // to store actual product price in String
        String actualPrice= getTextFromElement(By.xpath("//span[@class='price actual-price']"));
        // converting string into float
        float a = Float.parseFloat(actualPrice.replaceAll(",","").substring(1));
        System.out.println(a);
        // to store start price in string
        String startPrice=getTextFromElement(By.xpath("//span[@class='PriceRange' and text()='$700.00']"));
        // converting string into float
        float s = Float.parseFloat(startPrice.substring(1));
        System.out.println(s);
        // to store the end price
        String endPrice=getTextFromElement(By.xpath("//span[@class='PriceRange' and text()='$3,000.00']"));
        // converting string into float
        float e = Float.parseFloat(endPrice.replaceAll(",","").substring(1));
        System.out.println(e);
        // to check whether price is between start price and end price
        Assert.assertTrue(a>=s && a<=e);
        // to print the statement
        System.out.println("The product price "+ actualPrice+" is between "+startPrice+ " and " +endPrice);
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
        waitForElementVisible(By.xpath("//span[@class='cart-qty' and text()='(1)']"),5);
        // to select second book
        clickElements(By.linkText("Fahrenheit 451 by Ray Bradbury"));
        // to add second book in shopping cart
        clickElements(By.xpath("//input[@id='add-to-cart-button-37']"));
        // to make webdriver to wait until item adds into the shopping cart
        waitForElementVisible(By.xpath("//span[@class='cart-qty' and text()='(2)']"),5);
        String actual = getTextFromElement(By.className("cart-qty"));
        String actualQnt =actual.replace('(',' ').replace(')',' ').trim();
        // to check whether actual quantity matches expected quantity
        Assert.assertEquals(actualQnt,props.getProperty("expectedQnt"));
        // to click on shopping cart
        clickElements(By.xpath("//a[@href=\"/cart\" and text()='shopping cart']"));
        // This code check whether first book is on shopping cart page
        String product1=getTextFromElement(By.xpath("//a[@class='product-name' and text()='First Prize Pies']"));
        Assert.assertEquals("First Prize Pies",product1);
        // This code check whether second book is on shopping cart page
        String product2=getTextFromElement(By.xpath("//a[@class='product-name' and text()='Fahrenheit 451 by Ray Bradbury']"));
        Assert.assertEquals("Fahrenheit 451 by Ray Bradbury",product2);
    }
    @After
    public void teardown(){
        // to close the browser
        quiteDriver();
    }
}
