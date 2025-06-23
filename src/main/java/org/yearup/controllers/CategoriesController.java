package org.yearup.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

//added these imports below
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/categories")
@CrossOrigin

// add the annotations to make this a REST controller
// add the annotation to make this controller the endpoint for the following url
    // http://localhost:8080/categories
// add annotation to allow cross site origin requests
public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;


    // create an Autowired controller to inject the categoryDao and ProductDao

    // add the appropriate annotation for a get action

    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao)
    {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }
    @GetMapping("")
    public List<Category> getAll()
    {
        return categoryDao.getAllCategories();// find and return all categories
    }
    @GetMapping("{id}")
    // add the appropriate annotation for a get action
    public Category getById(@PathVariable int id)
    {
        return categoryDao.getById(id);// get the category by id
    }

    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        return productDao.listByCategoryId(categoryId);
        // get a list of product by categoryId

    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)

    // add annotation to call this method for a POST action
    // add annotation to ensure that only an ADMIN can call this function
    public Category addCategory(@RequestBody Category category)
    {
        categoryDao.create(category);
        return category;// insert the category
    }
    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    public void updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        categoryDao.update(id, category);// update the category by id
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    // add annotation to call this method for a DELETE action - the url path must include the categoryId
    // add annotation to ensure that only an ADMIN can call this function
    public void deleteCategory(@PathVariable int id)
    {
        categoryDao.delete(id);// delete the category by id
    }
}
