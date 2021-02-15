package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T03WebDriverCommandsAndWebElementCommands {
    WebDriver driver;
    String systemPath = System.getProperty("user.dir");

    @BeforeClass
    public void initBrowser()
    {
        System.setProperty("webdriver.chromedriver.setup",systemPath+"/files/chromedriver.exe");
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
    public void NavigateBetweenPages()
    {

    }


    @Test
    public void Verify_PageTile()
    {

    }

    @Test
    public void Verify_PageURL()
    {

    }

    @Test
    public void isElementDisplayed()
    {

    }

    @Test
    public void isElementEnabled()
    {

    }

    @Test
    public void isElementSelected()
    {

    }


}
