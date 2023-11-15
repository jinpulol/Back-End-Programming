package sof03.lfg.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import sof03.lfg.domain.Category;
import sof03.lfg.domain.CategoryRepository;

// Controller for categories
@Controller
public class CategoryController {

    // Injecting repositories
    @Autowired
    private CategoryRepository categoryRepository;

    // Show all categories
    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());

        return "categorylist";
    }
    
    // Add new category
    @GetMapping("/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }

    // Save new category
    @PostMapping("/savecategory")
    public String saveCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:categories";
    }

    // Delete category
    @GetMapping("/deletecategory/{id}")
    public String deleteCategory(Long categoryId, Model model) {
        categoryRepository.deleteById(categoryId);
        return "redirect:../categories";
    }
    
}