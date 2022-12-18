package com.classroom.LMS.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) throws Exception {

        if(this.categoryRepository.findByName(category.getName()) != null){
            throw new Exception("category already exists");
        }

        return this.categoryRepository.save(category);
    }

    @Override
    public Category findByName(String name) {
        return this.categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public void deleteAll() {
        this.categoryRepository.deleteAll();
    }
}
