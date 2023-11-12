package com.spring.springselenium.Utils;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.Configuraion.annotation.Page;
import com.spring.springselenium.StepDefinitions.ScenarioContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Page
public class ScreenshotUtils {
    public static Logger logger = LoggerFactory.getLogger(ScreenshotUtils.class);
    private static Map<Integer,ScenarioContext> contextMap = new HashMap<>();
    @Autowired
    WebDriver driver;
    @Autowired
    ScenarioContext scenarioContext;
    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver, this);
        contextMap.put(driver.hashCode(),scenarioContext);
    }

    public void insertScreenshot(String screenshotTitle){
        if(!scenarioContext.getScenario().isFailed() && scenarioContext.getScenario() !=null ){
            try{
                scenarioContext.getScenario().attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png", "screenShot");
            }
            catch (Exception e){
                logger.error("failed to add screenshot because scenario already failed");
            }
        }
    }
}
