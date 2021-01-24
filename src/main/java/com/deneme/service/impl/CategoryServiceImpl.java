package com.deneme.service.impl;

import com.deneme.dao.CategoryDAO;
import com.deneme.model.Category;
import com.deneme.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;


    @Override
    public long createCategory(Category category) {
        return categoryDAO.createCategory(category);
    }

    @Override
    public void deleteCategory(long categoryId) {
        categoryDAO.deleteCategory(categoryId);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryDAO.updateCategory(category);
    }
}
