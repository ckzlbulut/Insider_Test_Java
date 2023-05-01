package Tests;


import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import Pages.CareerPage;
import Pages.Homepage;
import Pages.QaPage;
import Utilities.BaseDriver;
import Utilities.Parents;

public class InsiderTests extends Parents {

    Homepage homePageElements = new Homepage();
    CareerPage careerPage = new CareerPage();

    QaPage qaPage=new QaPage();

    @Test
    public void homepage() {
        homePageElements.accept_cookies();
        homePageElements.assert_home_loaded();
    }

    @Test(dependsOnMethods = "homepage")
    public void career() {
        homePageElements.click_more_menu();
        homePageElements.click_careers();

        careerPage.assert_our_location_visible();
        careerPage.assert_team_visible();
        careerPage.assert_insider_life_visible();
    }

    @Test(dependsOnMethods = {"homepage", "career"})
    public void jobList() {
        careerPage.click_see_all_teams();
        careerPage.click_quality_assurance();
        qaPage.click_see_all_qa_jobs();

        qaPage.select_location("Istanbul, Turkey");
        qaPage.assert_job_list_presence();
    }


    @Test(dependsOnMethods = {"homepage", "career", "jobList"})
    public void jobPosts() {
        qaPage.assert_positions_contains("quality assurance");
        qaPage.assert_department_contains("quality assurance");
        qaPage.assert_locations_contains("Istanbul, Turkey");
        qaPage.assert_apply_button();
    }

    @Test(dependsOnMethods = {"homepage", "career", "jobList","jobPosts"})
    public void checkApplyPage()
    {
        qaPage.click_apply_buttons();
    }


    @AfterTest
    public void tearDown()
    {
        BaseDriver.waitAndQuit();
    }


}
