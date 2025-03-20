package org.example.Banking;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OperationData {
    private final double amount;
    private final boolean type;
    private final int bankAccount;
    private final String description;
    private final int categoryId;
    public OperationData(boolean type, int bankAccount, double amount, String description, int categoryId) {
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.description = description;
        this.categoryId = categoryId;
        this.type = type;
    }

    public int getAccountId() { return bankAccount; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }

    public boolean getType() { return type; }
    public int getCategoryId() { return categoryId;}

}
