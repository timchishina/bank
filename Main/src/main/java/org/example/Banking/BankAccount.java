package org.example.Banking;

public class BankAccount {
    private int id;
    private String name;
    private double balance;
    public int getId() { return id; }
    public double getBalance() { return balance; }
    public String getName() { return name; }
    public void setBalance(double balance) { this.balance = balance; }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}

