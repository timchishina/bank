package org.example.Banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    public static List<BankAccount> accounts;
    public static List<Category> categories;
    public static Map<Integer, List<Operation>> operations;
    public Bank(){
        this.accounts = new ArrayList<BankAccount>();
        this.categories = new ArrayList<Category>();
        this.operations = new HashMap<Integer, List<Operation>>();
    }
}
