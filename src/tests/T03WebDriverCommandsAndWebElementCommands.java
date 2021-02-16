package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class T03WebDriverCommandsAndWebElementCommands {
    WebDriver driver;
    WebDriverWait explicitWait;
    String systemPath = System.getProperty("user.dir");

    @BeforeClass
    public void initBrowser()
    {
        System.setProperty("webdriver.chrome.driver",systemPath+"/files/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }
    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }

    @Test
    public void NavigateBetweenPages() throws InterruptedException {
        explicitWait = new WebDriverWait(driver, 10);
        driver.get("https://pops.vn");

        driver.findElement(By.xpath("//a[text()='Thêm']")).click();
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/my-list']")));

        driver.findElement(By.xpath("//a[text()='Comics']")).click();
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Cập Nhật Mới']")));

        //Verify page title of More page
        driver.navigate().back();
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/my-list']")));
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle,"Phim, Show, Âm Nhạc Và Nhiều Hơn Nửa | POPS APP");

        //Verify page title of Comics page
        driver.navigate().forward();
        Thread.sleep(5000);
        pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "POPS | Film, Comedy, Shows");


    }


    @Test
    public void Verify_PageTile() throws InterruptedException {
        explicitWait = new WebDriverWait(driver, 5);

        driver.get("https://pops.vn");
        driver.findElement(By.xpath("//a[text()='Gói sản phẩm']")).click();

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Team Thầy Ba']")));

        Assert.assertEquals(driver.getTitle(), "Gói sản phẩm webtoon của Sơn Tùng M-TP và SBTC Team | POPS");
        driver.findElement(By.xpath("//div[@class='slick-list']//h4[contains(text(),'Gói 499')]")).click();
        Thread.sleep(5000);

        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Team Thầy Ba - Gói 499");

    }

    @Test
    public void Verify_PageURL()
    {
        driver.get("https://pops.vn");
        driver.findElement(By.cssSelector(".dropdown-menu__button")).click();
        driver.findElement(By.cssSelector(".menu__list a[href='/settings/remove-ads']")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String pageURL = driver.getCurrentUrl();
        Assert.assertEquals(pageURL, "https://pops.vn/settings/remove-ads");

    }

    @Test
    public void isElementDisplayed()
    {
        explicitWait = new WebDriverWait(driver, 10);
        driver.get("http://pops.vn");

        driver.findElement(By.cssSelector(".dropdown-menu__button")).click();
        driver.findElement(By.xpath("//a[@href='/payment']")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='payment-method ']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='payment-method ']")).isDisplayed());

    }
    public boolean isElementDisplayed(String xpath)
    {
        WebElement element = driver.findElement(By.xpath(xpath));
        if (element.isDisplayed())
        {
            System.out.println("Your element: "+element+ "is displayed");
            return true;
        }else
        {
            System.out.println(("Your element: "+element+"is not displayed"));
            return false;
        }
    }

    @Test
    public void isElementDisplayed02()
    {
        explicitWait = new WebDriverWait(driver, 10);
        driver.get("https://pops.vn");

        driver.findElement(By.cssSelector(".dropdown-menu__button")).click();
        driver.findElement(By.xpath("//a[@href='/payment']")).click();

        Assert.assertTrue(isElementDisplayed("//div[@class='payment-gateway__method']"));

   }


    @Test
    public void isElementDisabled()
    {
        explicitWait = new WebDriverWait(driver, 10);
        driver.get("https://pops.vn");

        driver.findElement(By.cssSelector(".dropdown-menu__button")).click();
        driver.findElement(By.xpath("//a[@href='/auth/signin-signup/signin']")).click();

        Assert.assertFalse(driver.findElement(By.xpath("//button[@class='signin__login-btn']")).isEnabled());

    }
    @Test
    public void isElementEnabled()
    {
        explicitWait = new WebDriverWait(driver, 10);
        driver.get("https://pops.vn");

        driver.findElement(By.cssSelector(".dropdown-menu__button")).click();
        driver.findElement(By.xpath("//a[@href='/auth/signin-signup/signin']")).click();

        driver.findElement(By.name("account")).sendKeys("mixixma@gmail.com");
        driver.findElement(By.name("password")).sendKeys("123456");

        Assert.assertTrue(isElementEnabled("//button[@class='signin__login-btn']"));
    }

    @Test
    public void isElementEnabled02()
    {
        explicitWait = new WebDriverWait(driver, 10);
        driver.get("https://pops.vn");

        driver.findElement(By.cssSelector(".dropdown-menu__button")).click();
        driver.findElement(By.xpath("//a[@href='/auth/signin-signup/signin']")).click();

        driver.findElement(By.name("account")).sendKeys("mixixma@gmail.com");

        Assert.assertFalse(isElementEnabled("//button[@class='signin__login-btn']"));
    }

    public boolean isElementEnabled(String xpath)
    {
        WebElement element = driver.findElement(By.xpath(xpath));
        if(element.isEnabled())
        {
            return true;
        }else
        {
            return false;
        }
    }

    @Test
    public void isElementSelected() throws InterruptedException
    {
        explicitWait = new WebDriverWait( driver, 10);

        driver.get("https://automationfc.github.io/basic-form/index.html");

        Assert.assertFalse(isElementSelected("//input[@id='development']"));
        driver.findElement(By.xpath("//input[@id='development']")).click();
        Assert.assertTrue(isElementSelected("//input[@id='development']"));


    }

    public boolean isElementSelected(String xpath)
    {
        WebElement element = driver.findElement(By.xpath(xpath));
        if(element.isSelected())
        {
            System.out.println("Element of xpath: "+element+" is selected!");
            return true;
        }else
            {
                System.out.println("Element of xpath: "+element+" is NOT selected!");
                return false;
            }
    }

    public void loginToAccount()
    {
        driver.get("https://pops.vn/auth/signin-signup/signin");
        driver.findElement(By.name("account")).sendKeys("mixixma@gmail.com");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@class='signin__login-btn']")).click();
    }
}
