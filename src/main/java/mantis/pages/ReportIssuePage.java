package mantis.pages;

import mantis.utils.TestUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReportIssuePage {

    private final WebDriver driver;

    private final WebDriverWait wait;
    @FindBy(id = "summary")
    private WebElement summaryField;
    @FindBy(id = "description")
    private WebElement descriptionField;
    @FindBy(xpath = "//span[@class='required pull-right']")
    private WebElement pageFooter;
    @FindBy(xpath = "//input[@value='Submit Issue']")
    private WebElement submitIssueButton;

    public ReportIssuePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30, 500);
        PageFactory.initElements(driver, this);
    }

    public void fillSummaryField(String summary) {
        summaryField.sendKeys(summary);
        summaryField.sendKeys(Keys.ENTER);
    }

    public void fillDescriptionField(String description) {
        descriptionField.sendKeys(description);
        descriptionField.sendKeys(Keys.ENTER);
    }

    public void scrollToTableFooter() {
        TestUtils.scrollToElement(driver, pageFooter);
    }

    public void clickSubmitIssueButton() {
        submitIssueButton.click();
    }
}
