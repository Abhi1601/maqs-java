/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.AutomationPageModel;
import com.cognizantsoftvision.maqs.selenium.factories.UIFindFactory;
import com.cognizantsoftvision.maqs.selenium.factories.UIWaitFactory;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The UI Find Factory unit test class.
 */
public class UIFindFactoryUnitTest extends BaseSeleniumTest {

  /**
   * The Automation Page Model.
   */
  AutomationPageModel automationPageModel;

  /**
   * Error string templates for assertion failures.
   */
  private static final String assertNotNullErrorTemplate = "The %s was null when it was expected to not be.";

  /**
   * Sets up the page models for the test.
   */
  public UIFind setUp() {
    automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    return UIFindFactory.getFind(this.getWebDriver());
  }

  /**
   * Test get ui find with element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetUIFindWithElement() {
    setUp();
    automationPageModel.open(automationPageModel.testSiteAutomationUrl);
    WebElement elementDriver = UIWaitFactory
        .getWaitDriver(automationPageModel.getWebDriver())
        .waitForClickableElement(automationPageModel.automationShowDialog1);

    UIFind findWithElement = UIFindFactory.getFind(elementDriver);
    Assert.assertNotNull(findWithElement,
        String.format(assertNotNullErrorTemplate, "findWithElement"));
  }

  /**
   * Test get ui find with driver.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetUIFindWithDriver() {
    UIFind findWithWebDriver = UIFindFactory.getFind(this.getWebDriver());
    Assert.assertNotNull(findWithWebDriver,
        String.format(assertNotNullErrorTemplate, "findWithWebDriver"));
  }
}
