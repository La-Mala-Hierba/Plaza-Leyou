package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * buscar el listado de category con el parámetro parentId
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(
            @RequestParam(value = "pid", defaultValue = "0") Long pid){
            if (pid == null || pid < 0){
                // 400: invalid parameter
                // return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                return ResponseEntity.badRequest().build();
            }
            List<Category> categories = this.categoryService.queryCategoriesByPid(pid);
            if (CollectionUtils.isEmpty(categories)){ // judge if the collection is null
                // 404
                return ResponseEntity.notFound().build();
            }
            //200 query successful
            return ResponseEntity.ok(categories);
    }

    /**
     * Añadir una category nueva
     * @param category
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveCategory(@RequestBody Category category){
        //System.out.println(category);
        this.categoryService.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * eliminar una category con el parámetro cid
     * @param id: cid
     * @return
     */
    @DeleteMapping("cid/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable ("id") Long id){
        if (id == null || id < 0){
            return ResponseEntity.badRequest().build();
        }
        this.categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * editar un category
     * @param id: cid
     * @param name
     * @return
     */
    @PutMapping("{id}/{name}")
    public ResponseEntity<Void> updateCategory(@PathVariable ("id") Long id, @PathVariable("name") String name){
        if ((id == null || id < 0) && StringUtils.isBlank(name)){
            return ResponseEntity.badRequest().build();
        }
        this.categoryService.updateCategory(id, name);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * buscar category con el parámetro brandId
     * @param bid
     * @return
     */
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryCategoriesByBrandId(@PathVariable("bid") Long bid){
        if ((bid == null || bid < 0)){
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = this.categoryService.queryCategoriesByBrandId(bid);
        if (CollectionUtils.isEmpty(categories)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping
    public ResponseEntity<List<String>> queryNamesByIds(@RequestParam("ids") List<Long> ids){
        List<String> names = this.categoryService.queryNamesByIds(ids);
        if (CollectionUtils.isEmpty(names)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(names);
    }

    /**
     * query all categoryNames by cid3
     * @param cid3
     * @return
     */
    @GetMapping("all/level/{id}")
    public ResponseEntity<List<Category>> queryAllCategoryLevelByCid3(@PathVariable("id") Long cid3){
        if (cid3 == null || cid3 < 0){
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = this.categoryService.queryAllCategoryLevelByCid3(cid3);
        if (CollectionUtils.isEmpty(categories)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categories);
    }
}
