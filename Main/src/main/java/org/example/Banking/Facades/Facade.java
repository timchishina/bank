package org.example.Banking.Facades;

import org.example.Banking.Category;

public interface Facade {
    void change();
    void delete();

    void change(Category category, boolean Type, String Name);
}
