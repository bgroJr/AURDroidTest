import io.appium.java_client.*
import org.openqa.selenium.support.ui.*


class Screen(val app: AppiumDriver<MobileElement>) {

  fun selectCriteria(criteria: String): Screen {
    val searchCriteria = app.findElement(
      MobileBy.AndroidUIAutomator("""text("$criteria")""")
    )
    searchCriteria.click()

    return this
  }

  fun enterTerm(term: String): Screen {
    val searchTerm = app.findElement(
      MobileBy.AndroidUIAutomator("""text("Package search")""")
    )
    searchTerm.click()
    searchTerm.sendKeys(term)

    return this
  }

  fun search(): Results.Screen {
    app.executeScript("mobile:performEditorAction", mapOf("action" to "search"))

    WebDriverWait(app, 10).until(
      ExpectedConditions.visibilityOfElementLocated(
        MobileBy.AndroidUIAutomator(
          """className("androidx.recyclerview.widget.RecyclerView")"""
        )
      )
    )

    return Results.Screen(app)
  }
}

