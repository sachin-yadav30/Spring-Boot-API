package org.gn.udip.repository;

import org.gn.udip.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<CategoryModel,Long>{
	
}
