package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class T01WebDriver {
    WebDriver driver;
    String systemPath = System.getProperty("user.dir");



    @BeforeClass
    public void initBrowser()
    {
        System.setProperty("webdriver.chrome.driver",systemPath+"/files/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @AfterClass
    public void closeBroswer()
    {
        driver.quit();
    }

    @Test
    public void openATestPage()
    {
        driver.get("http://www.google.com");
        String pageTitle = driver.getTitle();

        System.out.println(pageTitle);
    }

}
