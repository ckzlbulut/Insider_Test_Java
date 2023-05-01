package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BaseDriver;

public class LeverAppPage {

    WebDriver driver = BaseDriver.driver;
    public LeverAppPage()
    {
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[text()='Apply for this job']")
    private WebElement leverFormApplyButton;

}
