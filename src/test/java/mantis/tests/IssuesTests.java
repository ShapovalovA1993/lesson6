package mantis.tests;

import mantis.pages.MantisSite;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IssuesTests extends BaseTest {

    private MantisSite mantisSite;

    @Test
    public void checkIssuesNumber() throws InterruptedException {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");
        mantisSite.getMainPage().goToViewIssuesPage();

        mantisSite.getViewIssuesPage().scrollToTableFooter();
        Thread.sleep(3000);
        int actualIssuesNumber = mantisSite.getViewIssuesPage().countIssues();
        Assertions.assertEquals(50, actualIssuesNumber);
    }

    @Test
    public void createAndDeleteIssue() {
        mantisSite = new MantisSite(driver);
        mantisSite.login("admin", "admin20");
        mantisSite.getMainPage().goToReportIssuePage();

        mantisSite.getReportIssuePage().fillSummaryField("My unique summary");
        mantisSite.getReportIssuePage().fillDescriptionField("My unique description");

        mantisSite.getReportIssuePage().scrollToTableFooter();
        mantisSite.getReportIssuePage().clickSubmitIssueButton();

        mantisSite.getMainPage().goToViewIssuesPage();

        SoftAssertions softAssert = new SoftAssertions();

        if (!mantisSite.getViewIssuesPage().isViewIssuesPageContainsIssueWithThisSummary("My unique summary")) {
            Assertions.fail("Issue was not created!");
        }

        mantisSite.getViewIssuesPage().goToFirstIssueOnPage();
        String idAndSummary = driver.findElement(By.xpath("//td[@class='bug-summary']")).getText();
        String[] array = idAndSummary.split(": ");
        String issueId = array[0];
        String issueSummary = array[1];

        softAssert.assertThat(issueSummary).isEqualTo("My unique summary");

        driver.findElement(By.xpath("//input[@value='Delete']")).click();
        driver.findElement(By.xpath("//input[@value='Delete Issues']")).click();

        List<WebElement> issuesId = driver.findElements(By.xpath("//td[@class='column-id']"));

        for (WebElement id : issuesId) {
            softAssert.assertThat(id.getText()).isNotEqualTo(issueId);
        }

        softAssert.assertAll();

        mantisSite.getMainPage().logout();
    }
}
