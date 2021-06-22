package com.christian.productsandcategories.respositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.christian.productsandcategories.models.CategoryProduct;

@Repository
public interface CategoryProductRepository extends CrudRepository <CategoryProduct, Long> {
	List<CategoryProduct> findAll();
}
