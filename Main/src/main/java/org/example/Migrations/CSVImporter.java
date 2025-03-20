package org.example.Migrations;

import org.example.Banking.Facades.OperationFacade;
import org.example.Banking.Operation;
import org.example.Banking.OperationData;
import org.example.Services.OperationService;

import java.util.ArrayList;
import java.util.List;

public class CSVImporter extends DataImporter {
    public CSVImporter(OperationFacade operationFacade) {
        super(operationFacade);
    }

    @Override
    protected List<OperationData> parseData(String rawData) {
        List<OperationData> operationsData = new ArrayList<>();
        String[] lines = rawData.split("\n");

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();

            // Пропускаем первую строку, если это заголовок
            if (i == 0 && line.toLowerCase().contains("type")) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length < 5) continue;

            try {
                boolean type = Boolean.parseBoolean(parts[0].trim());
                int bankAccount = Integer.parseInt(parts[1].trim());
                double amount = Double.parseDouble(parts[2].trim());
                String description = parts[3].trim();
                int categoryId = Integer.parseInt(parts[4].trim());

                operationsData.add(new OperationData(type, bankAccount, amount, description, categoryId));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка парсинга строки: " + line);
            }
        }

        return operationsData;
    }


}
