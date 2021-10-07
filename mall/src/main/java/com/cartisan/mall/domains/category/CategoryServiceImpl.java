package com.cartisan.mall.domains.category;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static com.cartisan.mall.domains.consts.MallConst.ROOT_PARENT_ID;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * @author colin
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAllCategories() {
        final List<Category> categories = categoryRepository.findByStatus(1);

        final List<CategoryDTO> categoryDTOList = categories.stream()
                .filter(category -> category.getParentId().equals(ROOT_PARENT_ID))
                .map(this::category2CategoryDTO)
                .sorted(Comparator.comparing(CategoryDTO::getSortOrder).reversed())
                .collect(toList());

        findSubCategory(categoryDTOList, categories);
        return categoryDTOList;
    }

    @Override
    public Set<Long> findSubCategoryIds(Long id) {
        final List<Category> categories = categoryRepository.findByStatus(1);

        return findSubCategoryIds(id, categories);
    }

    private Set<Long> findSubCategoryIds(Long id, List<Category> categories) {
        final Set<Long> categoryIds = categories.stream()
                .filter(category -> category.getParentId().equals(id))
                .map(Category::getId)
                .collect(toSet());

        categoryIds.forEach(parentId->categoryIds.addAll(findSubCategoryIds(parentId, categories)));

        return categoryIds;
    }

    private void findSubCategory(List<CategoryDTO> parentCategoryDTOs, List<Category> categories) {
        for (CategoryDTO categoryDTO : parentCategoryDTOs) {
            final List<CategoryDTO> categoryDTOS = categories.stream()
                    .filter(category -> category.getParentId().equals(categoryDTO.getParentId()))
                    .map(this::category2CategoryDTO)
                    .sorted(Comparator.comparing(CategoryDTO::getSortOrder).reversed())
                    .collect(toList());

            if ((long) categoryDTOS.size() == 0) {
                findSubCategory(categoryDTOS, categories);
            }
            categoryDTO.setSubCategories(categoryDTOS);
        }
    }

    private CategoryDTO category2CategoryDTO(Category category) {
        final CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDTO);
        return categoryDTO;
    }
}
