package com.deneme.dao;

import com.deneme.model.Category;
import com.deneme.model.User;

public interface CategoryDAO {
     long createCategory(Category category);

     void deleteCategory(long categoryId);

     Category updateCategory(Category category);


     Category findByIdCategory(long categoryId);


}
