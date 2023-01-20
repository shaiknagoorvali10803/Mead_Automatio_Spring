package com.spring.springselenium.StepDefinitions;

import com.google.common.util.concurrent.Uninterruptibles;
import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.Configuraion.service.ScreenshotService;
import com.spring.springselenium.PageClass.Google.GooglePage;
import com.spring.springselenium.SeleniumUtils.SeleniumUtils;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class GoogleSteps {
    private static Map<Integer,ScenarioContext> contextMap = new HashMap<>();
    private static Map<Integer, Scenario> scenarioMap = new HashMap<>();
    @Autowired
    protected WebDriver driver;
    @Autowired
    protected WebDriverWait wait;
    @LazyAutowired
    protected ScreenshotService screenshotService;
    @Autowired
    public static TestUserDetails testUserDetails;
    @Autowired
    ScenarioContext scenarioContext;
    @LazyAutowired
    private SeleniumUtils utils;
    @LazyAutowired
    private GooglePage googlePage;
    @Autowired
    public GoogleSteps (TestUserDetails testUserDetails)
    {
        this.testUserDetails=testUserDetails;
        }

    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver, this);
        contextMap.put(driver.hashCode(),scenarioContext);
       }

   @Given("I am on the google site")
    public void launchSite() {
        this.googlePage.goTo();
       googlePage.addScreenShot();
        //scenarioContext.getScenario().attach(this.screenshotService.getScreenshot(), "image/png", scenarioContext.getScenario().getName());
        testUserDetails.setUserDetails(new UserDetails("Shaik.Nagoorvali","password"));
       System.out.println("Current Thread Number "+ Thread.currentThread().getThreadGroup() +"thread number"+ Thread.currentThread().getId());
        }
    @When("I enter {string} as a keyword")
    public void enterKeyword(String keyword) {
        this.googlePage.search(keyword);
        googlePage.addScreenShot();
        }

    @Then("I should see search results page")
    public void clickSearch() throws IOException {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(4));
        Assert.assertTrue(this.googlePage.isAt());
        System.out.println("hashcode scenario Context "+scenarioContext.getScenario().hashCode());
        System.out.println("hashcode driver "+driver.hashCode());
        googlePage.addScreenShot();
        //Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    @Then("I should see at least {int} results")
    public void verifyResults(int count) throws InterruptedException, IOException {
        System.out.println("The Username from GoogleTest Class is:" + testUserDetails.getUserDetails().getUsername());
        System.out.println("The Username from GoogleTest Class is:" + testUserDetails.getUserDetails().getPassword());
        Assert.assertTrue(this.googlePage.getCount() >= count);
        utils.singleClick(driver,By.xpath("//a[normalize-space()='Images']"));
        Thread.sleep(3000);
        googlePage.addScreenShot();
        System.out.println("Current Thread Number "+ Thread.currentThread().getThreadGroup() +"thread number"+ Thread.currentThread().getId());
        driver.findElement(By.xpath("//a[normalize-space()='Videos']")).click();
    }
 }
