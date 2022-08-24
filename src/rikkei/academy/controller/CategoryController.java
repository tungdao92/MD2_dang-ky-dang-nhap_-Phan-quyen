package rikkei.academy.controller;

import rikkei.academy.model.Category;
import rikkei.academy.service.category.CategoryServiceIMPL;
import rikkei.academy.service.category.ICategoryService;

import java.util.List;

public class CategoryController {
    private ICategoryService categoryService = new CategoryServiceIMPL();
    public List<Category> showListCategory(){
        return categoryService.findAll();
    }
    public void creteCategory(Category category) {
        categoryService.save(category);
        showListCategory();
    }
    public Category searchCategory(int id) {
        return categoryService.findByID(id);
    }
    public void editCategory(int id, Category category){
        Category category1 = categoryService.findByID(id);
        category1.setName(category.getName());
        showListCategory();
    }
}
