package com.leyou.item.service.impl;

import com.leyou.item.dao.CategoryRepository;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * buscar el listado de category con el parámetro parentId
     *
     * @param pid
     * @return
     */
    @Override
    public List<Category> queryCategoriesByPid(Long pid) {
        return this.categoryRepository.findByParentId(pid);
    }

    /**
     * Añadir una category nueva
     *
     * @param category
     * @return
     */
    @Transactional
    @Override
    public void save(Category category) {
        category.setId(null);
        this.categoryRepository.save(category);
        Optional<Category> optional = this.categoryRepository.findById(category.getParentId());
        if (optional.isPresent()) {
            Category parent = optional.get();
            parent.setIsParent(true);
            this.categoryRepository.save(parent);
        }
    }

    /**
     * eliminar una category con el parámetro cid
     * Estructura de tb_category: cid1, cid2, cid3
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void deleteCategory(Long id) {

        Optional<Category> optional = this.categoryRepository.findById(id);
        if (optional.isPresent()){
            Category category = optional.get();

            // Evalúa si el resultado es Parent
            if (!category.getIsParent()){
                //No es Parent
                //1. Evalúa si hay más childNode bajo el mismo parentNodes
                List<Category> childNodes = this.categoryRepository.findByParentId(category.getParentId());
                if (childNodes.size() == 1){
                    //2.1 No -> Eliminar los datos en tb_category_brand
                    this.categoryRepository.deleteCategoryAndBrand(category.getId());
                    //2.1.1 cambiar su parentNode(isParent->false)
                    Optional<Category> optionalParent = this.categoryRepository.findById(category.getParentId());
                    if (optionalParent.isPresent()){
                        Category parent = optionalParent.get();
                        parent.setIsParent(false);
                        this.categoryRepository.save(parent);
                    }
                    //2.1.2 eliminar el resultado mismo en tb_category
                    this.categoryRepository.deleteById(category.getId());
                }else {
                    //2.2 Yes( hay más childNode bajo el mismo parentNodes)
                    //2.2.1 eliminar los datos en tb_category_brand
                    this.categoryRepository.deleteCategoryAndBrand(category.getId());
                    //2.2.2 eliminar el resultado mismo en tb_category
                    this.categoryRepository.deleteById(category.getId());
                }
            }else {
                //Es Parent
                //crear un nodesList para guardar todos los nodes
                List<Category> nodesList = new ArrayList<>();
                this.queryAllNodes(category, nodesList);
                //crear un leafNodesList para guardar todos los leafNodes(cid3)
                List<Category> leafNodesList = new ArrayList<>();
                this.queryAllLeafNodes(category, leafNodesList);

                //forEach nodesList, eliminar todos los nodesList->tb_category
                nodesList.forEach(node -> this.categoryRepository.deleteById(node.getId()));
                //forEach leafList, eliminar todos los cid3->tb_category_brand
                leafNodesList.forEach(leafNode -> this.categoryRepository.deleteCategoryAndBrand(leafNode.getId()));
            }
        }
    }

    /**
     * editar un category
     * @param id: cid
     * @param name
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCategory(Long id, String name) {
        //buscar category con el parámetro id
        Optional<Category> optional = this.categoryRepository.findById(id);
        if (optional.isPresent()){
            //En caso de que el category existe, actualiza el name y guarda el cambio en db.
            Category category = optional.get();
            category.setName(name);
            this.categoryRepository.save(category);
        }
    }

    /**
     * buscar category con el parámetro brandId
     * @param bid
     * @return
     */
    @Override
    public List<Category> queryCategoriesByBrandId(Long bid) {
       return this.categoryRepository.findCategoriesByBrandId(bid);
    }

    /**
     * Coleccionar todos los childNodes en Arraylist para eliminarlos en tb_category
     * @param category
     * @param list
     */
    public void queryAllNodes(Category category, List<Category> list){
        Optional<Category> optional = this.categoryRepository.findById(category.getId());
        if (optional.isPresent()){
            Category nodeCategory = optional.get();
            //1. añadir primero el resultado en Arraylist
            list.add(nodeCategory);
            //2. buscar todos los childNodes bajo el resultado
            List<Category> categories = this.categoryRepository.findByParentId(nodeCategory.getId());
            //3. foreach todos los childNodes
            categories.forEach(childNodeCategory -> {
                //childNode: isParent->true, sigue buscando con recursion
                if (childNodeCategory.getIsParent()){
                    this.queryAllNodes(childNodeCategory, list);
                }else {
                    //childNode: isParent->false, añadirlo a Arraylist
                    list.add(childNodeCategory);
                }
            });
        }
    }

    /**
     * Coleccionar todos los leafNodes(cid3) en un list para eliminarlos en tb_category_brand
     * @param category
     * @param list
     */
    public void queryAllLeafNodes(Category category, List<Category> list){
        Optional<Category> optional = this.categoryRepository.findById(category.getId());
        if (optional.isPresent()){
            Category nodeCategory = optional.get();
            //evalúa si el resultado es leafNode(cid3)
            if (!nodeCategory.getIsParent()){
                //Yes-> añadirlo a Arraylist
                list.add(nodeCategory);
            }else {
                //No, no es leafNode(cid3)
                //buscar su childNodes
                List<Category> categories= this.categoryRepository.findByParentId(nodeCategory.getId());
                //forEach tods los childNodes -> búsqueda con recursion
                categories.forEach(childCategory -> {
                    this.queryAllLeafNodes(childCategory, list);
                });
            }
        }
    }

    /**
     * query category names by category's ids
     * @param ids
     * @return
     */
    public List<String> queryNamesByIds(List<Long> ids){
        List<Category> categories = this.categoryRepository.findAllById(ids);
        return categories.stream().map(category -> category.getName()).collect(Collectors.toList());
    }

    /**
     * Buscar todos los niveles de categoría con cid3
     * @param cid3
     * @return
     */
    @Override
    public List<Category> queryAllCategoryLevelByCid3(Long cid3) {
        Optional<Category> optional = this.categoryRepository.findById(cid3);
        if (optional.isPresent()){
            Category c3 = optional.get();
            Optional<Category> optionalC2 = this.categoryRepository.findById(c3.getParentId());
            if (optionalC2.isPresent()){
                Category c2 = optionalC2.get();
                Optional<Category> optionalC1 = this.categoryRepository.findById(c2.getParentId());
                Category c1 = optionalC1.orElse(null);
                return Arrays.asList(c1, c2, c3);
            }
        }
        return null;
    }
}
