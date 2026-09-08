package reports;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utilities.ConfigReader;

public class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {
        // Prevent object creation
    }

    public static ExtentReports getExtentReports() {

        if (extent == null) {

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());

            String configuredReportDir = ConfigReader.getReportPath();
            Path reportDirectory = Paths.get(configuredReportDir);
            if (!reportDirectory.isAbsolute()) {
                reportDirectory = Paths.get(System.getProperty("user.dir"), configuredReportDir);
            }

            try {
                Files.createDirectories(reportDirectory);
            } catch (Exception e) {
                throw new RuntimeException("Unable to create report directory: " + reportDirectory, e);
            }

            String reportPath = reportDirectory
                    .resolve("AutomationReport_" + timeStamp + ".html")
                    .toString();

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);

            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("SauceDemo Selenium Automation");
            sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);

            // System Information
            extent.setSystemInfo("Application", "SauceDemo");
            extent.setSystemInfo("Framework", "Selenium + TestNG");
            extent.setSystemInfo("Language", "Java");
            extent.setSystemInfo("Automation Engineer", "Your Name");
            extent.setSystemInfo("Operating System",
                    System.getProperty("os.name"));
            extent.setSystemInfo("Java Version",
                    System.getProperty("java.version"));

        }

        return extent;

    }

}