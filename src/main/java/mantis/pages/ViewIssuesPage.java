package mantis.pages;

import mantis.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ViewIssuesPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(css = "#buglist tbody tr")
    private List<WebElement> issues;
    @FindBy(xpath = "//td[@class='column-summary']")
    private List<WebElement> issuesSummaryList;
    @FindBy(xpath = "//table[@id='buglist']//tbody//tr[1]/td[4]")
    private WebElement firstIssueOnPage;
    @FindBy(css = "#bug_arr_all")
    private WebElement tableFooter;

    public ViewIssuesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 500);
        PageFactory.initElements(driver, this);
    }

    public int countIssues() {
        return issues.size();
    }

    public boolean isViewIssuesPageContainsIssueWithThisSummary(String summary) {
        for (WebElement issueSummary : issuesSummaryList) {
            if (issueSummary.getText().equals(summary)) {
                return true;
            }
        }

        return false;
    }

    public void goToFirstIssueOnPage() {
        firstIssueOnPage.click();
    }

    public void scrollToTableFooter() {
        TestUtils.scrollToElement(driver, tableFooter);
    }
}
