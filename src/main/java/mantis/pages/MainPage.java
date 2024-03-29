package mantis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "span.user-info")
    private WebElement userName;

    @FindBy(css = "a[href='/mantisbt/view_all_bug_page.php']")
    private WebElement viewIssuesPageButton;
    @FindBy(css = "a[href='/mantisbt/bug_report_page.php']")
    private WebElement reportIssuePageButton;
    @FindBy(xpath = "//a[@class='dropdown-toggle']")
    private WebElement dropDownToggle;
    @FindBy(css = "a[href='/mantisbt/logout_page.php']")
    private WebElement logoutButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 500);
        PageFactory.initElements(driver, this);
    }

    public String getUserName() {
        return userName.getText();
    }

    public void goToViewIssuesPage() {
        viewIssuesPageButton.click();
    }

    public void goToReportIssuePage() {
        reportIssuePageButton.click();
    }

    public void logout() {
        dropDownToggle.click();
        logoutButton.click();
    }
}
