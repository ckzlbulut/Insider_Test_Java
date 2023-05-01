package Pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BaseDriver;
import Utilities.Parents;

public class CareerPage extends Parents {
    WebDriver driver = BaseDriver.driver;
    public CareerPage()
    {
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[id='career-our-location']")
    private WebElement ourLocation;

    @FindBy(css = "#career-find-our-calling")
    private WebElement teams;

    @FindBy(xpath = "//*[text()='Life at Insider']")
    private WebElement lifeInsider;

    @FindBy(xpath = "//*[text()='See all teams']")
    private  WebElement seeAllTeams;

    @FindBy(css = "[href='https://useinsider.com/careers/quality-assurance/']")
    private WebElement qualityAssurance;

    @FindBy(xpath = "//*[text()='See all QA jobs']")
    private WebElement seeAllQA;


    public void assert_our_location_visible()
    {
        waitUntilVisible(ourLocation);
        Assert.assertTrue(ourLocation.isDisplayed());
    }

    public void assert_team_visible()
    {
        waitUntilVisible(teams);
        Assert.assertTrue(teams.isDisplayed());
    }

    public void assert_insider_life_visible()
    {
        waitUntilVisible(lifeInsider);
        Assert.assertTrue(lifeInsider.isDisplayed());
    }

    public void click_see_all_teams()
    {
        clickWithJS(seeAllTeams);
    }

    public void click_quality_assurance()
    {
        clickWithJS(qualityAssurance);
    }

}
