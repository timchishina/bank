package org.example;

import java.io.FileWriter;
import java.io.IOException;

class FileUtil {
    public static void writeToFile(String filePath, String data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
