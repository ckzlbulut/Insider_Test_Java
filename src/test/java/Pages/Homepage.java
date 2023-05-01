package Pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BaseDriver;
import Utilities.Parents;

public class Homepage extends Parents {
    WebDriver driver = BaseDriver.driver;
    public Homepage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[text()='Accept All']")
    private WebElement acceptCookies;

    @FindBy(xpath = "//body[contains(@class,'home page-template')]")
    private WebElement homePage;

    @FindBy(xpath = "//*[text()='More']")
    private WebElement moreMenu;

    @FindBy(css = "[href='https://useinsider.com/careers/']")
    private WebElement careers;

    public void accept_cookies()
    {
        driver.get("https://useinsider.com/");
        basicClickFunction(acceptCookies);
        waitUntilVisible(homePage);
        Assert.assertTrue(homePage.isDisplayed());
    }

    public void assert_home_loaded()
    {
        waitUntilVisible(homePage);
        Assert.assertTrue(homePage.isDisplayed());
    }

    public void click_more_menu()
    {
        basicClickFunction(moreMenu);
    }

    public void click_careers()
    {
        basicClickFunction(careers);
        Assert.assertEquals("https://useinsider.com/careers/", driver.getCurrentUrl());
    }
}
