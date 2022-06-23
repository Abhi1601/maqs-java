/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.utilities.helper.Config;
import com.cognizantsoftvision.maqs.utilities.helper.ConfigSection;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;

import java.util.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The Config Unit tests for playwright config content.
 */
public class PlaywrightConfigUnitTest {

  /**
   * String placeholder for "Browser".
   */
  private static final String browser = "Browser";

  /**
   * Get playwright section.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void getPlaywrightSection() {
    Map<String, String> config = Config.getSection(ConfigSection.PLAYWRIGHT_MAQS);
    Assert.assertNotNull(config);
  }

  /**
   * Get expected WebBase configuration.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void testGetBrowser() {
    Config.addTestSettingValues(Collections.singletonMap(browser, "Chrome"),
        ConfigSection.PLAYWRIGHT_MAQS, true);
    Assert.assertEquals(PlaywrightConfig.getBrowserName(), "Chrome");
  }

  /**
   * Data provider for config browser name test.
   *
   * @return browser name data
   */
//  @DataProvider (name = "browserType")
//  public Object[] browserType() {
//    List<String> data = new ArrayList<>();
//    data.add("Chromium");
//    data.add("Firefox");
//    data.add("Edge");
//    data.add("Webkit");
//    data.add("Chrome");
//     return new Object[] {data};
//  }

  /**
   * Test the config browser name.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void configBrowserName() {
    List<String> browserNames = new ArrayList<>();
    browserNames.add("Chromium");
    browserNames.add("Firefox");
    browserNames.add("Edge");
    browserNames.add("Webkit");
    browserNames.add("Chrome");

    for (String browserName : browserNames) {
      Config.addTestSettingValues(Collections.singletonMap(browser, browserName),
          ConfigSection.PLAYWRIGHT_MAQS, true);
      Assert.assertEquals(PlaywrightConfig.getBrowserName(), browserName);
    }
  }

  /**
   * Make sure error correct error is thrown if we use a bad browser name.
   */
  @Test(groups = TestCategories.PLAYWRIGHT, expectedExceptions = IllegalArgumentException.class)
  public void configBadBrowserName() {
    Config.addTestSettingValues(Collections.singletonMap(browser, "IE"), ConfigSection.PLAYWRIGHT_MAQS, true);
    PlaywrightBrowser type = PlaywrightConfig.getBrowserType();
    Assert.fail("IE returned type: " + type);
  }

  /**
   * Test the config browser enums.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void configBrowserEnum() {
    for (PlaywrightBrowser currentBrowser : PlaywrightBrowser.class.getEnumConstants()) {
      Config.addTestSettingValues(Collections.singletonMap(browser, currentBrowser.name()), ConfigSection.PLAYWRIGHT_MAQS, true);
      Assert.assertEquals(currentBrowser, PlaywrightConfig.getBrowserType());
    }
  }

  /**
   * Get expected UseProxy configuration.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void getUseProxy() {
    Assert.assertFalse(PlaywrightConfig.getUseProxy());
  }

  /**
   * Get expected proxy address configuration.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void getProxyAddress() {
    Assert.assertEquals(PlaywrightConfig.getProxyAddress(), "http://localhost:8002");
  }

  /**
   * Get expected proxy address configuration.
   */
  @Test(groups = TestCategories.PLAYWRIGHT, singleThreaded = true)
  public void getWindowSize() {
    String oldValue = Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS, "BrowserSize");
    
    try {
      Config.addTestSettingValues(Collections.singletonMap("BrowserSize", "600x900"),
      ConfigSection.PLAYWRIGHT_MAQS, true);
      Assert.assertEquals(PlaywrightConfig.getBrowserSize().getHeight(), 900);
      Assert.assertEquals(PlaywrightConfig.getBrowserSize().getWidth(), 600);
    }
    finally{
      Config.addTestSettingValues(Collections.singletonMap("BrowserSize", oldValue),
      ConfigSection.PLAYWRIGHT_MAQS, true);
    }
  }
}
