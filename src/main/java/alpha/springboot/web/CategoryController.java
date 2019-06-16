package alpha.springboot.web;
import alpha.springboot.dao.CategoryDAO;
import alpha.springboot.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.SortedMap;


@Controller
@RestController
public class CategoryController {
	@Autowired
	CategoryDAO categoryDAO;
	
//    @RequestMapping("/listCategory")
    @GetMapping("/categories")
    public String listCategory(Model m,@RequestParam(value = "start", defaultValue = "0") int start,@RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
    	start = start<0?0:start;
    	
    	Sort sort = new Sort(Sort.Direction.DESC, "id");
    	Pageable pageable = new PageRequest(start, size, sort);
    	Page<Category> page =categoryDAO.findAll(pageable);
    	

    	System.out.println(page.getNumber());
    	System.out.println(page.getNumberOfElements());
    	System.out.println(page.getSize());
    	System.out.println(page.getTotalElements());
    	System.out.println(page.getTotalPages());
    	
    	m.addAttribute("page", page);
    	
        return "listCategory";
    }



//	@RequestMapping("/addCategory")
//	@PostMapping("/categories")
//    public String addCategory(Category c) throws Exception {
//    	categoryDAO.save(c);
//    	return "redirect:/categories";
//    }
//    @RequestMapping("/deleteCategory")
	@DeleteMapping("/categories/{id}")
    public String deleteCategory(Category c) throws Exception {
    	categoryDAO.delete(c);
    	return "redirect:/categories";
    }
//    @RequestMapping("/updateCategory")
	@PutMapping("/categories/{id}")
    public String updateCategory(Category c) throws Exception {
    	categoryDAO.save(c);
    	return "redirect:/categories";
    }
    @GetMapping("/categories/{id}")
    public String getCategory(@PathVariable("id") int id, Model m) throws Exception {
    	Category c= categoryDAO.getOne(id);
    	m.addAttribute("c", c);
    	return "editCategory";
    }
//==================json
	@GetMapping("/category")
	public List<Category> listCategory(@RequestParam(value = "start",defaultValue = "0")int start,@RequestParam(value = "size",defaultValue = "5")int size)throws Exception{
    	start=start<0?0:start;
		Sort sort=new Sort(Sort.Direction.DESC,"id");
		Pageable pageable=new PageRequest(start,size,sort);
		Page<Category> page=categoryDAO.findAll(pageable);
		return page.getContent();
	}

	@GetMapping("/category/{id}")
	public Category getCategory(@PathVariable("id") int id) throws Exception{
    	Category c=categoryDAO.getOne(id);
		System.out.println(c);
		return c;
	}

	@PutMapping("/category")
	public void addCategory(@RequestBody Category category) throws Exception{

		System.out.println("springboot get browser use JSON submit data:"+category);
	}


}

