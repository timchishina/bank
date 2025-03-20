package org.example.Banking;

import org.example.Migrations.ExportVisitor;



public class Category {
    int id;
    boolean type;
    String name;
    public int getId() { return id; }
    public String getName() { return name; }
    public boolean getType() { return type; }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(boolean type) { this.type = type; }
    public String accept(ExportVisitor visitor) { return visitor.visit(this); }
}
