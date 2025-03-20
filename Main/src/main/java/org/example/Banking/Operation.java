package org.example.Banking;

import org.example.Migrations.ExportVisitor;

import  java.time.LocalDateTime;

public class Operation {
    private int id;
    private boolean type;
    private int bank_account_id;
    private double sum;
    private LocalDateTime date;
    private String description;
    private int category_id;

    public void setId(int id) { this.id = id; }
    public void setType(boolean type) { this.type = type;}
    public void setCategory_id(int category_id) { this.category_id = category_id; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public void setDescription(String description) { this.description = description; }
    public void setSum(double sum) { this.sum = sum;}
    public void setBank_account_id(int bank_account_id) { this.bank_account_id = bank_account_id; }

    public boolean getType() { return type; }
    public String getDescription() { return description; }
    public int getBank_account_id() { return bank_account_id; }
    public int getCategory_id() { return category_id; }
    public int getId() { return id; }
    public LocalDateTime getDate() { return date; }
    public double getSum() { return sum; }
    public String accept(ExportVisitor visitor) { return visitor.visit(this); }
}
