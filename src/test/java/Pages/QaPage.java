package Pages;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Utilities.BaseDriver;
import Utilities.Parents;
import java.util.List;


public class QaPage extends Parents {

    WebDriver driver = BaseDriver.driver;
    String QA_open_pos_url = "https://useinsider.com/careers/open-positions/?department=qualityassurance";
    String lever_form_url = "https://jobs.lever.co/useinsider";
    public QaPage()
    {
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".location-info .mb-0")
    private List<WebElement> locationInfo;

    @FindBy(xpath = "//*[text()='See all QA jobs']")
    private WebElement seeAllQA;

    @FindBy(css = "[name='filter-by-location']")
    private WebElement filterLocation;

    private final By positionTitle = By.cssSelector(".position-title");

    private final By positionDepartment = By.cssSelector(".position-department");

    private final By positionLocation = By.cssSelector(".position-location");

    @FindBy(css = ".position-list-item-wrapper")
    private List<WebElement> jobList;

    private final By applyNow = By.cssSelector("a[href*='jobs.lever.co/useinsider']");

    @FindBy(css = "#career-position-filter")
    private WebElement positionFilter;

    private final By option = By.cssSelector("option");

    @FindBy(xpath = "//*[text()='Apply for this job']")
    private WebElement leverApplyButton;

    public void click_see_all_qa_jobs()
    {
        basicClickFunction(seeAllQA);
        Assert.assertEquals(driver.getCurrentUrl(),"https://useinsider.com/careers/open-positions/?department=qualityassurance");
    }


    public void select_location(String location)
    {
        waitUntilClickable(filterLocation);

        wait_presence_of_options(filterLocation, option);
        selectByText(filterLocation,"Istanbul, Turkey");

        scrollWithJS(positionFilter);

    }

    public void assert_job_list_presence()
    {
        wait_list(jobList);
        Assert.assertTrue(jobList.size()>0);
    }

    public void assert_positions_contains(String title)
    {
        for (WebElement job: jobList) {
            WebElement position_element = job.findElement(positionTitle);

            Assert.assertTrue(isContains(position_element.getText(),title));
        }
    }


    public void assert_locations_contains(String location)
    {
        for (WebElement job: jobList) {
            WebElement location_element = job.findElement(positionLocation);

            Assert.assertTrue(isContains(location_element.getText(),location));
        }
    }

    public void assert_department_contains(String department)
    {
        for (WebElement job: jobList) {
            WebElement department_element = job.findElement(positionDepartment);

            Assert.assertTrue(isContains(department_element.getText(),department));
        }
    }
    public void assert_apply_button()
    {
        for (WebElement job: jobList) {
            Actions actions = new Actions(driver);
            actions.moveToElement(job);

            Assert.assertTrue(job.findElements(applyNow).size()>0);

        }
    }

    public void click_apply_buttons()
    {
        String postion_page_window = driver.getWindowHandle();

        for (WebElement job_post : jobList) {
            Actions actions1 = new Actions(driver);
            actions1.moveToElement(job_post).perform();

            WebElement apply_button = BaseDriver.wait.until(ExpectedConditions.elementToBeClickable(applyNow));
            apply_button.click();

            String new_window = getNewWindowHandle(postion_page_window, driver.getWindowHandles());
            driver.switchTo().window(new_window);

            Assert.assertTrue(driver.getCurrentUrl().contains("obs.lever.co/useinsider/"));
            Assert.assertTrue(leverApplyButton.isDisplayed());

            driver.close();
            driver.switchTo().window(postion_page_window);
        }
    }



}
