package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelUtil(String excelPath, String sheetName) {

        Path resolvedPath = resolvePath(excelPath);

        try {
            if (!Files.exists(resolvedPath)) {
                throw new RuntimeException("Excel file not found: " + resolvedPath);
            }

            FileInputStream fis = new FileInputStream(resolvedPath.toFile());
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            fis.close();

            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName + " in file " + resolvedPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to load Excel file: " + resolvedPath, e);
        }

    }

    private Path resolvePath(String filePath) {
        Path path = Paths.get(filePath);
        if (!path.isAbsolute()) {
            path = Paths.get(System.getProperty("user.dir"), filePath);
        }
        return path;
    }

    /**
     * Returns total rows (excluding header)
     */
    public int getRowCount() {

        return sheet.getLastRowNum();

    }

    /**
     * Returns total columns
     */
    public int getColumnCount() {
        Row row = sheet.getRow(0);

        if (row == null) {
            return 0;
        }

        return row.getLastCellNum();

    }

    /**
     * Returns data from a cell
     */
    public String getCellData(int rowNum, int colNum) {
        DataFormatter formatter = new DataFormatter();

        Row row = sheet.getRow(rowNum);

        if (row == null) {
            return "";
        }

        Cell cell = row.getCell(colNum);

        if (cell == null) {
            return "";
        }

        return formatter.formatCellValue(cell);

    }

    /**
     * Returns complete sheet data
     */
    public Object[][] getSheetData() {

        int rows = getRowCount();

        int cols = getColumnCount();

        Object[][] data = new Object[rows][cols];

        for (int i = 1; i <= rows; i++) {

            for (int j = 0; j < cols; j++) {

                data[i - 1][j] = getCellData(i, j);

            }

        }

        return data;

    }

    /**
     * Close workbook
     */
    public void closeWorkbook() {

        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to close workbook", e);
        }

    }

}