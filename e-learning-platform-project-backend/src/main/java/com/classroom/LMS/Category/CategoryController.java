package com.classroom.LMS.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("create")
    public Category create(@RequestBody Category category) throws Exception {
        return this.categoryService.create(category);
    }

    @GetMapping("all")
    public List<Category> getAll(Category category){
        return this.categoryService.getAll();
    }

    @DeleteMapping("deleteall")
    public void deleteAll(Category category){
        this.categoryService.deleteAll();
    }


}
