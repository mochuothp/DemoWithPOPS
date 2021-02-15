package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T02WebDriverWait {
    WebDriver driver;
    WebDriverWait expicilWait;
    String systemPath = System.getProperty("user.dir");

    @BeforeClass
    public void initBrowser()
    {
        System.setProperty("webdriver.gecko.driver",systemPath+"/files/geckodriver.exe");
        driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }

    @Test
    public void T01_NoSuchElementException()
    {
        driver.get("https://pops.vn");

        driver.findElement(By.xpath("//button[@class='dropdown-menu__button ']")).click();
    }

    @Test
    public void T02_ExpicitWait()
    {
        expicilWait = new WebDriverWait(driver, 5);

        driver.get("https://pops.vn");
        expicilWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/browse-more']")));
        driver.findElement(By.xpath("//a[@href='/browse-more']")).click();

        String pageTitle = driver.getTitle();
        System.out.println("You're at " + pageTitle);

    }
}
