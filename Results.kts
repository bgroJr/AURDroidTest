import io.appium.java_client.*
import io.appium.java_client.touch.*
import io.appium.java_client.touch.offset.*
import io.appium.java_client.android.*

import org.openqa.selenium.*
import org.openqa.selenium.support.ui.*


class Screen(val app: AppiumDriver<MobileElement>) {

  val swipes = 15

  val x1 = app.manage().window().getSize().getWidth() / 2
  val y1 = (app.manage().window().getSize().getHeight() * 0.9).toInt()
  val bottom = PointOption.point(x1, y1)

  val x2 = x1
  val y2 = (app.manage().window().getSize().getHeight() * 0.2).toInt()
  val top = PointOption.point(x2, y2)

  val twoSeconds = java.time.Duration.ofSeconds(2)
  val delay = WaitOptions.waitOptions(twoSeconds)

  fun scrollTo(result: String): Screen {
    for (i in 1..swipes) {
      AndroidTouchAction(app)
        .press(bottom)
        .waitAction(delay)
        .moveTo(top)
        .release()
        .perform()

      val resultCard = try {
        app.findElement(MobileBy.AndroidUIAutomator("""text("$result")"""))
      }
      catch (e: NoSuchElementException) {
        null
      }

      if (resultCard != null) {
        break
      }
    }

    return this
  }

  fun choose(result: String): Package.Screen {
    val resultCard = app.findElement(MobileBy.AndroidUIAutomator("""text("$result")"""))
    resultCard.click()

    WebDriverWait(app, 5).until(
      ExpectedConditions.visibilityOfElementLocated(
        MobileBy.id("com.rascarlo.aurdroid:id/info_result_body_include")
      )
    )

    return Package.Screen(app)
  }
}

