package com.cartisan.mall.goods.category;

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

    private final CategoryConverter converter = CategoryConverter.CONVERTER;

    public CategoryAppService(CategoryRepository repository, CategoryMapper categoryMapper) {
        this.repository = repository;
        this.categoryMapper = categoryMapper;
    }

    public PageResult<CategoryDto> searchCategories(@NonNull CategoryQuery categoryQuery, @NonNull Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber() + 1, pageable.getPageSize());
        final List<CategoryDto> categories = categoryMapper.searchCategories(categoryQuery.getParentId());

        PageInfo<CategoryDto> pageCategories = new PageInfo<>(categories);

        return new PageResult<>(pageCategories.getTotal(), pageCategories.getPages(), pageCategories.getList());
    }

    public CategoryDto getCategory(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public CategoryDto addCategory(CategoryParam categoryParam) {
        final Category category = new Category(categoryParam.getParentId(),
                categoryParam.getTemplateId(),
                categoryParam.getName(),
                categoryParam.getIsShow(),
                categoryParam.getIsMenu(),
                categoryParam.getSequence());

        return converter.convert(repository.save(category));
    }

    @Transactional(rollbackOn = Exception.class)
    public CategoryDto editCategory(Long id, CategoryParam categoryParam) {
        final Category category = requirePresent(repository.findById(id));

        category.describe(categoryParam.getParentId(),
                categoryParam.getTemplateId(),
                categoryParam.getName(),
                categoryParam.getIsShow(),
                categoryParam.getIsMenu(),
                categoryParam.getSequence());

        return converter.convert(repository.save(category));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeCategory(long id) {
        repository.deleteById(id);
    }
}
