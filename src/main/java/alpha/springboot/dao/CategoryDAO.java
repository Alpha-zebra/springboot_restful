package alpha.springboot.dao;

import alpha.springboot.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoryDAO extends JpaRepository<Category,Integer>{

}
