package com.deneme.service;

import com.deneme.model.Category;
import com.deneme.model.User;

import java.util.List;

;


public interface CategoryService {

    long createCategory(Category category);

    void deleteCategory(long categoryId);

    Category updateCategory(Category category);






}