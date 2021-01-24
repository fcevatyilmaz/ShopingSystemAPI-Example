package com.deneme.controller;

import com.deneme.model.Category;
import com.deneme.model.User;
import com.deneme.service.CategoryService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/categoryApi")
@Controller
public class CategoryController {

    private static final Logger logger = Logger.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/newCategory", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    long newCategory(@RequestBody Category category) {
        long id = categoryService.createCategory(category);
        logger.info("Category adding. id : " + id);
        return id;
    }

    @RequestMapping(value = "/editCategory/{categoryId}", method = RequestMethod.PUT, consumes = "application/json")
    public @ResponseBody
    Category updateCategory(@PathVariable(value = "categoryId") long id, @RequestBody Category category) {
        Category categoryTemp = new Category();
        categoryTemp.setCategoryId(category.getCategoryId());
        categoryTemp.setName(category.getName());
        logger.info("Category updating. : " + categoryTemp);
        return categoryService.updateCategory(categoryTemp);

    }
    @RequestMapping(value = "/removeCategory/{categoryId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteCategory(@PathVariable(value = "categoryId") long id) {
        logger.info("User deleting. Id : " + id);
        categoryService.deleteCategory(id);
    }


}
