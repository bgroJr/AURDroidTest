import org.junit.*
import org.junit.runner.*
import org.junit.runners.*

import io.appium.java_client.*
import org.openqa.selenium.remote.*


@RunWith(JUnit4::class)
class UITest {

  lateinit var home: Home.Screen

  @Before
  fun setUp() {
    val app = AppiumDriver<MobileElement>(
      java.net.URL("http://localhost:4723/wd/hub"),
      DesiredCapabilities(
        mapOf(
          "platformName" to "Android",
          "deviceName" to "Android Emulator",
          "automationName" to "UiAutomator2",
          "appPackage" to "com.rascarlo.aurdroid",
          "appActivity" to ".MainActivity",
          "skipDeviceInitialization" to true,
          "skipServerInstallation" to true,
          "noSign" to true
        )
      )
    )

    home = Home.Screen(app)
  }

  @Test
  fun `search for package to retrieve upstream URL`() {
    val results = home.selectCriteria("Name")
                      .enterTerm("spotify")
                      .search()

    val pkg = results.scrollTo("spotify")
                     .choose("spotify")

    val upstreamURL = pkg.retrieveUpstreamURL()
    Assert.assertEquals(upstreamURL, "https://www.spotify.com")
  }

  @After
  fun tearDown() {
    home.app.quit()
  }
}

