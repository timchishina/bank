package org.example.Migrations;

import java.util.ArrayList;
import java.util.List;

import org.example.Banking.Facades.OperationFacade;
import org.example.Banking.OperationData;
import org.json.JSONArray;
import org.json.JSONObject;

public class JSONImporter extends DataImporter {
    public JSONImporter(OperationFacade operationFacade) {
        super(operationFacade);
    }

    @Override
    protected List<OperationData> parseData(String rawData) {
        List<OperationData> operationsData = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(rawData);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonOp = jsonArray.getJSONObject(i);
            boolean type = jsonOp.getBoolean("type");
            int bankAccount = jsonOp.getInt("bankAccountId");
            int categoryId = jsonOp.getInt("categoryId");
            int accountId = jsonOp.getInt("accountId");
            double amount = jsonOp.getDouble("amount");
            String description = jsonOp.getString("description");
            operationsData.add(new OperationData(type, bankAccount, amount, description, categoryId));
        }
        return operationsData;
    }
}
