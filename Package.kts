import io.appium.java_client.*


class Screen(val app: AppiumDriver<MobileElement>) {

  fun retrieveUpstreamURL(): String {
    val url = app.findElement(
      MobileBy.AndroidUIAutomator("""textStartsWith("Upstream")""")
    )

    return url.getText().split(" ").last()
  }
}

