package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Specify the report file path
            String reportPath = "path/to/save/extent-report.html";

            // Create an ExtentSparkReporter instance with the report path
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            // Customize the report settings if needed
            // ...

            // Create the ExtentReports instance
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }
}