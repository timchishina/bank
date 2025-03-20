package org.example.Migrations;

import org.example.Banking.Operation;
import org.example.Banking.Facades.OperationFacade;
import org.example.Banking.OperationData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class DataImporter {
    protected final OperationFacade operationFacade;

    public DataImporter(OperationFacade operationFacade) {
        this.operationFacade = operationFacade;
    }

    public final void importData(String filePath) {
        try {
            String rawData = readFile(filePath);
            List<OperationData> operations = parseData(rawData);
            saveData(operations);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    private String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath))); // Читаем содержимое файла
    }

    protected abstract List<OperationData> parseData(String rawData); // Разный парсинг в CSV, JSON, YAML

    private void saveData(List<OperationData> operations) {
        for (OperationData op : operations) {
            operationFacade.create(op.getType(), op.getAccountId(), op.getAmount(), op.getDescription(), op.getCategoryId());
        }
        System.out.println("Импортировано " + operations.size() + " операций.");
    }
}
