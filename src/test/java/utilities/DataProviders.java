package utilities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;

public class DataProviders {

    private static final String LOGIN_DATA_PATH =
            "testdata/LoginData.xlsx";
    private static final String CHECKOUT_DATA_PATH =
            "testdata/CheckoutData.xlsx";

    private boolean isExcelFileMissing(String filePath) {
        Path resolvedPath = Paths.get(filePath);
        if (!resolvedPath.isAbsolute()) {
            resolvedPath = Paths.get(System.getProperty("user.dir"), filePath);
        }
        return !Files.exists(resolvedPath);
    }

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {

        if (isExcelFileMissing(LOGIN_DATA_PATH)) {
            return new Object[][] {
                    { ConfigReader.getUsername(), ConfigReader.getPassword() }
            };
        }

        ExcelUtil excel =
                new ExcelUtil(LOGIN_DATA_PATH, "Login");

        Object[][] data = excel.getSheetData();

        excel.closeWorkbook();

        return data;

    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginDataProvider() {

        if (isExcelFileMissing(LOGIN_DATA_PATH)) {
            return new Object[][] {
                    { ConfigReader.getInvalidUsername(), ConfigReader.getInvalidPassword() }
            };
        }

        ExcelUtil excel =
                new ExcelUtil(LOGIN_DATA_PATH, "InvalidLogin");

        Object[][] data = excel.getSheetData();

        excel.closeWorkbook();

        return data;

    }

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutDataProvider() {

        if (isExcelFileMissing(CHECKOUT_DATA_PATH)) {
            return new Object[][] {
                    { "John", "David", "560001" }
            };
        }

        ExcelUtil excel =
                new ExcelUtil(CHECKOUT_DATA_PATH,
                        "Checkout");

        Object[][] data = excel.getSheetData();

        excel.closeWorkbook();

        return data;

    }

}
