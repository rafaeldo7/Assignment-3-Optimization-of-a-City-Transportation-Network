package com.example.utils;

import java.io.FileWriter;
import java.io.IOException;

public class CSVExporter {
    public static void writeComparison(String filePath, String[][] data) {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (String[] row : data) {
                fw.write(String.join(",", row));
                fw.write("\n");
            }
            System.out.println("✅ CSV report saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
