# Selenium POM Framework Project

This project is a Selenium + TestNG automation framework for SauceDemo.

## Tech Stack

- Java 17
- Maven
- Selenium 4
- TestNG
- WebDriverManager
- ExtentReports

## Project Layout

- `src/main/java/pages` - Page Object classes
- `src/main/java/utilities` - Driver, waits, config, screenshot, data utilities
- `src/test/java/tests` - Test classes
- `src/test/java/listeners` - TestNG listener for reporting/screenshots
- `src/test/java/base` - Test base setup/teardown
- `testng.xml` - Test suite definition

## Prerequisites

- JDK 17
- Maven (or IntelliJ bundled Maven)
- Internet access for WebDriver binaries and SauceDemo site

## Configuration

Edit `src/main/resources/config.properties` if needed:

- `browser=chrome|edge|firefox`
- `headless=true|false`
- credentials and timeouts

## Run Tests

### Using Maven (terminal)

```powershell
Set-Location "C:\Users\AvvariVenkatesh\IdeaProjects\SeleniumPOMFrameworkProject"
mvn clean test
```

### Run a single suite explicitly

```powershell
Set-Location "C:\Users\AvvariVenkatesh\IdeaProjects\SeleniumPOMFrameworkProject"
mvn test -Dsurefire.suiteXmlFiles=testng.xml
```

### Using IntelliJ

1. Open Maven tool window.
2. Run lifecycle goal `test`.
3. Or run `testng.xml` directly as a TestNG suite.

## Reports and Screenshots

- Extent reports are created under path configured by `report.path`.
- Failure screenshots are created under path configured by `screenshot.path`.
- Missing report/screenshot directories are created automatically at runtime.

## Test Data Notes

`DataProviders` use Excel file paths:

- `testdata/LoginData.xlsx`
- `testdata/CheckoutData.xlsx`

If these files are not present, built-in fallback test data is used so tests can still execute.

