package com.spring.springselenium.PageClass.Google;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.spring.springselenium.Configuraion.annotation.LazyAutowired;
import com.spring.springselenium.Configuraion.annotation.Page;
import com.spring.springselenium.PageClass.Base;
import com.spring.springselenium.StepDefinitions.ScenarioContext;
import com.spring.springselenium.Utils.ScreenshotUtils;
import com.spring.springselenium.XLUtils.XLUtility_xlsx;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Page
public class GooglePage extends Base {

    @LazyAutowired
    ScreenshotUtils screenshotUtils;

    @Value("${application.url}")
    private String url;
    @FindBy(name = "q")
    private WebElement searchBox;
    @FindBy(css = "div.g")
    private List<WebElement> results;

    @FindBy(name = "btnK")
    private List<WebElement> searchBtns;
    public void goTo(){
        this.driver.navigate().to(url);
    }
    public void search(final String keyword){
        this.searchBox.sendKeys(keyword);
        this.searchBox.sendKeys(Keys.TAB);
        this.searchBtns
                .stream()
                .filter(e -> e.isDisplayed() && e.isEnabled())
                .findFirst()
                .ifPresent(WebElement::click);
    }
    public int getCount(){
        return this.results.size();
    }
    public boolean isAt() {
        return this.wait.until((d) -> this.searchBox.isDisplayed());
    }

}
