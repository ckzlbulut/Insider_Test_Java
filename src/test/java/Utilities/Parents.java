package Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class Parents {
    WebDriver driver = BaseDriver.driver;
    WebDriverWait wait = BaseDriver.wait;

    public void waitUntilVisible(WebElement element)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilClickable(WebElement element)
    {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void basicClickFunction(WebElement element)
    {
        waitUntilClickable(element);
        moveToElement(element);
        element.click();
    }

    public void clickWithJS(WebElement element)
    {
        moveToElement(element);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();",element);
    }

    public void selectByText(WebElement element, String value)
    {
        waitUntilVisible(element);
        Select ss = new Select(element);
        ss.selectByVisibleText(value);
    }

    public void moveToElement(WebElement element)
    {
        waitUntilVisible(element);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
    }


    public void scrollWithJS(WebElement element)
    {
        waitUntilVisible(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void wait_presence_of_options(WebElement select_element, By option_locator) {
        wait.until((WebDriver d) -> select_element.findElements(option_locator).size() > 1);
    }

    public void wait_list(List<WebElement> list)
    {
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }

    public boolean isContains(String str1, String str2)
    {
        return str1.toLowerCase().contains(str2.toLowerCase());
    }

    public String getNewWindowHandle(String main_window, Set<String> all_windows) {
        for (String window : all_windows) {
            if (!window.equals(main_window)) {
                return window;
            }
        }
        return null;
    }

}
