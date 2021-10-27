package com.cartisan.mall.goods.category;

import com.cartisan.dp.IdName;
import com.cartisan.dto.PageResult;
import com.cartisan.mall.goods.category.mapper.CategoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.util.AssertionUtil.requirePresent;

/**
 * @author colin
 */
@Service
public class CategoryAppService {
    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;


    public CategoryAppService(CategoryRepository repository, CategoryMapper categoryMapper) {
        this.repository = repository;
        this.categoryMapper = categoryMapper;
    }

    public PageResult<CategoryDto> searchCategories(@NonNull Long parentId, @NonNull Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        final List<CategoryDto> categories = categoryMapper.searchCategories(parentId);

        PageInfo<CategoryDto> pageCategories = new PageInfo<>(categories);

        return new PageResult<>(pageCategories.getTotal(), pageCategories.getPages(), pageCategories.getList());
    }

    public List<IdName<Long, String>> getCategories(@NonNull Long parentId) {
        return categoryMapper.getCategoryIdNames(parentId);
    }

    @Transactional(rollbackOn = Exception.class)
    public void addCategory(CategoryParam categoryParam) {
        final Category category = new Category(categoryParam.getParentId(),
                categoryParam.getTemplateId(),
                categoryParam.getName(),
                categoryParam.getIsShow(),
                categoryParam.getIsMenu(),
                categoryParam.getSequence());

        repository.save(category);
    }

    @Transactional(rollbackOn = Exception.class)
    public void editCategory(Long id, CategoryParam categoryParam) {
        final Category category = requirePresent(repository.findById(id));

        category.describe(categoryParam.getParentId(),
                categoryParam.getTemplateId(),
                categoryParam.getName(),
                categoryParam.getIsShow(),
                categoryParam.getIsMenu(),
                categoryParam.getSequence());

        repository.save(category);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeCategory(long id) {
        repository.deleteById(id);
    }
}
