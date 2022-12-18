package com.classroom.LMS.Category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface CategoryService {

    public Category create(Category category) throws Exception;
    public Category findByName(String name);

    public List<Category> getAll();

    public void deleteAll();

}
